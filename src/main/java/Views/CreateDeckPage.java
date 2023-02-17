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

import Controller.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Component;

public class CreateDeckPage extends JPanel {
	private JTextField txtTitle;
	private JTextField txtSchool;
	private JTextField txtFaculty;
	private JTextField txtCourse;
	private Controller controller;

	/**
	 * Create the panel.
	 */
	public CreateDeckPage(Controller controller) {
		setMinimumSize(new Dimension(750, 500));
		setSize(new Dimension(750, 500));
		setPreferredSize(new Dimension(750, 500));
		this.controller = controller;
		initialize();

	}
	
	private void initialize() {
		setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 738, 47);
		add(panel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.landingPage();
			}
		});
		btnNewButton.setBounds(10, 11, 63, 26);
		panel.add(btnNewButton);
		
		JButton profileButton = new JButton("Profile");
		profileButton.setBounds(655, 11, 71, 26);
		panel.add(profileButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 47, 738, 139);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel title = new JLabel("Create a new study set");
		title.setBounds(10, 11, 130, 16);
		panel_1.add(title);
		
		txtTitle = new JTextField();
		txtTitle.setText("Title*");
		txtTitle.setBounds(10, 36, 363, 20);
		panel_1.add(txtTitle);
		txtTitle.setColumns(10);
		
		JTextArea txtrDescription = new JTextArea();
		txtrDescription.setText("Description");
		txtrDescription.setBounds(10, 67, 363, 53);
		panel_1.add(txtrDescription);
		
		txtSchool = new JTextField();
		txtSchool.setText("School");
		txtSchool.setColumns(10);
		txtSchool.setBounds(383, 36, 343, 20);
		panel_1.add(txtSchool);
		
		txtFaculty = new JTextField();
		txtFaculty.setText("Faculty");
		txtFaculty.setColumns(10);
		txtFaculty.setBounds(383, 69, 343, 20);
		panel_1.add(txtFaculty);
		
		txtCourse = new JTextField();
		txtCourse.setText("Course");
		txtCourse.setColumns(10);
		txtCourse.setBounds(383, 100, 343, 20);
		panel_1.add(txtCourse);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Public");
		rdbtnNewRadioButton.setBounds(383, 11, 60, 24);
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnPrivate = new JRadioButton("Private");
		rdbtnPrivate.setBounds(447, 11, 65, 24);
		panel_1.add(rdbtnPrivate);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 197, 717, 113);
		add(panel_2);
		panel_2.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(10, 11, 347, 54);
		panel_2.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea_1.setBackground(Color.WHITE);
		textArea_1.setBounds(373, 11, 332, 54);
		panel_2.add(textArea_1);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(517, 78, 89, 23);
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_1_2 = new JButton("Save");
		btnNewButton_1_2.setBounds(616, 78, 89, 23);
		panel_2.add(btnNewButton_1_2);
		
		JButton btnCreateDeck = new JButton("Create Deck");
		btnCreateDeck.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCreateDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateDeck.setBounds(324, 409, 104, 26);
		add(btnCreateDeck);
		
		JButton btnNewButton_1_1 = new JButton("Add Card");
		btnNewButton_1_1.setBounds(20, 320, 695, 77);
		add(btnNewButton_1_1);
	}
}
