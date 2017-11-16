package connections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import auxiliary.ClientPOUI;
import auxiliary.Images;

//TODO: Class requires better error handling and catching

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
		reconnect();
	}

	private void reconnect() {
		while (true) {
			try {
				clientSocket = new Socket("localhost", 12312);
				out = new PrintWriter(clientSocket.getOutputStream());
				in = new ObjectInputStream(clientSocket.getInputStream());
				break;
			} catch (IOException e) {
				// do nothing, as loop will continue until successful connection is established.
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
		while (true) {
			try {
				out.println("pouirequest:" + productID);
				out.flush();
				Object received = in.readObject();
				if (received instanceof Images) {
					return new ClientPOUI((Images) received, productID);
				}
				break;
			} catch (IOException e) {
				System.out.println("Reestablishing connection with server...");
				reconnect();
				System.out.println("Connection established!");
			} catch (ClassNotFoundException e) {
				System.out.println("Invalid response from server!");
			}
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
		while (true) {
			out.println("productList:");
			out.flush();
			try {
				Object received = in.readObject();
				if (received instanceof String) {
					String productIDs = (String) received;
					return productIDs.split(";");
				}
				break;
			} catch (IOException e) {
				System.out.println("Reestablishing connection with server...");
				reconnect();
				System.out.println("Connection established!");
			} catch (ClassNotFoundException e) {
				System.out.println("Invalid response from server!");
			}
		}
		// if we get here something has gone wrong server side and there will be no POUIs to load.
		return null;
	}

	/**
	 * Sends all timings recorded by the client to the server for storage.
	 * @param timings A string of time values separated by a colon.
	 */
	public void reportTimings(String timings) {
		String timesReport = "reportTimings:" + timings;
		while (true) {
			out.println(timesReport);
			out.flush();
			try {
				Object received = in.readObject();
				if (received instanceof Boolean) {
					boolean response = (boolean) received;
					if (!response) {
						System.out.println("Server failed to store sent information.");
					}
				}
				break;
			} catch (IOException e) {
				System.out.println("Reestablishing connection with server...");
				reconnect();
				System.out.println("Connection established!");
			} catch (ClassNotFoundException e) {
				System.out.println("Invalid response from server!");
			}
		}
	}
}
