package fr.miage.devopsquiz;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuizEngineTest {
    private final Question q = new Question("Linux", "Test", new String[]{"un", "deux", "trois", "quatre"}, 'A');
    @Test void bonneReponse() { assertTrue(QuizEngine.correct(q, 'A')); }
    @Test void mauvaiseReponse() { assertFalse(QuizEngine.correct(q, 'B')); }
    @Test void calculScore() { assertEquals(1, QuizEngine.score(List.of(q,q), List.of('A','B'))); }
    @Test void categorieValide() { assertTrue(QuizEngine.validCategory("Linux")); }
    @Test void saisieInvalide() { assertThrows(IllegalArgumentException.class, () -> QuizEngine.parseChoice("Z")); }
    @Test void dixQuestionsParCategorie() { assertEquals(10, QuizEngine.selectQuestions(QuestionBank.all(), "Git", 42).size()); }
    @Test void healthcheckData() { assertTrue(QuestionBank.all().size() >= 20); }

    @Test void messageParfait() { assertEquals("Parfait !", QuizEngine.message(10)); }
    @Test void messageBravo() {
        assertEquals("Bravo !", QuizEngine.message(7));
        assertEquals("Bravo !", QuizEngine.message(9));
    }
    @Test void messageMoyen() {
        assertEquals("Pas mal, continuez !", QuizEngine.message(4));
        assertEquals("Pas mal, continuez !", QuizEngine.message(6));
    }
    @Test void messageFaible() {
        assertEquals("Courage, révisez et réessayez !", QuizEngine.message(0));
        assertEquals("Courage, révisez et réessayez !", QuizEngine.message(3));
    }
    @Test void messageScoreInvalide() {
        assertThrows(IllegalArgumentException.class, () -> QuizEngine.message(-1));
        assertThrows(IllegalArgumentException.class, () -> QuizEngine.message(11));
    }

}
