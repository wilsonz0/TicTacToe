package game;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

// will represent the Tic-Tac-Toe Board
public class Board {
	private Tile[][] board = new Tile[3][3];
	private StackPane pane;
	private boolean turn = true;	// True: Player 1, False: Player 2
	private int numOfMoves = 1;
	private boolean gameStatus = false;
	
	public Board() {
		pane = new StackPane();
		pane.setMinSize(Constants.APP_WIDTH, Constants.BOARD_HEIGHT);
		pane.setTranslateX(Constants.APP_WIDTH / 2);
		pane.setTranslateY((Constants.BOARD_HEIGHT / 2) + Constants.TOP_HEIGHT);
		
		addAllTiles();
	}
	
	// helper method: initialize all the tiles into the the board array 
	private void addAllTiles() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				Tile tile = new Tile();
				tile.getStackPane().setTranslateX((col * Constants.tileWidth) - Constants.tileWidth);
				tile.getStackPane().setTranslateY((row * Constants.tileHeight) - Constants.tileHeight);
				pane.getChildren().add(tile.getStackPane());
				
				board[row][col] = tile;
			}
		}
	}
	
	// method: checks for the 3 win conditions of the game	
	public boolean checkForWin() {
		return checkRows() || checkCols() || checkDiagonals();
	}
	
	// helper method: check for the win condition for all the rows	
	private boolean checkRows() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0].getMove().equals(board[i][1].getMove())
					&& board[i][0].getMove().equals(board[i][2].getMove()) 
					&& !board[i][0].getMove().isEmpty() ) {
				System.out.println("someone won on the ROWS!");
				return true;
			}
		}
		return false;
	}

	// helper method: check for the win condition for all the columns
	private boolean checkCols() {
		for (int i = 0; i < 3; i++) {
			if (board[0][i].getMove().equals(board[1][i].getMove())
					&& board[0][i].getMove().equals(board[2][i].getMove()) 
					&& !board[0][i].getMove().isEmpty() ) {
				System.out.println("someone won on the COLUMNS!");
				return true;
			}
		}
		return false;
	}
	
	// helper method: check for the win condition for the only 2 diagonals
	private boolean checkDiagonals() {
		// Top Left to Bottom Right Diagonal		
		if (board[0][0].getMove().equals(board[1][1].getMove())
				&& board[0][0].getMove().equals(board[2][2].getMove()) 
				&& !board[0][0].getMove().isEmpty() ) {
			System.out.println("someone won on the DIAGONALS of TL -> BR!");
			return true;
		}
		
		// Top Right to Bottom Left Diagonal
		if (board[0][2].getMove().equals(board[1][1].getMove())
				&& board[0][2].getMove().equals(board[2][0].getMove()) 
				&& !board[0][2].getMove().isEmpty() ) {
			System.out.println("someone won on the DIAGONALS of TR -> BL!");
			return true;
		}
		return false;
	}
	
	private boolean checkForTie() {
		if (numOfMoves == 9) {
			System.out.println("TIE"); 
			return true;
		}
		System.out.println(numOfMoves); 
		
		numOfMoves++;
		return false;
	}
	
	public StackPane getStackPane() {
		return pane;
	}
	
	public void startGame() {
		gameStatus = true;
	}
	
	public boolean getGameStatus() {
		return gameStatus;
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
				if (move.getText().isEmpty() && gameStatus) {
					if (turn) {
						move.setText("X"); 
					}
					else {
						move.setText("O");
					}
					turn = !turn;
					
					if (checkForTie() && checkForWin()) gameStatus = false;
				}
			});
		}
		
		public StackPane getStackPane() {
			return pane;
		}
		
		public String getMove() {
			return move.getText();
		}
	}
}
