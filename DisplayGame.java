import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DisplayGame extends JFrame{
	private static final long serialVersionUID = 1L;
	private Container pane;	// Contains board
	private JButton[][] gameBoard;	// 2D Array of buttons
	private String currPlayer;	// Tracks current player
	private int turnCount;	// Tracks turns
	private boolean hasWinner;	// Flag set when winner found
	private static final int boardRow = 3;	// Board size
	private static final int boardCol = 3;	// Board size
	
	// Constructs board, frame, and variables
	public DisplayGame() {
		
		// Constructs new frame
		super();
		
		// Initialize pane and layout
		pane = getContentPane();
		pane.setLayout(new GridLayout(boardRow + 1, boardCol));
		
		// Initialize frame
		setTitle("Tic-Tac-Toe");
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		// Initialize game variables
		gameBoard = new JButton[boardRow][boardCol];
		currPlayer = "X";
		turnCount = 1;
		hasWinner = false;
		
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
				
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// If empty space chosen by player
						if (((JButton) e.getSource()).getText().equals("")) {
							// Set chosen space to player's mark
							button.setText(currPlayer);
							// Check for a winner
							hasWinner();
							
							// If winner was found, reset
							// Else, continue to next turn
							if (hasWinner) {
								resetGame();
							}
							else {
								turnCount++;
								playerToggle();
							}
						}
						
					}
				});
				
				// Add button to pane
				pane.add(button);
			}
		}
	}
	
	// Change players
	private void playerToggle() {
		
		// Swap current player from X > O, or vice versa
		if (currPlayer.equals("X")) {
			currPlayer = "O";
		} else {
			currPlayer = "X";
		}
	}
	
	// Scan board for winner or tie
	// If winner/tie end game
	// Else return to game
	private void hasWinner() {
		
		// Row Win
		for (int i = 0; i < boardRow; i++) {
			if (gameBoard[i][0].getText().contentEquals(currPlayer) &&
				gameBoard[i][1].getText().contentEquals(currPlayer) &&
				gameBoard[i][2].getText().contentEquals(currPlayer))
			{
				hasWinner = true;
				JOptionPane.showMessageDialog(this, "Player " + currPlayer + " Wins!\n\nNew Game?");
				return;
			}
		}
		
		// Collumn Win
		for (int j = 0; j < boardCol; j++) {
			if (gameBoard[0][j].getText().contentEquals(currPlayer) &&
				gameBoard[1][j].getText().contentEquals(currPlayer) &&
				gameBoard[2][j].getText().contentEquals(currPlayer))
			{
				hasWinner = true;
				JOptionPane.showMessageDialog(this, "Player " + currPlayer + " Wins!\n\nNew Game?");
				return;
			}
		}
		
		// Diagonal Top-Left Win
		if(	gameBoard[0][0].getText().contentEquals(currPlayer) &&
			gameBoard[1][1].getText().contentEquals(currPlayer) &&
			gameBoard[2][2].getText().contentEquals(currPlayer))
		{
			hasWinner = true;
			JOptionPane.showMessageDialog(this, "Player " + currPlayer + " Wins!\n\nNew Game?");
			return;
		}
		
		// Diagonal Top-Right Win
		else if(gameBoard[0][2].getText().contentEquals(currPlayer) &&
				gameBoard[1][1].getText().contentEquals(currPlayer) &&
				gameBoard[2][0].getText().contentEquals(currPlayer))
		{
			hasWinner = true;
			JOptionPane.showMessageDialog(this, "Player " + currPlayer + " Wins!\n\nNew Game?");
			return;
		}
		
		// Tie Game
		else if (turnCount == (boardRow * boardCol)) {
			hasWinner = true;
			JOptionPane.showMessageDialog(this, "It's a tie game!\n\nNew Game?");
			return;
		}
	}
	
	// Resets variables to default and clears board
	private void resetGame() {
		currPlayer = "X";
		hasWinner = false;
		turnCount = 1;
		
		for(int i = 0; i < boardRow; i++) {
			for(int j = 0; j < boardCol; j++) {
				gameBoard[i][j].setText("");
			}
		}
	}

}
