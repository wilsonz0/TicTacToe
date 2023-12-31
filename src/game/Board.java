package game;

import java.util.ArrayList;

import bot.GameBot;
//import javafx.util.Pair;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Pair;

// will represent the Tic-Tac-Toe Board
public class Board {
	private TopContent topContent;
	private StackPane pane;
	private Line winLine;
	
	private Tile[][] tileBoard = new Tile[3][3];
	private char turn;
	private boolean gameStatus = false;		// true: game is active, false: game is stopped
	
	GameBot bot;
	private boolean isBotActive = false;
	
	public Board(TopContent topContent) {
		this.topContent = topContent;
		bot = new GameBot(this);
		
		// initialize the main board pane  
		pane = new StackPane();
		pane.setMinSize(Constants.APP_WIDTH, Constants.BOARD_HEIGHT);
		pane.setTranslateX(Constants.APP_WIDTH / 2);
		pane.setTranslateY((Constants.BOARD_HEIGHT / 2) + Constants.TOP_HEIGHT);
		
		addAllTiles();
		
		// initialize the win line and hide it
		winLine = new Line();
		winLine.setStrokeWidth(7);
		winLine.setStroke(Color.RED);
		pane.getChildren().add(winLine);
		winLine.setVisible(false);
	}
	
	public Board(Board board) {
		for (int row = 0; row < 3; row++) {
	        for (int col = 0; col < 3; col++) {
	            this.tileBoard[row][col] = new Tile();
	            this.tileBoard[row][col].setMove(board.tileBoard[row][col].getMove());
	        }
	    }
		this.turn = board.turn;
		this.gameStatus = board.gameStatus;
	}
	
	// helper method: initialize all the tiles into the the tileBoard array 
	private void addAllTiles() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				Tile tile = new Tile();
				tile.getStackPane().setTranslateX((col * Constants.tileWidth) - Constants.tileWidth);
				tile.getStackPane().setTranslateY((row * Constants.tileHeight) - Constants.tileHeight);
				pane.getChildren().add(tile.getStackPane());
				
