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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class Step5Controller implements Initializable{
@FXML
SceneManager manager;
@FXML
TableView<String[]> TableView1;
@FXML
TableView<String[]> TableView2;
JButton btnOk = new JButton("OK");
String[][] maskString;
String[][] costString;

static Hungarian_Algorithm h2;
	public void initData(SceneManager sceneManager) throws ParseException,IOException{
		// TODO Auto-generated method stub
		this.manager = sceneManager;
		h2= new Hungarian_Algorithm(manager.sdController.TableView.getItems());
		ShowDataController h1 = manager.sdController;
		Step2Controller step2 = manager.Step2Controller;
		Step3Controller step3 = manager.Step3Controller;
		Step4Controller step4 = manager.Step4Controller;
		
		int[][] path = new int[h2.cost.length * h2.cost[0].length + 2][2];
		
		int[][] mask = h2.hg_step5();//call step4
		maskString = new String[mask.length][mask[0].length];
		for(int i = 0 ;i < mask.length ;i++) {
			for(int j = 0;j < mask[0].length;j++) {
				maskString[i][j] = String.valueOf(mask[i][j]);
			}
		}
		ObservableList<String[]> row1 = FXCollections.observableArrayList(); 
		row1.clear();
		row1.addAll(Arrays.asList(maskString)); // To add mask as string to row1 list
		for (int i = 0; i < maskString.length; i++) { //To creat TableView1
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
		row2.clear();
		for (int i = 0; i < h2.cost.length ; i++) {
			for (int j = 0; j < h2.cost.length; j++) {
				costString[i][j] = String.valueOf(h2.cost[i][j]);// To convert the step two mask table to
																			// string type
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
		
//			for(TableColumn tc : TableView2.getColumns()) {
//				tc.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
//				@Override
//				public TableCell<String, String> call(TableColumn<String, String> param) {
//					TableCell cell = new TableCell<String, String>() {
//	                    @Override
//	                    public void updateItem(String item, boolean empty) {
//	                        super.updateItem(item, empty);
//	                        setText(empty ? null : getString());
//	                        if(!empty) {	                   
//	                    		for (int i = 0; i < maskString.length; i++) {  
//	                    			for(int j = 0;j< maskString[i].length;j++) {
//	                    				if(h2.mask[i][j] == 1) {
//	                    					if(costString[i][j] == this.getString()) {           
//	                    							setStyle("-fx-background-color:lightblue");
//	                    					}
//	                    				}
//	                    			
//	                    		}
//	                    		}
//	                        	
//	                        }
//	                   }
//	                    private String getString() {
//	                        return getItem() == null ? "" : getItem().toString();
//	                    }
//	                };
//					return cell;
//				}			
//			} );
//				
//			}
//		
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
								if ( h2.mask[row][column] == 1 )
									setStyle("-fx-background-color:lightblue");
								if ( h2.mask[row][column] == 2 )
									setStyle("-fx-background-color:red");
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(btnOk,"It is Step 5");
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
}
