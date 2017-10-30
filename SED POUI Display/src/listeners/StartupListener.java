package listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;

import connections.ServerConnection;
import customTypes.ClientPOUI;
import views.POUIView;

public class StartupListener implements ActionListener, KeyListener {
	private JComboBox<String> comboBox;
	private ServerConnection connection;


	public StartupListener(JComboBox<String> comboBox, ServerConnection connection) {
		this.comboBox = comboBox;
		this.connection = connection;
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
		ClientPOUI poui = connection.requestPOUI((String) comboBox.getSelectedItem());
		if (poui != null) {
			POUIView instructionView = new POUIView(poui);
			instructionView.setVisible();
		}
	}

}	
