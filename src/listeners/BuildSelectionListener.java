package listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;

import auxiliary.ClientPOUI;
import connections.ClientServerConnection;
import views.BuildSelectionView;
import views.POUIView;

/**
 * StartupListener responds to the submit button being clicked and triggers
 * the program to fetch the selected POUI and display it
 * @author jameschapman
 */
public class BuildSelectionListener implements ActionListener, KeyListener {
	/**
	 * Local instance of JComboBox, stores a reference from the StartupView's JComboBox
	 * so that we can access what the user has selected.
	 */
	private JComboBox<String> comboBox;
	
	/**
	 * An instance of ServerConnection that serves as our server connection -- used to fetch
	 * the POUIs from the server.
	 */
	private ClientServerConnection connection;
	
	/**
	 * The view being displayed to the user to select an assembly
	 */
	private BuildSelectionView view;

	/**
	 * Constructor for StartupListener that initializes local variables with the given parameters
	 * @param view The view that is being displayed to the user
	 * @param comboBox The comboBox that displays assemblies to the user
	 * @param connection A connection with the POUI server to be used to fetch images
	 */
	public BuildSelectionListener(BuildSelectionView view, JComboBox<String> comboBox, ClientServerConnection connection) {
		this.view = view;
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
	 * and displays them for the user
	 */
	private void getPOUIAndDisplay() {
		ClientPOUI poui = connection.requestPOUI((String) comboBox.getSelectedItem());
		if (poui != null) {
			POUIView instructionView = new POUIView(poui, connection);
			instructionView.setVisible();
			view.hideFrame();
		}
	}

}	
