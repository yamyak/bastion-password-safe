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
      
    // check for file with username as filename
    status = accountFile.exists();
    
    if(status)
    {
      try 
      {
        // read first line in file
        BufferedReader br = new BufferedReader(new FileReader(user));
        String encrypt_pass;
        status = ((encrypt_pass = br.readLine()) != null);
        if(status)
        {
          // decrypt the line using username as key
          String unencrypt_pass = Crypter.decrypt(encrypt_pass, user);
          // check if decrypted version matches password
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
    // create new file with username as file name
    File accountFile = new File(user);
      
    try 
    {
      // make sure account with same new doesn't already exist
      if(!accountFile.createNewFile())
      {
        return false;
      }
      
      // encrypt password with username as key and set as first line of file
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
