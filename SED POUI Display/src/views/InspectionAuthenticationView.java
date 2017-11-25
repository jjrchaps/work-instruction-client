package views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listeners.InspectSubmitListener;
import listeners.NextButtonListener;

/**
 * InspectionAuthenticationView will be displayed whenever the user tries to continue on to
 * the next step of a POUI and the current step requires a level three inspection to be 
 * completed. Once inspection is completed the user will have to enter the identifying 
 * number assigned to them, which will be logged to the server and stored.
 * @author jameschapman
 */
public class InspectionAuthenticationView {
	/**
	 * The frame that will contain the pane to input identifying code
	 */
	private JFrame mainFrame;
	
	/**
	 * Next button so that this view can initiate the changing of images once the user has entered
	 * their stamp ID
	 */
	private NextButtonListener nextListener;
	
	/**
	 * Creates new instance of the inspection view, and displays in in the middle of the display.
	 */
	public InspectionAuthenticationView(NextButtonListener nextListener) {
		this.nextListener = nextListener;
		mainFrame = new JFrame("Inspection");
		mainFrame.add(createPanel());
		mainFrame.pack();
		mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);
	}
	
	/**
	 * Creates the panel that will contain the text field and submit button for entering
	 * identifying number.
	 * @return The populated panel to be added to Frame and displayed.
	 */
	private JPanel createPanel() {
		JPanel mainPanel = new JPanel();
		
		JTextField textEnter = new JTextField("Stamp ID");
		mainPanel.add(textEnter);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new InspectSubmitListener(this, textEnter));
		mainPanel.add(submitButton);
		
		return mainPanel;
	}
	
	public void displayNext() {
		this.nextListener.displayNext();
	}
	
	public void setVisible() {
		mainFrame.setVisible(true);
	}
	
	public void hideFrame() {
		mainFrame.setVisible(false);
	}
}
