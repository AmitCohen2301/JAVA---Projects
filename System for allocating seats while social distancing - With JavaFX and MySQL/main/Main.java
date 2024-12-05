package main;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	// Start
	@Override
	public void start(Stage stage) throws Exception {
		view.View view = new view.View(stage);
		new Controller(view);
		
		view.showView();
	}
}