package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage; // the window that holds the contents
import javafx.scene.Scene; // the contents
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Main extends Application {
	Button button;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("Tic Tac Toe");

		button = new Button();
		button.setText("Click Me!");

		StackPane layout = new StackPane();
		layout.getChildren().add(button);

		Scene scene = new Scene(layout, 300, 250);
		arg0.setScene(scene);
		arg0.show();
	}
}
