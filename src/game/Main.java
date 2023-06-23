package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage; // the window that holds the contents
import javafx.scene.Scene; // the contents
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Main extends Application {
	// constants
	public static final int APP_WIDTH = 500;
	public static final int APP_HEIGHT = 500;
	public static final int TOP_HEIGHT = 100;
	public static final int TILE_BOARD_HEIGHT = 400;
	
	TopContent top;
	Board board;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		Scene scene = new Scene(mainPane, APP_WIDTH, APP_HEIGHT);
		
		initalizeTopLayout(mainPane);
		initalizeBoardLayout(mainPane);

		primaryStage.setTitle("Tic Tac Toe");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void initalizeTopLayout(BorderPane mainPane) {
		top = new TopContent();
		mainPane.getChildren().add(top.getStackPane());
		
	}
	
	private void initalizeBoardLayout(BorderPane mainPane) {
		board = new Board();
		mainPane.getChildren().add(board.getStackPane());
	}
}
