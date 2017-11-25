package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

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
	 * Takes an instance of inspect authentication view so that a trigger can be sent
	 * once the user has been authenticated.
	 * @param inspectionView The view that has constructed this listener
	 */
	public InspectSubmitListener(InspectionAuthenticationView inspectionView, JTextField textField) {
		this.inspectionView = inspectionView;
		this.textField = textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (textField.getText().length() == 3) {
			inspectionView.displayNext();
			inspectionView.hideFrame();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (textField.getText().length() == 3) {
			inspectionView.displayNext();
			inspectionView.hideFrame();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}

}
