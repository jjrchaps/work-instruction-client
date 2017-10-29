package customtypes;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.ImageIcon;

/**
 * POUI will contain the images that will be used for assembler instruction, as well as relevant meta data 
 * about that particular assembly.
 * @author jameschapman
 */
public class ClientPOUI {
	// Container to store all images in order within the object
	LinkedList<ImageIcon> images;

	// Iterator to go over steps in order when build is taking place
	ListIterator<ImageIcon> iterator;
	
	// setting up 2 boolean values to track whether previous or next was most recently called
	// this will correct an issue causing the same img to be returned when alternating between
	// the previous and next buttons
	private boolean nextWasCalled;
	private boolean previousWasCalled;
	
	/**
	 * Constructor for POUI. Will read in number the images in order of steps and store within object
	 * @param numberOfSteps The number of steps (and images) to be completed for this SED Assembly
	 * @param imageLocation The path to the images for this assembly. They should be name "step1.jpg", "step2.jpg", etc. and the path
	 * supplied should end with a forward slash
	 */
	public ClientPOUI(Images images) throws IOException {
		this.images = images.getImages();
		iterator = (ListIterator<ImageIcon>) this.images.iterator();
		nextWasCalled = false;
		previousWasCalled = false;
	}
	
	/**
	 * Starts the build, by returning the first step
	 * @return A buffered image that is the first step of the build process
	 */
	public ImageIcon startBuild() {
		return iterator.next();
	}

	/**
	 * If there is a next step, it will be returned. Else it will return null
	 * @return A buffered image if there are any more steps, null otherwise
	 */
	public ImageIcon nextStep() {
		if (previousWasCalled) {
			iterator.next();
			previousWasCalled = false;
		}
		if (iterator.hasNext()) {
			nextWasCalled = true;
			return iterator.next();
		}
		else {
			return null;
		}
	}

	/**
	 * If there is a previous step, it will be returned. Else it will return null
	 * @return A buffered image if there are any previous steps, null otherwise
	 */
	public ImageIcon previousStep() {
		if (nextWasCalled) {
			iterator.previous();
			nextWasCalled = false;
		}
		if (iterator.hasPrevious()) {
			previousWasCalled = true;
			return iterator.previous();
		}
		else {
			return null;
		}
	}
	
	public boolean hasNext() {
		return iterator.hasNext();
	}
	
	public boolean hasPrevious() {
		return iterator.hasPrevious();
	}

//	public static void main(String[] args) {
//		try {
//			POUI test = new POUI(3, "/Users/jameschapman/Projects/SED Projects/poui-displayer/Sample Images/");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
