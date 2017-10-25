package listeners;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
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

	private void getPOUI() {
		ArrayList<String> options = new ArrayList<String>();
		String input = textfield.getText();
		options.add("test");

		if (!(options.contains(input))) {
			textfield.setText("Please enter a valid product number");
		}
		else {
			ClientPOUI poui;
			try {
				LinkedList<ImageIcon> images = new LinkedList<ImageIcon>();
				for (int i = 1; i <= 3; i++) {
					String fileName = "/Users/jameschapman/Projects/SED Projects/poui-displayer/Sample Images/" + "step" + Integer.toString(i) + ".jpg";
					Image image = ImageIO.read(new File(fileName));
					ImageIcon img = new ImageIcon(image);
					images.add(img);
				}
				poui = new ClientPOUI(images);
				POUIView assemblyView = new POUIView(poui);
				assemblyView.setVisible();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//	@SuppressWarnings("unchecked")
	//	private void getPOUI() {
	//		Socket clientSocket;
	//		try {
	//			clientSocket = new Socket("localhost", 12312);
	//			PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
	//			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
	//			System.out.println("Connected");
	//			out.println(textfield.getText());
	//			out.flush();
	//			LinkedList<ImageIcon> receivedImages = (LinkedList<ImageIcon>) in.readObject();
	//			if (receivedImages != null) {
	//				ClientPOUI poui = new ClientPOUI(receivedImages);
	//				POUIView assemblyView = new POUIView(poui);
	//				assemblyView.setVisible();
	//			}
	//			else {
	//				textfield.setText("Please enter a valid product number");
	//			}
	//		} catch (UnknownHostException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		} catch (ClassNotFoundException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//	}

}	
