package main;

import views.ConnectionDetailsPromptView;

/**
 * This serves as the main entry point to the program. It opens a view to prompt the user for the
 * server IP address and port number to reach the work instruction server with.
 * @author jameschapman
 */
public class Client {
	/**
	 * The main entry point for the program.
	 * @param args Standard PSVM arguments, not currently used in this program.
	 */
	public static void main(String[] args) {
		ConnectionDetailsPromptView prompt = new ConnectionDetailsPromptView();
		prompt.setVisible();
	}
}