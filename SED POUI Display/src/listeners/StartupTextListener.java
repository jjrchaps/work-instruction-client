package listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JTextField;

public class StartupTextListener implements KeyListener {
	private JTextField textfield;
	private ArrayList<String> options;
	
	public StartupTextListener (JTextField textfield) {
		this.textfield = textfield;
		options = new ArrayList<String>();
		options.add("test");
		options.add("test2");
		options.add("test3");
		options.add("test4");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			checkInput();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}
	

	private void checkInput() {
		String input = textfield.getText();
		if (!(options.contains(input))) {
			textfield.setText("Please enter a valid product number");
		}
		else {
			textfield.setText("");
		}
	}
}
