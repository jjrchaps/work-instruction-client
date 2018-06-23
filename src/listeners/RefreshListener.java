package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import connections.ClientServerConnection;

/**
 * Listener for the refresh button in the build selection view. When clicked, it will fetch
 * an updated list of available builds from the server and update the combo box with the new
 * list.
 * @author jameschapman
 */
public class RefreshListener implements ActionListener {
	/**
	 * The current connection with the server, to be used to fetch updated build lists
	 */
	private ClientServerConnection connection;
	
	/**
	 * The combo box where the refreshed list is displayed.
	 */
	private JComboBox<String> comboBox;
	
	/**
	 * Creates a new instance of RefreshListener to update available build list.
	 * @param connection The current connection with the server, for fetching the new list
	 * @param comboBox The combo box that the contents need to be updated in.
	 */
	public RefreshListener(ClientServerConnection connection, JComboBox<String> comboBox) {
		this.connection = connection;
		this.comboBox = comboBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		comboBox.removeAllItems();
		String[] newOptions = connection.getProductIDs();
		for (String item : newOptions) {
			comboBox.addItem(item);
		}
	}

}
