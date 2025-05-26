/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author mushw
 */
//import static com.mycompany.chatapp.ChatApp.input;
import javax.swing.JOptionPane;
import java.util.regex.*;
import javax.swing.JPasswordField;
public class Verification {
    
    //private static String registeredUsername;
   // private static String registeredPassword;
    
    //Method to verify surname
    public static boolean checkSurname (String surname){
        if (surname == null || surname.isEmpty()){
         return false;   
        }
         return Character.isUpperCase(surname.charAt(0));
    }
    
    //Method to veryfy first name
    public static boolean checkFirstName (String firstName){
        if (firstName == null || firstName.isEmpty()){
          return false;
        }
        return Character.isUpperCase(firstName.charAt(0));
    }
    
    //Method to verify username
    public static boolean checkUserName(String username)
    {
        return username.contains("_") && username.length() <= 5;
    }
    
    //Method to verify password
    public static boolean checkPassword(String password){
        if (password == null) return false;
      if (!password.matches(".*[A-Z].*")){
          return false;
      }
      if (!password.matches(".*[?><,./';:~!@#$%^&*()_+=-`].*")){
          return false;
      }
      if (!password.matches(".*\\d.*")){
          return false;
      }
      return password.length() >= 8;
    }
    
    //Method to verify a South African cellphone number with +27 dialling code.
    public static boolean checkCellPhoneNumber(String cellNumber){
        return cellNumber.contains("+27");
        
    }
//  Login method
    public static boolean login(String regUsername, String regPassword){
        String logUsername;
        String logPassword;
        logUsername = JOptionPane.showInputDialog("Enter username");
        if (logUsername == null) return false;
        logPassword = JOptionPane.showInputDialog("Enter password");
        if (logPassword == null) return false;
        return regPassword.equals(logUsername) && regUsername.equals(logPassword);
        
        
    }
 
    
}
//}
