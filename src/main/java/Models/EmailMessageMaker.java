package Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailMessageMaker {

    private String subject;
    private String to;
    private final String from = "flashstudyhelp@gmail.com";
    private String body;

    //Call this function when New user is Created
    public void sendWelcomeEmail(String toEmail, String userName) {
        this.to = toEmail;
        this.subject = "Welcome to FlashStudy!";
        this.body = "Hello " + userName + ",\n\n" +
                "Welcome to FlashStudy, the best study partner you'll ever have! We're thrilled to have you on board and look forward to helping you achieve your academic goals.\n\n" +
                "With FlashStudy, you can create flashcards, take quizzes, collaborate with friends, and more! Our goal is to make studying more engaging and effective, and we're confident that our app will help you succeed.\n\n" +
                "Thank you for choosing FlashStudy. If you have any questions or feedback, please don't hesitate to reach out to us. We're always here to help.\n\n" +
                "Best regards,\n" +
                "Flash Study Team";
        EmailSender.sendEmail(from, to, subject, body);
        System.out.println("Welcome Email sent Successfully");
    }

    //This email will be sent to a user after some interval of time (spam)
    public void sendReminderEmail(String toEmail, String userName) {
        this.to = toEmail;
        this.subject = "How's your experience with FlashStudy?";
        String features = "- Flashcards\n- Quiz Mode\n- Colouring Coding\n- Drawing Flash Cards\n- Collaborating with Your Friends";                // replace with a list of features of your app
        this.body = "Hello " + userName + ",\n\n" +
                "It's been so long since you started using FlashStudy, and we hope you're enjoying our app so far. We wanted to check in with you and see how your experience has been.\n\n" +
                "If you have any feedback or suggestions on how we can improve the app, please let us know. We value your input and want to make sure we're meeting your needs.\n\n" +
                "Also, we wanted to remind you that FlashStudy offers a wide range of features to help you study more effectively, such as:\n" + features + "\n\n" +
                "We hope you've been able to take advantage of these features and that they're making your study sessions more productive.\n\n" +
                "Thank you for choosing FlashStudy as your study partner. We look forward to continuing to support your academic goals.\n\n" +
                "Best regards,\n" +
                "Flash Study Team";
        EmailSender.sendEmail(from, to, subject, body);
    }

    public void sendStudySessionReminder(String toEmail, String userName, LocalDateTime studySessionDateTime) {
        this.to = toEmail;
        this.subject = "Study Session Reminder: " + studySessionDateTime.format(DateTimeFormatter.ofPattern("MMM d, yyyy 'at' h:mm a"));
        this.body = "Hello " + userName + ",\n\n" +
                "Just a quick reminder that you have a study session scheduled on " + studySessionDateTime.format(DateTimeFormatter.ofPattern("MMM d, yyyy 'at' h:mm a")) + ". We hope you're looking forward to it!\n\n" +
                "Remember, FlashStudy is here to help you make the most of your study sessions. With features like flashcards, quizzes, and collaborative study tools, you can stay organized, stay focused, and achieve your academic goals.\n\n" +
                "If you need any assistance or have any questions, please don't hesitate to reach out to us. We're always here to help.\n\n" +
                "Good luck with your studies!\n\n" +
                "Best regards,\n" +
                "Flash Study Team";
        EmailSender.sendEmail(from, to, subject, body);
    }

    //Emails when New Deck is created
    public void sendNewDeckEmail(String toEmail, String userName, String deckName) {
        this.to = toEmail;
        this.subject = "New Flashcard Deck Created: " + deckName;
        this.body = "Hello " + userName + ",\n\n" +
                "We wanted to let you know that you have created a new flashcard deck in FlashStudy called \"" + deckName + "\". This is a great way to organize your study materials and keep your learning on track.\n\n" +
                "With FlashStudy, you can create flashcards, take quizzes, collaborate with friends, and more! Our goal is to make studying more engaging and effective, and we're confident that our app will help you succeed.\n\n" +
                "Thank you for choosing FlashStudy. If you have any questions or feedback, please don't hesitate to reach out to us. We're always here to help.\n\n" +
                "Best regards,\n" +
                "Flash Study Team";
        EmailSender.sendEmail(from, to, subject, body);
        System.out.println("New Deck created email sent");
    }
}