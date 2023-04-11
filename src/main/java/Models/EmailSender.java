package Models;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailSender {

    private static final String SMTP_PORT = "587";

    public static boolean sendEmail(String from, String to, String subject, String body) {

        boolean emailSent = false;
        //SMTP properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.starttls.enable",true);
        properties.put("mail.smtp.port",SMTP_PORT);
        properties.put("mail.smtp.host","smtp.gmail.com");

        String username = "flashstudyteam";                     //email id username before the @ part
        String password = "pewqhydyypgsktaq";                   // App password to access the email


        //Session

        jakarta.mail.Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });

        try{

            jakarta.mail.Message message = new MimeMessage(session);

            message.setRecipient(Message.RecipientType.TO, new jakarta.mail.internet.InternetAddress(to));        //Set to CC/BCC/From
            message.setFrom(new InternetAddress(from));                                                           //Set sender
            message.setSubject(subject);                                                                          //Subject
            message.setText(body);                                                                                //Set Text of email

            Transport.send(message);                                                                                //Send Message

            emailSent = true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return emailSent;
    }
}
