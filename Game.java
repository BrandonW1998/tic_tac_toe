/*
 * Tic-Tac-Toe Board Game
 * 
 * Author: Brandon Westbrook
 */
import javax.swing.SwingUtilities;

// Driver Class for Board Game
public class Game {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new DisplayGame();
			}
		});

	}

}
