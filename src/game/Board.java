package game;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

// will represent the Tic-Tac-Toe Board
public class Board {
	private String[][] board;
	private StackPane pane;
	
	private Label test = new Label("This is a test message");
	
	public Board() {
		board = new String[3][3];
		
		pane = new StackPane();
		pane.setMinSize(Constants.APP_WIDTH, Constants.BOARD_HEIGHT);
		pane.setTranslateX(Constants.APP_WIDTH / 2);
		pane.setTranslateY((Constants.BOARD_HEIGHT / 2) + Constants.TOP_HEIGHT);
		
		test.setMinSize(Constants.APP_WIDTH, Constants.BOARD_HEIGHT);
		pane.getChildren().add(test);
	}
	
	public StackPane getStackPane() {
		return pane;
	}
}
