/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



package com.mycompany.chatapp;

import static com.mycompany.chatapp.Verification.*;
import javax.swing.JOptionPane;

public class ChatApp {

    private static String storedFirstName;
    private static String storedSurname;
    private static String storedUserName;
    private static String storedPassword;
    private static boolean isLoggedIn = false;

    public static void main(String[] args) {
        // Welcome Message
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        // First Name Input
        String firstName = JOptionPane.showInputDialog("Please enter your first name:");
        if (firstName == null) return;
        if (!checkFirstName(firstName)) {
            JOptionPane.showMessageDialog(null, "First name is not correctly formatted.\nPlease ensure that your first name starts with a capital letter.");
            return;
        }
        storedFirstName = firstName;
        JOptionPane.showMessageDialog(null, "First name successfully captured.");

        // Surname Input
        String surname = JOptionPane.showInputDialog("Please enter your surname:");
        if (surname == null) return;
        if (!checkSurname(surname)) {
            JOptionPane.showMessageDialog(null, "Surname is not correctly formatted.\nPlease ensure that your surname starts with a capital letter.");
            return;
        }
        storedSurname = surname;
        JOptionPane.showMessageDialog(null, "Surname successfully captured.");

        // Username Input
        String username = JOptionPane.showInputDialog("Please enter your username:");
        if (username == null) return;
        if (!checkUserName(username)) {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted.\nPlease ensure it contains an underscore and is no more than five characters in length.");
            return;
        }
        JOptionPane.showMessageDialog(null, "Username successfully captured.");
        storedUserName = username;

        // Password Input
        String password = JOptionPane.showInputDialog("Please enter your password:");
        if (password == null) return;
        if (!checkPassword(password)) {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted.\nIt must contain at least 8 characters, a capital letter, a number and a special character.");
            return;
        }
        JOptionPane.showMessageDialog(null, "Password successfully captured.");
        storedPassword = password;

        // Cell Number Input
        String cellNumber = JOptionPane.showInputDialog("Please enter your cellphone number:");
        if (cellNumber == null) return;
        if (!checkCellPhoneNumber(cellNumber)) {
            JOptionPane.showMessageDialog(null, "Invalid format. Use international format (+27...) or local 10-digit number.");
            return;
        }
        JOptionPane.showMessageDialog(null, "Cell phone number successfully added.");

        JOptionPane.showMessageDialog(null, "You have registered successfully.");
        JOptionPane.showMessageDialog(null, "Click OK to proceed to the login page.");

        // Login
        //String password;
        password = JOptionPane.showInputDialog("Please enter your password:");
        if (password == null) return;

        if (!checkPassword(password)) {
         JOptionPane.showMessageDialog(null, 
        "Password is not correctly formatted.\n" +
        "It must contain at least 8 characters, a capital letter, a number, and a special character.");
            return;
    }

        storedPassword = password;
        
        JOptionPane.showMessageDialog(null, "Password successfully captured.");
runApplication();
            
    }

    
//Messaging application
    private static void runApplication() {
        while (true) {
            String[] options = {"Send Messages", "Show sent messages", "Show stored messages", "Quit"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Select an option:",
                    "QuickChat Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (choice) {
                case 0:
                    sendMessages();
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, Message.printMessageIds());
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, Message.showStoredMessages());
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Total messages sent: " + Message.returnTotalMessages() + "\nGoodbye!");
                    return;
                default:
                    break;
            }
        }
    }

    private static void sendMessages() {
        String numMessagesStr = JOptionPane.showInputDialog("How many messages do you want to send?");
        if (numMessagesStr == null) return;

        try {
            int numMessages = Integer.parseInt(numMessagesStr);

            for (int i = 0; i < numMessages; i++) {
                boolean success = sendSingleMessage(i + 1, numMessages);
                if (!success) {
                    break;
                }
            }

            JOptionPane.showMessageDialog(null, "All messages processed.\nTotal sent: " + Message.returnTotalMessages());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
        }
    }

    private static boolean sendSingleMessage(int current, int total) {
        JOptionPane.showMessageDialog(null, String.format("Message %d of %d", current, total));

        String recipient;
        while (true) {
            recipient = JOptionPane.showInputDialog("Enter recipient number (+CC for international):");
            if (recipient == null) return false;

            String validationMsg = Message.getRecipientValidationMessage(recipient);
            if (validationMsg.startsWith("Cell phone number successfully")) {
                break;
            }
            JOptionPane.showMessageDialog(null, validationMsg);
        }

        String content;
        while (true) {
            content = JOptionPane.showInputDialog("Enter your message (max 250 chars):");
            if (content == null) return false;

            String validationMsg = Message.getMessageContentValidationMessage(content);
            if (validationMsg.equals("Message ready to send.")) {
                break;
            }
            JOptionPane.showMessageDialog(null, validationMsg);
        }

        Message message = new Message(recipient, content);
        String result = message.sentMessage();
        JOptionPane.showMessageDialog(null, result);

        return true;
    }
    private static void sendValidatedMessage() {
    while (true) {
        String recipient = JOptionPane.showInputDialog("Enter recipient's phone number (+country code):");
        if (recipient == null) return;

        if (recipient.length() > 10 || (!recipient.startsWith("+"))) {
            JOptionPane.showMessageDialog(null, "Invalid number. Use international format and max 10 characters.");
            continue;
        }

        String content = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
        if (content == null) return;

        if (content.length() > 250) {
            JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
            continue;
        }

        Message msg = new Message(recipient, content);

        String[] options = {"Send", "Edit", "Cancel"};
        int choice = JOptionPane.showOptionDialog(null,
            "Message Preview:\n" + msg.toString(),
            "Send Message",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]);

        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, msg.sentMessage());
                return;
            case 1:
                continue; // Loop again to allow editing
            case 2:
            default:
                JOptionPane.showMessageDialog(null, "Message canceled.");
                return;
        }
    }
}
}
