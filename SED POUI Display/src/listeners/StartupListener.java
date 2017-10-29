package listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

import connections.ServerConnection;
import customtypes.ClientPOUI;
import views.POUIView;

public class StartupListener implements ActionListener, KeyListener {
	private JTextField textfield;
	private ServerConnection server;


	public StartupListener(JTextField textfield) {
		this.textfield = textfield;
		server = new ServerConnection();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getPOUIAndDisplay();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			getPOUIAndDisplay();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}

	/**
	 * Uses a ServerConnection object to fetch the desired POUI's from the server
	 * and displays them
	 */
	private void getPOUIAndDisplay() {
		ClientPOUI poui = server.requestPOUI(textfield.getText());
		if (poui != null) {
			POUIView instructionView = new POUIView(poui);
			instructionView.setVisible();
		}
		else {
			textfield.setText("Please enter a valid product ID");
		}
	}

}	
