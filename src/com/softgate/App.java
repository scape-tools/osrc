package com.softgate;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public final class App extends Application {

	private static final Properties properties = new Properties();

	public static int version = 155;
	
	@Override
	public void init() {
		try(FileReader reader = new FileReader(new File("version.properties"))) {
			properties.load(reader);
			version = Integer.parseInt(properties.getProperty("version"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage stage) {		
		try {
			StackPane root = FXMLLoader.load(App.class.getResource("/Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(App.class.getResource("/style.css").toExternalForm());
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
