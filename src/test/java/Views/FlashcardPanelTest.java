package Views;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlashcardPanelTest {
	

	@Test
	void imageOver100kB() {
        FlashCard flashCard = new FlashCard();
        byte[] imgData = new byte[102400]; // creating a 100KB file
        flashCard.setFlashCardImgData(imgData);
        assertFalse(flashCard.isFlashCardImgValid());
    }

    @Test
	void emptyImage() {
        FlashCard flashCard = new FlashCard();
        byte[] imgData = new byte[0];
        flashCard.setFlashCardImgData(imgData);
        assertFalse(flashCard.isFlashCardImgValid());
	}

    @Test
	void validImage() {
        FlashCard flashCard = new FlashCard();
        byte[] imgData = new byte[1024]; // creating a 1KB file
        flashCard.setFlashCardImgData(imgData);
        assertTrue(flashCard.isFlashCardImgValid());
	}

}
