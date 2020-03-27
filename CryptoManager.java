/**This class is a framework that manage the user input text
 * CMSC 203
 * March 26 2020
 * @author Anh Tu Linh Hoang
 * 
 */


public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**this method check if a character is in bounds 
	 * 
	 * @param c
	 * @return false is the character is not in bounds, else return true
	 */
	private static boolean isInBounds (char c)
	{
		if( c < LOWER_BOUND || c > UPPER_BOUND)
			return false;
		else
			return true;		
	}
	/**this method wrap an (int) ascii character or a Ceaser key character
	 * 
	 * @param c
	 * @param key
	 * @return a shifted character
	 */
	private static int wrap (int ascii)
	{ 
		while( !isInBounds((char)ascii))
		{
			if(ascii < LOWER_BOUND)
				ascii += RANGE;
			else // c > UPPER_BOUND
				ascii -= RANGE;
		}
		return ascii;
	}
	/*
	 * this method wrap a key for Ceaser Cipher
	 * @param key
	 * @return a shifted key
	 
	private static int wrap (int key)
	{
		while (key > UPPER_BOUND)
		{
			key -= (int) RANGE;
		}
		return key;
	}*/
	
	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		for ( int i = 0 ; i < plainText.length(); i++)
		{
			if( !isInBounds(plainText.charAt(i)))
				return false;
		}
		return true;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		String encryptedText = "";
		wrap(key); //actually this step is not necessary because the asciiChar will be wrapped later
		if (stringInBounds(plainText))
			for ( int i=0 ; i< plainText.length(); i++)
			{
				int asciiChar = (int)plainText.charAt(i) + key;
				encryptedText += (char)wrap(asciiChar);
			}
		else
			encryptedText = "This text is out of range!!";
		return encryptedText;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		String encryptedText = "";
		if (!stringInBounds(plainText))
			encryptedText = "the plain text is not in bounds";
		else
		{
		int index = 0;
		 // extend the key word to the length of string
		while (bellasoStr.length() < plainText.length() )
		{
			bellasoStr += bellasoStr.charAt(index);
			index++;
			if (index == bellasoStr.length())
				index = 0;
		}
		//encrypt each character of the plainText
		for ( int i = 0 ; i < plainText.length(); i++ )
		{
			int asciiChar = (int)plainText.charAt(i) + (int)bellasoStr.charAt(i);
			encryptedText +=  (char)wrap(asciiChar);
		}
		}
		return encryptedText;
		
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		String decryptedText = "";
		wrap(key);
		if (stringInBounds(encryptedText))
			for ( int i=0 ; i< encryptedText.length(); i++)
			{
				int asciiChar = (int)encryptedText.charAt(i) - key;
				char decryptedChar = (char)wrap(asciiChar);
				decryptedText += decryptedChar;
			}
		else
			decryptedText = "This text is out of range!!";
		return decryptedText;
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		String decryptedText = "";
		if (!stringInBounds(encryptedText))
			decryptedText = "the plain text is not in bounds";
		else
		{
		int index = 0;
		 // extend the key word to the length of string
		while (bellasoStr.length() < encryptedText.length() )
		{
			bellasoStr += bellasoStr.charAt(index);
			index++;
			if (index == bellasoStr.length())
				index = 0;
		}
		//decrypt each character of the plainText
		for ( int i = 0 ; i < encryptedText.length(); i++ )
		{
			int asciiChar = (int)encryptedText.charAt(i) - (int)bellasoStr.charAt(i);
			decryptedText +=  (char)wrap(asciiChar);
		}
		}
		return decryptedText;
	}
}
