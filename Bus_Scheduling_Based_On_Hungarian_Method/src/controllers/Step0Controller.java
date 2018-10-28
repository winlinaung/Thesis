package controllers;


import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import application.SceneManager;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class Step0Controller implements Initializable {
	@FXML
	Label City1;
	@FXML
	Label City2;
	@FXML
	SceneManager manager;
	@FXML
	TableColumn<DataBeam4, Integer> TableColTripNo1;
	@FXML
	TableColumn<DataBeam4, String> TableColDeperatureTime1;
	@FXML
	TableColumn<DataBeam4, String> TableColArrivalTime1;
	@FXML
	TableColumn<DataBeam4, Integer> TableColTripNo2;
	@FXML
	TableColumn<DataBeam4, String> TableColDeperatureTime2;
	@FXML
	TableColumn<DataBeam4, String> TableColArrivalTime2;
	@FXML
	TableView<DataBeam4> TableView1;
	@FXML
	TableView<DataBeam4> TableView2;
	@FXML
	TableView<String[]> TableView3;
	@FXML
	TableView<String[]> TableView4;
	@FXML
	TableView<String[]> TableView5;
	@FXML
	Label labelCityOne;
	@FXML
	Label labelCityTwo;
	@FXML
	Label labelCityThree;
	@FXML
	Label labelCityFour;
	ObservableList<DataBeam1> data;
	JButton btnOk = new JButton("Ok");
	public void StartStep0(ActionEvent event) throws IOException, ParseException {

	}
//	  for (int i=0; i<h1.array1.length; i++) 
//	  { 
//		  
//		   TableView2.getItems().addAll(new DataBeam4(i+1,data.get(i).getTextFieldBusDeperatureTime1(),data.get(i).getTextFieldBusDeperatureTime1()));
//		  
//	  }
//	TableView2.getItems().addAll(new DataBeam3(1,5.5,5.5,6,7,15.5,14.5),
//			new DataBeam3(2,7,28,27.5,5.5,15,16),
//			new DataBeam3(3,7.5,27.5,28,27,14.5,15.5),
//			new DataBeam3(4,15,14,13.5,12.5,27,6),
//			new DataBeam3(5,13,16,15.5,14.5,27,28),
//			new DataBeam3(6,12.5,15.5,16,15,5.5,27.5));

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(btnOk,"It is Step 0");
		TableColTripNo1.setCellValueFactory(new PropertyValueFactory<DataBeam4, Integer>("TripNo"));
		TableColDeperatureTime1.setCellValueFactory(new PropertyValueFactory<DataBeam4, String>("DeperatureTime"));
		TableColArrivalTime1.setCellValueFactory(new PropertyValueFactory<DataBeam4, String>("ArrivalTime"));
		TableColTripNo2.setCellValueFactory(new PropertyValueFactory<DataBeam4, Integer>("TripNo"));
		TableColDeperatureTime2.setCellValueFactory(new PropertyValueFactory<DataBeam4, String>("DeperatureTime"));
		TableColArrivalTime2.setCellValueFactory(new PropertyValueFactory<DataBeam4, String>("ArrivalTime"));

	}

	public void initData(SceneManager sceneManager) throws ParseException {
		// TODO Auto-generated method stub
		this.manager = sceneManager;
		Hungarian_Algorithm h2 = new Hungarian_Algorithm(manager.sdController.TableView.getItems());
		ShowDataController h1 = manager.sdController;
		
		int numOfRows = h1.TableView.getItems().size();// Integer.parseInt(h1.TextFieldNoOfTrip.getText());
		int numOfCols = 2;
		double[][] array = new double[numOfRows][numOfCols];
		int[][] assignment = new int[array.length][2];
		h2.generateSuitableLayoverTime(array);
		
		labelCityOne.setText(h2.BasedCityOne);
		labelCityTwo.setText(h2.BasedCityTwo);
		labelCityThree.setText(h2.BasedCityTwo);
		labelCityFour.setText(h2.BasedCityOne);
		
		for (int i = 0; i < manager.sdController.dataBeans.size(); i++) {
			DataBeam1 beam1 = manager.sdController.dataBeans.get(i);
			TableView1.getItems().add(
					new DataBeam4(i + 1, beam1.getTextFieldBusDeperatureTime1(), beam1.getTextFieldBusArrivalTime1()));
		}
		for (int i = 0; i < manager.sdController.dataBeans.size(); i++) {
			DataBeam1 beam1 = manager.sdController.dataBeans.get(i);
			TableView2.getItems().add(
					new DataBeam4(i + 1+TableView1.getItems().size(), beam1.getTextFieldBusDeperatureTime2(), beam1.getTextFieldBusArrivalTime2()));
		}
		ObservableList<String[]> row1 = FXCollections.observableArrayList(); // To add calculateTableOne in Hungarian
																				// Algorithm
		row1.clear();
		String[] leastLayoverTime = manager.sdController.dataBeans.get(0).getTextFieldLeastLayoverTime().split(":");
		double LeastLayoverTime = Double.valueOf(leastLayoverTime[0] + '.' + leastLayoverTime[1]);
		double[][] TableOne = new double[numOfRows][numOfRows];
		String[][] TableOneString = new String[numOfRows][numOfRows];
		TableOne = h2.calculateTableOne(LeastLayoverTime);
		for (int i = 0; i < h2.array1.length; i++) {
			for (int j = 0; j < h2.array1.length; j++) {
				TableOneString[i][j] = String.valueOf(TableOne[i][j]);
			}
		}

		row1.addAll(Arrays.asList(TableOneString));

		for (int i = 0; i < TableOneString.length; i++) {
			TableColumn tc1 = new TableColumn(String.valueOf(i+1));
			final int colNo = i;
			tc1.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public SimpleStringProperty call(CellDataFeatures<String[], String> p) {
					return new SimpleStringProperty((p.getValue()[colNo]));
				}
			});
			tc1.setPrefWidth(90);
			TableView3.getColumns().add(tc1);
		}

		TableView3.setItems(row1);

		ObservableList<String[]> row2 = FXCollections.observableArrayList();// To add TableView 4 into calculateTableTwo
																			// in Hungarian Algorithm
		row2.clear();
		double[][] TableTwo = new double[numOfRows][numOfRows];
		String[][] TableTwoString = new String[numOfRows][numOfRows];
		TableTwo = h2.calculateTableTwo(LeastLayoverTime);
		for (int i = 0; i < h2.array2.length; i++) {
			for (int j = 0; j < h2.array2.length; j++) {
				TableTwoString[i][j] = String.valueOf(TableTwo[i][j]);
			}
		}

		row2.addAll(Arrays.asList(TableTwoString));

		for (int i = 0; i < TableTwoString.length; i++) {
			TableColumn tc2 = new TableColumn(String.valueOf(i+1));
			final int colNo = i;
			tc2.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public SimpleStringProperty call(CellDataFeatures<String[], String> p) {
					return new SimpleStringProperty((p.getValue()[colNo]));
				}
			});
			tc2.setPrefWidth(90);
			TableView4.getColumns().add(tc2);
		}

		TableView4.setItems(row2);

		ObservableList<String[]> row3 = FXCollections.observableArrayList();// To add TableView 4 into calculateTableTwo
																			// in Hungarian Algorithm
		row3.clear();
		double[][] TableThree = new double[numOfRows][numOfRows];
		String[][] TableThreeString = new String[numOfRows][numOfRows];
		TableThree = h2.SelectSuitableIdleTime(TableOne, TableTwo);
		for (int i = 0; i < h2.array1.length; i++) {
			for (int j = 0; j < h2.array1.length; j++) {
				TableThreeString[i][j] = String.valueOf(TableThree[i][j]);
			}
		}

		row3.addAll(Arrays.asList(TableThreeString));

		for (int i = 0; i < TableThreeString.length; i++) {
			TableColumn tc3 = new TableColumn(String.valueOf(i+1));
			final int colNo = i;
			tc3.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public SimpleStringProperty call(CellDataFeatures<String[], String> p) {
					return new SimpleStringProperty((p.getValue()[colNo]));
				}
			});
			tc3.setPrefWidth(90);
			TableView5.getColumns().add(tc3);
		}

		TableView5.setItems(row3);
	}
	public void ShowNextStep() throws ParseException {
		Hungarian_Algorithm h2 = new Hungarian_Algorithm(manager.sdController.TableView.getItems());
		ShowDataController h1 = manager.sdController;
		String[] leastLayoverTime = manager.sdController.dataBeans.get(0).getTextFieldLeastLayoverTime().split(":");
		double LeastLayoverTime = Double.valueOf(leastLayoverTime[0] + '.' + leastLayoverTime[1]);

		h2.Step1();
		manager.showStep1View();
		
	}
}
