package Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailSenderTest {

    @Test
    //Also test by replacing to and from email and if it is received
    void sendEmailTest() {
        String from = "test.sender@gmail.com";
        String to = "test.receiver@gmail.com";
        String subject = "Test Subject";
        String body = "Test Body";

        boolean result = EmailSender.sendEmail(from, to, subject, body);

        assertTrue(result, "Email not sent successfully.");
    }
}

