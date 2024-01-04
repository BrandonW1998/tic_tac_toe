import java.awt.*;
import javax.swing.*;

public class DisplayGame extends JFrame{
	private static final long serialVersionUID = 1L;
	private Container pane;	// Contains board
	private JButton[][] gameBoard; // 2D Array of buttons
	private static final int boardRow = 3;	// Board size
	private static final int boardCol = 3;	// Board size
	
	// Constructs board, frame, and variables
	public DisplayGame() {
		
		// Constructs new frame
		super();
		
		// Initialize pane and layout
		pane = getContentPane();
		pane.setLayout(new GridLayout(boardRow, boardCol));
		
		// Initialize frame
		setTitle("Tic-Tac-Toe");
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		// Initialize game variables
		gameBoard = new JButton[boardRow][boardCol];
		
		// Create/Display board
		initBoard();
		
	}
	
	// Initialze board, add to pane
	private void initBoard() {
		
		// Initialize buttons
		for (int i = 0; i < boardRow; i++) {
			for(int j = 0; j < boardCol; j++) {
				JButton button = new JButton("");
				button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 125));
				gameBoard[i][j] = button;
				
				// Add button to pane
				pane.add(button);
			}
		}
	}

}
