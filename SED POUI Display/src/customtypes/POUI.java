package customtypes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.imageio.ImageIO;

/**
 * POUI will contain the images that will be used for assembler instruction, as well as relevant meta data 
 * about that particular assembly.
 * @author jameschapman
 */
public class POUI {
	// Container to store all images in order within the object
	LinkedList<BufferedImage> images;

	// Iterator to go over steps in order when build is taking place
	ListIterator<BufferedImage> iterator;

	/**
	 * Constructor for POUI. Will read in number the images in order of steps and store within object
	 * @param numberOfSteps The number of steps (and images) to be completed for this SED Assembly
	 * @param imageLocation The path to the images for this assembly. They should be name "step1.jpg", "step2.jpg", etc. and the path
	 * supplied should end with a forward slash
	 */
	public POUI(int numberOfSteps, String imageLocation) throws IOException {
		images = new LinkedList<BufferedImage>();
		for (int i = 1; i <= numberOfSteps; i++) {
			String fileName = imageLocation + "step" + Integer.toString(i) + ".jpg";
			BufferedImage img = ImageIO.read(new File(fileName));
			images.add(img);
		}
		iterator = (ListIterator<BufferedImage>) images.iterator();
	}
	
	/**
	 * Starts the build, by returning the first step
	 * @return A buffered image that is the first step of the build process
	 */
	public BufferedImage startBuild() {
		return iterator.next();
	}

	/**
	 * If there is a next step, it will be returned. Else it will return null
	 * @return A buffered image if there are any more steps, null otherwise
	 */
	public BufferedImage nextStep() {
		if (iterator.hasNext()) {
			return iterator.next();
		}
		else {
			return null;
		}
	}

	/**
	 * If there is a previou step, it will be returned. Else it will return null
	 * @return A buffered image if there are any previous steps, null otherwise
	 */
	public BufferedImage previousStep() {
		if (iterator.hasPrevious()) {
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
	// TODO: methods to return next step, go back a step, etc.
	// TODO: equals method that will simply check if the SED ID number is the same, we don't need to check more than that.

	public static void main(String[] args) {
		try {
			POUI test = new POUI(3, "/Users/jameschapman/Projects/SED Projects/poui-displayer/Sample Images/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
