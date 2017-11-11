package com.forge.bastion.bastion2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DataController {

	@FXML
	private Label account;
	
	@FXML
	private Label username;
	
	@FXML
	private Label password;
	
	@FXML
	private Button ok;
	
	private MainApp mainApp;
	private Stage dialogStage;
	
	private boolean okClicked = false;
	
	public DataController()
	{
		
	}
	
	@FXML
	private void onOK()
	{
		dialogStage.close();
	}
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}
	
	public void setDialogStage(Stage dialogStage) 
	{
        this.dialogStage = dialogStage;
    }
	
	public boolean isOkClicked() 
	{
        return okClicked;
    }
	
	public void setAccount(Account data)
	{
		account.setText(data.getAccount());
		username.setText(data.getUsername());
		password.setText(data.getPassword());
	}
}
