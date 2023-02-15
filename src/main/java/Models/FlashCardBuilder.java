package Models;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FlashCardBuilder {
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<Flashcard> cardList;
    private JFrame frame;


    public FlashCardBuilder(){
        //UI
        frame = new JFrame("Flash Card");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Jpanel
        JPanel mainPanel = new JPanel();

        //Font

        Font flashFont = new Font(Font.MONOSPACED,Font.BOLD,20);

        //Jtext Question area
        question = new JTextArea(6,21);
        question.setLineWrap(true);                                 //Wraps the text in textbox
        question.setWrapStyleWord(true);
        question.setFont(flashFont);

        //Jtext Answer area
        answer = new JTextArea(6,21);
        answer.setLineWrap(true);                                 //Wraps the text in textbox
        answer.setWrapStyleWord(true);
        answer.setFont(flashFont);


        //button

        JButton next = new JButton("next");

        //JLabels

        JLabel quesLabel = new JLabel("Question");
        JLabel ansLabel = new JLabel("Answer");


        //mainPanel

        mainPanel.add(quesLabel);
        mainPanel.add(question);
        mainPanel.add(ansLabel);
        mainPanel.add(answer);
        mainPanel.add(next);


        //Frame

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(350,500);
        frame.setVisible(true);




    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlashCardBuilder();
            }
        });
    }

}
