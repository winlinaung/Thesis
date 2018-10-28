package controllers;

import static java.lang.Math.floor;
import static java.lang.Math.round;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.HijrahChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import application.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class ShowDataController implements Initializable {
	public SceneManager manager;
	double[][] TableOne;
	double[][] TableTwo;
	double[][] array1;
	double[][] array2;
	double[][] SuitableTable;
	String[][] SuitableBasedCity;
	String BasedCityOne;
	String BasedCityTwo;
	@FXML
	TextField TextFieldBusNumber;
	@FXML
	TextField TextFieldResidenceCity1;
	@FXML
	TextField TextFieldBusDeperatureTime1;
	@FXML
	TextField TextFieldBusArrivalTime1;
	@FXML
	TextField TextFieldResidenceCity2;
	@FXML
	TextField TextFieldBusDeperatureTime2;
	@FXML
	TextField TextFieldBusArrivalTime2;
	@FXML
	TextField TextFieldLeastLayoverTime;
	@FXML
	DatePicker TextFieldDeperatureDate;
	@FXML
	Button BtnCalculate;
	@FXML
	Button BtnAddData;
	@FXML
	javafx.scene.control.TableView<DataBeam1> TableView;
	@FXML
	TableColumn<DataBeam2, Double> ResultTableColWaitingTime;
	@FXML
	TableColumn<DataBeam1, String> TableColBusNumber;
	@FXML
	TableColumn<DataBeam1, String> TableColResidenceCity1;
	@FXML
	TableColumn<DataBeam1, String> TableColBusDeperatureTime1;
	@FXML
	TableColumn<DataBeam1, String> TableColBusArrivalTime1;
	@FXML
	TableColumn<DataBeam1, String> TableColResidenceCity2;
	@FXML
	TableColumn<DataBeam1, String> TableColBusDeperatureTime2;
	@FXML
	TableColumn<DataBeam1, String> TableColBusArrivalTime2;
	@FXML
	TableColumn<DataBeam1, String> TableColLeastLayoverTime;
	@FXML
	TableColumn<DataBeam1, String> TableColDeperatureDate;
	JButton BtnOk;
	List<DataBeam1> dataBeans = new ArrayList<>(); 
	public void HomeView() {
		manager.showWelcomeView();
	}
//	ObservableList<MetaDadosInfo> data;
	public void handleAddData() {
		String BusNumber = TextFieldBusNumber.getText();
		String ResidenceCity1 = TextFieldResidenceCity1.getText();
		String BusDeperatureTime1 = TextFieldBusDeperatureTime1.getText();
		String BusArrivalTime1 = TextFieldBusArrivalTime1.getText();
		String ResidenceCity2 = TextFieldResidenceCity2.getText();
		String BusDeperatureTime2 = TextFieldBusDeperatureTime2.getText();
		String BusArrivalTime2 = TextFieldBusArrivalTime2.getText();
		String LeastLayoverTime = TextFieldLeastLayoverTime.getText();
		String DeperatureDate = String.valueOf(TextFieldDeperatureDate.getValue());
		
		if (BusNumber.isEmpty() || ResidenceCity1.isEmpty() || BusDeperatureTime1.isEmpty()
				|| BusArrivalTime1.isEmpty() || ResidenceCity2.isEmpty() || BusDeperatureTime2.isEmpty()
				|| BusArrivalTime2.isEmpty() || LeastLayoverTime.isEmpty()) {
			JOptionPane.showMessageDialog(BtnOk, "Your need to fill some TextField");
		} else if (!BusNumber.matches("[0-9][a-zA-Z][/][0-9][0-9][0-9][0-9]")) {
			TextFieldBusNumber.setText("");
			JOptionPane.showMessageDialog(BtnOk, "Your Bus Number Format is wrong");
		} else if (!ResidenceCity1.matches("[a-zA-Z]+")) {
			TextFieldResidenceCity1.setText("");
			JOptionPane.showMessageDialog(BtnOk, "Your Residence City1 Format is wrong");
		} else if (!BusDeperatureTime1.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
			TextFieldBusDeperatureTime1.setText("");
			JOptionPane.showMessageDialog(BtnOk, "Your Bus Deperature Time1 Format is wrong");

		} else if (!BusArrivalTime1.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {

			TextFieldBusArrivalTime1.setText("");
			JOptionPane.showMessageDialog(BtnOk, "Your Bus Arrival Time1 Format is wrong");

		} 

		else if (!ResidenceCity2.matches("[a-zA-Z]+")) {
			TextFieldResidenceCity2.setText("");
			JOptionPane.showMessageDialog(BtnOk, "Your Residence City2 Format is wrong");
		} else if (!BusDeperatureTime2.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {

			TextFieldBusDeperatureTime2.setText("");
			JOptionPane.showMessageDialog(BtnOk, "Your Bus Deperature Time2 Format is wrong");

		} else if (!BusArrivalTime2.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {

			TextFieldBusArrivalTime2.setText("");
			JOptionPane.showMessageDialog(BtnOk, "Your Bus Arrival Time2 Format is wrong");

		} else if (!LeastLayoverTime.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
			TextFieldLeastLayoverTime.setText("");
			JOptionPane.showMessageDialog(BtnOk, "Your Least Layover Time Format is wrong");

		}

		else {

			DataBeam1 dataBean1= new DataBeam1(BusNumber, ResidenceCity1, BusDeperatureTime1, BusArrivalTime1,
					ResidenceCity2, BusDeperatureTime2, BusArrivalTime2, LeastLayoverTime, DeperatureDate);
			dataBeans.add(dataBean1);
			
			TableView.getItems().add(dataBean1);
			TextFieldBusNumber.clear();
			TextFieldBusDeperatureTime1.clear();
			TextFieldBusArrivalTime1.clear();
			TextFieldBusDeperatureTime2.clear();
			TextFieldBusArrivalTime2.clear();
			TableView.setEditable(false);
		}
	}

	public void NextModelPage(ActionEvent event) throws IOException, InterruptedException {
		
		manager.showStep0View();
	}

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Callback<DatePicker,DateCell> dayCellFactory = TextFieldDeperatureDate -> new DateCell() // To disable past date in date picker textField
		{
			@Override
			public void updateItem(LocalDate item,boolean empty) {
				super.updateItem(item,empty);
				if(item.isBefore(LocalDate.now())) {
					setStyle("-fx-background-color:lightgray;");
					setDisable(true);
				}
			}
			
		};
		StringConverter converter = new StringConverter<LocalDate>() {
			@Override
			public String toString(LocalDate date) {
				if(date != null)
					return dateFormatter.format(date);
				else
					return "";
			}
			@Override
			public LocalDate fromString(String string) {
				if(string != null && !string.isEmpty()) {
					LocalDate date = LocalDate.parse(string, dateFormatter);
					if(date.isBefore(LocalDate.now())) {
						return TextFieldDeperatureDate.getValue();
					}
					else return date;
				}
				return null;
			}
			};
		
		
		TextFieldDeperatureDate.setDayCellFactory(dayCellFactory);
		TextFieldDeperatureDate.setConverter(converter);
		TextFieldDeperatureDate.setValue(LocalDate.now());
//		TextFieldDeperatureDate.setChronology(HijrahChronology.INSTANCE);
		
		TableColBusNumber.setCellValueFactory(new PropertyValueFactory<DataBeam1, String>("TextFieldBusNumber"));
		TableColResidenceCity1
				.setCellValueFactory(new PropertyValueFactory<DataBeam1, String>("TextFieldResidenceCity1"));
		TableColBusDeperatureTime1
				.setCellValueFactory(new PropertyValueFactory<DataBeam1, String>("TextFieldBusDeperatureTime1"));
		TableColBusArrivalTime1
				.setCellValueFactory(new PropertyValueFactory<DataBeam1, String>("TextFieldBusArrivalTime1"));
		TableColResidenceCity2
				.setCellValueFactory(new PropertyValueFactory<DataBeam1, String>("TextFieldResidenceCity2"));
		TableColBusDeperatureTime2
				.setCellValueFactory(new PropertyValueFactory<DataBeam1, String>("TextFieldBusDeperatureTime2"));
		TableColBusArrivalTime2
				.setCellValueFactory(new PropertyValueFactory<DataBeam1, String>("TextFieldBusArrivalTime2"));
		TableColLeastLayoverTime
				.setCellValueFactory(new PropertyValueFactory<DataBeam1, String>("TextFieldLeastLayoverTime"));
		TableColDeperatureDate
				.setCellValueFactory(new PropertyValueFactory<DataBeam1, String>("TextFieldDeperatureDate"));

	}

	public void initData(SceneManager sceneManager) {
		this.manager = sceneManager;
	}
}
