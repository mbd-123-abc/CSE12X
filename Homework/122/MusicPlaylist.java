//Mahika Bagri 
//CSE 122 
//1 May 2025


//This is a model music playlist, where you can add, play, and view songs, 
//& view, clear, and delete history

import java.util.*;
import java.io.*;

public class MusicPlaylist {
    public static void main(String[] args){
        //initialize all variables including a scanner, a user choice string, a playlist queue
        //and a history stack
        Scanner console = new Scanner(System.in);
        String userChoice = "";
        Queue<String> queue = new LinkedList<>();
        Stack<String> history = new Stack<>();
        //welcomes user to playlist
        System.out.println("Welcome to the CSE 122 Music Playlist!");
        //while the user doesn't want to quit
        while (!userChoice.equalsIgnoreCase("q")) {
            //print out menu options
            System.out.println("(A) Add song");
            System.out.println("(P) Play song");
            System.out.println("(H) Print history");
            System.out.println("(V) View playlist");
            System.out.println("(C) Clear history");
            System.out.println("(D) Delete from history");
            System.out.println("(Q) Quit");
            System.out.println();
            //prompting the user to pick a menu option of what to do
            System.out.print("Enter your choice: ");
            userChoice = console.nextLine();
            //if the user picks a/A (add), the addToPlaylist method is called allowing a user to
            // add songs to the playlist
            if (userChoice.equalsIgnoreCase("a")) {
                addToPlaylist(console, queue);
                System.out.println();
            //if the user picks p/P (play), the play method is called allowing a user to play songs
            //from their playlist
            } else if (userChoice.equalsIgnoreCase("p")) {
                play(console, queue, history);
                System.out.println();
            //if the user picks h/H (history), the history method is called allowing a user to view
            //their played history 
            } else if (userChoice.equalsIgnoreCase("h")) {
                history(history);
                System.out.println();
            //if the user picks v/V (view), the view method is called allowing a user to view
            //their playlist
            } else if (userChoice.equalsIgnoreCase("v")) {
                view(queue);
                System.out.println();
            //if the user picks c/C (clear), the clear method is called allowing a user to clear
            //their history
            } else if (userChoice.equalsIgnoreCase("c")) {
                clear(history);
                System.out.println();
            //if the user picks d/D (delete), the delete method is called allowing a user to delete
            //from their history
            } else if (userChoice.equalsIgnoreCase("d")) {
                delete(console, history);
                System.out.println();
            }
        }
    }

    // Behavior: 
    //   - This method allows the user to add all elements from a stack to another stack in order 
    // Parameters:
    //   - s1: a String stack that contains everything we need to move
    //   - s2: a String stack that is where we have to move all the queque elements to
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    public static void s2s(Stack<String> s1, Stack<String> s2) {
        while(!s1.isEmpty()){
            s2.push(s1.pop());

        }
    }

    // Behavior: 
    //   - This method allows the user to add songs to the playlist as long as the song has a name 
    // Parameters:
    //   - console: a scanner of the System that takes in user imput 
    //   - queue: a queue of the playlist songs
    // Returns:
    //   - None
    // Exceptions:
    //   - IllegalArgumentException: thrown when the song title is empty
    public static void addToPlaylist(Scanner console, Queue<String> queue){
        //Prompts the user for the song name
        System.out.print("Enter song name: ");
        String song = console.nextLine();
        //if there is no name, it throws an IllegalArgumentException
        if(song.isEmpty()){
            throw new IllegalArgumentException();
        }
        //if there is a name, the song is added to the playlist queque
        queue.add(song);
        System.out.println("Successfully added " + song);
    }

