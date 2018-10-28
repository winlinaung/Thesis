package controllers;

public class DataBeam4 {
int TripNo;
String DeperatureTime;
String ArrivalTime;

public DataBeam4(int TripNo1,String DeperatureTime1,String ArrivalTime1) {
	this.TripNo = TripNo1;
	this.DeperatureTime = DeperatureTime1;
	this.ArrivalTime = ArrivalTime1;
}

public int getTripNo() {
	return TripNo;
}

public void setTripNo(int tripNo) {
	TripNo = tripNo;
}

public String getDeperatureTime() {
	return DeperatureTime;
}

public void setDeperatureTime(String deperatureTime) {
	DeperatureTime = deperatureTime;
}

public String getArrivalTime() {
	return ArrivalTime;
}

public void setArrivalTime(String arrivalTime) {
	ArrivalTime = arrivalTime;
}

}
