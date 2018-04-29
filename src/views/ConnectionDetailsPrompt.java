package views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listeners.ConnectionDetailSubmitListener;

/**
 * ConnectionDetailsPrompt is the first view that is displayed when the user starts the
 * client program. It is how the client program knows what ip address and port to connect
 * to for work instructions. 
 * @author jameschapman
 */
public class ConnectionDetailsPrompt {
	/**
	 * The frame that will contain the pane to input identifying code
	 */
	private JFrame mainFrame;

	/**
	 * Creates a new instance of ConnectionDetailsPrompt and centers on display.
	 */
	public ConnectionDetailsPrompt() {
		mainFrame = new JFrame("Server Information");
		mainFrame.add(createPanel());
		mainFrame.pack();
		mainFrame.setAlwaysOnTop(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);
	}

	/**
	 * Creates the panel that will contain the text field and submit button for entering
	 * identifying number.
	 * @return The populated panel to be added to Frame and displayed.
	 */
	private JPanel createPanel() {
		JPanel mainPanel = new JPanel();

		JTextField ipAddressField = new JTextField("  IP ADDRESS  ");
		mainPanel.add(ipAddressField);
		
		JTextField portField = new JTextField("  PORT  ");
		mainPanel.add(portField);
		
		JButton submitButton = new JButton("Submit");
		mainPanel.add(submitButton);
		
		// Create communal listener and add to relevant fields -- ipAddress field doesn't get one since you will always
		// have to have entered the port number as well.
		ConnectionDetailSubmitListener listener = new ConnectionDetailSubmitListener(this, ipAddressField, portField);
		
		portField.addActionListener(listener);
		submitButton.addActionListener(listener);
		
		return mainPanel;
	}

	public void setVisible() {
		mainFrame.setVisible(true);
	}

	public void hideFrame() {
		mainFrame.setVisible(false);
	}
	
	/**
	 * Used for testing
	 * @param args Standard PSVM variable declaration, not used in this instance.
	 */
	public static void main(String[] args) {
		ConnectionDetailsPrompt test = new ConnectionDetailsPrompt();
		test.setVisible();
	}
}
