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
}
