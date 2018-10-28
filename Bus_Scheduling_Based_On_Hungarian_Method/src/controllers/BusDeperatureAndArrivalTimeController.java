package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import application.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class BusDeperatureAndArrivalTimeController implements Initializable{
@FXML
SceneManager manager;
@FXML 
javafx.scene.control.TableView<DataBeam2> ResultTableView;
@FXML 
TableColumn<DataBeam2,String> ResultTableColBusNumber;
@FXML 
TableColumn<DataBeam2,Integer> ResultTableColTripNo1;
@FXML 
TableColumn<DataBeam2,String> ResultTableColBusDeperatureTime1;
@FXML 
TableColumn<DataBeam2, Integer> ResultTableColTripNo2;
@FXML 
TableColumn<DataBeam2,String> ResultTableColBusDeperatureTime2;
@FXML 
TableColumn<DataBeam2,String> ResultTableColResidenceCity;
@FXML 
TableColumn<DataBeam2,String> ResultTableColWaitingTime;
@FXML 
TableColumn<DataBeam2,String> ResultTableColBusDeperatureDate1;
@FXML 
TableColumn<DataBeam2,String> ResultTableColDeperatureTime2;
@FXML 
TableColumn<DataBeam2,String> ResultTableColBusDeperatureDate2;
@FXML 
TableColumn<DataBeam2,String> ResultTableColResidenceCity1;
@FXML 
TableColumn<DataBeam2,String> ResultTableColResidenceCity2;
@FXML 
TableColumn<DataBeam2,String> ResultTableColSuitableBasedCity;
@FXML
Label LabelUserName;
public void HomeView() {
	manager.showWelcomeView();
}
public void showCalculatedResultTable() throws IOException, ParseException,SQLException{
	double leastLayoverTime;
	
	 String BusNumber = null;
	 int TripNo1 = 0;
	 String DeperatureTime1 = null;
	 int TripNo2 = 0;
	 String DeperatureTime2 = null;
	 String ResidenceCity1 = null;
	 String ResidenceCity2 = null;
	 String WaitingTime = null;
	 String DeperatureDate1 = null;
	 String DeperatureDate2 = null;
	 String SuitableBasedCity = null;
	 try {
			String url = "jdbc:sqlite:/home/shadowfield/eclipse-java-photon-workspace/Bus_Scheduling_Based_On_Hungarian_Method/DBForLogin";
			Connection conn = DriverManager.getConnection(url);
			
			PreparedStatement statement1 = conn.prepareStatement("select Id from Login where User_Name = ?");// To get user id from login user name
			statement1.setString(1,manager.LoginAndRegController.loginUserName);
		    statement1.setQueryTimeout(30);  // set timeout to 30 sec.
		    ResultSet rs1= statement1.executeQuery();
		    int LoginUserNameId = rs1.getInt(1);
		    
		    PreparedStatement statement = conn.prepareStatement("select * from Result_Table where Id = ?"); // To get All of Result data from Result_Tabe
			statement.setInt(1, LoginUserNameId);
			
			ResultSet rs2 = statement.executeQuery();
			while(rs2.next()) {
			BusNumber = rs2.getString(2);
			ResidenceCity1 = rs2.getString(3);
			TripNo1 = rs2.getInt(4);
			DeperatureDate1 = rs2.getString(5);
			DeperatureTime1 = rs2.getString(6);
			ResidenceCity2 = rs2.getString(7);
			TripNo2 = rs2.getInt(8);
			DeperatureDate2 = rs2.getString(9);
			DeperatureTime2 = rs2.getString(10);
			WaitingTime = rs2.getString(11);
			SuitableBasedCity = rs2.getString(12);
			ResultTableView.getItems().add(new DataBeam2(BusNumber,ResidenceCity1,TripNo1,DeperatureTime1,ResidenceCity2,TripNo2,DeperatureTime2,WaitingTime,DeperatureDate1,DeperatureDate2,SuitableBasedCity));
			}
	 }
	 catch(Exception e) {
		 e.getMessage();
	 }
		
	
}
	public void initData(SceneManager sceneManager) {
		// TODO Auto-generated method stub
		this.manager = sceneManager;
		LabelUserName.setText(manager.LoginAndRegController.loginUserName);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				
				ResultTableColBusNumber.setCellValueFactory(new PropertyValueFactory<DataBeam2,String>("BusNumber"));
				ResultTableColTripNo1.setCellValueFactory(new PropertyValueFactory<DataBeam2,Integer>("TripNo1"));
				ResultTableColBusDeperatureTime1.setCellValueFactory(new PropertyValueFactory<DataBeam2,String>("DeperatureTime1"));
				ResultTableColTripNo2.setCellValueFactory(new PropertyValueFactory<DataBeam2,Integer>("TripNo2"));
				ResultTableColBusDeperatureTime2.setCellValueFactory(new PropertyValueFactory<DataBeam2,String>("DeperatureTime2"));
				ResultTableColResidenceCity1.setCellValueFactory(new PropertyValueFactory<DataBeam2,String>("ResidenceCity1"));
				ResultTableColResidenceCity2.setCellValueFactory(new PropertyValueFactory<DataBeam2,String>("ResidenceCity2"));
				ResultTableColWaitingTime.setCellValueFactory(new PropertyValueFactory<DataBeam2,String>("WaitingTime"));
				ResultTableColBusDeperatureDate1.setCellValueFactory(new PropertyValueFactory<DataBeam2,String>("DeperatureDate1"));
				ResultTableColBusDeperatureDate2.setCellValueFactory(new PropertyValueFactory<DataBeam2,String>("DeperatureDate2"));
				ResultTableColSuitableBasedCity.setCellValueFactory(new PropertyValueFactory<DataBeam2,String>("SuitableBasedCity"));
	}

}
