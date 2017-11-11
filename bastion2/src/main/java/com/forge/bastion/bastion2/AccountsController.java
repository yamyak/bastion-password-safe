package com.forge.bastion.bastion2;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TableColumn;
import java.util.Optional;
import javafx.scene.control.Alert.AlertType;

public class AccountsController 
{
	@FXML
	private TableView<Account> table;
	
	@FXML 
	private TableColumn<Account, String> accountColumn;
	
	@FXML 
	private TableColumn<Account, String> usernameColumn;
	
	@FXML
	private Button create;
	
	@FXML
	private Button view;
	
	@FXML
	private Button edit;
	
	@FXML
	private Button delete;
	
	private MainApp mainApp;
	
	private AccountsAlgorithm algo;
	
	public AccountsController()
	{
		algo = new AccountsAlgorithm();
	}
	
	@FXML
	private void initialize()
	{
		accountColumn.setCellValueFactory(cellData -> cellData.getValue().getAccountProperty());
		usernameColumn.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
	}
	
	@FXML
	private void onCreate()
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("NewView.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        
	        Stage dialogStage = new Stage();
            dialogStage.setTitle("Create Account");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            NewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();
            
            if(controller.isOkClicked())
            {
            	algo.getAccountData().add(controller.createAccount());
            	algo.updateFile();
            }
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onView()
	{
		try 
		{
			int selectedIndex = table.getSelectionModel().getSelectedIndex();
			if(selectedIndex >= 0)
			{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("DataView.fxml"));
		        AnchorPane page = (AnchorPane) loader.load();
		        
		        Stage dialogStage = new Stage();
	            dialogStage.setTitle("View Account");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(mainApp.getPrimaryStage());
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);
	            
	            DataController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            
	            controller.setAccount(algo.getAccountData().get(selectedIndex));
	            
	            dialogStage.showAndWait();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onEdit()
	{
		try 
		{
			int selectedIndex = table.getSelectionModel().getSelectedIndex();
			if(selectedIndex >= 0)
			{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("EditView.fxml"));
		        AnchorPane page = (AnchorPane) loader.load();
		        
		        Stage dialogStage = new Stage();
	            dialogStage.setTitle("Edit Account");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(mainApp.getPrimaryStage());
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);
	            
	            EditController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            
	            Account current = algo.getAccountData().get(selectedIndex);
	            controller.setAccount(current);
	            
	            dialogStage.showAndWait();
	            
	            if(controller.isOkClicked())
	            {
	            	Account newData = controller.editAccount();
	            	current.setAccount(newData.getAccount());
	            	current.setUsername(newData.getUsername());
	            	current.setPassword(newData.getPassword());
	            	
	            	algo.updateFile();
	            }
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onRemove()
	{
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0)
		{
			Account data = algo.getAccountData().get(selectedIndex);
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete Account");
			alert.setHeaderText(data.getAccount() + " : " + data.getUsername());
			alert.setContentText("Delete this account?");
	
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				table.getItems().remove(selectedIndex);
				
				algo.updateFile();
			}
		}
	}
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
		
		Stage stage = mainApp.getPrimaryStage();
		
		stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() 
		{
			  @Override
			  public void handle(KeyEvent evt) 
			  {
			     if(evt.getCode().equals(KeyCode.ESCAPE))
			     {
			        stage.close();
			     }
			  }
		});
		
		table.setItems(algo.getAccountData());
	}
	
	public void initAccounts(String user, String pass)
	{
		algo.setAccountInfo(user, pass);
		algo.loadAccountData();
	}

}
