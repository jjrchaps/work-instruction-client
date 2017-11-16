package views;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import auxiliary.ClientPOUI;
import connections.ServerConnection;
import listeners.BuildCompleteListener;
import listeners.NextButtonListener;
import listeners.PreviousButtonListener;

/**
 * This view will display the POUI images as well as provide the basic interface for iterating through
 * the different steps.
 * @author jameschapman
 */
public class POUIView {
	/**
	 * Local instance of ClientPOUI, will use this copy fetched from the server to be displayed to the user.
	 */
	private ClientPOUI assemblyPOUI;
	
	/**
	 * Only instance of JFrame used throughout the class. All components will be added to this frame, and the
	 * frame will be displayed.
	 */
	private JFrame mainFrame;
	
	/**
	 * The pane that contains the buttons. They will be displayed below the images and 'Build Complete' will be
	 * dynamically displayed.
	 */
	private JPanel buttonPane;
	
	/**
	 * A connection with the server that will be passed to Listeners to be used when necessary.
	 */
	private ServerConnection connection;
	
	/**
	 * Constructs a new instance of POUIView, initializes all local variables and takes in a ClientPOUI object
	 * that is the POUI that will be displayed.
	 * @param assemblyPOUI The POUI that the user has requested and is to be displayed, fetched from POUI server.
	 */
	public POUIView(ClientPOUI assemblyPOUI, ServerConnection connection) {
		this.connection = connection;
		this.assemblyPOUI = assemblyPOUI;
		this.mainFrame = new JFrame("POUI");
		this.mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.mainFrame.add(createPanels());
		this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.mainFrame.setVisible(true);
	}
	
	/**
	 * Creates the panels that will be added to the main JFrame and then displayed.
	 * @return mainPanel The panel that contains all sub-components to be added to the frame.
	 */
	private JPanel createPanels() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel imagePane = new JPanel();
		imagePane.setLayout(new BoxLayout(imagePane, BoxLayout.PAGE_AXIS));

		// add image to panel
		JLabel poui = new JLabel(assemblyPOUI.startBuild());
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
		NextButtonListener nextListener = new NextButtonListener(assemblyPOUI, poui, complete);
		next.addActionListener(nextListener);
		BuildCompleteListener completeListener = new BuildCompleteListener(mainFrame, connection, assemblyPOUI);
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
}
