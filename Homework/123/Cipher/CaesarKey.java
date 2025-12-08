//Mahika Bagri
//CSE 123 
//8 October 2025

// This class is a type of cipher that is made using a given key word. It is able to encrypt
//and decrypt any text within a specified character range.  
public class CaesarKey extends Substitution {

    // Behavior: 
    //   - This method constructs a new caesar key cipher by creating the encryption key
    //by adding all characters in the encodable range (in order) to the given key
    // Parameters: 
    //   - key: the word/characters the encryption key should start with.
    // Exceptions:
    //   - if key is null, an IllegalArgumentException is throw.
    //   - if encoding is null, it's lenght is not equal to the length of characters in encodable 
    //range, contains a character outside the given range, or it contains the same letter twice, 
    //an IllegalArgumentException is thrown.


    public CaesarKey(String key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        super();
        for(int i = Cipher.MIN_CHAR; i <= Cipher.MAX_CHAR; i++){
            if(key.indexOf((char)(i))==-1){
                key = key + (char)(i);
            }
        }
        super.setEncoding(key);
    }
}
