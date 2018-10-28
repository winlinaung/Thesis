package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import java.util.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import application.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class welcomeController implements Initializable{

	SceneManager manager;
	public void Exit() {
		System.exit(0);
	}
	@FXML
	public void AlgorithmHistory() {
		manager.showAlgorithmHistoryView();
	}
	@FXML
	public void Calculation(ActionEvent event) throws IOException{

		manager.showModelView();
	}
	@FXML
	public void BusDeperatureAndArrivalTime() {
		manager.showBusDeperatureAndArrivalTimeView();
	}
	@FXML
	public void DirectionofSoftware() {
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public void initData(SceneManager sceneManager) {
		// TODO Auto-generated method stub
		this.manager = sceneManager;
	}

}

