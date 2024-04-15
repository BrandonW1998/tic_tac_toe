import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DisplayGame extends JFrame{
	private static final long serialVersionUID = 1L;
	private Container pane;	// Contains board
	private JButton[][] gameBoard;	// 2D Array of buttons
	private Integer clockVal; // Value displayed on clock
	private Timer timer; // Tracks time for clock
	private JLabel turnLabel; // Displays player turn
	private JLabel clockLabel;	// Displays label of clock
	private JLabel clock; // Displays clock
	private String currPlayer;	// Tracks current player
	private int turnCount;	// Tracks turns
	private boolean hasWinner;	// Flag set when winner found
	private static final int boardRow = 3;	// Board size
	private static final int boardCol = 3;	// Board size
	private static final int initClockVal = 5;
	
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
		clockVal = initClockVal;
		turnCount = 1;
		hasWinner = false;
		
		// Create/Display board
		initBoard();
		
	}
	
	// Initialze board, add to pane
	private void initBoard() {
		// Initialize timer on 1000ms interval
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Updates current time
				clockVal--;
				// Else Update clock with new time
				clock.setText(clockVal.toString());
				pane.remove(clock);
				pane.add(clock);
				// If timer hits 0, end game and stop timer
				if (clockVal == 0) {
					playerToggle();
					hasWinner = true;
					timer.stop();
					JOptionPane.showMessageDialog(pane, "The timer has run out!\n\nNew Game?");
					resetGame();
				}
			}
		});
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
								clockVal = initClockVal + 1;
								turnLabel.setText(currPlayer + "'s turn");
								pane.remove(turnLabel);
								pane.add(turnLabel, 9);
								
							}
						}
						
					}
				});
				
				// Add button to pane
				pane.add(button);
			}
		}
		
		// Initialize turn tracker
		turnLabel = new JLabel(currPlayer + "'s turn");
		turnLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		pane.add(turnLabel);
		
		// Initialize timer
		clockLabel = new JLabel("Time:");
		clockLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 70));
		clock = new JLabel(clockVal.toString());
		clock.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
		
		timer.start();
		pane.add(clockLabel);
		pane.add(clock);
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
				timer.stop();
				hasWinner = true;
				JOptionPane.showMessageDialog(pane, "Player " + currPlayer + " Wins!\n\nNew Game?");
				return;
			}
		}
		
		// Collumn Win
		for (int j = 0; j < boardCol; j++) {
			if (gameBoard[0][j].getText().contentEquals(currPlayer) &&
				gameBoard[1][j].getText().contentEquals(currPlayer) &&
				gameBoard[2][j].getText().contentEquals(currPlayer))
			{
				timer.stop();
				hasWinner = true;
				JOptionPane.showMessageDialog(pane, "Player " + currPlayer + " Wins!\n\nNew Game?");
				return;
			}
		}
		
		// Diagonal Top-Left Win
		if(	gameBoard[0][0].getText().contentEquals(currPlayer) &&
			gameBoard[1][1].getText().contentEquals(currPlayer) &&
			gameBoard[2][2].getText().contentEquals(currPlayer))
		{
			timer.stop();
			hasWinner = true;
			JOptionPane.showMessageDialog(pane, "Player " + currPlayer + " Wins!\n\nNew Game?");
			return;
		}
		
		// Diagonal Top-Right Win
		else if(gameBoard[0][2].getText().contentEquals(currPlayer) &&
				gameBoard[1][1].getText().contentEquals(currPlayer) &&
				gameBoard[2][0].getText().contentEquals(currPlayer))
		{
			timer.stop();
			hasWinner = true;
			JOptionPane.showMessageDialog(pane, "Player " + currPlayer + " Wins!\n\nNew Game?");
			return;
		}
		
		// Tie Game
		else if (turnCount == (boardRow * boardCol)) {
			timer.stop();
			hasWinner = true;
			JOptionPane.showMessageDialog(pane, "It's a tie game!\n\nNew Game?");
			return;
		}
	}
	
	// Resets variables to default and clears board
	private void resetGame() {
		currPlayer = "X";
		hasWinner = false;
		turnCount = 1;
		clockVal = initClockVal + 1;
		turnLabel.setText(currPlayer + "'s turn");
		
		timer.stop();
		timer.start();
		
		for(int i = 0; i < boardRow; i++) {
			for(int j = 0; j < boardCol; j++) {
				gameBoard[i][j].setText("");
			}
		}
	}

}
