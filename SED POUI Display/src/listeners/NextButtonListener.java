package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import auxiliary.ClientPOUI;
import views.InspectionAuthenticationView;

/**
 * Listener for the next button in the pouiview class. If there is another image, it will display it.
 * @author jameschapman
 */
public class NextButtonListener implements ActionListener {
	/** 
	 * save an instance of the POUI we're working on to pull up future steps
	 */
	private ClientPOUI poui;

	/**
	 * instance of the imagePanel that is displayed in the POUIView
	 */
	private JLabel imageLabel;

	/**
	 * complete button instance so that it's visibility can be dynamically changed
	 */
	private JButton completeButton;

	/**
	 * Prepares local instance variables for all parameters so the button is able to swap images when desired.
	 * @param poui The poui that is currently being displayed
	 * @param imageLabel The JLabel that is displaying the ImageIcon
	 * @param completeButton A reference to the "Build Complete" button so we can dynamically display it
	 */
	public NextButtonListener(ClientPOUI poui, JLabel imageLabel, JButton completeButton) {
		this.poui = poui;
		this.imageLabel = imageLabel;
		this.completeButton = completeButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (poui.requiresInspection()) {
			InspectionAuthenticationView inspectView = new InspectionAuthenticationView(this);
			inspectView.setVisible();
		}
		else {
			displayNext();
		}
	}
	
	public void displayNext() {
		poui.setCurrentStepInspected();
		if (poui.hasNext()) {
				imageLabel.setIcon(poui.nextStep());
				if (!(poui.hasNext())) {
					completeButton.setVisible(true);
				}
			}
	}
}