				tileBoard[row][col] = tile;
			}
		}
	}
	
	// main method: if all the position is NOT empty then there is a tie
	public boolean checkForTie() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (tileBoard[row][col].getMove().isEmpty()) return false;
			}
		}
		
		return true;
	}
	
	// main method: checks for the 3 win conditions of the game	
	public boolean checkForWin() {
		if (checkRows() || checkCols() || checkDiagonals()) {
			return true;
		}
		return false;
	}
	
	// helper method: check for the win condition for all the rows	
	private boolean checkRows() {
		for (int i = 0; i < 3; i++) {
			if (tileBoard[i][0].getMove().equals(tileBoard[i][1].getMove())
					&& tileBoard[i][0].getMove().equals(tileBoard[i][2].getMove()) 
					&& !tileBoard[i][0].getMove().isEmpty() ) {
//				System.out.println("someone won on the ROWS!");
				drawWinLine(tileBoard[i][0], tileBoard[i][1], tileBoard[i][2]);
				return true;
			}
		}
		return false;
	}

	// helper method: check for the win condition for all the columns
	private boolean checkCols() {
		for (int i = 0; i < 3; i++) {
			if (tileBoard[0][i].getMove().equals(tileBoard[1][i].getMove())
					&& tileBoard[0][i].getMove().equals(tileBoard[2][i].getMove()) 
					&& !tileBoard[0][i].getMove().isEmpty() ) {
//				System.out.println("someone won on the COLUMNS!");
				drawWinLine(tileBoard[0][i], tileBoard[1][i], tileBoard[2][i]);
				return true;
			}
		}
		return false;
	}
	
	// helper method: check for the win condition for the only 2 diagonals
	private boolean checkDiagonals() {
		// Top Left to Bottom Right Diagonal		
		if (tileBoard[0][0].getMove().equals(tileBoard[1][1].getMove())
				&& tileBoard[0][0].getMove().equals(tileBoard[2][2].getMove()) 
				&& !tileBoard[0][0].getMove().isEmpty() ) {
//			System.out.println("someone won on the DIAGONALS of TL -> BR!");
			drawWinLine(tileBoard[0][0], tileBoard[1][1], tileBoard[2][2]);
			return true;
		}
		
		// Top Right to Bottom Left Diagonal
		if (tileBoard[0][2].getMove().equals(tileBoard[1][1].getMove())
				&& tileBoard[0][2].getMove().equals(tileBoard[2][0].getMove()) 
				&& !tileBoard[0][2].getMove().isEmpty() ) {
//			System.out.println("someone won on the DIAGONALS of TR -> BL!");
			drawWinLine(tileBoard[0][2], tileBoard[1][1], tileBoard[2][0]);
			return true;
		}
		return false;
	}
	
	// main method: after one of the player wins, display a line on the winning 3 tiles 
	public void drawWinLine(Tile first, Tile second, Tile third) {
		if (winLine != null) {
			winLine.setVisible(true);
			winLine.setStartX(first.getStackPane().getTranslateX());
			winLine.setStartY(first.getStackPane().getTranslateY());
			winLine.setEndX(third.getStackPane().getTranslateX());
			winLine.setEndY(third.getStackPane().getTranslateY());
			winLine.setTranslateX(second.getStackPane().getTranslateX());
			winLine.setTranslateY(second.getStackPane().getTranslateY());
		}
	}
	
	// main method: allows the game to start and restart the singleplayer game mode
	public void startSingleGame() {
		gameStatus = true;
		isBotActive = true;
		turn  = 'X';
		topContent.setTitle("Player X's turn");
		topContent.setButtonVisibility(false);
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				tileBoard[row][col].setMove("");
			}
		}
		
		winLine.setVisible(false);
	}
	
	// main method: allows the game to start and restart the two-player game mode
	public void startDoubleGame() {
		gameStatus = true;
		turn  = 'X';
		topContent.setTitle("Player X's turn");
		topContent.setButtonVisibility(false);
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				tileBoard[row][col].setMove("");
			}
		}
		
		winLine.setVisible(false);
	}
	
	// main method: will end the game and reset some variables
	public void endGame() {
		printBoard();
		
		gameStatus = false;
		isBotActive = false;
		topContent.setButtonVisibility(true);
		if (checkForWin()) {
			topContent.setTitle("Player " + turn + " wins!");
		}
		else {
			topContent.setTitle("A TIE!");
		}
	}
	
	/*
	 * A series of setters
	 */
	public void setMoveOnCoord(int row, int col, String s) {
		tileBoard[row][col].setMove(s);
	}
	
	/*
	 * A series of getters
	 */
	public boolean getGameStatus() {
		return gameStatus;
	}
	
	public char getTurn() {
		return turn;
	}
	
	public StackPane getStackPane() {
		return pane;
	}
	
	public ArrayList<int[]> getAllPossibleActions(String move) {
		ArrayList<int[]> result = new ArrayList<int[]>();
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (tileBoard[row][col].getMove().equals("")) {
					result.add(new int[] {row, col});
				}
			}
		}
		
		return result;
	}
	
	/*
	 * DEBUGGING PURPOSE
	 */
	public void printBoard() {
		for (int row = 0; row < 3; row++) {
			System.out.print("[");
			for (int col = 0; col < 3; col++) {
				System.out.print( tileBoard[row][col].getMove() );
				if (tileBoard[row][col].getMove().isEmpty()) System.out.print("-");
				
				if (col < 2) System.out.print( ", " );
			}
			System.out.println("]");
		}
	}
	
	/*
	 * Defines a Tile class which describes the individual boxes in a tic-tac-toe board  
	 */
	private class Tile {
		private Label move;
		private StackPane pane;
		
		public Tile() {			
			pane = new StackPane();
			pane.setMinSize(Constants.tileWidth, Constants.tileHeight);
			
			Rectangle border = new Rectangle(Constants.tileWidth, Constants.tileHeight);
			border.setFill(Color.TRANSPARENT);
			border.setStroke(Color.BLACK);
			pane.getChildren().add(border);
			
			move = new Label("");
			move.setFont(Font.font(50));
			pane.getChildren().add(move);
				
			pane.setOnMouseClicked(e -> {
				if (isBotActive) {
					singlePlayerAction();
				}
				else {
					twoPlayerAction();
				}
			});
		}
		
		/*
		 *  when triggered the method will set the position of the X that the player chose
		 *  and clicked. Then, the bot method will be called to find the next bot move based
		 *  on the implemented algorithm.
		 */
		
		private void singlePlayerAction() {
			System.out.println("singlePlayerAction() Ran");
			if (move.getText().isEmpty() && gameStatus) {
				if (turn == 'X') {
					move.setText("X"); 
					topContent.setTitle("Player O's turn");

					if (pane != null && (checkForWin() || checkForTie()) ) {
						endGame();
					}
					else {
						Pair<int[], Integer> nextMove = bot.getNextMove('O');
						setMoveOnCoord(nextMove.getKey()[0], nextMove.getKey()[1], "O");
						topContent.setTitle("Player X's turn");
						System.out.println("coord X: " + nextMove.getKey()[0]);
						System.out.println("coord Y: " + nextMove.getKey()[1]);
						System.out.println("value: " + nextMove.getValue());
					}
				}
			}
		}
		
		/*
		 * when triggered, the method will place the X or O on the position that the player
		 * chose and clicked.
		 */
		private void twoPlayerAction() {
			System.out.println("twoPlayerAction() Ran");
			if (move.getText().isEmpty() && gameStatus) {
				if (turn == 'X') {
					move.setText("X"); 
					topContent.setTitle("Player O's turn");
				}
				else {
					move.setText("O");
					topContent.setTitle("Player X's turn");
				}
				
				if (checkForWin() || checkForTie() ) endGame();
				
				turn = turn == 'X' ? 'O' : 'X';
			}
		}
		
		public StackPane getStackPane() {
			return pane;
		}
		
		public void setMove(String s) {
			move.setText(s);
		}
		
		public String getMove() {
			return move.getText();
		}
	}
}
