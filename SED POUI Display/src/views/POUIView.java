package views;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import customTypes.ClientPOUI;
import listeners.BuildCompleteListener;
import listeners.NextButtonListener;
import listeners.PreviousButtonListener;

/**
 * This view will display the POUI images as well as provide the basic interface for iterating through
 * the different steps.
 * @author jameschapman
 */
public class POUIView {
	private ClientPOUI assemblyPOUI;
	private JFrame mainFrame;
	private JPanel buttonPane;
	//TODO: commenting
	public POUIView(ClientPOUI assemblyPOUI) {
		this.assemblyPOUI = assemblyPOUI;
		this.mainFrame = new JFrame("POUI");
		this.mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.mainFrame.add(createPanel());
		this.mainFrame.pack();
		this.mainFrame.setVisible(true);
	}

	private JPanel createPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel imagePane = new JPanel();
		imagePane.setLayout(new BoxLayout(imagePane, BoxLayout.PAGE_AXIS));

		// add image to panel
		JLabel poui = new JLabel(assemblyPOUI.nextStep());
		poui.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		imagePane.add(poui);

		// create pane for buttons
		buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

		// create and add previous button to panel
		JButton previous = new JButton("Previous");
		previous.setAlignmentX(JButton.LEFT_ALIGNMENT);

		buttonPane.add(previous);

		buttonPane.add(Box.createHorizontalGlue());

		JButton complete = new JButton("Build Complete");
		complete.setAlignmentX(JButton.CENTER_ALIGNMENT);
		complete.setVisible(false);
		buttonPane.add(complete);
		buttonPane.add(Box.createHorizontalGlue());

		// create and add next button to panel
		JButton next = new JButton("Next");
		next.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		buttonPane.add(next);

		// add action listeners to buttons
		PreviousButtonListener previousListener = new PreviousButtonListener(assemblyPOUI, poui, complete);
		previous.addActionListener(previousListener);
		NextButtonListener nextListener = new NextButtonListener(assemblyPOUI, poui, complete, mainFrame);
		next.addActionListener(nextListener);
		BuildCompleteListener completeListener = new BuildCompleteListener(mainFrame);
		complete.addActionListener(completeListener);

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

	public void showCompleteButton() {
	}
}
