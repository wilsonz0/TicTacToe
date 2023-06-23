package game;

import javafx.scene.layout.StackPane;

// will represent the Tic-Tac-Toe Board
public class Board {
	private StackPane pane;
	private String[][] board = new String[3][3];
	
	public Board() {
		pane = new StackPane();
	}
	
	public StackPane getStackPane() {
		return pane;
	}
}
