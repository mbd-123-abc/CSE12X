// Mahika Bagri
// 12/5/2025
// CSE 123 
// C3: collection
// TA: Ben Wang

import java.io.*;
import java.util.*;

//this class 
public class Chapter implements Comparable<Chapter> {

    private final int number;
    private final String name;
    private final String pov;
    private final String setting;
    private final String purpose;
    private final List<String> keyEvents;

    // Behavior: 
    //   - This method contructs a new chapter.
    // Parameters:
    //   - number: the chapter number. 
    //   - name: the chapter name. 
    //   - pov: the pov the chapter is told from. 
    //   - setting: the chapter setting. 
    //   - purpose: thechapter purpose. 
    //   - keyEvents: the chapter key.events. 
    // Exceptions:
    //   - if the number is less than 1, an IllegalArgumentException is thrown
    //   - if the any of the other arguments are null/void, an IllegalArgumentException is thrown
    public Chapter(int number, String name, String pov, String setting, String purpose, 
    List keyEvents){
        if(number < 1||name == null||pov == null||setting == null||purpose == null||
        keyEvents == null||name.isEmpty()||pov.isEmpty()||setting.isEmpty()||purpose.isEmpty()||
        keyEvents.isEmpty()){
            throw new IllegalArgumentException();
        }

        this.number = number;
        this.name = name; 
        this.pov = pov; 
        this.setting = setting;
        this.purpose = purpose;
        this.keyEvents = keyEvents;
    }


    // Behavior: 
    //   - This method contructs a new chapter through input   
    // Parameters:
    //   - input: the scanner the user wants to put input in. 
    public static Chapter parse(Scanner input){
        System.out.print("Chapter Number: ");
        int number = input.nextInt();
        input.nextLine();

        System.out.print("Chapter Name: ");
        String name = input.nextLine();

        System.out.print("Which characters pov is this chapter told from? ");
        String pov = input.nextLine();

        System.out.print("Where is this chapter set? ");
        String setting = input.nextLine();

        System.out.print("What is the purpose of this chapter? ");
        String purpose = input.nextLine();

        System.out.print("What are the key event(s) in this chapter? \n");
        System.out.print("Type done once all events are listed. \n");
        List<String> keyEvents = new ArrayList<String>();
        String event = input.nextLine();
            while(!event.toLowerCase().equals("done")){
                keyEvents.add(event);
                event = input.nextLine();
            }
        
        return new Chapter(number, name, pov, setting, purpose, keyEvents);
    }

    // Behavior: 
    //   - This method classifies is a to string method for a chapter
    // Returns:
    //   - string: the string version of the chapter  
    public String toString() {
        String chapter = "";
        chapter += "Chapter " + this.number + ": " + this.name + ".\n";
        chapter += "This chapter is told from the perspective of " + this.pov + " in " + 
        this.setting + ".\n";
        chapter += "This chapter accomplishes " + this.purpose + " through the events: ";
        for(int i = 0; i < keyEvents.size() - 1; i++){
            chapter += keyEvents.get(i) + ", ";
        }
        chapter += keyEvents.get(keyEvents.size() - 1) + ".\n";
        return chapter; 
    }

    // Behavior: 
    //   - This method compares 2 chapters.
    // Parameters:
    //   - other: the chapter the user wants to compare to this one 
    // Returns:
    //   - int: the comparison value
    public int compareTo(Chapter other){
        if(this.number != other.number){
            return this.number - other.number; 
        } else if(!this.name.equals(other.name)){
            return this.name.compareTo(other.name);
        } else if(!this.pov.equals(other.pov)){
            return this.pov.compareTo(other.pov);
        } else if(!this.setting.equals(other.setting)){
            return this.setting.compareTo(other.setting);
        } else if(!this.purpose.equals(other.purpose)){
            return this.purpose.compareTo(other.purpose);
        } else if(!this.keyEvents.equals(other.keyEvents)){
            return other.keyEvents.size() - this.keyEvents.size();
        }
        return 0; 
    }

    //getter method for chapter number
    public int getNumber(){
        return this.number; 
    }

    // getter method for chapter name
    public String getName(){
        return this.name; 
    }

    // getter method for chapter character whose pov it's from
    public String getPOV(){
        return this.pov; 
    }

    // getter method for chapter setting
    public String getSetting(){
        return this.setting; 
    }

    // getter method for chapter purpose 
    public String getPurpose(){
        return this.purpose; 
    }

    // getter method for chapter key events
    public List getKeyEvents(){
        return this.keyEvents; 
    }

    // Behavior: 
    //   - This method creates a hashcode for the chapter.
    // Returns:
    //   - int: the hashcode
    public int hashCode(){
        int hashcode = 0; 
        hashcode += 31*this.number; 
        hashcode += 31*this.name.hashCode();
        hashcode += 31*this.pov.hashCode();
        hashcode += 31*this.setting.hashCode();
        hashcode += 31*this.purpose.hashCode();
        hashcode += 31*this.keyEvents.hashCode();
        return hashcode;
    }
}
