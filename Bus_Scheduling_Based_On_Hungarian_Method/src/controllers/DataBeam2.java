package controllers;

import java.time.LocalDate;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataBeam2 {
	String BusNumber;
	int TripNo1;
	String DeperatureTime1;
	int TripNo2;
	String DeperatureTime2;
	String ResidenceCity1;
	String ResidenceCity2;
	String WaitingTime;
	String DeperatureDate1;
	String DeperatureDate2;
	String SuitableBasedCity;
	
	public DataBeam2(String ResultTableBusNumber,String ResultTableResidenceCity1,int ResultTableTripNo1,String ResultTableDeperatureTime1,String ResultTableResidenceCity2,int ResultTableTripNo2,String ResultTableDeperatureTime2,String ResultTableWaitingTime,String DeperatureDate1,String DeperatureDate2,String ResultTableSuitableBasedCity) {
	this.BusNumber = ResultTableBusNumber;
	this.ResidenceCity1 = ResultTableResidenceCity1;
	this.TripNo1 = ResultTableTripNo1;
	this.DeperatureTime1 = ResultTableDeperatureTime1;
	this.ResidenceCity2 = ResultTableResidenceCity2;
	this.TripNo2 = ResultTableTripNo2;
	this.DeperatureTime2 = ResultTableDeperatureTime2;
	this.WaitingTime =  ResultTableWaitingTime;
	this.DeperatureDate1 = DeperatureDate1;
	this.DeperatureDate2 = DeperatureDate2;
	this.SuitableBasedCity = ResultTableSuitableBasedCity;
	}


	public String getBusNumber() {
		return BusNumber;
	}


	public void setBusNumber(String busNumber) {
		BusNumber = busNumber;
	}


	public int getTripNo1() {
		return TripNo1;
	}


	public void setTripNo1(int tripNo1) {
		TripNo1 = tripNo1;
	}


	public String getDeperatureTime1() {
		return DeperatureTime1;
	}


	public void setDeperatureTime1(String deperatureTime1) {
		DeperatureTime1 = deperatureTime1;
	}


	public int getTripNo2() {
		return TripNo2;
	}


	public void setTripNo2(int tripNo2) {
		TripNo2 = tripNo2;
	}


	public String getDeperatureTime2() {
		return DeperatureTime2;
	}


	public void setDeperatureTime2(String deperatureTime2) {
		DeperatureTime2 = deperatureTime2;
	}


	public String getResidenceCity1() {
		return ResidenceCity1;
	}


	public void setResidenceCity1(String residenceCity1) {
		ResidenceCity1 = residenceCity1;
	}


	public String getResidenceCity2() {
		return ResidenceCity2;
	}


	public void setResidenceCity2(String residenceCity2) {
		ResidenceCity2 = residenceCity2;
	}


	public String getWaitingTime() {
		return WaitingTime;
	}


	public void setWaitingTime(String waitingTime) {
		WaitingTime = waitingTime;
	}


	public String getDeperatureDate1() {
		return DeperatureDate1;
	}


	public void setDeperatureDate1(String deperatureDate1) {
		DeperatureDate1 = deperatureDate1;
	}


	public String getDeperatureDate2() {
		return DeperatureDate2;
	}


	public void setDeperatureDate2(String deperatureDate2) {
		DeperatureDate2 = deperatureDate2;
	}


	public String getSuitableBasedCity() {
		return SuitableBasedCity;
	}


	public void setSuitableBasedCity(String suitableBasedCity) {
		SuitableBasedCity = suitableBasedCity;
	}


	public static void linearTablaConductores(ObservableList<DataBeam2> data) {
		String BusNumber = null; 
		int TripNo1 = 0;
		String BusDeperatureTime1 = null;
		int TripNo2 = 0; 
		String BusDeperatureTime2 = null; 
		String ResidenceCity1 = null;
		String ResidenceCity2 = null;
		String LeastLayoverTime = null;
		String DeperatureDate1 = null,DeperatureDate2 = null;
		String SuitableBasedCity = null;
		data = FXCollections.observableArrayList();
		data.addAll( new DataBeam2(BusNumber,ResidenceCity1,TripNo1,BusDeperatureTime1,ResidenceCity2,TripNo2,BusDeperatureTime2,LeastLayoverTime,DeperatureDate1,DeperatureDate2,SuitableBasedCity));
	}
}
