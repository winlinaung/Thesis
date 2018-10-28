package controllers;

import java.text.ParseException;
import java.util.Arrays;

import application.SceneManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class Step7Controller {
	@FXML
	public SceneManager manager;
	@FXML
	TableView<String[]> TableView1;
	@FXML
	TableView<String[]> TableView2;
	static Hungarian_Algorithm h2;
	String[][] SuitableBasedCity;
	String[][] SuitableIdleTimeString;
	double[][] SuitableIdleTime;

	int[][] mask;

	public void initData(SceneManager sceneManager) throws ParseException {
		// TODO Auto-generated method stub
		this.manager = sceneManager;
		h2 = new Hungarian_Algorithm(manager.sdController.TableView.getItems());
		mask = this.manager.Step3Controller.h2.mask;
		h2.hg_step3();
		
		ShowDataController h1 = manager.sdController;
		SuitableBasedCity = new String[h2.cost.length][h2.cost[0].length];

		double leastLayoverTime;

		String leastLayoverTime1[] = h1.TableColLeastLayoverTime.getCellData(1).split(":"); // To change Time format to
																							// number format
		leastLayoverTime = Double.valueOf(leastLayoverTime1[0] + "." + leastLayoverTime1[1]);

		SuitableIdleTime = h2.SelectSuitableIdleTime(h2.calculateTableOne(leastLayoverTime),
				h2.calculateTableTwo(leastLayoverTime));

		ObservableList<String[]> row1 = FXCollections.observableArrayList();
		row1.clear();
		SuitableIdleTimeString = new String[SuitableIdleTime.length][SuitableIdleTime[0].length];

		for (int i = 0; i < SuitableIdleTimeString.length; i++) {
			for (int j = 0; j < SuitableIdleTimeString[0].length; j++) {
				SuitableIdleTimeString[i][j] = String.valueOf(SuitableIdleTime[i][j]);
			}
		}
		row1.addAll(Arrays.asList(SuitableIdleTimeString));

		for (int i = 0; i < SuitableIdleTimeString.length; i++) { // To creat TableView1
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

		for (int i = 0; i < SuitableBasedCity.length; i++) {
			for (int j = 0; j < SuitableBasedCity[0].length; j++) {
				SuitableBasedCity[i][j] = h2.SuitableBasedCity[i][j];
			}
		}

		row2.addAll(Arrays.asList(SuitableBasedCity));

		for (int i = 0; i < SuitableBasedCity.length; i++) { // To creat TableView1
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

		TableView2.setItems(row2);
	}

	public void showResultView() {
		manager.showResultView();
	}

}
