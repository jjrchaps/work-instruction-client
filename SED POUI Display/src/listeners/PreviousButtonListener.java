package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import customTypes.ClientPOUI;

/**
 * Listener for the previous button in the pouiview class. If there is a previous image, it will display it.
 * @author jameschapman
 */
public class PreviousButtonListener implements ActionListener {
	/**
	 * save an instance of the POUI we're working on to pull up future steps
	 */
	private ClientPOUI poui;
	/**
	 * instance of the imagePanel that is displayed in the POUIView
	 */
	private JLabel imageLabel;
	/**
	 *  instance of JButton to store the build complete button, this allows it to be dynamically 
	 *  controlled as the directions are followed
	 */
	private JButton completeButton;
	
	/**
	 * A local instance of JFrame to store the mainFrame that the POUIs are displayed it. This allows
	 * the class to change the frame size to match the new image
	 */
	private JFrame mainFrame;

	/**
	 * Prepares local instances of variables to be able to make the image swap
	 * @param poui The source POUI object to get the images from
	 * @param imagePanel The pane that the images are being displayed in
	 */
	public PreviousButtonListener(ClientPOUI poui, JLabel imageLabel, JButton completeButton, JFrame mainFrame) {
		this.poui = poui;
		this.imageLabel = imageLabel;
		this.completeButton = completeButton;
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (poui.hasPrevious()) {
			imageLabel.setIcon(poui.previousStep());
			mainFrame.pack();
			if (completeButton.isVisible()) {
				completeButton.setVisible(false);
			}
		}
	}
}
