package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Controller.Controller;
import Models.Flashcard;

import javax.swing.JLabel;
import java.nio.file.Files;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class FlashcardPanel extends JPanel implements ActionListener {

	private JTextArea flashcardQuestion = new JTextArea();
	private JTextArea flashcardAnswer = new JTextArea();
	private JButton deleteFlashcard = new JButton("Delete");
	private JButton saveFlashcard = new JButton("Save");

	private JButton uploadImgBtn = new JButton("Upload Image");
	private JPanel currentPanel;
	JButton btnAutoComplete = new JButton("Auto Complete");
	private ArrayList<Flashcard> flashcards;
	private Controller controller;
	private Flashcard flashcard;
	private String deckID;
	private byte[] flashCardImgData;
	private JLabel uploadedImgPreview = new JLabel("");

	private String mode;

	/**
	 * Create the panel.
	 */
	
	
	
	// New Flashcard
	public FlashcardPanel(ArrayList<Flashcard> flashcards, Controller controller, String deckID, String mode) {
		this.flashcards = flashcards;
		this.controller = controller;
		this.deckID = deckID;
		this.currentPanel = this;
		this.mode = mode;
		initialize();
	}
	
	private void initialize() {
		this.checkQuestionEmpty();
		//System.out.println("FLASHCARD");
		this.setBackground(Color.WHITE);
		//this.setBounds(10, 197, 717, 113);
		setPreferredSize(new Dimension(718, 110));
		
		this.setLayout(null);
		flashcardQuestion.addKeyListener(new KeyAdapter() {
			int i = 0;
			
			@Override
			public void keyTyped(KeyEvent e) {
				checkQuestionEmpty();
			}
		});
		
	
		flashcardQuestion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		flashcardQuestion.setBackground(Color.WHITE);
		flashcardQuestion.setBounds(10, 11, 347, 54);
		this.add(flashcardQuestion);
		

		flashcardAnswer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		flashcardAnswer.setBackground(Color.WHITE);
		flashcardAnswer.setBounds(373, 11, 332, 54);
		flashcardAnswer.setLineWrap(true);
		flashcardAnswer.setWrapStyleWord(true);
		this.add(flashcardAnswer);
		deleteFlashcard.setForeground(new Color(255, 255, 255));
		deleteFlashcard.setBackground(new Color(0, 0, 0));
		
		
		deleteFlashcard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashcards.remove(flashcard);
				setBackground(Color.WHITE);
				setVisible(false);
			}
		});
		deleteFlashcard.setBounds(517, 78, 89, 23);
		this.add(deleteFlashcard);
		saveFlashcard.setForeground(new Color(255, 255, 255));
		saveFlashcard.setBackground(new Color(0, 0, 0));
		saveFlashcard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashcard = controller.createFlashcard(flashcardQuestion.getText(), flashcardAnswer.getText(), deckID, flashCardImgData);

				flashcards.add(flashcard);
				
				if (mode.equals("edit")) {
					controller.addFlashcard(flashcard, deckID);
				}
				
				setBackground(new Color(51, 204, 255));
				
			}
		});
		
		
		saveFlashcard.setBounds(616, 78, 89, 23);
		this.add(saveFlashcard);
		
		setSize(717, 113);
		

		uploadImgBtn.setForeground(new Color(255, 255, 255));
		uploadImgBtn.setBackground(new Color(0, 0, 0));
		uploadImgBtn.setBounds(10, 79, 173, 21);
		uploadImgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser browseImgFile = new JFileChooser();
				FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES","png","jpg","jpeg","gif");
				browseImgFile.addChoosableFileFilter(fnef);
				int showOpenDialogue = browseImgFile.showOpenDialog(null);
				if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
					File selectedImgFile = browseImgFile.getSelectedFile();
					
			        // Check if the selected file has a valid extension
			        String extension = getFileExtension(selectedImgFile.getName());
			        if (!isValidExtension(extension)) {
			            JOptionPane.showMessageDialog(null, "Invalid file type selected. Please select a file with a valid image extension (.png, .jpg, .jpeg, .gif).", "Invalid file uploaded", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
			        
					long fileSizeInKiloBytes = selectedImgFile.length() / 1024;
					if(fileSizeInKiloBytes > 0 && fileSizeInKiloBytes < 100) {
						String selectedImgPath = selectedImgFile.getAbsolutePath();
						try {
							flashCardImgData = Files.readAllBytes(selectedImgFile.toPath());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error reading file", "Invalid file uploaded", JOptionPane.ERROR_MESSAGE);
						}
						ImageIcon previewIcon = new ImageIcon(selectedImgPath);
						Image resizedImg = previewIcon.getImage().getScaledInstance(uploadedImgPreview.getWidth(), uploadedImgPreview.getHeight(), Image.SCALE_SMOOTH);
						uploadedImgPreview.setIcon(new ImageIcon(resizedImg));
					} else {
						JOptionPane.showMessageDialog(null, "The selected file must be less than 100kB and a valid image file.", "Invalid file uploaded", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});
		this.add(uploadImgBtn);
		
		uploadedImgPreview.setLabelFor(uploadImgBtn);
		uploadedImgPreview.setBounds(215, 75, 67, 35);
		add(uploadedImgPreview);

		
		btnAutoComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String autoCompletedAnswer = controller.autoComplete(flashcardQuestion.getText(), "");
				flashcardAnswer.setText(autoCompletedAnswer);
				currentPanel.revalidate();
				currentPanel.repaint();
			}
		});
		
		
		
		btnAutoComplete.setForeground(Color.WHITE);
		btnAutoComplete.setBackground(Color.BLACK);
		btnAutoComplete.setBounds(383, 78, 122, 23);
		add(btnAutoComplete);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	private String getFileExtension(String fileName) {
	    int dotIndex = fileName.lastIndexOf(".");
	    if (dotIndex > 0) {
	        return fileName.substring(dotIndex + 1).toLowerCase();
	    }
	    return "";
	}

	// Returns true if the given extension is valid, false otherwise
	private boolean isValidExtension(String extension) {
	    String[] validExtensions = {"png", "jpg", "jpeg", "gif"};
	    for (String validExtension : validExtensions) {
	        if (validExtension.equals(extension)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public void checkQuestionEmpty() {
		if (flashcardQuestion.getText().trim().isEmpty()) {
			btnAutoComplete.setEnabled(false);
		} else {
			btnAutoComplete.setEnabled(true);
		}

	}
}
	
	
