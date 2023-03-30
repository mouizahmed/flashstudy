package Views;

import Models.EmailSender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExamReminderView extends JFrame {

    private final JTextField examField, dateField, timeField, emailField;

    public ExamReminderView() {
        setTitle("Exam Reminder");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel examLabel = new JLabel("Exam Name:");
        examField = new JTextField();
        JLabel dateLabel = new JLabel("Exam Date (dd/mm/yyyy):");
        dateField = new JTextField();
        JLabel timeLabel = new JLabel("Exam Time:");
        timeField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        panel.add(examLabel);
        panel.add(examField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(timeLabel);
        panel.add(timeField);
        panel.add(emailLabel);
        panel.add(emailField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String examName = examField.getText();
                String examDateString = dateField.getText();
                String examTime = timeField.getText();
                String email = emailField.getText();

                // Validate user inputs
                if (examName.isEmpty() || examDateString.isEmpty() || examTime.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill out all fields.");
                    return;
                }

                // Parse exam date
                LocalDate examDate;
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    examDate = LocalDate.parse(examDateString, formatter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please enter date in dd/mm/yyyy format.");
                    return;
                }

                // Send email reminder
                String subject = "Reminder: " + examName + " is coming up soon!";
                String body = "Hello,\n\n" +
                        "This is a friendly reminder that you have an exam coming up soon: " + examName + " on " +
                        examDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " at " + examTime + ".\n\n" +
                        "Don't forget to study! FlashStudy can help you prepare for your exam. Good luck!\n\n" +
                        "Best regards,\n" +
                        "Flash Study Team";
                EmailSender.sendEmail("flashstudyhelp@gmail.com", email, subject, body);

                // Show success message
                JOptionPane.showMessageDialog(null, "Reminder email sent successfully.");
            }
        });
        panel.add(submitButton);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose(); // Close only the ExamReminderView window
            }
        });

        add(panel);
        setVisible(true);
    }
}
