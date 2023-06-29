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
	
	TopContent top;
	Board board;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		Scene scene = new Scene(mainPane, Constants.APP_WIDTH, Constants.APP_HEIGHT);
		
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
