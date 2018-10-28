package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import application.SceneManager;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Step1Controller implements Initializable {
	@FXML
	SceneManager manager;
	@FXML
	TableView<String[]> TableView1;
	double[][] Cost;
	JButton btnOk = new JButton("OK");
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(btnOk,"It is Step 1");
	}

	public void initData(SceneManager sceneManager) throws ParseException,IOException{
		// TODO Auto-generated method stub
		this.manager = sceneManager;
		Hungarian_Algorithm h2 = new Hungarian_Algorithm(manager.sdController.TableView.getItems());
		ShowDataController h1 = manager.sdController;
		System.out.println(manager);
		ObservableList<String[]> row1 = FXCollections.observableArrayList(); // To add calculateTableOne in Hungarian
		// Algorithm
		int numOfRows = h1.TableView.getItems().size();// Integer.parseInt(h1.TextFieldNoOfTrip.getText());
		int numOfCols = 2;
		row1.clear();
		
		String[] leastLayoverTime = manager.sdController.dataBeans.get(0).getTextFieldLeastLayoverTime().split(":");
		double LeastLayoverTime = Double.valueOf(leastLayoverTime[0] + '.' + leastLayoverTime[1]);
		Cost = new double[numOfRows][numOfRows];
		String[][] costString = new String[numOfRows][numOfRows];
		
		double maxWeightPlusOne = h2.findLargest(h2.SelectSuitableIdleTime(h2.calculateTableOne(LeastLayoverTime), h2.calculateTableTwo(LeastLayoverTime))) + 1;
	
		
		h2.hgAlgorithmAssignments(h2.SelectSuitableIdleTime(h2.calculateTableOne(LeastLayoverTime), h2.calculateTableTwo(LeastLayoverTime)), "min");
//		double[][] cost = h2.copyToSquare(h2.SelectSuitableIdleTime(h2.calculateTableOne(LeastLayoverTime), h2.calculateTableTwo(LeastLayoverTime)), maxWeightPlusOne);	//Create the cost matrix
		Cost = h2.hg_step1();
		for (int i = 0; i < h2.array1.length; i++) {
			for (int j = 0; j < h2.array1.length; j++) {
				costString[i][j] = String.valueOf(Cost[i][j]);
			}
		}
		row1.addAll(Arrays.asList(costString));

		for (int i = 0; i < costString.length; i++) {
			TableColumn tc1 = new TableColumn(String.valueOf(i + 1));
			final int colNo = i;
			tc1.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public SimpleStringProperty call(CellDataFeatures<String[], String> p) {
					return new SimpleStringProperty((p.getValue()[colNo]));
				}
			});
			tc1.setPrefWidth(90);
			TableView1.getColumns().add(tc1);
		}

		TableView1.setItems(row1);

	}
	public void ShowNextStep() throws IOException,ParseException{
		Hungarian_Algorithm h2 = new Hungarian_Algorithm(manager.sdController.TableView.getItems());
		ShowDataController h1 = manager.sdController;
		switch(h2.step1) {
		case 1 :
			manager.showStep1View();
			break;
		case 2:
			manager.showStep2View();
			break;
		case 3:
			manager.showStep3View();
			break;
		case 4:
			manager.showStep4View();
			break;
		case 5:
			manager.showStep5View();
			break;
		case 6:
			manager.showStep6View();
			break;
		case 7:
			manager.showStep7View();
			break;
		}
	}
}
