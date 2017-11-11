package com.forge.bastion.bastion2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class AccountsAlgorithm {
	
	private ObservableList<Account> accountList = FXCollections.observableArrayList();
	
	private String username;
	
	private String password;
	
	public AccountsAlgorithm()
	{
	}
	
	private Account encryptAccount(String phrase, Account acc)
	{
		Account data = null;
		
		String a = Crypter.encrypt(acc.getAccount(), phrase);
		String u = Crypter.encrypt(acc.getUsername(), phrase);
		String p = Crypter.encrypt(acc.getPassword(), phrase);
		
		data = new Account(a, u, p);
		
		return data;
	}
	
	public boolean updateFile()
	{
		boolean status = true;
		
		File accountFile = new File(username);
		try 
    	{
			if(accountFile.delete() && accountFile.createNewFile())
			{
				BufferedWriter bw = new BufferedWriter(new FileWriter(username));
				String encrypt_pass = Crypter.encrypt(password, username);
				bw.write(encrypt_pass);
				bw.newLine();
				
				for(int i = 0; i < accountList.size(); i++)
				{
					Account acc = accountList.get(i);
					
					Account encrypt_acc = encryptAccount(password, acc);
					bw.write(encrypt_acc.getAccount());
					bw.newLine();
			    	bw.write(encrypt_acc.getUsername());
			    	bw.newLine();
			    	bw.write(encrypt_acc.getPassword());
			    	bw.newLine();
				}
				
				bw.close();
	    	}
    	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public void setAccountInfo(String user, String pass)
	{
		this.username = user;
		this.password = pass;
	}
	
	private Account decryptAccount(String phrase, String acc, String user, String pass)
	{
		Account data = null;
		
		String a = Crypter.decrypt(acc, phrase);
		String u = Crypter.decrypt(user, phrase);
		String p = Crypter.decrypt(pass, phrase);
		
		data = new Account(a, u, p);
		
		return data;
	}
	
	public void loadAccountData()
	{
		String acc, user, pass;
			
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(username));
			String encrypt_pass;
			boolean status = ((encrypt_pass = br.readLine()) != null);
			while(status && (acc = br.readLine()) != null)
			{
				user = br.readLine();
				pass = br.readLine();
				
				Account data = decryptAccount(password, acc, user, pass);
				accountList.add(data);
			}
			
			br.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public ObservableList<Account> getAccountData()
	{
		return accountList;
	}
}
