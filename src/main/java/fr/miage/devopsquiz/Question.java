package fr.miage.devopsquiz;

public record Question(String category, String title, String[] choices, char answer) {
    public Question {
        if (choices == null || choices.length != 4) throw new IllegalArgumentException("4 choix requis");
        if (answer < 'A' || answer > 'D') throw new IllegalArgumentException("Réponse A-D requise");
    }
}
