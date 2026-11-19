package fr.miage.devopsquiz;

import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public final class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    private static final ScoreStore STORE = new ScoreStore(Path.of(System.getenv().getOrDefault("QUIZ_DATA", "data") , "scores.csv"));
    public static void main(String[] args) {
        if (args.length > 0 && "--healthcheck".equals(args[0])) {
            boolean ok = QuestionBank.all().size() >= 20 && QuizEngine.CATEGORIES.stream()
                .allMatch(c -> QuestionBank.all().stream().filter(q -> q.category().equals(c)).count() >= 10);
            System.out.println(ok ? "HEALTHCHECK_OK" : "HEALTHCHECK_FAIL");
            if (!ok) System.exit(1);
            return;
        }
        LOG.info("Démarrage DevOpsQuiz");
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nDEVOPS QUIZ\n1. Commencer un quiz\n2. Consulter les règles\n3. Afficher les meilleurs scores\n4. Quitter\nVotre choix :");
                if (!in.hasNextLine()) return;
                switch (in.nextLine().trim()) {
                    case "1" -> play(in);
                    case "2" -> System.out.println("10 questions, 4 réponses (A-D), 1 point par bonne réponse.");
                    case "3" -> { try { STORE.bestScores().forEach(System.out::println); }
                                   catch (IOException e) { LOG.log(Level.WARNING, "Lecture des scores impossible", e); } }
                    case "4" -> { LOG.info("Arrêt DevOpsQuiz"); return; }
                    default -> System.out.println("Choix invalide: tapez 1, 2, 3 ou 4.");
                }
            }
        }
    }
    private static void play(Scanner in) {
        System.out.println("Pseudo (lettres/chiffres/_/-) :");
        if (!in.hasNextLine()) return;
        String pseudo = in.nextLine().trim();
        if (!pseudo.matches("[A-Za-z0-9_-]{1,24}")) { System.out.println("Pseudo invalide."); return; }
        System.out.println("Catégorie : 1 Linux, 2 Git, 3 Jenkins, 4 Docker, 5 DevOps, 6 Général");
        if (!in.hasNextLine()) return;
        String choice = in.nextLine().trim();
        String category = switch (choice) {
            case "1" -> "Linux"; case "2" -> "Git"; case "3" -> "Jenkins";
            case "4" -> "Docker"; case "5" -> "DevOps"; case "6" -> "General";
            default -> "INVALID";
        };
        if (!QuizEngine.validCategory(category)) { System.out.println("Catégorie invalide."); return; }
        List<Question> questions = QuizEngine.selectQuestions(QuestionBank.all(), category, System.nanoTime());
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Question " + (i+1) + "/10 : " + q.title());
            for (int j=0; j<4; j++) System.out.println((char)('A'+j) + ". " + q.choices()[j]);
            char answer;
            while (true) {
                System.out.println("Réponse (A-D) :");
                if (!in.hasNextLine()) return;
                try { answer = QuizEngine.parseChoice(in.nextLine()); break; }
                catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
            }
            if (QuizEngine.correct(q, answer)) { score++; System.out.println("Correct !"); }
            else System.out.println("Incorrect. Bonne réponse : " + q.answer());
        }
        System.out.println("Note: " + score + "/10 - " + (score*10) + "% - " + (score >= 7 ? "Bravo !" : "Continuez !"));
        try { STORE.save(pseudo, score); LOG.info("Fin de partie : pseudo=" + pseudo + " score=" + score); }
        catch (IOException | IllegalArgumentException e) { LOG.log(Level.WARNING,"Sauvegarde impossible",e); }
    }
}
