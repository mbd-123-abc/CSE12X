//Mahika Bagri
//CSE 123 
//8 October 2025

import java.util.*;

// This class is a type of cipher that is made using a given encryption key. It is able to encrypt
//and decrypt any text within a specified character range.  
public class Substitution extends Cipher {

    private Map<Character, Character> encryptingChars;
    private Map<Character, Character> decrpytingChars;

    // Behavior: 
    //   - This method constructs a substitution cipher with an empty encoding. 

    public Substitution(){}

    // Behavior: 
    //   - This method constructs a substitution cipher with a given encoding.
    // Parameters:
    //   - Encoding: The encryption key that is a scrambled version of the encodable range. 
    // Exceptions:
    //   - if encoding is null, it's lenght is not equal to the length of characters in encodable 
    //range, contains a character outside the given range, or it contains the same letter twice, 
    //an IllegalArgumentException is thrown.


    public Substitution(String encoding){
        setEncoding(encoding);
    }  

    // Behavior: 
    //   - This method sets the encoding (encryption key), connecting it to it's cipher. 
    // Parameters:
    //   - encoding: The encryption key that is a scrambled version of the encodable range. 
    // Exceptions:
    //   - if encoding is null, it's lenght is not equal to the length of characters in encodable 
    //range, contains a character outside the given range, or it contains the same letter twice, 
    //an IllegalArgumentException is thrown.

    public void setEncoding(String encoding){
        if(encoding == null||Cipher.TOTAL_CHARS != encoding.length()){
            throw new IllegalArgumentException();
        }
        this.encryptingChars = new TreeMap<>();
        this.decrpytingChars = new TreeMap<>();
        for(int i = 0; i < encoding.length(); i++){
            if(!Cipher.isCharInRange(encoding.charAt(i))){
                throw new IllegalArgumentException(); 
            }
            if(this.decrpytingChars.containsKey(encoding.charAt(i))){
                throw new IllegalArgumentException();
            }else{
                this.decrpytingChars.put(encoding.charAt(i), (char)(Cipher.MIN_CHAR+i));
                this.encryptingChars.put((char)(Cipher.MIN_CHAR+i), encoding.charAt(i));
            }
        }
    }

    // Behavior: 
    //   - This method encrypts a string using it's given encryption key.
    // Parameters:
    //   - input: the string the user wants to encode that only contains value in range. 
    // Returns:
    //   - String: the encrypted version of the given string 
    // Exceptions:
    //   - if the encryption key is empty, an IllegalStateException is thrown
    //   - if the input is null, an IllegalArgumentException is thrown

    public String encrypt(String input){
        if(this.encryptingChars == null){
            throw new IllegalStateException();
        }
        if(input == null){
            throw new IllegalArgumentException();
        }
        String output = "";
        for(int i = 0; i < input.length(); i++){
            output = output + this.encryptingChars.get(input.charAt(i));
        }
        return output;
    }

    // Behavior: 
    //   - This method decrypts a string using it's given encryption key.
    // Parameters:
    //   - input: the string the user wants to decrypt that only contains value in range. 
    // Returns:
    //   - String: the decrypted version of the given string 
    // Exceptions:
    //   - if the encryption key is empty, an IllegalStateException is thrown
    //   - if the input is null, an IllegalArgumentException is thrown

    public String decrypt(String input){
        if(this.decrpytingChars == null){
            throw new IllegalStateException();
        }
        if(input == null){
            throw new IllegalArgumentException();
        }
        String output = "";
        for(int i = 0; i < input.length(); i++){
            output = output + this.decrpytingChars.get(input.charAt(i));
        }
        return output;
    }

}
