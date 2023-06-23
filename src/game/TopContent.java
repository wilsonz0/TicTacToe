package game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

// will be the content above the board
public class TopContent {
	private StackPane pane;
	private Button startGame;
	
	public TopContent() {
		pane = new StackPane();
	}
	
	public StackPane getStackPane() {
		return pane;
	}
}
