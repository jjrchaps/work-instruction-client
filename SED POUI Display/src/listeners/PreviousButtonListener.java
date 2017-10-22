package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import customtypes.POUI;

/**
 * Listener for the previous button in the pouiview class. If there is a previous image, it will display it.
 * @author jameschapman
 */
public class PreviousButtonListener implements ActionListener {
	// save an instance of the POUI we're working on to pull up future steps
	private POUI poui;
	// instance of the imagePanel that is displayed in the POUIView
	private JLabel imageLabel;
	
	/**
	 * Prepares local instances of variables to be able to make the image swap
	 * @param poui The source POUI object to get the images from
	 * @param imagePanel The pane that the images are being displayed in
	 */
	public PreviousButtonListener(POUI poui, JLabel imageLabel) {
		this.poui = poui;
		this.imageLabel = imageLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (poui.hasPrevious()) {
			imageLabel.setIcon(new ImageIcon(poui.previousStep()));
		}
	}

}