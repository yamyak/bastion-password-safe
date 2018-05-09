package com.forge.bastion.bastion2;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;

public class MainApp extends Application 
{
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) 
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Bastion v2");
		
		initRootLoader();
		
		showLoginView();
	}

  // Initialize the view
	public void initRootLoader()
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("RootView.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} 
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void showLoginView()
	{
		try 
		{
      // Display the login view
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("LoginView.fxml"));
			AnchorPane anchor = (AnchorPane) loader.load();
			
			rootLayout.setCenter(anchor);
			
      // setup the login controller
			LoginController controller = loader.getController();
			controller.setMainApp(this);
		} 
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void showAccountsView(String user, String pass)
	{
		try
		{
      // Display the accounts view
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("AccountsView.fxml"));
			AnchorPane anchor = (AnchorPane) loader.load();
			
			primaryStage.setWidth(400);
			primaryStage.setHeight(550);
			rootLayout.setCenter(anchor);
			
      // Setup the accounts controller
			AccountsController controller = loader.getController();
			controller.initAccounts(user, pass);
			controller.setMainApp(this);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() 
	{
    return primaryStage;
  }
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
