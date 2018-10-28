package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import application.SceneManager;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.util.Callback;

public class Step4Controller implements Initializable{
@FXML
SceneManager manager;
@FXML
TableView<String[]> TableView1;
@FXML
TableView<String[]> TableView2;
JButton btnOk = new JButton("OK");
int[] row_col;
int[] rowCover;
int[] zero_RC;
String[][] maskString;
String[][] costString;
static Hungarian_Algorithm h2;
	public void initData(SceneManager sceneManager) throws IOException,ParseException{
		// TODO Auto-generated method stub
		this.manager = sceneManager;
		h2 = new Hungarian_Algorithm(manager.sdController.TableView.getItems());
		ShowDataController h1 = manager.sdController;
		Step2Controller step2 = manager.Step2Controller;
		Step3Controller step3 = manager.Step3Controller;
		Step6Controller step6 = manager.Step6Controller;
		
		ArrayList beams = new ArrayList();
		ObservableList<String[]> row1 = FXCollections.observableArrayList(); 
		row1.clear();
		row_col = new int[2];	//Holds row and col of uncovered zero.
		zero_RC = step2.zero_RC;
		rowCover = step2.rowCover;
		
		row_col = h2.hg_step4();//call step4
		
		maskString = new String[h2.mask.length][h2.mask[0].length];
		for (int i = 0; i < h2.mask.length; i++) {
			for (int j = 0; j < h2.mask[i].length; j++) {
				maskString[i][j] = String.valueOf(h2.mask[i][j]);// To convert the step two mask table
																					// to string type
			}
		}
		row1.addAll(Arrays.asList(maskString));
		
		for (int i = 0; i < maskString[0].length; i++) { //To creat TableView1
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
		
		ObservableList<String[]> row2 = FXCollections.observableArrayList(); 
		row2.clear();
		costString = new String[h2.cost.length][h2.cost[0].length];
		
		for (int i = 0; i < h2.mask.length; i++) {
			for (int j = 0; j < h2.mask[i].length; j++) {
				costString[i][j] = String.valueOf(h2.cost[i][j]);// To convert the step two mask table
																					// to string type
			}
		}
		row2.addAll(Arrays.asList(costString));
		
		for (int i = 0; i < maskString.length; i++) { //To creat TableView2
			TableColumn tc2 = new TableColumn(String.valueOf(i + 1));
			final int colNo = i;
	
			tc2.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public SimpleStringProperty call(CellDataFeatures<String[], String> p) {
					return new SimpleStringProperty((p.getValue()[colNo]));
				}
			});
		
	
			tc2.setPrefWidth(90);
		
			TableView2.getColumns().add(tc2);
		}
	


		for (int i = 0; i < TableView2.getColumns().size(); i++) {
			TableColumn tc = TableView2.getColumns().get(i);
			tc.setId(String.valueOf(i));

			tc.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
				@Override
				public TableCell<String, String> call(TableColumn<String, String> param) {
					TableCell cell = new TableCell<String, String>() {
						@Override
						public void updateItem(String item, boolean empty) {
							super.updateItem(item, empty);
							setText(empty ? null : getString());
							// setStyle("-fx-background-color:lightblue");
							if (!empty) {
								int row = getIndex();
								int column = Integer.parseInt(this.getTableColumn().getId());
								if (h2.colCover[column] == 1 )
									setStyle("-fx-background-color:lightgray");
								if (h2.rowCover[row] == 1)
									setStyle("-fx-background-color:lightgray");
								if (h2.mask[row][column] == 2 )
									setStyle("-fx-background-color:red");
								if (h2.mask[row][column] == 1 )
									setStyle("-fx-background-color:lightblue");

							}
						}

						private String getString() {
							return getItem() == null ? "" : getItem().toString();
						}
					};
					return cell;
				}
			});

		

		}
					TableView2.setItems(row2);
	}
		
	public void ShowNextStep() throws IOException,ParseException{ //Based On the h2.step1 Link to next step view
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(btnOk,"It is Step 4");
	}
}
