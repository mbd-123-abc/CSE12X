//Mahika Bagri 
//CSE 122 
//29 May 2025

import java.util.*;
import java.io.*;

//checks for specified coding errors within a file 

public class Linter {
    private List<Check> linterChecks;

    // Behavior: 
    // initializes a list of checks into the Linter class
    // Parameters:
    // - checks: list of all checks that should be conducted
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    public Linter(List<Check> checks){
        linterChecks = checks;
    }

    // Behavior: 
    // Checks for all necessary code errors within a given file
    // Parameters:
    // - fileName: the name of the file that must be checked for errors
    // Returns:
    //   - List<Error>: List of all errors found in the file 
    // Exceptions:
    //   - None
    public List<Error> lint(String fileName) 
        throws FileNotFoundException{
            List<Error> linterErrors = new ArrayList<>();

            Scanner fileScan = new Scanner(new File(fileName));
            int lineNumber = 1;

            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();

                for(int i = 0; i < linterChecks.size(); i++){
                    Check currentCheck = linterChecks.get(i);
                    Optional<Error> currentErrorOption = currentCheck.lint(line, lineNumber);
                    if (currentErrorOption.isPresent()) {
                        linterErrors.add(currentErrorOption.get());
                    }
                }

                lineNumber++;
            }
            return linterErrors;
        }

}  
