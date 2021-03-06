package com.forge.bastion.bastion2;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Crypter {
	
	private static SecretKeySpec secretKey;
  private static byte[] key;
  
  // Set the encryption key
  public static void setKey(String newKey)
  {
    MessageDigest sha = null;
    
    String myKey = newKey;
    while(myKey.length() < 16)
    {
      myKey = myKey + newKey;
    }
    myKey = myKey.substring(0,16);
      
    try 
    {
      key = myKey.getBytes("UTF-8");
      sha = MessageDigest.getInstance("SHA-1");
      key = sha.digest(key);
      key = Arrays.copyOf(key, 16);
      secretKey = new SecretKeySpec(key, "AES");
    }
    catch (Exception e) 
    {
      e.printStackTrace();
    }
  }
 
  // Encrypt a string
  public static String encrypt(String strToEncrypt, String secret)
  {
    try
    {
      setKey(secret);
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }
    catch (Exception e)
    {
      System.out.println("Error while encrypting: " + e.toString());
    }
    
    return null;
  }
  
  Decrypt a string
  public static String decrypt(String strToDecrypt, String secret)
  {
    try
    {
      setKey(secret);
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }
    catch (Exception e)
    {
      System.out.println("Error while decrypting: " + e.toString());
    }
    
    return null;
  }

}
