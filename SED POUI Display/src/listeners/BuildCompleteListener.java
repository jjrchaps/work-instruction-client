package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * BuildCompleteListener is an action listener for the 'Build Complete' button in
 * POUI view frame. For now, it simply hides the frame when clicked. Future development
 * would have it close the window and report back to the server with statistics and serial
 * numbers, etc
 * @author jameschapman
 */
public class BuildCompleteListener implements ActionListener {
	/**
	 * Local instance of JFrame that will be used as a reference to the View frame.
	 * When clicked, the listener will close the POUI window.
	 */
	private JFrame viewFrame;
	
	public BuildCompleteListener(JFrame viewFrame) {
		this.viewFrame = viewFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		viewFrame.setVisible(false);
	}

}
