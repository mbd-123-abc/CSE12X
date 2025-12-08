//Mahika Bagri
//CSE 123 
//8 October 2025

import java.util.List;

// This class is a type of cipher that is made using a list of ciphers. It is able to encrypt
//and decrypt any text within a specified character range utilizing all the given ciphers.  
public class MultiCipher extends Cipher{

    private List<Cipher> ciphersMulti; 

    // Behavior: 
    //   - This method constructs a new multiCipher. 
    // Parameters:
    //   - cipher: a list of ciphers the user wants to use in this multiCipher in order
    // Exceptions:
    //   - if ciphers is null, an IllegalArgumentException is thrown

    public MultiCipher(List<Cipher> ciphers){
        if(ciphers == null){
            throw new IllegalArgumentException();
        }
        this.ciphersMulti = ciphers;
    }

    // Behavior: 
    //   - This method encrypts a string using the list of ciphers the user wants to use.
    // Parameters:
    //   - input: the string the user wants to encrypt that only contains value in range. 
    // Returns:
    //   - String: the encrypted version of the given string  
    // Exceptions:
    //   - if the input is null, an IllegalArgumentException is thrown

    public String encrypt(String input){
        if(input == null){
            throw new IllegalArgumentException();
        }
        String returnString = "";
        returnString = this.ciphersMulti.get(0).encrypt(input);
        for(int i = 1; i < this.ciphersMulti.size(); i++){
            returnString = this.ciphersMulti.get(i).encrypt(returnString);
        } 
        return returnString;
    }

    // Behavior: 
    //   - This method decrypts a string using the list of ciphers used to encrypt.
    // Parameters:
    //   - input: the string the user wants to decrypt that only contains value in range. 
    // Returns:
    //   - String: the decrypted version of the given string 
    // Exceptions:
    //   - if the input is null, an IllegalArgumentException is thrown
    
    public String decrypt(String input){
        if(input == null){
            throw new IllegalArgumentException();
        }
        String returnString = "";
        returnString = this.ciphersMulti.get(ciphersMulti.size() -1).decrypt(input);
        for(int i = this.ciphersMulti.size() -2; i > -1; i--){
            returnString = this.ciphersMulti.get(i).decrypt(returnString);
        } 
        return returnString;
    }

}
