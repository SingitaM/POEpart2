/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.chatapp.Verification;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mushw
 */

public class VerficationTest {
    Verification chat_obj = new Verification();
    
    public VerficationTest() {
                
    }
    // Testing checkUserName method.
    @Test
    public void checkUserName(){
        String userTest= "Sya_i";
        assertTrue(Verification.checkUserName(userTest));
    }
    
    // Testing checkPassword method.
    @Test
    public void checkPassword(){
        String userPass= "Password@1";
        assertTrue(Verification.checkPassword(userPass));
    }
    
    //Testing checkCellPhoneNumber method
    @Test
    public void checkCellPhoneNumber(){
        String userCell= "+27736258777";
        assertTrue(Verification.checkCellPhoneNumber(userCell));
    }
    
    //Test login
    @Test
    public void login(){
        String userN = "Sig_i";
        assertTrue(Verification.checkUserName(userN));
        String userP = "Password@1";
        //String SInput = "Sig_i\nPasswrod@1\n";
        //Scanner scanner = new Scanner(SInput);
        assertTrue(Verification.checkPassword(userP));
    }
            
}


/**Reference
 * OpenAI.(2025. ChatGPT (April GPT-4)[Language.
 * https://chatgpt.com/c/67fd46ae-c108-8010-aaa1-123979697305
 */