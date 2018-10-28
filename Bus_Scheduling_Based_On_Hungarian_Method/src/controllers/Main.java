package controllers;

import javafx.application.Application;

import application.SceneManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public  class Main extends Application{
	public void start(Stage primaryStage) throws Exception {
		try {
			Scene scene = new Scene(new StackPane());
			primaryStage.initStyle(StageStyle.DECORATED);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			SceneManager manager = new SceneManager(scene);
			manager.showLoginAndRegistrationView();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
