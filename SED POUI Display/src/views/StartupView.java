package views;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import connections.ServerConnection;
import listeners.StartupListener;

/**
 * StartUp view is what the user will first see when the program is launched. It has a quick explanation and welcome
 * message, and then a box to enter the SED product ID of the unit they are about to build.
 * @author jameschapman
 */
public class StartupView {
	private JFrame mainFrame;
	private ServerConnection connection;
	
	public StartupView(ServerConnection connection) {
		this.connection = connection;
		mainFrame = new JFrame("Welcome");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(this.createPanel());
		mainFrame.pack();
	}
	
	/**
	 * Creates the panel with the proper formats, as well as creates all components
	 * and adds the relevant listeners to them
	 * @return A completed JPanel ready to be added to a frame and displayed
	 */
	private JPanel createPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

		JLabel explanationLabel = new JLabel("Please enter the SED product ID number to load POUI");
		explanationLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		explanationLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		mainPanel.add(explanationLabel);
		
		String[] options = connection.getProductIDs();
		JComboBox<String> comboBox = new JComboBox<String>(options);
		comboBox.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
		mainPanel.add(comboBox);

		JButton submitButton = new JButton("Submit");
		submitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		StartupListener listener = new StartupListener(comboBox, connection);
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