    // Behavior: 
    //   - This method allows the user to play the first song in the playlist queue, removes the
    //.    song from the queue and adds it to the history stack
    // Parameters:
    //   - console: a scanner of the System that takes in user imput 
    //   - queue: a queue of the playlist songs
    //   - history: a stack of the songs played/history
    // Returns:
    //   - None
    // Exceptions:
    //   - IllegalStateException: thrown wwhen the playlist queue is empty
    public static void play(Scanner console, Queue<String> queue, Stack<String> history){
        //if the queue is empty, throws an IllegalStateException
        if(queue.isEmpty()){
            throw new IllegalStateException();
        }
        //removes the first song from the queue and plays it
        String song = queue.remove();
        System.out.println("Playing song: " + song);
        //puts song in the history stack
        history.push(song);
    }

    // Behavior: 
    //   - This method allows the user to view the history of songs they have played 
    // Parameters:
    //   - history: a stack of the songs played/history
    // Returns:
    //   - None
    // Exceptions:
    //   - IllegalStateException: thrown when the history stack is empty
    public static void history(Stack<String> history){
        //if history is empty, throws the IllegalStateException
        if(history.isEmpty()){
            throw new IllegalStateException();
        }
        //creates an auxiliary stack 
        Stack<String> aux = new Stack<>();
        //prints out each song from the history stack and puts it into the auxiliary stack
        while(!history.isEmpty()){
            String song = history.pop();
            System.out.println("    " + song);
            aux.push(song);
        }
        //puts everything from the auxiliary stack back into the history stack 
        s2s(aux, history);
    }

    // Behavior: 
    //   - This method allows the user to view all songs in their playlist   
    // Parameters:
    //   - queue: a queue of the playlist songs
    // Returns:
    //   - None
    // Exceptions:
    //   - IllegalStateException: thrown if playlist is empty
    public static void view(Queue<String> queue){
        //if playlist is empty, throw IllegalStateException
        if(queue.isEmpty()){
            throw new IllegalStateException();
        }
        //initialize variable for playlist queue size because it will change
        int length = queue.size();
        //for each song in the playlist queue, remove it, print it, and add it back to the end
        for(int i = 0; i < length; i++){
            String song = queue.remove();
            System.out.println("    " + song);
            queue.add(song);
        }  
    }

    // Behavior: 
    //   - This method clears the played songs history   
    // Parameters:
    //   - history: a stack of the songs played/history
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    public static void clear(Stack<String> history){
        //initialize variable for history stack size because it will change
        int length = history.size();
        //for each song in the history, remove it
        for(int i = 0; i < length; i++){
            history.pop();
        }
    }

    // Behavior: 
    //   - This method allows users to delete a number of songs from their history
    //.    Positive number for top of history stack, and negative if from the bottom  
    // Parameters:
    //   - console: a scanner of the System that takes in user imput 
    //   - history: a stack of the songs played/history
    // Returns:
    //   - None
    // Exceptions:
    //   - IllegalArgumentException: thrown if number of songs the user wants to delete exceeds
    //     number of songs in history 
    public static void delete(Scanner console, Stack<String> history){
        //tells user how to use positive and negative to specify area of history to delete from
        System.out.println("A positive number will delete from recent history.");
        System.out.println("A negative number will delete from the beginning of history.");
        //prompts user for number of songs to delete
        System.out.print("Enter number of songs to delete: ");
        String stringNum = console.nextLine();
        int num = Integer.parseInt(stringNum);
        //if given absolute value of number exceeds number of songs in history, throws
        //IllegalArgumentException
        if(Math.abs(num) > history.size()){
            throw new IllegalArgumentException();
        }
        //if the number is greater than 0, takes off that many songs from top of history stack
        if(num > 0){
            for(int i = 0; i < num; i++){
                history.pop();
            }
        //if the number is less than 0
        } else if (num < 0) {
            //creates an auxiliary stack 
            Stack<String> aux = new Stack<>();
            //puts everything into the auxiliary stack, flipping the order
            s2s(history, aux);
            //takes off that many songs from top of auxiliary stack
            for(int i = 0; i > num; i--){
                aux.pop();
            }
            //puts everything back into the history stack
            s2s(aux, history);
        }
    }
}
