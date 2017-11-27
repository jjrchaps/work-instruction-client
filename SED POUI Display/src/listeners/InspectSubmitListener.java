package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

import connections.ClientServerConnection;
import views.InspectionAuthenticationView;

public class InspectSubmitListener implements ActionListener, KeyListener {
	/**
	 * Stores the calling instance of InspectionAuthenticationView for use once
	 * the user has been authenticated
	 */
	private InspectionAuthenticationView inspectionView;

	/**
	 * The text field that the user entered their stamp number into.
	 */
	private JTextField textField;

	/**
	 * A connection to the server to check if user ID is permitted to do inspection.
	 */
	private ClientServerConnection connection;

	/**
	 * The current step number that is being checked with the server
	 */
	private int currentStep;

	/**
	 * The productID of the build that is having an inspect authenticated.
	 */
	private String productID;

	/**
	 * Takes an instance of inspect authentication view so that a trigger can be sent
	 * once the user has been authenticated.
	 * @param inspectionView The view that has constructed this listener
	 */
	public InspectSubmitListener(InspectionAuthenticationView inspectionView, JTextField textField, ClientServerConnection connection, 
			int currentStep, String productID) {
		this.inspectionView = inspectionView;
		this.textField = textField;
		this.connection = connection;
		this.currentStep = currentStep;
		this.productID = productID;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		checkEnteredText();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		checkEnteredText();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}

	private void checkEnteredText() {
		String userText = textField.getText();			
		if (connection.checkInspectionPrivilege(userText, productID, currentStep)) {
			inspectionView.displayNext();
			inspectionView.hideFrame();
		}
		else {
			textField.setText("Try Again");
		}
	}

}
