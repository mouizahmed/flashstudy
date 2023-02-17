package Views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class CreateDeckPage extends JPanel {
	private JTextField txtTitle;
	private JTextField txtSchool;
	private JTextField txtFaculty;
	private JTextField txtCourse;

	/**
	 * Create the panel.
	 */
	public CreateDeckPage() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 450, 47);
		add(panel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(10, 11, 55, 23);
		panel.add(btnNewButton);
		
		JButton profileButton = new JButton("Profile");
		profileButton.setBounds(377, 11, 63, 23);
		panel.add(profileButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 47, 450, 139);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel title = new JLabel("Create a new study set");
		title.setBounds(10, 11, 113, 14);
		panel_1.add(title);
		
		txtTitle = new JTextField();
		txtTitle.setText("Title*");
		txtTitle.setBounds(10, 36, 250, 20);
		panel_1.add(txtTitle);
		txtTitle.setColumns(10);
		
		JTextArea txtrDescription = new JTextArea();
		txtrDescription.setText("Description");
		txtrDescription.setBounds(10, 67, 250, 53);
		panel_1.add(txtrDescription);
		
		txtSchool = new JTextField();
		txtSchool.setText("School");
		txtSchool.setColumns(10);
		txtSchool.setBounds(270, 36, 170, 20);
		panel_1.add(txtSchool);
		
		txtFaculty = new JTextField();
		txtFaculty.setText("Faculty");
		txtFaculty.setColumns(10);
		txtFaculty.setBounds(270, 69, 170, 20);
		panel_1.add(txtFaculty);
		
		txtCourse = new JTextField();
		txtCourse.setText("Course");
		txtCourse.setColumns(10);
		txtCourse.setBounds(270, 100, 170, 20);
		panel_1.add(txtCourse);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Public");
		rdbtnNewRadioButton.setBounds(326, 7, 53, 23);
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnPrivate = new JRadioButton("Private");
		rdbtnPrivate.setBounds(381, 7, 59, 23);
		panel_1.add(rdbtnPrivate);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 197, 430, 113);
		add(panel_2);
		panel_2.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(10, 11, 200, 54);
		panel_2.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea_1.setBackground(Color.WHITE);
		textArea_1.setBounds(220, 11, 200, 54);
		panel_2.add(textArea_1);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(232, 76, 89, 23);
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_1_2 = new JButton("Save");
		btnNewButton_1_2.setBounds(331, 76, 89, 23);
		panel_2.add(btnNewButton_1_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(10, 316, 430, 77);
		add(panel_2_1);
		panel_2_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton_1_1 = new JButton("Add Card");
		panel_2_1.add(btnNewButton_1_1);
		
		JButton btnCreateDeck = new JButton("Create Deck");
		btnCreateDeck.setBounds(349, 415, 91, 23);
		add(btnCreateDeck);

	}
}
