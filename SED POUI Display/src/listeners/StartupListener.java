package listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import customtypes.ClientPOUI;
import views.POUIView;

public class StartupListener implements ActionListener, KeyListener {
	private JTextField textfield;


	public StartupListener(JTextField textfield) {
		this.textfield = textfield;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getPOUI();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			getPOUI();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}

	@SuppressWarnings("unchecked")
	private void getPOUI() {
		Socket clientSocket;
		try {
			clientSocket = new Socket("localhost", 12312);
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			String input = textfield.getText();
			out.println(input);
			out.flush();
			LinkedList<ImageIcon> receivedImages = (LinkedList<ImageIcon>) in.readObject();
			if (receivedImages != null) {
				ClientPOUI poui = new ClientPOUI(receivedImages);
				POUIView assemblyView = new POUIView(poui);
				assemblyView.setVisible();
			}
			else {
				textfield.setText("Please enter a valid product number");
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}	
