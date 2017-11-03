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
				//TODO: Display error message when a connection cannot be established, query to retry?
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
		// add the string 'pouirequest:' so the server knows what type of
		// information we are seeking
		out.println("pouirequest:" + productID);
		out.flush();
		try {
			Object received = in.readObject();
			if (received instanceof Images) {
				return new ClientPOUI((Images) received);
			}
		} catch (ClassNotFoundException|IOException e) {
			e.printStackTrace();
		}
		// if we've made it here, we haven't found the desired POUI. Return null.
		return null;
	}

	/**
	 * Fetches a list of products that have available POUIs from the server. A valid request takes the form is a string
	 * sent to the server that says "productList:".
	 * @return An array of String that is all the available product names from the server
	 */
	public String[] getProductIDs() {
		// semicolon is always added after the request, even if no extra information follows
		out.println("productList:");
		out.flush();
		try {
			Object received = in.readObject();
			if (received instanceof String) {
				String productIDs = (String) received;
				return productIDs.split(";");
			}
		}
		catch (ClassNotFoundException|IOException e) {
			e.printStackTrace();
		}
		// if we've made it here, we should return an empty list
		String[] emptyList = {};
		return emptyList;
	}
}
