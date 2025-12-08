//Mahika Bagri 
//CSE 121
//March 3 2025 

import java.util.*;

public class DDA {
    //initializing class constants 
    public static final String[] ACTIONS = {"⬆️", "➡️", "⬇️", "⬅️", "⏫"};
    public static final String[] RESPONSES = {"UP", "RIGHT", "DOWN", "LEFT", "JUMP"};
    public static final String STAR = "⭐";
    public static final boolean EXTENSION_FLAG = false;
    public static final String[] THEMES = {"Disco", "Classical", "Ballroom", "Ballet", "Hip-Hop", "Tap", "Salsa", "Bollywood"};
    
    //Behavior: uses all other methods to run everything 
    //Parameters: none
    //Returns: none
    public static void main(String args[]){
        //initializing parameters  
        Scanner console = new Scanner(System.in);
        Random randy = new Random();
        //calls all neccessary methods to play dance dance arrayvolution
        if (EXTENSION_FLAG == false){
            showActions();
            String[] movesSelected = makeMoves(console, randy);
            double finalScore = playGame(console, movesSelected);
            endScreen(finalScore, movesSelected.length);
        //calls all neccessary methods to play dance to impress
        } else {
            showActionsDTI();
            pickThemeDTI(randy);
            int dtiTurns = howManyMovesDTI(console);
            String[] stringMovesDTI = playGameDTI(console, dtiTurns);
            endScreenDTI(stringMovesDTI, randy);
        }
    }

    //Behavior: prints out welcome message and rules for extension 
    //Parameters: none
    //Returns: none
    public static void showActionsDTI(){
        //welcome message for user 
        System.out.println("Welcome to Dance To Impress!");
        System.out.println();
        System.out.println("These are the possible actions and their responses:");
        int amountOfActions = ACTIONS.length;
        //prints out each rule in the arrays together 
        for(int i = 0;i < amountOfActions; i++){
            System.out.println(RESPONSES[i] + ": " + ACTIONS[i]);
        }
    }
    
    //Behavior: prints out a random theme for the user  
    //Parameters: random
    //Returns: none
    public static void pickThemeDTI(Random randy){
        System.out.println();
        String chosenTheme = THEMES[randy.nextInt(THEMES.length)];
        System.out.println("Your theme is: " + chosenTheme);
        }

    //Behavior: the user can enter how many moves they'd like  
    //Parameters: none
    //Returns: none
    public static int howManyMovesDTI(Scanner console){
            System.out.print("How many moves would you like to play? ");
            int turns = console.nextInt();
            System.out.println();
            return turns;
    }

    //Behavior: It lets the player create their own array of moves 
    //Parameters: a scanner and an integer of turns  
    //Returns: the string of moves  
    public static String[] playGameDTI(Scanner console, int movesToPlay){
        System.out.println("Let's Dance!");
        String[] movesDTI = new String[movesToPlay];
        for(int i = 0; i < movesToPlay; i++){
            System.out.print((i+1)+": ");
            String response = console.next();
            movesDTI[i] = response;
        }
        return movesDTI; 
    }

    //Behavior: prints out ending screen 
    //Parameters: an array of the players moves and a random 
    //Returns: none
    public static void endScreenDTI(String[] playerMoves, Random randy){
        //checks and creates a string of how many stars should be given to the user
        System.out.println("These are your moves: " + Arrays.toString(playerMoves));
        String stars = "";
        double percentScored = randy.nextDouble(1);
        if (percentScored == 1){
            stars = "⭐⭐⭐⭐⭐";
        } else if (percentScored >= .8){
            stars = "⭐⭐⭐⭐";
        } else if (percentScored >= .6){
            stars = "⭐⭐⭐";
        } else if (percentScored >= .4){
            stars = "⭐⭐";
        } else {
            stars = "⭐";
        } 
        //prints. out ending stuff, ending message
        System.out.println();
        System.out.println("Woah that was groovy!");
        System.out.println("The audience rated your moves: "+stars+ "(" + (int)(percentScored*100)+"%)");
        System.out.println("Thanks for playing!");
    }

    //Behavior: prints out welcome message and rules 
    //Parameters: none
    //Returns: none
    public static void showActions(){
        //welcome message for user 
        System.out.println("Welcome to Dance Dance Arrayvolution!");
        System.out.println();
        System.out.println("These are the possible actions and their correct responses:");
        int amountOfActions = ACTIONS.length;
        //prints out each rule in the arrays together 
        for(int i = 0;i < amountOfActions; i++){
            System.out.println(ACTIONS[i] + ": " + RESPONSES[i]);
        }
    }

    //Behavior: it creates a random array of moves that the user plays
    //Parameters: a scanner and a random 
    //Returns: an array of moves 
    public static String[] makeMoves(Scanner console, Random randy){
        System.out.println();
        System.out.print("How many moves would you like to play? ");
        // the user can enter how many moves they'd like
        int turns = console.nextInt();
        System.out.println();
        String[] moves = new String[turns]; 
        //creates a new random action for each move in the array
        for(int i = 0;i < turns;i++){
             moves[i] = ACTIONS[randy.nextInt(ACTIONS.length)];
        }
        return moves;
    }

    //Behavior: checks wether a string belongs to the action array 
    //Parameters: takes one string 
    //Returns: index integar of string in array 
    public static int getActionIndex(String selectedAction){
        int indexOfSelectedAction = -1;
        //traverses array to check for string 
        for(int i = 0; i < ACTIONS.length;i++){
            if(ACTIONS[i].equals(selectedAction)){
                indexOfSelectedAction = i;
            }
        }
        return indexOfSelectedAction;
    }

    //Behavior: It checks wether the player typed the write answer for each move and changes score
    //Parameters: a scanner and an array of moves 
    //Returns: the score of the player after playing game 
    public static double playGame(Scanner console, String[] movesToPlay){
        System.out.println("Let's Dance!");
        double score = 0;
        //lets player play each move
        for(int i = 0; i < movesToPlay.length; i++){
            System.out.print("("+(i+1)+") "+movesToPlay[i]+": ");
            String response = console.next();
            response = response.toUpperCase();
            //checks if move had the right answer or not 
            String answer = RESPONSES[getActionIndex(movesToPlay[i])];
            if(response.equals(answer.toUpperCase())){
                score++;
            } else if (response.contains(answer.toUpperCase())){
                score = score + 0.5;
            }
        }
        return score; 
    }

    //Behavior: takes the scores and prints out how well the user scored 
    //Parameters: a double of the players score and an integar of the max possible score 
    //Returns: none
    public static void endScreen(double endScore, int maxScorePossible){
        //checks and creates a string of how many stars should be given to the user
        String stars = "";
        double percentScored = endScore/maxScorePossible;
        if (percentScored == 1){
            stars = "⭐⭐⭐⭐⭐";
        } else if (percentScored >= .8){
            stars = "⭐⭐⭐⭐";
        } else if (percentScored >= .6){
            stars = "⭐⭐⭐";
        } else if (percentScored >= .4){
            stars = "⭐⭐";
        } else {
            stars = "⭐";
        } 
        //prints. out ending stuff, ending message
        System.out.println();
        System.out.println("Woah that was groovy!");
        System.out.println("You Scored: "+stars+" (" +endScore+ "/" +maxScorePossible+")");
        System.out.println("Thanks for playing!");
    }
}
