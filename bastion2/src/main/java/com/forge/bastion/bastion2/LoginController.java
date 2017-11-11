package com.forge.bastion.bastion2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class LoginController 
{
	
	@FXML
	private TextField username;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Button login;
	
	@FXML
	private Button exit;
	
	@FXML
	private Button newAccount;
	
	private MainApp mainApp;
	
	private LoginAlgorithm algo;
	
	public LoginController()
	{
		algo = new LoginAlgorithm();
	}
	
	@FXML
	private void initialize()
	{
		username.setText("");
		password.setText("");
	}
	
	@FXML
	private void onLogin()
	{
		boolean valid = false;
		
		valid = algo.checkForAccount(username.getText(), password.getText());
		
		if(valid)
		{
			this.mainApp.showAccountsView(username.getText(), password.getText());	
		}
		
	}
	
	@FXML
	private void onExit()
	{
		Stage stage = (Stage) exit.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void onNewAccount()
	{
		boolean success = false;
		
		success = algo.createNewAccount(username.getText(), password.getText());
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Account Creation");
		if(success)
		{
			alert.setContentText("Account Creation Successful");
		}
		else
		{
			alert.setContentText("Account Creation Failed");
		}
		alert.showAndWait();
	}
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}
}
