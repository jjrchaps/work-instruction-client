package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import connections.ClientServerConnection;
import views.ConnectionDetailsPrompt;
import views.StartupView;

public class ConnectionDetailSubmitListener implements ActionListener, KeyListener {
	/**
	 * The view being displayed to the user prompting for the IP Address and port
	 */
	private ConnectionDetailsPrompt promptView;

	/**
	 * The field containing the user entered IP Address
	 */
	private JTextField ipAddress;

	/**
	 * The field containing the user entered port number
	 */
	private JTextField portNumber;

	public ConnectionDetailSubmitListener(ConnectionDetailsPrompt promptView, JTextField ipAddress,
			JTextField portNumber){
		this.promptView = promptView;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		startServer();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		startServer();
	}

	private void startServer() {
		promptView.hideFrame();
		String ipAddress = this.ipAddress.getText();
		int port = Integer.parseInt(this.portNumber.getText());
		ClientServerConnection serverConnection = new ClientServerConnection(ipAddress, port);
		StartupView startup = new StartupView(serverConnection);
		startup.makeVisible();
	}
}
