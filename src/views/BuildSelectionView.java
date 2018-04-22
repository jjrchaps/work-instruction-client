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

import connections.ClientServerConnection;
import listeners.BuildSelectionListener;
import listeners.RefreshListener;

/**
 * BuildSelectionView is the view displayed to the user to select which work instruction
 * will be built. A simple welcome is displayed as well as a combo box to select an assembly 
 * to build.
 * @author jameschapman
 */
public class BuildSelectionView {
	/**
	 * The frame that contains the welcome message and method to select POUI to load.
	 */
	private JFrame mainFrame;
	
	/**
	 * A local instance of the connection with the server, to be passed to the appropriate listeners.
	 */
	private ClientServerConnection connection;
	
	/**
	 * Constructor for StartupView, takes in a connection object and initializes all local variables.
	 * @param connection The connection with the server, used for loading images and list of available POUIs.
	 */
	public BuildSelectionView(ClientServerConnection connection) {
		this.connection = connection;
		
		mainFrame = new JFrame("Welcome");
		mainFrame.setUndecorated(true);
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

		JButton refreshButton = new JButton("Refresh List");
		refreshButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		RefreshListener refreshListener = new RefreshListener(connection, comboBox);
		refreshButton.addActionListener(refreshListener);
		mainPanel.add(refreshButton);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		BuildSelectionListener selectListener = new BuildSelectionListener(this, comboBox, connection);
		submitButton.addActionListener(selectListener);
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
