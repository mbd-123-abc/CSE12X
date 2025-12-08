//Mahika Bagri 
//CSE 121
//February 25 2025

//takes and analyzes patient data to classify their priority in ER waiting room
import java.util.*;
public class PatientPrioritizer {
    //hospital zip code as a class constant 
    public static final int HOSPITAL_ZIP = 12345;
    //Method main: intiates variables, calls methods to analyze patient data, outputs all analysis
    //no value returned, no parameters
    public static void main(String args[]){
        //initializing all necessary values
        Scanner console = new Scanner(System.in);
        int numPatients = 0;
        int maxPriorityScore = 0;
        //prints out intro paragraph greeting users 
        intro();
        //stores patient name for later use 
        String patientName = inputPatientName(console);
        //keeps prompting for patient information until asked to quit
        while (!patientName.equals("quit")){
            //adds 1 to number of patients served
            numPatients++;
            //asks and stores patient info and info analysis 
            int patientScore = userInput(console);
            priorityScorePrint(patientName, patientScore);
            patientName = inputPatientName(console);
            //changes max score as greater values come in
            if(patientScore>maxPriorityScore){
                maxPriorityScore = patientScore;
            }
        }
        //prints out stats for the day 
        daySummary(numPatients, maxPriorityScore);
    }

    //Method 1: prints intro message to user, no value returned, no parameters
    public static void intro() {
        System.out.println("Hello! We value you and your time, so we will help");
        System.out.println("you prioritize which patients to see next!");
        System.out.println("Please answer the following questions about the next patient so");
        System.out.println("we can help you do your best work :)");
         System.out.println();
    }

    //Method 2: records patient name parameter: scanner to input name, returns: patient name string
    public static String inputPatientName(Scanner nameConsole){
        System.out.println("Please enter the next patient's name or \"quit\" to end the program.");
        System.out.print("Patient's name: ");
        //input for patients name and returns the name 
        String name = nameConsole.next();
        return name;
    }

    //Method 3: allows user to input age, zip code, insurance information, pain level, temperature
    //analyses info using method 4 
    //parameter: scanner to input all information, returns: int patient priority score
    public static int userInput(Scanner infoConsole){
        //input patients age 
        System.out.print("Patient age: ");
        int age = infoConsole.nextInt();
        //input patients zip code 
        System.out.print("Patient zip code: ");
        int zipCode = infoConsole.nextInt();
        //check if zip code is valid 
        boolean zipCodeValid = fiveDigits(zipCode);
        //if not valid asks for a valid zip code 
        while (!zipCodeValid){
            System.out.print("Invalid zip code, enter valid zip code: ");
            zipCode = infoConsole.nextInt();
            zipCodeValid = fiveDigits(zipCode);  
        }
        //input wether patients insurance will cover this visit/if it's in network 
        System.out.print("Is our hospital \"in network\" for the patient's insurance? ");
        String insurance = infoConsole.next();
        //input patients pain level  
        System.out.print("Patient pain level (1-10): ");
        int pain = infoConsole.nextInt();
        //if not valid pain level asks again for a valid pain level
        while (pain<1|pain>10){
            System.out.print("Invalid pain level, enter valid pain level (1-10): ");
            pain = infoConsole.nextInt();
        }
        //input patients temperature in Fahrenheit
        System.out.print("Patient temperature (in degrees Fahrenheit): ");
        double temperature = infoConsole.nextDouble();
        System.out.println();
        //calls upon method 4 to analyze data for priority and stores it 
        int score = priorityScore(age, zipCode, insurance, pain, temperature);
        //returns priority number 
        return score;
    }

    //Method 4: computes priority score utilizing multiple factors 
    //parameters: all patient information needed for priority analysis 
    //returns: int patient priority score to method 3 ONLY 
    public static int priorityScore(int age, int zipCode, String insurance, int pain, double 
    temperature){
        //initializes variable 
        int score = 100;
        //checks for high priority ages (less than 12 or 65+) 
        if(age<12|age>=65){
            score = score + 40;
        }
        //checks if the zip code fist numbers match
        if (HOSPITAL_ZIP/10000==zipCode/10000){
            score = score + 27;
            //checks if the zip code second numbers match
            if(HOSPITAL_ZIP/1000==zipCode/1000){
                score = score + 20;
            }
        }
        //checks if insurance will cover this trip, and adds priority if it does
        if (insurance.equals("y")|insurance.equals("yes")){
            score = score + 17;
        }
        //checks for high priority pain (more than 7) plus just pain in general
        if (pain < 7){
            score = score + pain + 10;
        } else {
            score = score + pain + 100;
        }
        //checks for high priority temperature (more than 99.5 F)
        if (temperature > 99.5){
            score = score + 30; 
        }
        //returns new score based on input changes 
        return score;
    }

    //Method 5: prints patients priority back to patient 
    //parameters: patients name and patients priority score   
    //returns: nothing 
    public static void priorityScorePrint(String name, int score) {
        //classifies priority score 
        String priority = "";
        if (score>=250){
            priority = "high";
        } else if (score<150){
            priority = "low";
        } else {
            priority = "medium";
        } 
        //general print statements using parameters 
        System.out.println("We have found patient " + name + " to have a priority score of: "
        + score);
        System.out.print("We have determined this patient is " + priority + " priority");
        //the last line differs based on previous classification 
        if (score>=250){
            System.out.println(",");
            System.out.println("and it is advised to call an appropriate medical provider ASAP.");
        } else if (score<150){
            System.out.println(".");
            System.out.println("Please put them on the waitlist for when a medical provider becomes" 
            + " available.");
        } else {
            System.out.println(".");
            System.out.println("Please assign an appropriate medical provider to their case");
            System.out.println("and check back in with the patient's condition in a little while.");
        }
        System.out.println();
        //general thank you paragraph 
        System.out.println("Thank you for using our system!");
        System.out.println("We hope we have helped you do your best!");
        System.out.println();
    }

    //Method 6: prints out all stats for the entire day: number of patients seved, highest score
    //parameters: number of patients and maximum priority score of the day  
    //returns: nothing   
    public static void daySummary(int numPatients, int maxScore){
        System.out.println("Statistics for the day:");
        //general print statements using parameters
        System.out.println("..." + numPatients + " patients were helped");
        System.out.println("...the highest priority patient we saw had a score of " + maxScore);
        System.out.println("Good job today!");
    }

    //Method 7: checks wether zip code has 5 digits
    //parameter: input zip code of patient
    //returns: boolean of wether the zip code has 5 digits 
    public static boolean fiveDigits(int val) {
        val = val / 10000; // get first digit
        if (val == 0) { // has less than 5 digits
            return false;
        } else if (val / 10 == 0) { // has 5 digits
            return true;
        } else { // has more than 5 digits
            return false; 
        }
    }
}
