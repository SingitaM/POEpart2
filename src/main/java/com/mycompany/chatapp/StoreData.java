/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author mushw
 */
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class StoreData {
    public static void saveDetails(String firstName, String surname, String username, String password, String cellNumber){
        
        JSONObject userJson = new JSONObject();
        userJson.put("username", username);
        userJson.put("password", password);
        userJson.put("cellNumber", cellNumber);
        userJson.put("firstName", firstName);
        userJson.put("surname", surname);
        
        try (FileWriter file = FileWriter("user_data.json")){
            file.write(userJson.toString(4));
            JOptionPane.showMessageDialog(null,"User registration save in user_data.json");
        } catch (IOException e){
            JOptionPane.showMessageDialog(null,"An error occured");
        
      }
        
    }

    private static FileWriter FileWriter(String user_datajson) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
