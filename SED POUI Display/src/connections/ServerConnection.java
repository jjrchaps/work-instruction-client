package connections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import customTypes.ClientPOUI;
import customTypes.Images;

/**
 * ServerConnection will act as the local method to encapsulate all communication
 * with the server in one place. A connection will be established on startup and maintained
 * throughout the use of the program.
 * @author jameschapman
 *
 */
public class ServerConnection {
	private Socket clientSocket;
	private PrintWriter out;
	private ObjectInputStream in;

	/**
	 * Constructor will initialize a connection with the server as well as the input
	 * and output stream for future use
	 */
	public ServerConnection() {
		while (true) {
			try {
				clientSocket = new Socket("localhost", 12312);
				out = new PrintWriter(clientSocket.getOutputStream());
				in = new ObjectInputStream(clientSocket.getInputStream());
				break;
			} catch (IOException e) {
				//TODO: Display error message when a connection cannot be established.
				// do nothing, continue trying to connect to server
			}
		}
	}

	/**
	 * Fetches the relevant images from the POUI server and returns a ClientPOUI object
	 * If the desired POUI's cannot be found, requestPOUI will return null
	 * @param productID The productID string for the desired poui
	 * @return a ClientPOUI Object if the productID is valid, null otherwise
	 */
	public ClientPOUI requestPOUI(String productID) {
		out.println(productID);
		out.flush();
		try {
			Object received = in.readObject();
			if (received instanceof Images) {
				return new ClientPOUI((Images) received);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
