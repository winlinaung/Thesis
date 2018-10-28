package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import application.SceneManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class Step2Controller implements Initializable {
	@FXML
	SceneManager manager;
	@FXML
	TableView<String[]> TableView1;
	@FXML
	TableView<String[]> TableView2;
	int[][] mask;
	int[] rowCover;
	int[] colCover;
	int[] zero_RC;
	int[][] path;
	int[][] Mask;
	String[][] maskString;
	String[][] costString;
	double[][] Cost;
	JButton btnOk = new JButton("OK");

	int tmp=0;
	static Hungarian_Algorithm h2;
	public void initData(SceneManager sceneManager) throws ParseException, IOException {
		// TODO Auto-generated method stub

//	
		this.manager = sceneManager;
		h2 = new Hungarian_Algorithm(manager.sdController.TableView.getItems());
		mask = this.manager.Step3Controller.h2.mask;
		h2.hg_step2();
		
		ShowDataController h1 = manager.sdController;
		int numOfRows = h1.TableView.getItems().size();// Integer.parseInt(h1.TextFieldNoOfTrip.getText());
		int numOfCols = 2;

		double leastLayoverTime;

		String leastLayoverTime1[] = h1.TableColLeastLayoverTime.getCellData(1).split(":"); // To change Time format to
																							// number format
		leastLayoverTime = Double.valueOf(leastLayoverTime1[0] + "." + leastLayoverTime1[1]);

		Mask = new int[numOfRows][numOfRows];
		maskString = new String[numOfRows][numOfRows];
		costString = new String[numOfRows][numOfRows];

		double maxWeightPlusOne = h2.findLargest(h2.SelectSuitableIdleTime(h2.calculateTableOne(leastLayoverTime),
				h2.calculateTableTwo(leastLayoverTime))) + 1;
		Cost = manager.Step1Controller.Cost;

		rowCover = new int[Cost.length]; // The row covering vector.
		colCover = new int[Cost[0].length]; // The column covering vector.
		zero_RC = new int[2]; // Position of last zero from Step 4.
		path = new int[Cost.length * Cost[0].length + 2][2];
		
		ObservableList<String[]> row1 = FXCollections.observableArrayList();
		row1.clear();
		h2.hgAlgorithmAssignments(h2.SelectSuitableIdleTime(h2.calculateTableOne(leastLayoverTime), h2.calculateTableTwo(leastLayoverTime)), "min");
		
		
				h2.hg_step2();
				maskString = new String[h2.mask.length][h2.mask[0].length];
				for (int i = 0; i < Mask.length; i++) {
					for (int j = 0; j < Mask.length; j++) {
						maskString[i][j] = String.valueOf(h2.mask[i][j]);// To convert the step two mask table
																							// to string type
					}
				}
		row1.addAll(Arrays.asList(maskString));

		for (int i = 0; i < maskString.length; i++) { // To creat TableView1
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

		ObservableList<String[]> row2 = FXCollections.observableArrayList(); // To add calculateTableOne in Hungarian
		row2.clear();
		for (int i1 = 0; i1 < Cost.length; i1++) {
			for (int j1 = 0; j1 < Cost.length; j1++) {
				costString[i1][j1] = String.valueOf(h2.cost[i1][j1]);// To convert the step two mask table to
																			// string type
			}
		}
		row2.addAll(Arrays.asList(costString));

		for (int i = 0; i < costString.length; i++) { // To creat TableView1
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
		TableView2.setItems(row2);

		for (int i = 0; i < TableView1.getColumns().size(); i++) {
			TableColumn tc = TableView1.getColumns().get(i);
			tc.setId(String.valueOf(i));
			tc.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
				@Override
				public TableCell<String, String> call(TableColumn<String, String> param) {
					TableCell cell = new TableCell<String, String>() {
						@Override
						public void updateItem(String item, boolean empty) {
							super.updateItem(item, empty);
							setText(empty ? null : getString());
							if (!empty) {
								int row = getIndex();
								int col = Integer.parseInt(getTableColumn().getId());
								if (h2.mask[row][col] == 1) {
									setStyle("-fx-background-color:lightblue");
								}
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
								if (h2.mask[row][column] == 1)
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

	
	}

	public void ShowNextStep() throws IOException, ParseException { // Based On the h2.step1 Link to next step view
		Hungarian_Algorithm h2 = new Hungarian_Algorithm(manager.sdController.TableView.getItems());
		ShowDataController h1 = manager.sdController;
		switch (h2.step1) {
		case 1:
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
		JOptionPane.showMessageDialog(btnOk, "It is Step 2");
	}
}
