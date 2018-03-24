package views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connections.ClientServerConnection;
import listeners.InspectSubmitListener;
import listeners.NextButtonListener;

/**
 * InspectionAuthenticationView will be displayed whenever the user tries to continue on to
 * the next step of a POUI and the current step requires a level three inspection to be 
 * completed. Once inspection is completed the user will have to enter the identifying 
 * number assigned to them, which will be logged to the server and stored.
 * @author jameschapman
 */
public class InspectionAuthenticationView {
	/**
	 * The frame that will contain the pane to input identifying code
	 */
	private JFrame mainFrame;
	
	/**
	 * Next button so that this view can initiate the changing of images once the user has entered
	 * their stamp ID
	 */
	private NextButtonListener nextListener;
	
	/**
	 * A connection with the server for authenticate a user is allowed to perform a level 3 inspection.
	 */
	private ClientServerConnection connection;
	
	/**
	 * The current step number that is being checked
	 */
	private int currentStep;
	
	/**
	 * The product ID that the inspect is being authenticated for
	 */
	private String productID;
	
	/**
	 * Creates new instance of the inspection view, and displays in in the middle of the display.
	 */
	public InspectionAuthenticationView(NextButtonListener nextListener, ClientServerConnection connection, int currentStep, String productID) {
		this.nextListener = nextListener;
		this.connection = connection;
		this.currentStep = currentStep;
		this.productID = productID;
		mainFrame = new JFrame("Inspection");
		mainFrame.add(createPanel());
		mainFrame.pack();
		mainFrame.setAlwaysOnTop(true);
		mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
		
		JTextField stampField = new JTextField("Stamp ID");
		stampField.addActionListener(new InspectSubmitListener(this, stampField, connection, currentStep, productID));
		mainPanel.add(stampField);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new InspectSubmitListener(this, stampField, connection, currentStep, productID));
		mainPanel.add(submitButton);
		
		return mainPanel;
	}
	
	/**
	 * Displays the next image for the current POUI, if there is one.
	 */
	public void displayNext() {
		this.nextListener.displayNext();
	}
	
	/**
	 * Displays the next image after the server inspection authentication
	 */
	public void displayNextAfterAuthentication() {
		this.nextListener.displayNextAfterAuthentication();
	}
	
	public void setVisible() {
		mainFrame.setVisible(true);
	}
	
	public void hideFrame() {
		mainFrame.setVisible(false);
	}
}
