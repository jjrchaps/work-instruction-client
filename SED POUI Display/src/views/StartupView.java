package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

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
	/**
	 * The frame that contains the welcome message and method to select POUI to load.
	 */
	private JFrame mainFrame;
	
	/**
	 * A local instance of the connection with the server, to be passed to the appropriate listeners.
	 */
	private ServerConnection connection;
	
	/**
	 * Constructor for StartupView, takes in a connection object and initializes all local variables.
	 * @param connection The connection with the server, used for loading images and list of available POUIs.
	 */
	public StartupView(ServerConnection connection) {
		this.connection = connection;
		mainFrame = new JFrame("Welcome");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(this.createPanel());
		mainFrame.pack();
		// sets the startup window to be displayed in the center of the display.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);
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

		JLabel explanationLabel = new JLabel("Please select an assembly and click submit to load POUI");
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
