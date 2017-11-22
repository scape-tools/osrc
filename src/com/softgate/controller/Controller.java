package com.softgate.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import com.softgate.App;
import com.softgate.net.CacheRequester;

import javafx.animation.PauseTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Controller implements Initializable {
	
	@FXML
	private Button requestBtn;
	
	@FXML
	private Text statusText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}
	
	@FXML
	private void requestRevision() {
		CacheRequester requester = new CacheRequester();

		createTask(new Task<Boolean>() {

			@Override
			protected Boolean call() throws Exception {
				
				updateMessage("Connecting...");
				
		        requester.connect("oldschool" + 1 + ".runescape.com", App.version);

		        while (requester.getState() != CacheRequester.State.CONNECTED) {
		        	
		        	if (requester.getState() == CacheRequester.State.OUTDATED) {
		        		updateMessage(String.format("Requesting=%d", requester.getRevision()));
		        	}
		        	
		            requester.process();
		        }

		        if (requester.getRevision() > App.version) {
		        	App.version = requester.getRevision();
		        	try(PrintWriter writer = new PrintWriter(new FileWriter(new File("./version.properties")))) {
		        		writer.println(String.format("version=%d", requester.getRevision()));
					}
				}

		        updateMessage(String.format("Success!\nRevision=%d", requester.getRevision()));
				return true;
			}
			
		});

	}
	
	private void createTask(Task<?> task) {

		requestBtn.setVisible(false);
		statusText.textProperty().unbind();
		statusText.textProperty().bind(task.messageProperty());

		new Thread(task).start();

		task.setOnSucceeded(e -> {

			PauseTransition pause = new PauseTransition(Duration.seconds(6));

			pause.setOnFinished(event -> {
				statusText.textProperty().unbind();
				statusText.setText("");
				requestBtn.setVisible(true);				
			});

			pause.play();
		});

		task.setOnFailed(e -> {

			PauseTransition pause = new PauseTransition(Duration.seconds(6));

			pause.setOnFinished(event -> {
				statusText.textProperty().unbind();
				statusText.setText("");
				requestBtn.setVisible(true);
			});

			pause.play();

		});
	}

}
