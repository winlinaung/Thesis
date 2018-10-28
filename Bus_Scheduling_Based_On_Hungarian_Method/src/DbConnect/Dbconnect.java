package DbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Dbconnect {
private Dbconnect() {
		
	}
public static Dbconnect getInstance() {
	return new Dbconnect();
	
}
public Connection getConnection() {
	String url = "jdbc:sqlite:/home/shadowfield/eclipse-java-photon-workspace/Bus_Scheduling_Based_On_Hungarian_Method/DBForLogin";
	Connection conn = null;
	try {
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection(url);
	
	}
	catch(SQLException | ClassNotFoundException e ) {
		e.printStackTrace();
	}
	return conn;
}
}
