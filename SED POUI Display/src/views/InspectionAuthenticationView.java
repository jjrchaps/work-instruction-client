package views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * InspectionAuthenticationView will be displayed whenver the user tries to continue on to
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
	
	public InspectionAuthenticationView() {
		mainFrame = new JFrame("Enter Stamp Number");
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
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		
		JTextField textEnter = new JTextField("Enter number here");
		textEnter.setAlignmentX(JTextField.CENTER_ALIGNMENT);
		mainPanel.add(textEnter);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		mainPanel.add(submitButton);
		
		return mainPanel;
	}
	
	public void makeVisible() {
		mainFrame.setVisible(true);
	}
	
	public void hideFrame() {
		mainFrame.setVisible(false);
	}
}
