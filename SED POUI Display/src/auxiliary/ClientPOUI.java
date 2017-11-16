package auxiliary;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.ImageIcon;

/**
 * POUI will contain the images that will be used for assembler instruction, as well as relevant meta data 
 * about that particular assembly.
 * @author jameschapman
 */
public class ClientPOUI {
	/**
	 *  Container to store all images in order within the object
	 */
	LinkedList<ImageIcon> images;

	/**
	 * Iterator to go over steps in order when build is taking place
	 */
	ListIterator<ImageIcon> iterator;

	/**
	 * Setting to track when the next button was clicked. This will ensure the iterator serves up the 
	 * correct image when necessary.
	 */
	private boolean nextWasCalled;

	/**
	 * Setting to track when the previous button was clicked. This will ensure the iterator serves up the 
	 * correct image when necessary.
	 */
	private boolean previousWasCalled;

	/**
	 * Array to store all the timings recorded
	 */
	private float[] timings;

	/**
	 * Long to store the time that the image began being displayed in nanoseconds.
	 */
	private long startTime;

	/**
	 * Long to store the time the image stopped being displayed in nanoseconds.
	 */
	private long endTime;
	
	/**
	 * The identifying string for this build
	 */
	private String productID;

	/**
	 * Constructor for POUI. Will read in number the images in order of steps and store within object
	 * @param numberOfSteps The number of steps (and images) to be completed for this SED Assembly
	 * @param imageLocation The path to the images for this assembly. They should be name "step1.jpg", "step2.jpg", etc. and the path
	 * supplied should end with a forward slash
	 */
	public ClientPOUI(Images images, String productID) throws IOException {
		this.images = images.getImages();
		this.productID = productID;
		iterator = (ListIterator<ImageIcon>) this.images.iterator();
		nextWasCalled = false;
		previousWasCalled = true;
		timings = new float[images.getImages().size()];
		for (int i = 0; i < timings.length; i++) {
			timings[i] = 0;
		}
	}

	/**
	 * Starts the build, by returning the first step
	 * @return A buffered image that is the first step of the build process
	 */
	public ImageIcon startBuild() {
		startTime = System.nanoTime();
		return images.getFirst();
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
			endTime = System.nanoTime();
			ImageIcon next = iterator.next();
			// converting recorded time to from nanoseconds to seconds rounded to two decimal places.
			BigDecimal rawTime = new BigDecimal((endTime - startTime)/1000000000.0);
			BigDecimal roundedTime = rawTime.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			// set the time for the index that had just been displayed
			// set the time for the index that had just been displayed
			timings[iterator.previousIndex()-1] += roundedTime.floatValue();
			startTime = System.nanoTime();
			nextWasCalled = true;
			return next;
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
			endTime = System.nanoTime();
			ImageIcon previous = iterator.previous();
			// converting recorded time to from nanoseconds to seconds rounded to two decimal places.
			BigDecimal rawTime = new BigDecimal((endTime - startTime)/1000000000.0);
			BigDecimal roundedTime = rawTime.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			// set the time for the index that had just been displayed
			timings[iterator.nextIndex()+1] += roundedTime.floatValue();
			startTime = System.nanoTime();
			previousWasCalled = true;
			return previous;
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

	/**
	 * Generates a string containing all the timings separated with colons. String starts with product id.
	 * @return A string containing all the timings in string format.
	 */
	public String getTimings() {
		// if we are getting timings, it means build complete was just pressed. Which also means that
		// the very last image was displayed but the time not tracked, so track it now
		this.endTime = System.nanoTime();
		BigDecimal rawTime = new BigDecimal((endTime - startTime)/1000000000.0);
		BigDecimal roundedTime = rawTime.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		// set the time for the index that had just been displayed. Since we are at the end of the poui, we know
		// it has to be the last index of timings as well.
		timings[timings.length - 1] += roundedTime.floatValue();
		
		// now build the string with final time recordings within
		String stringTimings = this.productID;
		for (int i = 0; i < timings.length; i++) {
			stringTimings += ":" + this.timings[i];
		}
		return stringTimings;
	}
}
