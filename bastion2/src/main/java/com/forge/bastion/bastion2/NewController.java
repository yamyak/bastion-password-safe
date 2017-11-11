package com.forge.bastion.bastion2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewController {
	
	@FXML
	private TextField account;
	
	@FXML
	private TextField username;
	
	@FXML
	private TextField password;
	
	@FXML
	private Button ok;
	
	@FXML
	private Button cancel;
	
	private MainApp mainApp;
	private Stage dialogStage;
	
	private boolean okClicked = false;
	
	public NewController()
	{
		
	}
	
	private boolean InputValid()
	{
		boolean status = (account.getText().length() > 0);
		status &= (username.getText().length() > 0);
		status &= (password.getText().length() > 0);
		
		return status;
	}
	
	public Account createAccount()
	{
		Account data = new Account(account.getText(), username.getText(), password.getText());
		
		return data;
	}
	
	@FXML
	private void onOK()
	{
		if(InputValid())
		{
			okClicked = true;
			dialogStage.close();
		}
	}
	
	@FXML
	private void onCancel()
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
}
