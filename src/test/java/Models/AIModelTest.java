package Models;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AIModelTest {

	private AIModel aiModel;

    @BeforeEach
    void setUp() {
        aiModel = new AIModel();
    }

    @Test
    void testGenerateMC() throws IOException, InterruptedException {
        String term = "Java";
        String definition = "A high-level programming language developed by Sun Microsystems";
        MultipleChoiceQuestion mcQ = aiModel.generateMC(term, definition);
        assertNotNull(mcQ);
        assertNotNull(mcQ.getQuestion());
        assertTrue(mcQ.checkAnswer("A high-level programming language developed by Sun Microsystems"));
        assertNotNull(mcQ.getAllOptions());
        assertEquals(mcQ.getAllOptions().length, 4);
    }

    @Test
    void testVerifyFlashcard() throws IOException, InterruptedException {
        String term = "Java";
        String definition = "A high-level programming language developed by Sun Microsystems";
        String wrongDefinition = "Java is a type of coffee that programmers drink to stay awake while coding.";
        Verdict verdict = aiModel.verifyFlashcard(term, definition);
        Verdict verdict2 = aiModel.verifyFlashcard(term, wrongDefinition);
        assertNotNull(verdict);
        assertNotNull(verdict2);
        assertTrue(verdict.getVerdict());
        assertFalse(verdict2.getVerdict());
        //assertNotNull(verdict.isCorrect());
        if (verdict.getVerdict()) {
            assertNull(verdict.getCorrection());
        } else {
            assertNotNull(verdict.getCorrection());
            assertNotEquals(verdict.getCorrection(), "");
        }
        
        if (verdict2.getVerdict()) {
            assertNull(verdict2.getCorrection());
        } else {
            assertNotNull(verdict2.getCorrection());
            assertNotEquals(verdict2.getCorrection(), "");
        }
    }

    @Test
    void testAutoComplete() throws IOException, InterruptedException {
        String term = "Java";
        String context = "Introduction to Object Oriented Programming";
        String suggestion = aiModel.autoComplete(term, context);
        assertNotNull(suggestion);
        assertNotEquals(suggestion, "");
    }

}
