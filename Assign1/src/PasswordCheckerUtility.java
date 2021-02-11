import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * Gets the password the user enters, checks validity
 * using different methods.
 * @author Eric Deng
 *
 */
public class PasswordCheckerUtility {

    PasswordCheckerUtility()
    {

    }

    /**
     * Checks the password length requirement - – The password must be at least 6 characters long
     * @param password  - password string to be checked for length
     * @return true if meet min length requirement
     * @throws LengthException - thrown if does not meet min length requirement
     */

    public static boolean isValidLength(String password) throws LengthException{

        if(password.length()>=6) {
            return true;
        }



        throw(new LengthException("The password must be at least 6 characters long"));

    }

    /**
     * Checks the password alpha character requirement - Password must contain an uppercase alpha character
     *
     * @param password - password string to be checked for alpha character requirement
     * @return true if meet alpha character requirement
     * @throws NoUpperAlphaException - thrown if does not meet alpha character requirement
     */
    public static boolean hasUpperAlpha(String password)
            throws NoUpperAlphaException{
        char ch;
        for(int i=0;i < password.length();i++) {
            ch = password.charAt(i);



            if(Character.isUpperCase(ch)) {
                return true;
            }

        }

        throw (new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character"));
    }
    /**
     * Checks the password lowercase requirement - Password must contain a lowercase alpha character
     * @param password - password string to be checked for lowercase requirement
     * @return true if meet lowercase requirement
     * @throws NoLowerAlphaException - thrown if does not meet lowercase requirement
     */
    public static boolean hasLowerAlpha(String password)
            throws NoLowerAlphaException{
        char ch;
        for(int i=0;i < password.length();i++) {
            ch = password.charAt(i);



            if(Character.isLowerCase(ch)) {
                return true;
            }

        }

        throw (new NoLowerAlphaException("The password must contain at least one lower case alphabetic character"));
    }
    /**
     * Checks the password Digit requirement - Password must contain a numeric character
     *
     * @param password - password string to be checked for Digit requirement
     * @return true if meet Digit requirement
     * @throws NoDigitException - thrown if does not meet Digit requirement
     */
    public static boolean hasDigit(String password) throws NoDigitException {

        char ch;
        for(int i=0;i < password.length();i++) {
            ch = password.charAt(i);


            if(Character.isDigit(ch)) {
                return true;
            }
        }

        throw(new NoDigitException("The password must contain at least one digit"));
    }
    /**
     * Checks the password SpecialCharacter requirement - Password must contain a Special Character
     * @param password - password string to be checked for SpecialCharacter requirement
     * @return true if meet SpecialCharacter requirement
     * @throws NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
     */
    public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {

        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches())
        {
            return true;
        }


        throw (new NoSpecialCharacterException("The password must contain at least one special character"));



    }
    /**
     * Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
     * @param password - password string to be checked for Sequence requirement
     * @return false if does NOT meet Sequence requirement
     * @throws InvalidSequenceException - thrown if does not meet Sequence requirement
     */
    public static boolean hasSameCharInSequence(String password)
            throws InvalidSequenceException{

        Pattern pattern = Pattern.compile("(?i)(.)\\1\\1+");
        Matcher matcher = pattern.matcher(password);
        if(!matcher.find())
        {
            return false;
        }

        throw (new InvalidSequenceException("The password cannot contain more than two of the same character in sequence"));
    }



    /**
     * Return true if valid password (follows all rules from above), returns false if an invalid password
     * @param password  - string to be checked for validity
     * @return true if valid password (follows all rules from above), false if an invalid password
     * @throws LengthException  - thrown if length is less than 6 characters
     * @throws NoDigitException - thrown if no uppercase alphabetic
     * @throws NoUpperAlphaException - thrown if no lowercase alphabetic
     * @throws NoLowerAlphaException - thrown if no digit
     * @throws InvalidSequenceException - thrown if does not meet SpecialCharacter requirement
     * @throws NoSpecialCharacterException - thrown if more than 2 of same character.
     */
    public static boolean isValidPassword(String password) throws LengthException, NoDigitException,NoUpperAlphaException,NoLowerAlphaException,InvalidSequenceException, NoSpecialCharacterException{


        if(isValidLength(password)&&hasUpperAlpha(password)&&hasLowerAlpha(password)&&hasDigit(password)&&hasSpecialChar(password)&&!hasSameCharInSequence(password)) {
            return true;
        }
//		else if(!isValidLength(password))
//		{
//			throw(new LengthException("Password less than 6 characters"));
//		}
//
//
//		else if(!hasUpperAlpha(password)) {
//			throw(new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character"));
//
//		}
//
//		else if(!hasLowerAlpha(password)) {
//			throw(new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character"));
//
//		}
//
//		else if(!hasDigit(password)) {
//			throw(new NoDigitException("The password must contain at least one digit"));
//
//		}
//
//		else if(!hasSpecialChar(password)) {
//			throw (new NoSpecialCharacterException("The password must contain at least one special character"));
//
//		}
////		else if(hasSameCharInSequence(password)) {
////			throw (new InvalidSequenceException("The password cannot contain more than two of the same character in sequence"));
////
////		}
        else
        {
            return false;
        }





    }

    /**
     * Checks if password is valid but between 6 -9 characters
     *
     * @param password - string to be checked if weak password
     * @return true if length of password is between 6 and 9 (inclusive).
     * @throws WeakPasswordException WeakPasswordException
     */
    public static boolean isWeakPassword(String password) throws WeakPasswordException{

//	try {
//		isValidPassword(password);
//		if(password.length()>=10) {
//			return true;
//		}
//	}
//	catch(Exception ex) {
//		return false;
//	}
//
        if(password.length()<=9&&password.length()>=6) {
            return true;
        }
        else if(password.length()>=10) {
            return false;
        }




        throw(new WeakPasswordException());



    }
    /**
     * Reads a file of passwords and the passwords that failed the check will be added to an invalidPasswords with the reason
     * @param passwords - list of passwords read from a file
     * @return invalidPasswords - ArrayList of invalid passwords in the correct format
     */
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalidPasswords= new ArrayList<String>();
        for(int i=0; i<passwords.size(); i++) {

            try{
                isValidPassword(passwords.get(i));
            }
            catch(Exception ex)	{
                invalidPasswords.add(passwords.get(i)+" - "+ex.getMessage());

            }
        }


        return invalidPasswords;
    }


    /**
     * Compare equality of two passwords
     * @param password - password string to be checked for
     * @param passwordConfirm - passwordConfirm string to be checked against password for
     * @throws UnmatchedException - thrown if not same (case sensitive)
     */
    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{

        if(!password.matches(passwordConfirm)) {
            throw (new UnmatchedException());
        }

    }


    /**
     * Compare equality of two passwords
     * @param password - password string to be checked for
     * @param passwordConfirm - passwordConfirm string to be checked against password for
     * @return true if both same (case sensitive)
     */
    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {

        if(password.matches(passwordConfirm)) {
            return true;
        }
        return false;
    }

    /**
     * Weak password length check - Password contains 6 to 9 characters, still considered valid, just weak
     * @param password - password string to be checked for Sequence requirement
     * @return true if password contains 6 to 9 characters
     */
    public static boolean hasBetweenSixAndNineChars(String password) {
        if(password.length()<=9&&password.length()>=6) {
            return true;
        }
       return false;
    }
}

