package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import connections.ServerConnection;
import customTypes.ClientPOUI;

/**
 * BuildCompleteListener is an action listener for the 'Build Complete' button in
 * POUI view frame. For now, it simply hides the frame when clicked. Future development
 * would have it close the window and report back to the server with statistics and serial
 * numbers, etc
 * @author jameschapman
 */
public class BuildCompleteListener implements ActionListener {
	/**
	 * Local instance of JFrame that will be used as a reference to the View frame.
	 * When clicked, the listener will close the POUI window.
	 */
	private JFrame viewFrame;
	
	/**
	 * Connection to the POUI server that will be used to report build times.
	 */
	private ServerConnection connection;
	
	/**
	 * The assembly that will be completed where the build timings can be fetched.
	 */
	private ClientPOUI poui;
	
	/**
	 * Creates a new instance of the build complete button, and takes in as parameters
	 * the frame it's within as well as a connection to the server to report build timings.
	 * @param viewFrame The frame that this listener belongs to
	 * @param connection A connection with the POUI server
	 * @paran poui The poui that's currently being displayed to the user.
	 */
	public BuildCompleteListener(JFrame viewFrame, ServerConnection connection, ClientPOUI poui) {
		this.viewFrame = viewFrame;
		this.connection = connection;
		this.poui = poui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		connection.reportTimings(poui.getTimings());
		viewFrame.setVisible(false);
	}

}
