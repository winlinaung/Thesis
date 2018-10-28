package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.EventObject;
import java.util.ResourceBundle;

import application.SceneManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ResultController implements Initializable{
	@FXML 
	public SceneManager manager;
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
	TableColumn<DataBeam2,String> ResultTableColResidenceCity1;
	@FXML 
	TableColumn<DataBeam2,String> ResultTableColResidenceCity2;
	@FXML 
	TableColumn<DataBeam2,String> ResultTableColWaitingTime;
	@FXML 
	TableColumn<DataBeam2,String> ResultTableColBusDeperatureDate1;
	@FXML 
	TableColumn<DataBeam2,String> ResultTableColDeperatureTime2;
	@FXML 
	TableColumn<DataBeam2,String> ResultTableColBusDeperatureDate2;
	@FXML 
	TableColumn<DataBeam2,String> ResultTableColSuitableBasedCity;
	
	@FXML
	TextField TextFieldCarGateName;
	@FXML
	TextField TextFieldCarNumber;
	@FXML
	TextField TextFieldResidenceCity1;
	@FXML
	TextField TextFieldCarDeperatureTime1;
	@FXML
	TextField TextFieldCarArrivalTime1;
	@FXML
	TextField TextFieldResidenceCity2;
	@FXML
	TextField TextFieldCarDeperatureTime2;
	@FXML
	TextField TextFieldCarArrivalTime2;
	@FXML
	TextField TextFieldLeastLayoverTime;
	public void HomeView() {
		manager.showWelcomeView();
	}
	@FXML 
	public void CalculateByHungarianMethod() throws IOException, ParseException,SQLException{
		double leastLayoverTime;
		ShowDataController h1 = manager.sdController;
		Hungarian_Algorithm1 h2 = new Hungarian_Algorithm1(manager.sdController.TableView.getItems());
		 String BusNumber;
		 String BusGateName;
		 int TripNo1;
		 String DeperatureTime1;
		 int TripNo2;
		 String DeperatureTime2;
		 String ResidenceCity1;
		 String ResidenceCity2;
		 String SuitableBasedCity;
		 double WaitingTime1;
		 
		int numOfRows = h1.TableView.getItems().size();//Integer.parseInt(h1.TextFieldNoOfTrip.getText());
		int numOfCols = 2;
		double[][] array = new double[numOfRows][numOfCols]; 
		int[][] assignment = new int[array.length][2];
		h2.generateSuitableLayoverTime(array);
		String leastLayoverTime1[] = h1.TableColLeastLayoverTime.getCellData(1).split(":"); //To change Time format to number format
		leastLayoverTime = Double.valueOf(leastLayoverTime1[0] + "." + leastLayoverTime1[1]);
		
		assignment = h2.hgAlgorithmAssignments(h2.SelectSuitableIdleTime(h2.calculateTableOne(leastLayoverTime), h2.calculateTableTwo(leastLayoverTime)), "min");
		double sum = 0.0;
		for (int i=0; i<assignment.length; i++) 
		{ 
			 int Route_Number1 = assignment[i][0]+1 ;
			 int Route_Number2 = h2.SuitableTable.length+assignment[i][1]+1;
			 DeperatureTime1 = String.valueOf(h2.array1[assignment[i][0]][0]);
			 DeperatureTime2 = String.valueOf(h2.array2[assignment[i][1]][0]);
			 String deperatureTime1[] = DeperatureTime1.split("\\.");
			 String deperatureTime1Part1 = deperatureTime1[0];
			 String deperatureTime1Part2 = deperatureTime1[1];
			 DeperatureTime1 = deperatureTime1Part1 +":" +deperatureTime1Part2 +"0";
			 String deperatureTime2[] = DeperatureTime2.split("\\."); 
			 String deperatureTime2Part1 = deperatureTime2[0];
			 String deperatureTime2Part2 = deperatureTime2[1];
			 DeperatureTime2 = deperatureTime2Part1 +":" +deperatureTime2Part2 +"0";
		
			 String Route_Number = Route_Number1+" --- "+Route_Number2;
			 sum = sum + h2.SuitableTable[assignment[i][0]][assignment[i][1]]; 
			 //</COMMENT> 
			 BusNumber = h1.TableColBusNumber.getCellData(i);   
			 TripNo1 = Route_Number1;
			 TripNo2 = Route_Number2;
			 SuitableBasedCity = h2.SuitableBasedCity[assignment[i][0]][assignment[i][1]];
			 ResidenceCity1 = h1.TableColResidenceCity1.getCellData(i);
			 ResidenceCity2 = h1.TableColResidenceCity2.getCellData(i);
			 DecimalFormat df = new DecimalFormat("#.#");
			 WaitingTime1 = Double.parseDouble(df.format(h2.SuitableTable[assignment[i][0]][assignment[i][1]]));
			 String WaitingTime = String.valueOf(WaitingTime1);
			 String waitingTime[] = WaitingTime.split("\\.");
			 String waitingTimePart1 = waitingTime[0];
			 String waitingTimePart2 = waitingTime[1];
			 WaitingTime = waitingTimePart1 + ":"  + waitingTimePart2 + "0";
			 
			 String DeperatureDate1 = String.valueOf(h1.TableColDeperatureDate.getCellData(i));
			 String DeperatureDate2 = h2.SuitableDeperatureDate[assignment[i][0]][assignment[i][1]];
			 
			
			 ResultTableView.getItems().add(new DataBeam2(BusNumber,ResidenceCity1,TripNo1,DeperatureTime1,ResidenceCity2,TripNo2,DeperatureTime2,WaitingTime,DeperatureDate1,DeperatureDate2,SuitableBasedCity));
			 
			 
			 try {
			String url = "jdbc:sqlite:/home/shadowfield/eclipse-java-photon-workspace/Bus_Scheduling_Based_On_Hungarian_Method/DBForLogin";
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement statement = conn.prepareStatement("insert into Result_Table(Id,Bus_Number,Residence_City1,Trip_No1,Deperature_Date1,Deperature_Time1,Residence_City2,Trip_No2,Deperature_Date2,Deperature_Time2,Waiting_Time,Suitable_Based_City) values (?,?,?,?,?,?,?,?,?,?,?,?); ");
		   
		    int LoginUserNameId = manager.LoginAndRegController.LoginUserNameId;
		    
			statement.setQueryTimeout(30);  // set timeout to 30 sec.     
		    statement.setLong(1,Integer.valueOf(LoginUserNameId));
		    statement.setString(2,BusNumber);
		    statement.setString(3,ResidenceCity1);
		    statement.setLong(4,TripNo1);
		    statement.setString(5,DeperatureDate1);
		    statement.setString(6,DeperatureTime1);
		    statement.setString(7,ResidenceCity2);
		    statement.setLong(8,TripNo2);
		    statement.setString(9,DeperatureDate2);
		    statement.setString(10,DeperatureTime2);
		    statement.setString(11,WaitingTime);
		    statement.setString(12,SuitableBasedCity);
		    int j1 = statement.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
		}
		}
		
		         
		}
		

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
	public void initData(SceneManager manager) {
		this.manager = manager;
	}
	
}
