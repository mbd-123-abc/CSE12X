//Mahika Bagri 
//CSE 122 
//29 May 2025

import java.util.*;

//checks for lines that contain "break" outside of inline comments using Checks interface
public class BreakCheck implements Check{

    // Behavior: 
    // Checks for lines that contain "break" outside of inline comments
    // Parameters:
    // - line: a String that contains a line from the file to check
    // - lineNumber: an integer that tells use the line number within the file
    // Returns:
    //   - Optional<Error>: an optional that contains the error, if there is one, else is empty
    // Exceptions:
    //   - None
	public Optional<Error> lint(String line, int lineNumber){

        int code = 2;
        String message = "Contains Break";

        String breakString = "break";
        String inlineCommentString = "//";

        if(line.contains(inlineCommentString)){
            int inlineCommentIndex = line.indexOf(inlineCommentString);
            line = line.substring(0, inlineCommentIndex); 
        }

        if(line.contains(breakString)){
            Error breakError = new Error(code, lineNumber, message);
            return Optional.of(breakError);
        }

        return Optional.empty();
    }
}
