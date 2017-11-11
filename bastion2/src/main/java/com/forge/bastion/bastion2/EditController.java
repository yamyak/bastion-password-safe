package com.forge.bastion.bastion2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class EditController {
	
	@FXML
	private TextField account;
	
	@FXML
	private TextField username;
	
	@FXML
	private TextField password;
	
	@FXML
	private Button edit;
	
	@FXML
	private Button cancel;
	
	private MainApp mainApp;
	private Stage dialogStage;
	
	private boolean okClicked = false;
	
	public EditController()
	{
		
	}
	
	private boolean InputValid()
	{
		boolean status = (account.getText().length() > 0);
		status &= (username.getText().length() > 0);
		status &= (password.getText().length() > 0);
		
		return status;
	}
	
	public Account editAccount()
	{
		Account data = new Account(account.getText(), username.getText(), password.getText());
		
		return data;
	}
	
	@FXML
	private void onEdit()
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
	
	public void setDialogStage(Stage dialogStage) 
	{
        this.dialogStage = dialogStage;
    }
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
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
