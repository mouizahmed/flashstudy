package Views;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.Scrollbar;

public class HomePage extends JPanel {
	private JTextField txtSearchDecks;

	/**
	 * Create the panel.
	 */
	public HomePage() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 47);
		add(panel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(10, 11, 55, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.setLayout(null);
		panel.add(btnNewButton);
		
		JButton profileButton = new JButton("Profile");
		profileButton.setBounds(377, 11, 63, 23);
		panel.add(profileButton);
		
		txtSearchDecks = new JTextField();
		txtSearchDecks.setToolTipText("");
		txtSearchDecks.setBounds(138, 80, 173, 20);
		add(txtSearchDecks);
		txtSearchDecks.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 111, 450, 189);
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 11, 137, 42);
		panel_1.add(panel_2);
		
	
		
		JLabel lblNewLabel = new JLabel("Search All Decks");
		lblNewLabel.setBounds(186, 55, 78, 14);
		add(lblNewLabel);

	}
}
