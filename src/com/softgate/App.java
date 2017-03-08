package com.softgate;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;


public final class App extends Application {
	
	@Override
	public void init() {

	}
	
	@Override
	public void start(Stage stage) {		
		try {
			StackPane root = FXMLLoader.load(App.class.getResource("/com/softgate/Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(App.class.getResource("style.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("OSRS Revision Checker");
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {		
		launch(args);
	}
}
