package Views;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Views.FlashcardPanel;
import Models.Flashcard;

class FlashcardPanelTest {

    @Test
    void imageOver100kB() {

        byte[] imgData = new byte[102400]; // creating a 100KB file
        FlashcardPanel FCP = new FlashcardPanel(null, null, null, null);
        assertFalse(FCP.isImageFileSizeValid(imgData.length));
    }

    @Test
    void emptyImage() {
        byte[] imgData = new byte[0]; // creating an empty file
        FlashcardPanel FCP = new FlashcardPanel(null, null, null, null);
        assertFalse(FCP.isImageFileSizeValid(imgData.length));
    }

    @Test
    void validImage() {
        byte[] imgData = new byte[1024]; // creating a 1KB file
        FlashcardPanel FCP = new FlashcardPanel(null, null, null, null);
        assertTrue(FCP.isImageFileSizeValid(imgData.length));
    }

    @Test
    void invalidImageLength() {
        FlashcardPanel FCP = new FlashcardPanel(null, null, null, null);
        assertFalse(FCP.isImageFileSizeValid(-1));
    }
}
