package controllers;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import DbConnect.Dbconnect;
import application.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.JButton;
public class SignUpController implements Initializable{
	@FXML 
	private TextField TextFieldUserName;
	@FXML 
	private PasswordField TextFieldPassword;
	@FXML 
	private TextField TextFieldEmail;
	@FXML 
	private TextField TextFieldPhoneNumber;
	@FXML
	private SceneManager manager;
	JButton BtnOk;
	Connection conn = Dbconnect.getInstance().getConnection();
	public void SignIn(javafx.scene.input.MouseEvent event) throws IOException{
		try {
		Statement statement1 = conn.createStatement();
	    statement1.setQueryTimeout(30);  // set timeout to 30 sec.
	    ResultSet rs = statement1.executeQuery("select * from Login");
	    while(rs.next()) {

			 if(TextFieldUserName.getText().equals("") && TextFieldPassword.getText().equals("") && TextFieldEmail.getText().equals("")  && TextFieldPhoneNumber.getText().equals("")) {
	        	 JOptionPane.showMessageDialog(BtnOk,"You need to fil User Name And User Password");
				TextFieldUserName.setText("");
	         }
		else if(TextFieldUserName.getText().equals("") ) {
	        	 JOptionPane.showMessageDialog(BtnOk,"You need to fil User Name And User Password");
				TextFieldUserName.setText("");
	         }
			else if(TextFieldPassword.getText().equals("") ) {
				JOptionPane.showMessageDialog(BtnOk,"You need to fil UUser Password");
			}
			else if(TextFieldEmail.getText().equals("") ) {
				JOptionPane.showMessageDialog(BtnOk,"You need to fil Email");
			}
			else if( TextFieldPhoneNumber.getText().equals("")) {
				JOptionPane.showMessageDialog(BtnOk,"You need to fil Email");
			}
			else if(TextFieldUserName.getText().equals(rs.getString(2))) {
				TextFieldUserName.setText("");
				JOptionPane.showMessageDialog(BtnOk,"The User Name is already used");
			}
			
			else if(validateEmail()) {
				 JOptionPane.showMessageDialog(BtnOk,"You need to fill Valid Email Account");
			}
			else if(TextFieldEmail.getText().equals(rs.getString(4))) {
				TextFieldEmail.setText("");
				JOptionPane.showMessageDialog(BtnOk, "Email is already used");
			}
			else {
			PreparedStatement statement = conn.prepareStatement("insert into Login(Id,User_Name,User_Password,User_Email,User_Phone) values(?,?,?,?,?); ");
		    statement.setQueryTimeout(30);  // set timeout to 30 sec.     
		    statement.setString(1,null);
		    statement.setString(2,TextFieldUserName.getText());
		    statement.setString(3,TextFieldPassword.getText());
		    statement.setString(4,TextFieldEmail.getText());
		    statement.setLong(5, Integer.valueOf(TextFieldPhoneNumber.getText()));
		    int i = statement.executeUpdate();
	
		    manager.showWelcomeView();
	        }
		}
	    }
		catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.getMessage();
	}
		}		
	private boolean validateEmail() {
		Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
		Matcher matcher = p.matcher(TextFieldEmail.getText());
		if(matcher.find() && matcher.group().equals(TextFieldEmail.getText())) {
			return false;
		}
		else {
			
		return true;
		}
	}
//	private boolean checkOldUser() throws SQLException {
//		try {
//		Statement statement = conn.createStatement();
//	    statement.setQueryTimeout(30);  // set timeout to 30 sec.
//	    ResultSet rs = statement.executeQuery("select * from Login");
//	    while(rs.next()) {
//		if(TextFieldUserName.getText().equals(rs.getString(2))) {
//			
//		return true;
//		}
//		else
//		{
//			return false;
//		}
//	    }
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//	private boolean checkOldEmail() throws SQLException {
//		try {
//		Statement statement = conn.createStatement();
//	    statement.setQueryTimeout(30);  // set timeout to 30 sec.
//	    ResultSet rs = statement.executeQuery("select * from Login");
//	   
//	    while(rs.next()) {
//	    	 System.out.println("Old Email" + rs.getString(4));
//		if(TextFieldEmail.getText().equals(rs.getString(4))) {
//		return true;
//		}
//		else
//		{
//			return false;
//		}
//	    }
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//	    return false;
//	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void initData(SceneManager sceneManager) {
		this.manager = sceneManager;
	}
}
