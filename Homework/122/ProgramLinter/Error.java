//Mahika Bagri 
//CSE 122 
//29 May 2025

//Constructs an error whenever one is detected 
public class Error{
    private int errorCode;
    private int errorLineNumber; 
    private String errorMessage;

    // Behavior: 
    // Constructs an Error
    // Parameters:
    // - code: an integer that represents the code number of the error \
    // - line: a String that contains a line from the file to check
    // - lineNumber: an integer that tells use the line number within the file
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    public Error(int code, int lineNumber, String message){
        errorCode = code;
        errorLineNumber = lineNumber;
        errorMessage = message;
    }

    // Behavior: 
    // Turns the error into a string
    // Parameters:
    // - None
    // Returns:
    //   - String: The string that represents the error
    // Exceptions:
    //   - None
    public String toString(){
        String errorToString = "(Line: "+errorLineNumber+") has error code "+errorCode+": " 
        +errorMessage;
        return errorToString; 
    }

    // Behavior: 
    // finds and returns the current errors line number 
    // Parameters:
    // - None
    // Returns:
    //   - int: the current errors line number
    // Exceptions:
    //   - None
    public int getLineNumber(){
        return errorLineNumber;
    }

    // Behavior: 
    // finds and returns the current errors error code 
    // Parameters:
    // - None
    // Returns:
    //   - int: the current errors error code
    // Exceptions:
    //   - None
    public int getCode(){
        return errorCode;
    }

    // Behavior: 
    // finds and returns the current errors error message 
    // Parameters:
    // - None
    // Returns:
    //   - String: the current errors error message
    // Exceptions:
    //   - None
    public String getMessage(){
        return errorMessage;
    }

}
