package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import connections.ClientServerConnection;

public class RefreshListener implements ActionListener {
	/**
	 * The current connection with the server, to be used to fetch updated build lists
	 */
	private ClientServerConnection connection;
	
	/**
	 * The combo box where the refreshed list is displayed.
	 */
	private JComboBox<String> comboBox;
	
	public RefreshListener(ClientServerConnection connection, JComboBox<String> comboBox) {
		this.connection = connection;
		this.comboBox = comboBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		comboBox = new JComboBox<String>(connection.getProductIDs());
	}

}
