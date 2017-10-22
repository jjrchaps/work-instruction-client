package views;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import customtypes.POUI;

/**
 * This view will display the POUI images as well as provide the basic interface for iterating through
 * the different steps.
 * @author jameschapman
 */
public class POUIView {
	private POUI assemblyPOUI;
	private JFrame mainFrame;

	public POUIView(POUI assemblyPOUI) {
		this.assemblyPOUI = assemblyPOUI;
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(createPanel());
		mainFrame.pack();
	}

	private JPanel createPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel imagePane = new JPanel();
		imagePane.setLayout(new BoxLayout(imagePane, BoxLayout.PAGE_AXIS));
		imagePane.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		
		// add image to panel
		JLabel poui = new JLabel(new ImageIcon(assemblyPOUI.nextStep()));
		poui.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		imagePane.add(poui);
		
		// create pane for buttons
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
		// create and add previous button to panel
		JButton previous = new JButton("Previous");
		previous.setAlignmentX(JButton.LEFT_ALIGNMENT);
		buttonPane.add(previous);
		
		buttonPane.add(Box.createHorizontalGlue());
		
		// create and add next button to panel
		JButton next = new JButton("Next");
		next.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		buttonPane.add(next);
		
		// add image and buttons to mainPanel
		mainPanel.add(imagePane, BorderLayout.CENTER);
		mainPanel.add(buttonPane, BorderLayout.PAGE_END);
		
		return mainPanel;
	}
	
	public void setVisible() {
		mainFrame.setVisible(true);
	}
	
	public void hideView() {
		mainFrame.setVisible(false);
	}
	
	public static void main(String[] args) {
		POUI test;
		try {
			test = new POUI(3, "/Users/jameschapman/Projects/SED Projects/poui-displayer/Sample Images/");
			POUIView testView = new POUIView(test);
			testView.setVisible();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
