//Mahika Bagri 
//CSE 122 
//April 10 2025

// This is a music box that lets you create your own melodies. It takes in each note and returns
//a song. It also takes any song and gives you the most common natural in each melody of the song. 

import java.util.*;

public class MusicBox {
    //initializing all class variables
    public static final String NOTES = "CDEFGAB";
    public static final String SHARP = "♯";
    public static final String FLAT = "♭";
    
    // Behavior: 
    //   - This method calls upon other methods to actually create the music box
    // Parameters:
    //   - None
    // Returns:
    //   - None
    public static void main(String[] args) {
        //initializing Scanner
        Scanner console = new Scanner(System.in);
        //calls upon composeSong method to create 2D array of song
        String[][] song = composeSong(console);
        //prints out the song 
        System.out.println("Returned song 2D array:");
        //for each melody
        for (int i = 0; i < song.length; i++) {
            //for each note
            for (int j = 0; j < song[0].length; j++) {
                //print out the note 
                System.out.print(song[i][j] + " "); 
            }
            //Start the next melody at the next line 
            System.out.println();
        }

        //Uncomment to test mostCommonNaturals method
        /*
        //Leave a line between 
        System.out.println();
        //Create a new string with a song 
        String[][] input = {};
        //Test the mostCommonNaturals method using a song
        String[] output = mostCommonNaturals(input);
        //Print out the most common note in the melody for each song 
        System.out.println(Arrays.deepToString(output));
        */
    }

    // Behavior: 
    //   - This method composes a song array based on user input  
    // Parameters:
    //   - Console: a scanner to take in user input 
    // Returns:
    //   - String[][]: a 2D string array where each cell contains a note, each row contains a
    //.  melody 
    public static String[][] composeSong(Scanner console){
        // Asks the user to enter number of melodies as a string  
        System.out.print("Enter the number of melodies: ");
        String melodies = console.nextLine();
        //Turns number of melodies into a useable Integer value  
        int numMelodies = Integer.parseInt(melodies);
        //Asks the user to enter number of notes in a melody as a string  
        System.out.print("Enter the length of each melody: ");
        String lenMelodies = console.nextLine(); 
        //Turns length of melodies into a useable Integer value  
        int numLenMelodies = Integer.parseInt(lenMelodies);
        //Initializes array with as many rows as user specified melodies, and as many columns as 
        //user specified length of melodies
        String[][] songArray = new String[numMelodies][numLenMelodies];
        //calls upon the userCreateSong method so that the user can actully populate the song array
        userCreateSong(numMelodies, numLenMelodies, songArray, console);
        //return the song as an array
        return songArray;
    }

    // Behavior: 
    //   - This method actually populates the song array based on user input 
    // Parameters:
    //.  - Int: an integer that contains the number of melodies in a song
    //.  - Int: an integer that contains the length of melodies in a song
    //   - String[][]: a 2D string array where each cell contains a note, each row contains a
    //.  melody    
    //   - Console: a scanner to take in user input 
    // Returns:
    //   - None
    public static void userCreateSong(int numMelodies, int numLenMelodies, String[][] songArray,
    Scanner console){
        //for each row/melody
        for(int i = 1; i <= numMelodies; i++){
            System.out.println();
            //Tell user which melody this is
            System.out.println("Composing melody #" + i);
            //for each column/note
            for(int j = 1; j <= numLenMelodies; j++){
                //Tell user which note this is 
                System.out.print("  Enter note #" + j + ": ");
                //Let them input the note, and add the note to the array
                String noteCell = console.next();
                //find the natural note in the entire note 
                String noteNatural = noteCell.substring(0, 1);
                //If the natural note is not an actual note, keep prompting the user for a new note
                while(!NOTES.contains(noteNatural)){
                    //Ask the user to try again 
                    System.out.print("  Please try again: ");
                    //reset the variable that contains the note and natural note to the new input
                    noteCell = console.next();
                    noteNatural = noteCell.substring(0, 1);
                }
                //if the note exists, set the cell (note in the correct melody) to the note 
                songArray[i-1][j-1] = noteCell;
            }
        }
        //Ask for the next note on the next line 
        System.out.println();
    }

    // Behavior: 
    //   - This method finds the most common note in each melody of a song  
    // Parameters:
    //   - naturalsMelodies: an array of the actual song  
    // Returns:
    //   - String[]: a 1D string array where each cell contains the most common note in the melody
    // with the same index 
    public static String[] mostCommonNaturals(String[][] naturalsMelodies){
        //Initializes string to fit the length of how many melodies the song has 
        String[] mostCommonString = new String[naturalsMelodies.length];
        //for each row/melody of the song array
        for(int i = 0; i < naturalsMelodies.length; i++){
            //initialize a new string for the counts of each natural note 
            int[] counts = new int[NOTES.length()];
            //for each note in the melody 
            for(int j = 0; j < naturalsMelodies[i].length; j++){
                //find the index of where the note is in the NOTES string using findIndex method
                int index = findIndex(naturalsMelodies[i][j]);
                //if it is is a natural note
                if(index != -1){
                    //increase the count of that natural note in the counts array by 1
                    counts[index]++;
                }
            }
            //using the greatestIndex method, find which natural note in the counts array has the 
            //earliest index and has the highest count
            int greatestIndex = greatestIndex(counts);
            //set the greates note for the melody in the gretest note array to that note 
            mostCommonString[i] = NOTES.substring(greatestIndex, greatestIndex+1);
        }
        //return the string of most common notes 
        return mostCommonString;
    }

    // Behavior: 
    //   - This method finds the index of a given natural note in the NOTES String   
    // Parameters:
    //   - note: a string that contains the note we want to index   
    // Returns:
    //   - int: the index of the note in the NOTES string 
    public static int findIndex(String note){
        //initializes return index 
        int ind = -1;
        //for each note in the NOTES string 
        for(int i = 0; i < NOTES.length(); i++){
            //if the note given is the same as the note at this index 
            if(NOTES.substring(i, i+1).equals(note)){
                //set the index to the index of the note in the NOTES string 
                ind = i; 
            }
        }
        //return the index 
        return ind;
    }

    // Behavior: 
    //   - This method finds the index of the most common note in a melody of a song  
    // Parameters:
    //   - counts: a 1d array of the counts of each natural note occurance in a melody
    // Returns:
    //   - Int: a integar that respresents the index of the most common note in the counts array
    public static int greatestIndex(int[] counts){
        //initializes the variables for index of most common note and amount of times the note
        //occurs 
        int greatestIndexCounts = 0;
        int actualCounts = 0; 
        //for each cell in the counts array
        for(int i = 0; i < counts.length; i++){
            //if the amount of counts at the cell is greater than the current greatest
            if(counts[i]>actualCounts){
                //set the greatest index to this cell 
                greatestIndexCounts = i;
                //set the greatest counts to the contents of this cell 
                actualCounts = counts[greatestIndexCounts];
            }
        }
        //return the greatest amount index 
        return greatestIndexCounts;
    }
}
