package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.SceneManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ModelController implements Initializable{
	SceneManager manager;
	@FXML 
	javafx.scene.control.TableView<DataBeam3> TableView;
	@FXML 
	TableColumn<DataBeam3,Double> TripNo;
	@FXML 
	TableColumn<DataBeam3,Double> TripNo7;
	@FXML 
	TableColumn<DataBeam3,Double> TripNo8;
	@FXML 
	TableColumn<DataBeam3,Double> TripNo9;
	@FXML 
	TableColumn<DataBeam3, Double> TripNo10;
	@FXML 
	TableColumn<DataBeam3,Double> TripNo11;
	@FXML 
	TableColumn<DataBeam3,Double> TripNo12;

	public void NextCalculation(ActionEvent event) throws IOException {

		manager.showInputView();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TripNo.setCellValueFactory(new PropertyValueFactory<DataBeam3,Double>("TripNo"));
		TripNo7.setCellValueFactory(new PropertyValueFactory<DataBeam3,Double>("TripNo7"));
		TripNo8.setCellValueFactory(new PropertyValueFactory<DataBeam3,Double>("TripNo8"));
		TripNo9.setCellValueFactory(new PropertyValueFactory<DataBeam3,Double>("TripNo9"));
		TripNo10.setCellValueFactory(new PropertyValueFactory<DataBeam3,Double>("TripNo10"));
		TripNo11.setCellValueFactory(new PropertyValueFactory<DataBeam3,Double>("TripNo11"));
		TripNo12.setCellValueFactory(new PropertyValueFactory<DataBeam3,Double>("TripNo12"));
		TableView.getItems().addAll(new DataBeam3(1,5.5,5.5,6,7,15.5,14.5),
				new DataBeam3(2,7,28,27.5,5.5,15,16),
				new DataBeam3(3,7.5,27.5,28,27,14.5,15.5),
				new DataBeam3(4,15,14,13.5,12.5,27,6),
				new DataBeam3(5,13,16,15.5,14.5,27,28),
				new DataBeam3(6,12.5,15.5,16,15,5.5,27.5));
	}
	public void initData(SceneManager sceneManager) {
		// TODO Auto-generated method stub
		this.manager = sceneManager;
	}



}
