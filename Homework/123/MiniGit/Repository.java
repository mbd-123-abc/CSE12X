//Mahika Bagri
//CSE 123 
//29 October 2025

import java.util.*;
import java.text.SimpleDateFormat;

//This class is a git-like repository, where you can create, commit, synchronize respositories, and
//drop a commit
//You can also get some of its attributes: head (first commit), size history, check if it has
//a certian commit, and the string version of the repository

public class Repository {
    private Commit head;
    private int size;
    private String name; 

    // Behavior: 
    //   - This method creates a new repository with a given name
    // Parameters:
    //   - name: the string the user wants to name the repository
    // Exceptions:
    //   - if the name is empty or null, an IllegalArgumentException is thrown
    public Repository(String name){
        if(name == null||name.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.size = 0;
    }

    // Behavior: 
    //   - This method gets the first commit in the repository
    // If head is null, this method returns null
    // Returns:
    //   - String: the ID of the first commit in the repository
    public String getRepoHead(){
        if(this.head == null){
            return null;
        }
        return this.head.id;
    }

    // Behavior: 
    //   - This method gets the size of the repository
    // Returns:
    //   - int: the size of the repository
    public int getRepoSize(){
        return this.size;
    }

    // Behavior: 
    //   - This method gets the string version of the repository
    // Returns:
    //   - String: the string version of the repository
    //   - if the the first commit in the repository is null or the repository doesn't exist,
    //   it returns repositor y name
    //   - otherwise it returns repository name and the current repository head 
    public String toString(){
        if(this.head == null){
            return (this.name + " - No commits");
        }
        return (this.name + " - Current head: " + this.head.toString());
    }

    // Behavior: 
    //   - This method checks if a certain commit is in the repository, given its id
    // Parameters:
    //   - targetID: the id of the commit the user wants to check is in the repository
    // Returns:
    //   - boolean: wether the target id was found in the repository
    // Exceptions:
    //   - if the targetID is null, an IllegalArgumentException is thrown
    public boolean contains(String targetId){
        if(targetId == null){
            throw new IllegalArgumentException();
        }
        Commit current = this.head;
        while(current != null){
            if(current.id.equals(targetId)){
                return true;
            }
            current = current.past;
        }
        return false; 
    }

    // Behavior: 
    //   - This method gets the history of a repository (the last n commits)
    // if n is larger than the size of the repository, all commits in the repository are returned 
    // Parameters:
    //   - n: the number of commits the user wants the history of, 
    //   beginning at the last commit made by user  
    // Returns:
    //   - String: a list of commits in their string format seperated by newlines
    // Exceptions:
    //   - if the n is less than or equal to 0, an IllegalArgumentException is thrown
    public String getHistory(int n){
        if(n <= 0){
            throw new IllegalArgumentException();
        }
        if(n > this.size){
            n = this.size;
        }
        String history = "";
        Commit current = this.head;
        int i = 0;
        while((i < n) && (current != null)){
            history += current.toString();
            if(i < n -1){
                history += "\n";
            }
            current = current.past;
            i++;
        }
        return history;
    }

    // Behavior: 
    //   - This method creates a new commit with the given user message to the repository
    // Parameters:
    //   - message: the string the user wants as the message of the commit
    // Returns:
    //   - String: the id of the commit that was just made
    // Exceptions:
    //   - if the message is null, an IllegalArgumentException is thrown
    public String commit(String message){
        if(message == null){
            throw new IllegalArgumentException();
        }
        this.head = new Commit(message, this.head);
        this.size += 1;
        return this.head.id;
    }

    // Behavior: 
    //   - This method drops a given commit from the repository, given its ID
    // Parameters:
    //   - targetId: the id of the commit the user wants to drop from the repository
    // Returns:
    //   - boolean: wether or not the targetID was found in and dropped from the repository
    // Exceptions:
    //   - if the targetID is null, an IllegalArgumentException is thrown
    public boolean drop(String targetId){
        if(targetId == null){
            throw new IllegalArgumentException();
        }
        if(this.head!= null){
            if(this.head.id.equals(targetId)){
                this.head = this.head.past;
                size -= 1;
                return true;
            }
            Commit current = this.head;
            while(current.past != null){
                if(current.past.id.equals(targetId)){
                    current.past = current.past.past;
                    size -= 1;
                    return true;
                }
                current = current.past;
            }
        }
        return false;
    }

    // Behavior: 
    //   - This method synchronizes two repositories based on their given chronological order
    //   and turns this repository into the synchronized repository
    //   - The other repository is emptied 
    //   - If this repository has no commits, this repository becomes the other repository
    //   - If the other repository is empty, this repository is left as is
    //   - The size of both repositories are updated to reflect synchronization 
    // Parameters:
    //   - other: the repository the user wants to synchronize with this one
    // Exceptions:
    //   - if other is null, an IllegalArgumentException is thrown
    public void synchronize(Repository other){
        if(other == null){
            throw new IllegalArgumentException();
        }
        if (this.head == null) {
            this.head = other.head;
        }else if(other.head == null){
            //do nothing
        }else{
            Commit current = this.head;
            Commit head;
            Commit tail;
            if(current.timeStamp > other.head.timeStamp){
                head = current;
                tail = current;
                current = current.past;
            }else{
                head = other.head;
                tail = other.head;
                other.head = other.head.past;
            }
            while(current != null && other.head != null){
                if(current.timeStamp > other.head.timeStamp){
                    tail.past = current; 
                    current = current.past;
                }else{
                    tail.past = other.head; 
                    other.head = other.head.past;
                }
                tail = tail.past;
            }
            if(current != null){
                tail.past = current;
            }else if(other.head != null){
                tail.past = other.head;
            }
            this.head = head;
        }
        this.size += other.size;
        other.size = 0; 
        other.head = null;
    }

    /**
     * DO NOT MODIFY
     * A class that represents a single commit in the repository.
     * Commits are characterized by an identifier, a commit message,
     * and the time that the commit was made. A commit also stores
     * a reference to the immediately previous commit if it exists.
     *
     * Staff Note: You may notice that the comments in this 
     * class openly mention the fields of the class. This is fine 
     * because the fields of the Commit class are public. In general, 
     * be careful about revealing implementation details!
     */
    public static class Commit {

        private static int currentCommitID;

        /**
         * The time, in milliseconds, at which this commit was created.
         */
        public final long timeStamp;

        /**
         * A unique identifier for this commit.
         */
        public final String id;

        /**
         * A message describing the changes made in this commit.
         */
        public final String message;

        /**
         * A reference to the previous commit, if it exists. Otherwise, null.
         */
        public Commit past;

        /**
         * Constructs a commit object. The unique identifier and timestamp
         * are automatically generated.
         * @param message A message describing the changes made in this commit. Should be non-null.
         * @param past A reference to the commit made immediately before this
         *             commit.
         */
        public Commit(String message, Commit past) {
            this.id = "" + currentCommitID++;
            this.message = message;
            this.timeStamp = System.currentTimeMillis();
            this.past = past;
        }

        /**
         * Constructs a commit object with no previous commit. The unique
         * identifier and timestamp are automatically generated.
         * @param message A message describing the changes made in this commit. Should be non-null.
         */
        public Commit(String message) {
            this(message, null);
        }

        /**
         * Returns a string representation of this commit. The string
         * representation consists of this commit's unique identifier,
         * timestamp, and message, in the following form:
         *      "[identifier] at [timestamp]: [message]"
         * @return The string representation of this collection.
         */
        @Override
        public String toString() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(timeStamp);

            return id + " at " + formatter.format(date) + ": " + message;
        }

        /**
        * Resets the IDs of the commit nodes such that they reset to 0.
        * Primarily for testing purposes.
        */
        public static void resetIds() {
            Commit.currentCommitID = 0;
        }
    }
}
