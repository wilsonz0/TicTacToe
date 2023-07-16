package game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

// will be the content above the board
public class TopContent {
	private StackPane pane;
	private Button singlePlayer;
	private Button twoPlayer;
	
	private Label title;
	
	public TopContent() {
		pane = new StackPane();
		pane.setMinSize(Constants.APP_WIDTH, Constants.TOP_HEIGHT);
		pane.setTranslateX(Constants.APP_WIDTH / 2);
		pane.setTranslateY(Constants.TOP_HEIGHT / 2);
		
		title = new Label("Tic-Tac-Toe");
		title.setMinSize(Constants.APP_WIDTH, Constants.TOP_HEIGHT);
		title.setTranslateY(Constants.TOP_HEIGHT / 2 - 70);
		title.setAlignment(Pos.CENTER);
		title.setFont(Font.font(25));
		pane.getChildren().add(title);
		
		singlePlayer = new Button("Single Player");
		singlePlayer.setMinSize(100, 30);
		singlePlayer.setTranslateX(-60);
		singlePlayer.setTranslateY(30);
		pane.getChildren().add(singlePlayer);
		
		twoPlayer = new Button("Two Player");
		twoPlayer.setMinSize(100, 30);
		twoPlayer.setTranslateX(60);
		twoPlayer.setTranslateY(30);
		pane.getChildren().add(twoPlayer);
	}
	
	public StackPane getStackPane() {
		return pane;
	}
	
	public void setTitle(String str) {
		title.setText(str);
	}
	
	public void setButtonVisibility(boolean bool) {
		singlePlayer.setVisible(bool);
		twoPlayer.setVisible(bool);
	}
	
	public void setStartButton(Board board) {
		singlePlayer.setOnAction(e -> {
			if (!board.getGameStatus()) board.startSingleGame();
		});
		
		twoPlayer.setOnAction(e -> {
			if (!board.getGameStatus()) board.startDoubleGame();
		});
	}
}
