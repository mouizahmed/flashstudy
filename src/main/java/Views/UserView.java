package Views;

import Models.User;

import javax.swing.*;
import java.awt.*;

public class UserView extends JPanel {
    private User user;

    public UserView(User user) {
        this.user = user;

        initialize();
    }

    private void initialize() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(400, 300));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        JLabel nameLabel = new JLabel(user.getUsername());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        GridBagConstraints gbc_nameLabel = new GridBagConstraints();
        gbc_nameLabel.insets = new Insets(10, 10, 10, 10);
        gbc_nameLabel.gridx = 0;
        gbc_nameLabel.gridy = 0;
        add(nameLabel, gbc_nameLabel);

        JLabel emailLabel = new JLabel(user.getEmail());
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        GridBagConstraints gbc_emailLabel = new GridBagConstraints();
        gbc_emailLabel.insets = new Insets(0, 10, 10, 10);
        gbc_emailLabel.gridx = 0;
        gbc_emailLabel.gridy = 1;
        add(emailLabel, gbc_emailLabel);

        JLabel decksLabel = new JLabel("Decks:");
        decksLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridBagConstraints gbc_decksLabel = new GridBagConstraints();
        gbc_decksLabel.insets = new Insets(10, 10, 10, 10);
        gbc_decksLabel.gridx = 0;
        gbc_decksLabel.gridy = 2;
        add(decksLabel, gbc_decksLabel);

        JPanel decksPanel = new JPanel();
        decksPanel.setBackground(Color.WHITE);
        decksPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc_decksPanel = new GridBagConstraints();
        gbc_decksPanel.insets = new Insets(0, 10, 10, 10);
        gbc_decksPanel.gridx = 0;
        gbc_decksPanel.gridy = 3;
        add(decksPanel, gbc_decksPanel);

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

    /*   Testing Userview

    public static void main(String[] args) {
        // create a sample user
        User user = new User("johndoe", "johndoe@example.com", "password", LocalDate.now());
        Deck deck1 = new Deck("Math", new ArrayList<Flashcard>(), user.getUsername(), true, "123");
        Deck deck2 = new Deck("Science", new ArrayList<Flashcard>(), user.getUsername(), true, "456");
        user.addDeck(deck1);
        user.addDeck(deck2);

        // create and display the user view
        UserView userView = new UserView(user);
        userView.setVisible(true);
    }

     */
}

