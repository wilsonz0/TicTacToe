package game;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// will represent the Tic-Tac-Toe Board
public class Board {
	private Tile[][] board = new Tile[3][3];;
	private StackPane pane;
	private boolean turn = true;	// True: Player 1, False: Player 2 
	
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
	
	public StackPane getStackPane() {
		return pane;
	}
	
	/*
	 * Defines a Tile class which describes the individual boxes in a tic-tac-toe board  
	 */
	private class Tile {
		Label move;
		private StackPane pane;
		
		public Tile() {			
			pane = new StackPane();
			pane.setMinSize(Constants.tileWidth, Constants.tileHeight);
			
			Rectangle border = new Rectangle(Constants.tileWidth, Constants.tileHeight);
			border.setFill(Color.TRANSPARENT);
			border.setStroke(Color.BLACK);
			pane.getChildren().add(border);
			
			move = new Label("");
			pane.getChildren().add(move);
			
			pane.setOnMouseClicked(e -> {
				if (turn) {
					move.setText("X"); 
				}
				else {
					move.setText("O");
				}
				turn = !turn;
			});
		}
		
		public StackPane getStackPane() {
			return pane;
		}
	}
}
