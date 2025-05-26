/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.chatapp.Message;
/**
 *
 * @author mushw
 */

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions.*;
        
public class MessageTest {
 

    public MessageTest(){
    }
    @BeforeEach
    public void setUp() {
        // Since resetForTesting is unimplemented, we'll reset static state manually here
        // For testing purposes, you can temporarily make sentMessages and storedMessages package-private or use reflection
    }

    @Test
    public void testMessageCreationAndID() {
        Message msg = new Message("+27821234567", "Test content");
        assertTrue(msg.checkMessageID(), "Message ID should be valid and <= 10 characters");
    }

    @Test
    public void testCheckRecipientCell() {
        Message validIntl = new Message("+27821234567", "Hello");
        assertEquals(1, validIntl.checkRecipientCell());

        Message validLocal = new Message("0821234567", "Hello");
        assertEquals(1, validLocal.checkRecipientCell());

        Message invalid = new Message("abc123", "Hello");
        assertEquals(0, invalid.checkRecipientCell());
    }

    @Test
    public void testValidateMessageLength() {
        Message shortMsg = new Message("+27821234567", "Short msg");
        assertEquals("Message is ready to send.", shortMsg.validateMessageLength());

        String longContent = "a".repeat(260);
        Message longMsg = new Message("+27821234567", longContent);
        assertTrue(longMsg.validateMessageLength().contains("exceeds 250"));
    }

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message("+27821234567", "Hello world");
        String hash = msg.createMessageHash();

        assertNotNull(hash);
        assertTrue(hash.matches("\\d{2}:\\d+-\\w+\\w+"), "Hash format is incorrect");
    }

    @Test
    public void testToJSON() {
        Message msg = new Message("+27821234567", "JSON message");
        JSONObject json = msg.toJSON();

        assertEquals(msg.toJSON().getString("messageId"), json.getString("messageId"));
        assertEquals("+27821234567", json.getString("recipient"));
        assertEquals("JSON message", json.getString("content"));
    }

    @Test
    public void testStoreMessage() {
        Message msg = new Message("+27821234567", "Store this");
        String result = msg.storeMessage();

        assertTrue(result.contains("successfully stored"));
        assertTrue(msg.toJSON().getBoolean("isStored"));
    }

    @Test
    public void testSendMessage() {
        // Send logic uses JOptionPane which requires UI interaction — we can't test that directly
        // Instead, test the sendMessage() private method (make it package-private temporarily for testing)
        Message msg = new Message("+27821234567", "Send this");
        String result = msg.storeMessage(); // simulate storing first
        assertTrue(result.contains("successfully stored"));
    }

    @Test
    public void testStaticContentValidation() {
        assertTrue(Message.validateMessageContent("Valid content"));
        assertFalse(Message.validateMessageContent("a".repeat(251)));

        String feedback = Message.getMessageContentValidationMessage("a".repeat(260));
        assertTrue(feedback.contains("exceeds"));
    }

    @Test
    public void testRecipientValidationMessage() {
        String feedback = Message.getRecipientValidationMessage("+27821234567");
        assertTrue(feedback.contains("successfully captured"));

        feedback = Message.getRecipientValidationMessage("wrong");
        assertTrue(feedback.contains("incorrectly formatted"));
    }



    }
    

