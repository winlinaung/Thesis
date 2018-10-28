package controllers;


import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class DataBeam1 {
	String TextFieldBusNumber;
	String TextFieldResidenceCity1;
	String TextFieldBusDeperatureTime1;
	String TextFieldBusArrivalTime1;
	String TextFieldResidenceCity2;
	String TextFieldBusDeperatureTime2;
	String TextFieldBusArrivalTime2;
	String TextFieldLeastLayoverTime;
	String TextFieldDeperatureDate;
	public DataBeam1(String tableColCarNumber,String ResidenceCity1,String tableColCarDeperatureTime1,String tableColCarArrivalTime1,String ResidenceCity2,String tableColCarDeperatureTime2,String tableColCarArrivalTime2,String tableColLeastLayoverTime,String tableColDeperatureDate) {

	this.TextFieldBusNumber = tableColCarNumber;
	this.TextFieldResidenceCity1 = ResidenceCity1;
	this.TextFieldBusDeperatureTime1 = tableColCarDeperatureTime1;
	this.TextFieldBusArrivalTime1 = tableColCarArrivalTime1;
	this.TextFieldResidenceCity2 = ResidenceCity2;
	this.TextFieldBusDeperatureTime2 = tableColCarDeperatureTime2;
	this.TextFieldBusArrivalTime2 = tableColCarArrivalTime2;
	this.TextFieldLeastLayoverTime = tableColLeastLayoverTime;
	this.TextFieldDeperatureDate = tableColDeperatureDate;
	
	}

	public String getTextFieldBusNumber() {
		return TextFieldBusNumber;
	}

	public void setTextFieldBusNumber(String textFieldBusNumber) {
		TextFieldBusNumber = textFieldBusNumber;
	}

	public String getTextFieldResidenceCity1() {
		return TextFieldResidenceCity1;
	}

	public void setTextFieldResidenceCity1(String textFieldResidenceCity1) {
		TextFieldResidenceCity1 = textFieldResidenceCity1;
	}

	public String getTextFieldBusDeperatureTime1() {
		return TextFieldBusDeperatureTime1;
	}

	public void setTextFieldBusDeperatureTime1(String textFieldBusDeperatureTime1) {
		TextFieldBusDeperatureTime1 = textFieldBusDeperatureTime1;
	}

	public String getTextFieldBusArrivalTime1() {
		return TextFieldBusArrivalTime1;
	}

	public void setTextFieldBusArrivalTime1(String textFieldBusArrivalTime1) {
		TextFieldBusArrivalTime1 = textFieldBusArrivalTime1;
	}

	public String getTextFieldResidenceCity2() {
		return TextFieldResidenceCity2;
	}

	public void setTextFieldResidenceCity2(String textFieldResidenceCity2) {
		TextFieldResidenceCity2 = textFieldResidenceCity2;
	}

	public String getTextFieldBusDeperatureTime2() {
		return TextFieldBusDeperatureTime2;
	}

	public void setTextFieldBusDeperatureTime2(String textFieldBusDeperatureTime2) {
		TextFieldBusDeperatureTime2 = textFieldBusDeperatureTime2;
	}

	public String getTextFieldBusArrivalTime2() {
		return TextFieldBusArrivalTime2;
	}

	public void setTextFieldBusArrivalTime2(String textFieldBusArrivalTime2) {
		TextFieldBusArrivalTime2 = textFieldBusArrivalTime2;
	}

	public String getTextFieldLeastLayoverTime() {
		return TextFieldLeastLayoverTime;
	}

	public void setTextFieldLeastLayoverTime(String textFieldLeastLayoverTime) {
		TextFieldLeastLayoverTime = textFieldLeastLayoverTime;
	}

	public String getTextFieldDeperatureDate() {
		return TextFieldDeperatureDate;
	}

	public void setTextFieldDeperatureDate(String textFieldDeperatureDate) {
		TextFieldDeperatureDate = textFieldDeperatureDate;
	}

	public static void linearTablaConductores(ObservableList<DataBeam1> data) {
		String BusNumber = null;
		String ResidenceCity1 = null;
		String BusDeperatureTime1 = null;
		String BusArrivalTime1 = null; 
		String ResidenceCity2 = null;
		String BusDeperatureTime2 = null; 
		String BusArrivalTime2 = null;
		String LeastLayoverTime = null;
		String DeperatureDate = null;
		data = FXCollections.observableArrayList();
		data.add(new DataBeam1(BusNumber,ResidenceCity1,BusDeperatureTime1,BusArrivalTime1,ResidenceCity2,BusDeperatureTime2,BusArrivalTime2,LeastLayoverTime,DeperatureDate));
	}

//	@Override
//	public String toString() {
//		return "MetaDadosInfo [TextFieldBusNumber=" + TextFieldBusNumber + ", TextFieldBusDeperatureTime1="
//				+ TextFieldBusDeperatureTime1 + ", TextFieldBusArrivalTime1=" + TextFieldBusArrivalTime1
//				+ ", TextFieldBusDeperatureTime2=" + TextFieldBusDeperatureTime2 + ", TextFieldBusArrivalTime2="
//				+ TextFieldBusArrivalTime2 + ", TextFieldLeastLayoverTime=" + TextFieldLeastLayoverTime + ", BusNumber="
//				+ BusNumber + ", BusDeperatureTime1=" + BusDeperatureTime1 + ", BusArrivalTime1=" + BusArrivalTime1
//				+ ", BusDeperatureTime2=" + BusDeperatureTime2 + ", BusArrivalTime2=" + BusArrivalTime2
//				+ ", LeastLayoverTime=" + LeastLayoverTime + "]";
//	}	
}