package Views;

import Models.User;

import javax.swing.*;

import Controller.Controller;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserView extends JPanel {
    private User user;
    private Controller controller;
    
    public UserView(User user, Controller controller) {
        this.user = user;
        this.controller = controller;

        initialize();
    }

    private void initialize() {
        setPreferredSize(new Dimension(750, 500));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        setLayout(null);

        JLabel nameLabel = new JLabel(user.getUsername());
        nameLabel.setBounds(151, 101, 245, 22);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(nameLabel);

        JLabel emailLabel = new JLabel(user.getEmail());
        emailLabel.setBounds(165, 133, 166, 17);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(emailLabel);

        JLabel decksLabel = new JLabel("Decks:");
        decksLabel.setBounds(172, 170, 199, 19);
        decksLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(decksLabel);

        JPanel decksPanel = new JPanel();
        decksPanel.setBounds(0, 0, 0, 0);
        decksPanel.setBackground(Color.WHITE);
        decksPanel.setLayout(new GridBagLayout());
        add(decksPanel);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 738, 47);
        add(panel);
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.previous();
        		
        	}
        });
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setBounds(10, 11, 63, 26);
        panel.add(btnNewButton);

        int row = 0;
        for (int i = 0; i < user.userDeckList().size(); i++) {
            JLabel deckNameLabel = new JLabel(user.userDeckList().get(i).getDeckTitle());
            deckNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            GridBagConstraints gbc_deckNameLabel = new GridBagConstraints();
            gbc_deckNameLabel.insets = new Insets(0, 0, 10, 10);
            gbc_deckNameLabel.gridx = 0;
            gbc_deckNameLabel.gridy = row;
            decksPanel.add(deckNameLabel, gbc_deckNameLabel);

            JLabel deckSizeLabel = new JLabel("(" + user.userDeckList().get(i).getAllFlashcards().size() + " cards)");
            deckSizeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            GridBagConstraints gbc_deckSizeLabel = new GridBagConstraints();
            gbc_deckSizeLabel.insets = new Insets(0, 0, 10, 0);
            gbc_deckSizeLabel.gridx = 1;
            gbc_deckSizeLabel.gridy = row;
            decksPanel.add(deckSizeLabel, gbc_deckSizeLabel);

            row++;
        }
    }
}

