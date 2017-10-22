package views;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listeners.SubmitButtonListener;
import listeners.StartupTextListener;

public class StartupView {
	private JFrame mainFrame;
	
	public StartupView() {
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(this.createPanel());
		mainFrame.pack();
	}
	
	private JPanel createPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));


		JLabel welcomeLabel = new JLabel("Welcome!");
		welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 36));
		welcomeLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		mainPanel.add(welcomeLabel);

		JLabel explanationLabel = new JLabel("Please enter the SED product ID number to load POUI");
		explanationLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		explanationLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		mainPanel.add(explanationLabel);
		
		JTextField unitIDInput = new JTextField("Enter ID here");
		unitIDInput.setAlignmentX(JTextField.CENTER_ALIGNMENT);
		unitIDInput.addKeyListener(new StartupTextListener(unitIDInput));
		mainPanel.add(unitIDInput);

		JButton submitButton = new JButton("Submit");
		submitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		SubmitButtonListener listener = new SubmitButtonListener(unitIDInput);
		submitButton.addActionListener(listener);
		mainPanel.add(submitButton);
		
		return mainPanel;
	}
	
	public void makeVisible() {
		mainFrame.setVisible(true);
	}
	
	public void hideFrame() {
		mainFrame.setVisible(false);
	}
}
