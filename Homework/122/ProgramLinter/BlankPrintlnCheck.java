//Mahika Bagri 
//CSE 122 
//29 May 2025

import java.util.*;

//checks for lines that contain blank println statements using Checks interface
public class BlankPrintlnCheck implements Check{

    // Behavior: 
    // Checks for lines that contain blank println statements
    // Parameters:
    // - line: a String that contains a line from the file to check
    // - lineNumber: an integer that tells use the line number within the file
    // Returns:
    //   - Optional<Error>: an optional that contains the error, if there is one, else is empty
    // Exceptions:
    //   - None
	public Optional<Error> lint(String line, int lineNumber){

        int code = 3;
        String message = "Contains Blank Println";

        String blankPrintlnString = "System.out.println(\"\")";

        if(line.contains(blankPrintlnString)){
            Error blankPrintlnError = new Error(code, lineNumber, message);
            return Optional.of(blankPrintlnError);
        }
        return Optional.empty();
    }
}
