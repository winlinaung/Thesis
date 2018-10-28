package controllers;

import application.SceneManager;
import javafx.fxml.FXML;

public class AlgorithmHistoryController {
@FXML
public SceneManager manager;
public void HomeView() {
	manager.showWelcomeView();
}

public void initData(SceneManager sceneManager) {
	// TODO Auto-generated method stub
	this.manager = sceneManager;
}
}
