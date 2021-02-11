import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author
 *
 */
public class PasswordCheckerTest_STUDENT_Test {
    ArrayList<String> passwords;
    String[] p= {"a1Ab1Bc1Cd1D#",
            "334455BB",
            "Im2cool4U#",
            "george2ZZZ",
            "4Sal#",
            "bertha22"};
    @Before
    public void setUp() throws Exception {
        passwords = new ArrayList<String>();
        passwords.addAll((Arrays.asList(p)));
    }

    @After
    public void tearDown() throws Exception {
        passwords = null;
    }

    /**
     * Test if the password is less than 8 characters long.
     * This test should throw a LengthException for second case.
     */
    @Test
    public void testIsValidPasswordTooShort()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("abc12"));
        }
        catch(LengthException e)
        {
            assertTrue("Successfully threw a lengthExcepetion",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides lengthException",false);
            System.out.println("line 49");
        }
    }

    /**
     * Test if the password has at least one uppercase alpha character
     * This test should throw a NoUpperAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoUpperAlpha()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("abc12f"));
        }
        catch(NoUpperAlphaException e)
        {
            assertTrue("Successfully threw a NoUpperAlphaExceptionExcepetion",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides NoUpperAlphaExceptionException",false);
            System.out.println("line 49");
        }
    }

    /**
     * Test if the password has at least one lowercase alpha character
     * This test should throw a NoLowerAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoLowerAlpha()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("ASD12A"));
        }
        catch(NoLowerAlphaException e)
        {
            assertTrue("Successfully threw a NoLowerAlphaExceptionExcepetion",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides NoLowerAlphaExceptionException",false);
            System.out.println("line 49");
        }
    }
    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsWeakPassword()
    {
        try{
            assertTrue(PasswordCheckerUtility.isWeakPassword("asd1#A"));
        }
        catch(WeakPasswordException e)
        {
            assertTrue("Successfully threw a WeakPasswordException",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides WeakPasswordException",false);
            System.out.println("line 49");
        }
    }

    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsValidPasswordInvalidSequence()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("asd1#A"));
        }
        catch(InvalidSequenceException e)
        {
            assertTrue("Successfully threw a InvalidSequenceException",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides InvalidSequenceException",false);
            System.out.println("line 49");
        }
    }

    /**
     * Test if the password has at least one digit
     * One test should throw a NoDigitException
     */
    @Test
    public void testIsValidPasswordNoDigit()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("asd#A#"));

        }
        catch(NoDigitException e)
        {
            assertTrue("Successfully threw a NoDigitException",true);
        }
        catch(Exception e)
        {
            assertTrue("Threw some other exception besides NoDigitException",false);
            System.out.println("line 49");
        }
    }

    /**
     * Test correct passwords
     * This test should not throw an exception
     */
    @Test
    public void testIsValidPasswordSuccessful()
    {
        try{
            assertEquals(true,PasswordCheckerUtility.isValidPassword("strongPWD1"));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test the invalidPasswords method
     * Check the results of the ArrayList of Strings returned by the validPasswords method
     */
    @Test
    public void testInvalidPasswords() {


        ArrayList<String> InvalidPasswords=	PasswordCheckerUtility.getInvalidPasswords(passwords);


        for(int i=0; i<InvalidPasswords.size();i++) {
            try
            {
                assertTrue(PasswordCheckerUtility.isValidPassword(InvalidPasswords.get(i)));
            }
            catch(Exception ex)
            {
                assertTrue("Threw "+ ex.getMessage(),true);

            }

        }

    }


}

