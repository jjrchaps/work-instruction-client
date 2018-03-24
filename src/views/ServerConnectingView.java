package views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerConnectingView {
	/**
	 * The frame that will contain the pane to input identifying code
	 */
	private JFrame mainFrame;
	
	/**
	 * Creates new instance of the inspection view, and displays in in the middle of the display.
	 */
	public ServerConnectingView() {
		mainFrame = new JFrame("Server Connection");
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
		
		JLabel message = new JLabel("Connecting to server...");
		message.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		mainPanel.add(message);
		
		return mainPanel;
	}
	
	public void setVisible() {
		mainFrame.setVisible(true);
	}
	
	public void hideFrame() {
		mainFrame.setVisible(false);
	}
}
