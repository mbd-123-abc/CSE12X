//Mahika Bagri
//CSE 123 
//8 October 2025

// This class is a type of cipher that is made using a given number to shift the encryption range.
// It is able to encrypt and decrypt any text within a specified character range.  
public class CaesarShift extends Substitution {

    // Behavior: 
    //   - This method constructs a new caesar shift cipher by creating the encryption key
    //by shifting the given range of characters
    // Parameters: 
    //   - shift: the amount of characters by which the range should shift left.
    // Exceptions:
    //   - if shift is negative, an IllegalArgumentException is throw.

    public CaesarShift(int shift){
        if(shift<0){
            throw new IllegalArgumentException();
        }
        String encryption = "";
        for(int i = Cipher.MIN_CHAR; i <= Cipher.MAX_CHAR; i++){
            encryption = encryption + (char)(i);
        } 
        encryption = encryption.substring(shift%encryption.length(), encryption.length()) 
        +  encryption.substring(0, shift%encryption.length());
        super(encryption);
    }
}
