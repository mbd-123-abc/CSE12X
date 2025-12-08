//Mahika Bagri
//CSE 123 
//5 December 2025

// Mahika Bagri
// 12/5/2025
// CSE 123 
// C3: collection
// TA: Ben Wang

import java.io.*;
import java.util.*;

//this class is a collection manager that manages a collection. 
//it allows user to create, add, save, and resume collections, along with checking if elements 
//exist and filtering 
public class CollectionManager {
    private ChapterNode overallRoot;

    // Behavior: 
    //   - This method constructs an empty collection.    
    public CollectionManager(){
        this.overallRoot = null;
    }

    // Behavior: 
    //   - This method constructs a collection given a file of the collection (for resume).
    // Parameters:
    //   - input: the scanner that contains the file the user wants to load in. 
    // Exceptions:
    //   - if the input is null, an IllegalArgumentException is thrown
    //   - if the input is empty, an IllegalStateException is thrown
    public CollectionManager(Scanner input){
        if(input == null){
            throw new IllegalArgumentException();
        }

        while(input.hasNextLine()){
            String line = input.nextLine();
            if(line.toLowerCase().equals("new chapter")){
            
                int number = input.nextInt();
                input.nextLine();
                String name = input.nextLine();
                String pov = input.nextLine();
                String setting = input.nextLine();
                String purpose = input.nextLine();
                List<String> keyEvents = new ArrayList<String>();
                String event = input.nextLine();
                while(event.contains(",")){
                    int indexOfComma = event.indexOf(',');
                    keyEvents.add(event.substring(0, indexOfComma));
                    event = event.substring(indexOfComma+2); //bc have space after 
                }
                keyEvents.add(event);

                Chapter chapter = new Chapter(number, name, pov, setting, purpose, keyEvents);
                this.add(chapter);
            }
        }

        if(this.overallRoot == null){
            throw new IllegalStateException();
        }
    }

    // Behavior: 
    //   - This method adds a new item to your colleciton.
    // Parameters:
    //   - chapter: the item the user wants to add to the collection.
    // in this case, a chapter for a book 
    // Exceptions:
    //   - if the chapter is null, an IllegalArgumentException is thrown
    public void add(Chapter chapter){
        if(chapter == null){
            throw new IllegalArgumentException();
        }
        this.overallRoot = add(chapter, this.overallRoot);
    }

    // Behavior: 
    //   - This method adds a new item to your colleciton in the right position.
    // Parameters:
    //   - chapter: the item the user wants to add to the collection.
    // in this case, a chapter for a book 
    //.  - curr: the specific place that is being checked for if the chapter can begin
    //added there
    private ChapterNode add(Chapter chapter, ChapterNode curr){
        if(curr == null){
            curr = new ChapterNode(chapter);
        }

        if(chapter.compareTo(curr.chapter) < 0){
            curr.left = add(chapter, curr.left);
        } else if (chapter.compareTo(curr.chapter) > 0){
            curr.right = add(chapter, curr.right);
        } //if the same, do nothing bc copies are unnecessary in this collection

        return curr;
    }

    // Behavior: 
    //   - This method checks if your collection contains a specific item.
    // Parameters:
    //   - number: the chapter number you want to check if it's in your collection 
    // Returns:
    //   - boolean: wether or not the item is the collection
    //true if it isEmpty/false otherwise  
    // Exceptions:
    //   - if the input is a number lessthan 1, an IllegalArgumentException is thrown
    public boolean contains(int number) {
        if(number < 1){
            throw new IllegalArgumentException();
        }
        return contains(number, this.overallRoot);
    }

    // Behavior: 
    //   - This method checks if your collection contains a specific item by looking in the 
    // area it would most likely be found.
    // Parameters:
    //   - number: the chapter number you want to check if it's in your collection 
    //.  - curr: the specific place that is being checked for if the chapter number is there
    // Returns:
    //   - boolean: wether or not the item is the collection
    //true if it isEmpty/false otherwise  
    private boolean contains(int number, ChapterNode curr) {
        if (curr == null) {
            return false;
        } else if (curr.chapter.getNumber() == number) {
            return true;
        } else if (number < curr.chapter.getNumber()) {
            return contains(number, curr.left);
        } else {
            return contains(number, curr.right);
        }
    }

    // Behavior: 
    //   - This method saves a collection into a file.
    // Parameters:
    //   - output: the PrintStream of the file the user wants to save the collection to. 
    // Exceptions:
    //   - if the output isnull_, an IllegalArgumentException is thrown
    public void save(PrintStream output){
        if(output == null){
            throw new IllegalArgumentException();
        }
        save(output, this.overallRoot);
    }

    // Behavior: 
    //   - This method filters our collection for specific chapter numbers.
    // Parameters:
    //   - number: the number the user wants to filter for. 
    // Returns:
    //   - list: a list of all the chapter that have that number  
    // Exceptions:
    //   - if the number is less than 1, an IllegalArgumentException is thrown
    public List<Chapter> filterNumber(int number){
        if(number < 1){
            throw new IllegalArgumentException();
        }
        List<Chapter> numberFilterResults = new ArrayList<Chapter>();
        filterNumber(number, this.overallRoot, numberFilterResults);
        return numberFilterResults;
    }

