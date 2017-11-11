package com.forge.bastion.bastion2;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class LoginAlgorithm 
{
    public LoginAlgorithm() 
    {
    }
    
    public boolean checkForAccount(String user, String pass)
    {
    	boolean status = false;
	    	
    	File accountFile = new File(user);
	    	
    	status = accountFile.exists();
    	
    	if(status)
    	{
    		try 
    		{
    			
    			BufferedReader br = new BufferedReader(new FileReader(user));
    			String encrypt_pass;
    			status = ((encrypt_pass = br.readLine()) != null);
    			if(status)
    			{
    				String unencrypt_pass = Crypter.decrypt(encrypt_pass, user);
    				status = pass.equals(unencrypt_pass);
    			}
    			
    			br.close();
    		} 
    		catch (Exception e) 
    		{
    			e.printStackTrace();
    			status = false;
    		}
    	}
    	
    	return status;
    }
    
    public boolean createNewAccount(String user, String pass)
    {   	
    	File accountFile = new File(user);
	    	
    	try 
    	{
			if(!accountFile.createNewFile())
			{
				return false;
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(user));
			String encrypt_pass = Crypter.encrypt(pass, user);
			bw.write(encrypt_pass);
			bw.newLine();
			bw.close();
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    	
    	return true;
    }
}
