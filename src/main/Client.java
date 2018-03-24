package main;

import connections.ClientServerConnection;
import views.StartupView;

/**
 * This serves as the main entry point to the program. It establishes a connection with the POUI server
 * and once connected shows the startup screen.
 * @author jameschapman
 */
public class Client {
	/**
	 * The main entry point for the program.
	 * @param args Standard PSVM arguments, not currently used in this program.
	 */
	public static void main(String[] args) {
		ClientServerConnection connection = new ClientServerConnection();
		StartupView startup = new StartupView(connection);
		startup.makeVisible();
	}
}