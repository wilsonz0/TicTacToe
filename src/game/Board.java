package game;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

// will represent the Tic-Tac-Toe Board
public class Board {
	private TopContent topContent;
	private Tile[][] board = new Tile[3][3];
	private StackPane pane;
	private char turn = 'X';
	private int numOfMoves = 1;
	private boolean gameStatus = false;
	private Line winLine; 
	
	public Board(TopContent topContent) {
		this.topContent = topContent;
		
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
	
	// main method: after 9 moves, there will be a tie iff there are no wins (handled in Tile class)
	private boolean checkForTie() {
		if (numOfMoves == 9) {
			topContent.setTitle("A TIE!");
			return true;
		}
		System.out.println(numOfMoves); 
		
		numOfMoves++;
		return false;
	}
	
	// main method: checks for the 3 win conditions of the game	
	public boolean checkForWin() {
		if (checkRows() || checkCols() || checkDiagonals()) {
			topContent.setTitle("Player " + turn + " wins!");
			return true;
		}
		return false;
	}
	
	// helper method: check for the win condition for all the rows	
	private boolean checkRows() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0].getMove().equals(board[i][1].getMove())
					&& board[i][0].getMove().equals(board[i][2].getMove()) 
					&& !board[i][0].getMove().isEmpty() ) {
				System.out.println("someone won on the ROWS!");
				drawWinLine(board[i][0], board[i][1], board[i][2]);
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
				drawWinLine(board[0][i], board[1][i], board[2][i]);
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
			drawWinLine(board[0][0], board[1][1], board[2][2]);
			return true;
		}
		
		// Top Right to Bottom Left Diagonal
		if (board[0][2].getMove().equals(board[1][1].getMove())
				&& board[0][2].getMove().equals(board[2][0].getMove()) 
				&& !board[0][2].getMove().isEmpty() ) {
			System.out.println("someone won on the DIAGONALS of TR -> BL!");
			drawWinLine(board[0][2], board[1][1], board[2][0]);
			return true;
		}
		return false;
	}
	
	// main method: after one of the player wins, display a line on the winning 3 tiles 
	public void drawWinLine(Tile first, Tile second, Tile third) {
		winLine.setVisible(true);
		winLine.setStartX(first.getStackPane().getTranslateX());
		winLine.setStartY(first.getStackPane().getTranslateY());
		winLine.setEndX(third.getStackPane().getTranslateX());
		winLine.setEndY(third.getStackPane().getTranslateY());
		winLine.setTranslateX(second.getStackPane().getTranslateX());
		winLine.setTranslateY(second.getStackPane().getTranslateY());
		
	}
	
	public void startGame() {
		gameStatus = true;
		topContent.setButtonVisibility(false);
		topContent.setTitle("Player X's turn");
	}
	
	public boolean getGameStatus() {
		return gameStatus;
	}
	
	public StackPane getStackPane() {
		return pane;
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
					if (turn == 'X') {
						move.setText("X"); 
						
						topContent.setTitle("Player O's turn");
					}
					else {
						move.setText("O");
						
						topContent.setTitle("Player X's turn");
					}
					
					if (checkForWin() || checkForTie()) gameStatus = false;
					
					turn = turn == 'X' ? 'O' : 'X';
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
