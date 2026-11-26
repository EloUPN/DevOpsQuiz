package fr.miage.devopsquiz;

import java.util.*;

public final class QuizEngine {
    private QuizEngine() {}
    public static final List<String> CATEGORIES = List.of("Linux", "Git", "Jenkins", "Docker", "DevOps");
    public static boolean validCategory(String category) {
        return "General".equals(category) || CATEGORIES.contains(category);
    }
    public static char parseChoice(String input) {
        if (input == null || !input.trim().toUpperCase(Locale.ROOT).matches("[ABCD]"))
            throw new IllegalArgumentException("Choisissez A, B, C ou D");
        return input.trim().toUpperCase(Locale.ROOT).charAt(0);
    }
    public static boolean correct(Question q, char answer) { return q.answer() == answer; }

    public static String message(int score) {
        if (score < 0 || score > 10) throw new IllegalArgumentException("Score entre 0 et 10");
        if (score == 10) return "Parfait !";
        if (score >= 7) return "Bravo !";
        if (score >= 4) return "Pas mal, continuez !";
        return "Courage, révisez et réessayez !";
    }

    public static int score(List<Question> qs, List<Character> answers) {
        if (qs.size() != answers.size()) throw new IllegalArgumentException("Tailles différentes");
        int points = 0;
        for (int i = 0; i < qs.size(); i++) if (correct(qs.get(i), answers.get(i))) points++;
        return points;
    }
    public static List<Question> selectQuestions(List<Question> bank, String category, long seed) {
        if (!validCategory(category)) throw new IllegalArgumentException("Catégorie inconnue");
        List<Question> filtered = new ArrayList<>();
        for (Question q : bank) if ("General".equals(category) || q.category().equals(category)) filtered.add(q);
        if (filtered.size() < 10) throw new IllegalArgumentException("Il faut 10 questions dans la catégorie");
        Collections.shuffle(filtered, new Random(seed));
        return List.copyOf(filtered.subList(0, 10));
    }
}