    // Behavior: 
    //   - This method filters our collection for specific number.
    // Parameters:
    //   - pov: the number the user wants to filter for. 
    //   - list: a list of all the chapter that have that number  
    //   - curr: the current place that's being checked for the number
    private void filterNumber(int number, ChapterNode curr, List<Chapter> numberFilterResults){
        if (curr != null) {
            if (curr.chapter.getNumber() == number) {
                numberFilterResults.add(curr.chapter);
                filterNumber(number, curr.left, numberFilterResults);
                filterNumber(number, curr.right, numberFilterResults);
            } else if (number < curr.chapter.getNumber()) {
                filterNumber(number, curr.left, numberFilterResults);
            } else {
                filterNumber(number, curr.right, numberFilterResults);
            }
        } 
    }


    // Behavior: 
    //   - This method filters our collection for specific pov character.
    // Parameters:
    //   - pov: the name of the character the user wants to filter for. 
    // Returns:
    //   - list: a list of all the chapter that have that characters pov  
    // Exceptions:
    //   - if the pov is empty or null, an IllegalArgumentException is thrown    
    public List<Chapter> filterPOV(String pov){
        if(pov.isEmpty()||pov == null){
            throw new IllegalArgumentException();
        }
        List<Chapter> povFilterResults = new ArrayList<Chapter>();
        filterPOV(pov, this.overallRoot, povFilterResults);
        return povFilterResults;
    }

    // Behavior: 
    //   - This method filters our collection for specific pov.
    // Parameters:
    //   - pov: the pov the user wants to filter for. 
    //   - list: a list of all the chapter that have that pov  
    //   - curr: the current place that's being checked for the pov
    private void filterPOV(String pov, ChapterNode curr, List<Chapter> povFilterResults){
        if (curr != null) {
            if (curr.chapter.getPOV().equals(pov)) {
                povFilterResults.add(curr.chapter);
            }
            filterPOV(pov, curr.left, povFilterResults);
            filterPOV(pov, curr.right, povFilterResults); 
        } 
    }

    // Behavior: 
    //   - This method filters our collection for specific setting.
    // Parameters:
    //   - pov: the setting the user wants to filter for. 
    // Returns:
    //   - list: a list of all the chapter that have that setting  
    // Exceptions:
    //   - if the setting is empty or null, an IllegalArgumentException is thrown
    public List<Chapter> filterSetting(String setting){
        if(setting.isEmpty()||setting == null){
            throw new IllegalArgumentException();
        }
        List<Chapter> settingFilterResults = new ArrayList<Chapter>();
        filterSetting(setting, this.overallRoot, settingFilterResults);
        return settingFilterResults;
    }

    // Behavior: 
    //   - This method filters our collection for specific setting.
    // Parameters:
    //   - pov: the setting the user wants to filter for. 
    //   - list: a list of all the chapter that have that setting  
    //   - curr: the current place that's being checked for the setting
    private void filterSetting(String setting, ChapterNode curr, List<Chapter> settingFilterResults){
        if (curr != null) {
            if (curr.chapter.getSetting().equals(setting)) {
                settingFilterResults.add(curr.chapter);
            }
            filterSetting(setting, curr.left, settingFilterResults);
            filterSetting(setting, curr.right, settingFilterResults); 
        } 
    }


    // Behavior: 
    //   - This method saves a collection into a file.
    // Parameters:
    //   - output: the PrintStream of the file the user wants to save the collection to.
    //   - curr: the current place that's being saved 
    private void save(PrintStream output, ChapterNode curr){
        if(curr != null){
            if(curr != null){
                output.println("new chapter");

                output.println(curr.chapter.getNumber());
                output.println(curr.chapter.getName());
                output.println(curr.chapter.getPOV());
                output.println(curr.chapter.getSetting());
                output.println(curr.chapter.getPurpose());
                List<String> keyEvents = curr.chapter.getKeyEvents();
                for(int i = 0; i < keyEvents.size() - 1; i++){
                    output.print(keyEvents.get(i) + ", ");
                }
                output.println(keyEvents.get(keyEvents.size() - 1));

                save(output, curr.left);
                save(output, curr.right);
            }
        }
    }

    // Behavior: 
    //   - This method returns the string version of the collection.
    // Returns:
    //   - string: the string version of the collection  
    //if empty prints empty
    public String toString(){
        if(this.overallRoot == null){
            System.out.println("Empty Collection");
        }

        return toString(this.overallRoot, "");
    }

    // Behavior: 
    //   - This method returns the string version of the collection.
    // Returns:
    //   - string: the string version of the collection  
    //if empty prints empty
    //.  - curr: the current place thats being translated to sstring
    private String toString(ChapterNode curr, String soFar){
        if(curr == null){
            return ""; 
        }

        soFar += toString(curr.left, soFar);
        soFar += curr.chapter.toString() + "\n";
        soFar += toString(curr.right, soFar);

        return soFar;
    }

    //this class is a chapter node, that contains a chapter, and branches into 2 chapters  
    private static class ChapterNode {
        public final Chapter chapter;
        public ChapterNode left;
        public ChapterNode right;

        public ChapterNode(Chapter chapter) {
            this(chapter, null, null);
        }

        public ChapterNode(Chapter chapter, Chapter left, Chapter right) {
            if(chapter == null){
                throw new IllegalArgumentException();
            }
            this.chapter = chapter;
            if(left != null){
                this.left = new ChapterNode(left);
            }
            if(right != null){
                this.right = new ChapterNode(right);
            }
        }

    public ChapterNode(ChapterNode other) {
            if(other == null){
                throw new IllegalArgumentException();
            }
            this(other.chapter);
        }

        public String toString() {
            return this.chapter.toString();
        }
    }

}
