package game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

// will be the content above the board
public class TopContent {
	private StackPane pane;
	private Button startGame;
	
	private Label title;
	
	public TopContent() {
		pane = new StackPane();
		pane.setMinSize(Constants.APP_WIDTH, Constants.TOP_HEIGHT);
		pane.setTranslateX(Constants.APP_WIDTH / 2);
		pane.setTranslateY(Constants.TOP_HEIGHT / 2);
		
		title = new Label("Tic-Tac-Toe");
		title.setMinSize(Constants.APP_WIDTH, Constants.TOP_HEIGHT);
		pane.getChildren().add(title);
		
		startGame = new Button("Start");
		startGame.setMinSize(100, 30);
		pane.getChildren().add(startGame);
	}
	
	public StackPane getStackPane() {
		return pane;
	}
}
