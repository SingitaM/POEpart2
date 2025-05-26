/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author mushw
 */

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;


public class Message {
    private static int totalMessagesSent = 0;
    private static int messageCounter = 0;
    private static List<Message> sentMessages = new ArrayList<>();
    private static Map<String, Message> storedMessages = new HashMap<>();
    Random random = new Random();

    public static void resetForTesting() {
        totalMessagesSent = 0;
        messageCounter = 0;
        sentMessages.clear();
        storedMessages.clear();
    }
 
    private final String messageID;
    private final String messageHash;
    private final String recipient;
    private final String content;
    private boolean isSent;
    private boolean isStored;
    private final int numMessagesSent;
 
    public Message(String recipient, String content){
        this.messageID = generateMessageID();
        this.content = content;
        this.messageHash = createMessageHash();
        this.isSent = false;
        this.isStored = false;
        this.recipient = recipient;
        this.numMessagesSent = ++messageCounter;
 }
   
    //Method to generate message ID
    private String generateMessageID(){
        Random random = new Random();
        long id = 1000000000L + random.nextInt(900000000);
        return String.valueOf(id);
    }
    
    //Method to check message ID
    public boolean checkMessageID(){
            return messageID != null && 
           messageID.matches("\\d{10}");
    }
    
    //Method to cheack recipient cell.
    public int checkRecipientCell(){
            if (recipient == null || recipient.isEmpty()){
                return -1;
            }
            
            if (recipient.startsWith("+") && recipient.length() == 12){
            return 1;
            }
            else if (recipient.matches("\\d{10}")) {
            return 1;
            }   
            return 0;
        }
    
    //Method to generate mesage hash
    public String createMessageHash(){
        String[] words = content.split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        String firstTwoDigits = messageID.substring(0, 2);
        
        return (firstTwoDigits + ":" + words.length + "-" + firstWord + lastWord).toLowerCase();
    }
    
    //Method for message menu
  public String sentMessage() {
        String[] options = {"Send Message", "Disregard Message", "Store Message to send later"};
        int choice = JOptionPane.showOptionDialog(null,
            "Choose an action for the message:\n" + this.toString(),
            "Message Action",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]);
       
        switch (choice) {
            case 0: // Send
                return sendMessage();
            case 1: // Disregard
                return "Message disregarded.";
            case 2: // Store
                return storeMessage();
            default:
                return "No action selected.";
        }
    }
   
  //Method to send messages
    private String sendMessage() {
        isSent = true;
        totalMessagesSent++;
        sentMessages.add(this);
        return "Message successfully sent.\n" + this.toString();
    }
   
    //Method to store messages
    public String storeMessage() {
        isStored = true;
        storedMessages.put(messageID, this);
        JSONObject jsonMessage = toJSON();
        System.out.println("Stored message (JSON): " + jsonMessage.toString());
        return "Message successfully stored.\nMessage ID: " + messageID;
    }
   
    //Storee profile contents using JSONObject
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("messageId", messageID);
        json.put("messageHash", messageHash);
        json.put("recipient", recipient);
        json.put("content", content);
        json.put("isSent", isSent);
        json.put("isStored", isStored);
        json.put("numMessagesSent", numMessagesSent);
        return json;
    }
   
    //Print sent messages
    public static String printMessageIds() {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }
       
        StringBuilder sb = new StringBuilder();
        sb.append("Sent Messages:\n");
        for (Message msg : sentMessages) {
            sb.append("ID: ").append(msg.messageID)
              .append(" | To: ").append(msg.recipient)
              .append(" | ").append(msg.content.length()).append(" chars\n");
        }
        return sb.toString();
    }
   
    public static int returnTotalMessages() {
        return totalMessagesSent;
    }
   
    public static String showStoredMessages() {
        if (storedMessages.isEmpty()) {
            return "No messages are currently stored.";
        }
       
        StringBuilder sb = new StringBuilder();
        sb.append("Stored Messages:\n");
        for (Map.Entry<String, Message> entry : storedMessages.entrySet()) {
            Message msg = entry.getValue();
            sb.append("ID: ").append(msg.messageID)
              .append(" | To: ").append(msg.recipient)
              .append(" | ").append(msg.content.length()).append(" chars\n");
        }
        return sb.toString();
    }
   
    @Override
    public String toString() {
        return "Message Details:\n" +
               "ID: " + messageID + "\n" +
               "Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Content: " + content + "\n" +
               "Length: " + content.length() + " characters\n" +
               "Message #" + numMessagesSent + " of " + messageCounter;
    }
   
    public static boolean validateMessageContent(String content) {
        return content != null && content.length() <= 250;
    }
   
    public static String getMessageContentValidationMessage(String content) {
        if (content == null) {
            return "Message cannot be null.";
        }
        if (content.length() > 250) {
            return String.format("Message exceeds 250 characters by %d, please reduce size.",
                   content.length() - 250);
        }
        return "Message ready to send.";
    }
   
    public static String getRecipientValidationMessage(String recipient) {
        Message temp = new Message(recipient, "test");
        int result = temp.checkRecipientCell();
        if (result == 1) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. " +
                   "Please correct the number and try again.";
        }
    }

    /**
     *
     * @return
     */
    public String validateMessageLength() {
        int messageLength = this.content.length();
        if (messageLength <= 250) {
            return "Message is ready to send.";
        } else {
            int excess = messageLength - 250;
            return "Message exceeds 250 charectars by " + excess + " characters.";
                }
        
        
    }
}
