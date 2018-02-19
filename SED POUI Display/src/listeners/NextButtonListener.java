package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import auxiliary.ClientPOUI;
import connections.ClientServerConnection;
import views.InspectionAuthenticationView;

/**
 * Listener for the next button in the pouiview class. If there is another image, it will display it.
 * @author jameschapman
 */
public class NextButtonListener implements ActionListener, KeyListener {
	/** 
	 * save an instance of the POUI we're working on to pull up future steps
	 */
	private ClientPOUI poui;

	/**
	 * instance of the imagePanel that is displayed in the POUIView
	 */
	private JLabel imageLabel;

	/**
	 * complete button instance so that it's visibility can be dynamically changed
	 */
	private JButton completeButton;

	/**
	 * A connection to the server, to be passed along to the inspection view when necessary
	 */
	private ClientServerConnection connection;

	/**
	 * Boolean to track whether or not the inspection view is already being shown to the user.
	 */
	private boolean inspectionCurrentlyDisplayed;

	/**
	 * Prepares local instance variables for all parameters so the button is able to swap images when desired.
	 * @param poui The poui that is currently being displayed
	 * @param imageLabel The JLabel that is displaying the ImageIcon
	 * @param completeButton A reference to the "Build Complete" button so we can dynamically display it
	 */
	public NextButtonListener(ClientPOUI poui, JLabel imageLabel, JButton completeButton, ClientServerConnection connection) {
		this.poui = poui;
		this.imageLabel = imageLabel;
		this.completeButton = completeButton;
		this.connection = connection;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		wasActivated();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			wasActivated();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing.
	}

	/**
	 * If either the next button was clicked or the right arrow was pressed, this method will
	 * start the changing of images.
	 */
	public void wasActivated() {
		// if this step requires inspection and the inspection view isn't already
		// being shown, create a new instance and display.
		if (inspectionCurrentlyDisplayed == false) {
			if (poui.requiresInspection()) {
				inspectionCurrentlyDisplayed = true;
				InspectionAuthenticationView inspectView = new InspectionAuthenticationView(this, connection, 
						poui.getCurrentStepNumber(), poui.getProductID());
				inspectView.setVisible();
			}
			else {
				displayNext();
			}
		}
	}

	/**
	 * Displays the next image in the poui, if there is one.
	 */
	public void displayNext() {
		poui.setCurrentStepInspected();
		if (poui.hasNext()) {
			imageLabel.setIcon(poui.nextStep());
			if (!(poui.hasNext())) {
				completeButton.setVisible(true);
			}
		}
	}

	/**
	 * Method to display the next image after an inspection has been authenticated. This way the boolean
	 * tracking the inspection display isn't being accessed unnecessarily whenever a new image is
	 * displayed.
	 */
	public void displayNextAfterAuthentication() {
		inspectionCurrentlyDisplayed = false;
		this.displayNext();
	}
}
