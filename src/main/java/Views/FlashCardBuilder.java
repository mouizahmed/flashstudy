package Views;
import javax.swing.*;

import Models.Flashcard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


        //Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu flashcardMenu = new JMenu("Flashcard");
        JMenuItem newFlashCardDeck = new JMenuItem("New Deck");
        JMenuItem SaveDeck = new JMenuItem("Save Deck");

        flashcardMenu.add(newFlashCardDeck);
        flashcardMenu.add(SaveDeck);


        menuBar.add(flashcardMenu);


        //event listeners
        newFlashCardDeck.addActionListener(new newFlashCardDeckListener());
        SaveDeck.addActionListener(new SaveDeckListener());

        //add menu to Jframebar

        frame.setJMenuBar(menuBar);

        // Initiating Card
        cardList = new ArrayList<Flashcard>();


        System.out.println("Number of flashcard is : " + cardList.size());


        //mainPanel

        mainPanel.add(quesLabel);
        mainPanel.add(question);
        mainPanel.add(ansLabel);
        mainPanel.add(answer);
        mainPanel.add(next);
        next.addActionListener(new nextFlashcardListener());


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


    class nextFlashcardListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Next Button Working");
            //creating a new flashcard
            Flashcard card = new Flashcard(question.getText(),answer.getText());
            cardList.add(card);
            System.out.println("Number of flashcard is : " + cardList.size());
            clearCard();            //Clears the card after clicking next



        }
    }

    class newFlashCardDeckListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("New deck button working");
        }
    }

    class SaveDeckListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Save deck button working");
        }
    }

    private void clearCard(){
        //clears Jtext in question and answer

        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

}
