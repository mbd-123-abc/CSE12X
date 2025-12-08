//Mahika Bagri 
//CSE 122 
//29 May 2025

import java.util.*;

//checks for lines that are over 100 characters using Checks interface
public class LongLineCheck implements Check{

    // Behavior: 
    // Checks for lines over 100 characters long
    // Parameters:
    // - line: a String that contains a line from the file to check
    // - lineNumber: an integer that tells use the line number within the file
    // Returns:
    //   - Optional<Error>: an optional that contains the error, if there is one, else is empty
    // Exceptions:
    //   - None
	public Optional<Error> lint(String line, int lineNumber){
        int code = 1;
        String message = "Long Line";

        if(line.length() > 100){
            Error longLineError = new Error(code, lineNumber, message);
            return Optional.of(longLineError);
        }
        return Optional.empty();
    }
    
}
