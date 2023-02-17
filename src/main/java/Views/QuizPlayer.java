package Views;

import javax.swing.*;
import Models.Flashcard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;


public class QuizPlayer {
    private JTextArea display;

    private JTextArea answer;

    private ArrayList<Flashcard> cardList;

    private JFrame frame;

    private boolean isShowAnswer;

    private JButton showAnswer;

    public QuizPlayer(){

        // Build the UI For the Quiz
        frame = new JFrame("Flash Card Player");

        //J panel
        JPanel mainPanel = new JPanel();

        //Font
        Font flashFont = new Font(Font.MONOSPACED,Font.BOLD,21);


        //Jtext display area
        display = new JTextArea(12,21);
        display.setLineWrap(true);                                 //Wraps the text in textbox
        display.setWrapStyleWord(true);
        display.setFont(flashFont);

        //button
        JButton showAnswer = new JButton("Show Answer");

        //JLabels


        //main Panel
        mainPanel.add(showAnswer);

        //Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenuItem loadMenuItem = new JMenuItem("Load a new Card Set");
        JMenu flashcardMenu = new JMenu("Flashcard");
        JMenuItem newFlashCardDeck = new JMenuItem("New Deck");
        JMenuItem SaveDeck = new JMenuItem("Save Deck");

        flashcardMenu.add(newFlashCardDeck);
        flashcardMenu.add(loadMenuItem);
        menuBar.add(flashcardMenu);

        //event listeners
        showAnswer.addActionListener(new NextCardListener());
        loadMenuItem.addActionListener(new OpenMenuListner());

        //add menu to Jframebar

        frame.setJMenuBar(menuBar);



        //Frame
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(350,500);
        frame.setVisible(true);










    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizPlayer();
            }
        });
    }


    class NextCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            if(isShowAnswer){
                System.out.println("Show answer is true");
                showAnswer.setText("Next Card");
                isShowAnswer= false;
            }
            else{
                showNextCard();
            } // TO BE FIXED: To show the next card and stop when the card is last.

        }

    }

    class OpenMenuListner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("New deck button working");
        }


    }

    private void makeCard( ) {

    }//To be implemented
    private void showNextCard(){

    }// To be implemented






}
