package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import connections.ClientServerConnection;
import views.ConnectionDetailsPrompt;
import views.BuildSelectionView;

/**
 * ConnectionDetailsSubmitListener is the listener for the submit button located
 * within the ConnectionDetailsPrompt view. It takes the values entered into the
 * IP address field and port field and creates a connection to the server with 
 * those values.
 * @author jameschapman
 */
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

	/**
	 * Creates new listener for the submit button. On button press, the ipAddress field and
	 * portNumber field will be parsed and the values used to connect to the work instruction
	 * server.
	 * @param promptView The view that is presented to the user.
	 * @param ipAddress The field where the user will enter the IP address of the server.
	 * @param portNumber The port that the server is listening for connections on.
	 */
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
		connectToServer();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		connectToServer();
	}

	/**
	 * Starts a connection with the work instruction server the the information parsed
	 * from the IP Address and Port number fields.
	 */
	private void connectToServer() {
		promptView.hideFrame();
		String ipAddress = this.ipAddress.getText();
		int port = Integer.parseInt(this.portNumber.getText());
		ClientServerConnection serverConnection = new ClientServerConnection(ipAddress, port);
		BuildSelectionView startup = new BuildSelectionView(serverConnection);
		startup.makeVisible();
	}
}
