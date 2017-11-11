package com.forge.bastion.bastion2;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Account {
	
	private StringProperty accountName;
	
	private StringProperty username;
	
	private StringProperty password;
	
	Account(String acc, String user, String pass)
	{
		this.accountName = new SimpleStringProperty(acc);
		this.username = new SimpleStringProperty(user);
		this.password = new SimpleStringProperty(pass);
	}
	
	public void setAccount(String acc)
	{
		this.accountName.set(acc);
	}
	
	public void setUsername(String user)
	{
		this.username.set(user);
	}
	
	public void setPassword(String pass)
	{
		this.password.set(pass);
	}
	
	public String getAccount()
	{
		return accountName.get();
	}
	
	public String getUsername()
	{
		return username.get();
	}
	
	public String getPassword()
	{
		return password.get();
	}
	
	public StringProperty getAccountProperty()
	{
		return accountName;
	}
	
	public StringProperty getUsernameProperty()
	{
		return username;
	}
}
