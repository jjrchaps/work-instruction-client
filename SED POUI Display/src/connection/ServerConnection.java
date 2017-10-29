package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import customtypes.ClientPOUI;

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
		try {
			clientSocket = new Socket("localhost", 12312);
			out = new PrintWriter(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ClientPOUI requestPOUI(String productID) {
		
		
		
		return null;
	}
}
