package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

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


public class LoginAndRegistrationController implements Initializable{
	SceneManager manager;
@FXML 
private TextField TextFieldUserName;
@FXML 
private PasswordField TextFieldPassword;
JButton BtnOk;
static String loginUserName;
static int LoginUserNameId;
@FXML 
public void LogIn(javafx.scene.input.MouseEvent event) throws IOException{
	Connection conn = Dbconnect.getInstance().getConnection();
	try {
		Statement statement = conn.createStatement();
	    statement.setQueryTimeout(30);  // set timeout to 30 sec.
	    ResultSet rs = statement.executeQuery("select * from Login");
	    while(rs.next())
        {
	           // read the result set
	          if(TextFieldUserName.getText().equals(rs.getString(2)) && TextFieldPassword.getText().equals(rs.getString(3))) {
	 		    LoginUserNameId = rs.getInt(1);
	 		    
	        	 loginUserName = rs.getString(2);
	        	 manager.showWelcomeView();
				  break;
	          }
	          else if(TextFieldUserName.getText().equals("") && TextFieldPassword.getText().equals("")) {
		        	 JOptionPane.showMessageDialog(BtnOk,"You need to fil User Name And User Password");
					TextFieldUserName.setText("");
					break;
		         }
	          else if(TextFieldUserName.getText().equals("")) {
		        	 JOptionPane.showMessageDialog(BtnOk,"You need to fill User Name");
		        	 TextFieldPassword.setText("");
		        	 break;
		         }
	          else if(TextFieldPassword.getText().equals("")) {
		        	 JOptionPane.showMessageDialog(BtnOk,"You need to fil User Password");
					TextFieldUserName.setText("");
					break;
		         }
	          else if((!(TextFieldUserName.getText().equals(rs.getString(2))) && TextFieldPassword.getText().equals(rs.getString(3)))) {
	        	  
	        	  JOptionPane.showMessageDialog(BtnOk, "You need to check up your account name" );
	        	  TextFieldUserName.setText("");
	        	  break;
	          }	     
	          else if((TextFieldUserName.getText().equals(rs.getString(2)) && !(TextFieldPassword.getText().equals(rs.getString(3))))) {
	        	  
	        	  JOptionPane.showMessageDialog(BtnOk, "You need to check your account password" );
	        	  TextFieldPassword.setText("");		
	        	  break;
	          }	     
	          else if(!(TextFieldUserName.getText().equals(rs.getString(2)) && TextFieldPassword.getText().equals(rs.getString(3)))) {
	        	  
	        	  JOptionPane.showMessageDialog(BtnOk, "You need to check your account name and password and if you have not a account.You need to click Sign Up Button" );
	        	  TextFieldUserName.setText("");
	        	  TextFieldPassword.setText("");
	        	  break;
	          }	         
        }
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
	
	}
public void SignIn(MouseEvent event) throws IOException {
	manager.showSignUpView();
	
}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
}
public void initData(SceneManager sceneManager) {
	// TODO Auto-generated method stub
	this.manager = sceneManager;
}

}


