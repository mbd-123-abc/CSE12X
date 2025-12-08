//Mahika Bagri 
//CSE 122 
//April 24 2025

// This is an todo list maker, where you can add, mark as done, save, and load tasks
//The creative extention allows the user to sort their todo list by due date

import java.util.*;
import java.io.*;

public class TodoListManager {
    //initilize class constant for creative extension 
    public static final boolean EXTENSION_FLAG = false;

    public static void main(String[] args) throws FileNotFoundException {
        //initilize all necessary variables: a scanner, array list, string, and int 
		Scanner console = new Scanner(System.in);
        ArrayList<String> todo = new ArrayList<>();
        ArrayList<String> dueDates = new ArrayList<>();
        String userChoice = "";
        //welcomes user 
        System.out.println("Welcome to your TODO List Manager!");
        //lets user pick what they would like to do with their todo list next
        System.out.println("What would you like to do?");
		System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs, (S)ave TODOs, (Q)uit? ");
		userChoice = console.nextLine();
        //while the user doesn't want to quit
		while (!userChoice.equalsIgnoreCase("q")) {
            //if the user wants to add a todo list item, the add method is called
			if (userChoice.equalsIgnoreCase("a")) {
				add(console, todo, dueDates);
                //if the extention flag is on, sort the todo list by due date using sortByDueDate
                if (EXTENSION_FLAG && todo.size() > 1){
                    sortByDueDate(todo, dueDates);
                }
            //if the user wants to mark a todo list item as done, the markAsDone method is called
			} else if (userChoice.equalsIgnoreCase("m")) {
				markAsDone(console, todo);
            //if the user wants to load a todo list from a file, the load method is called
			} else if (userChoice.equalsIgnoreCase("l")) {
				load(console, todo);
            //if the user wants to save a todo list into a file, the save method is called
			} else if (userChoice.equalsIgnoreCase("s")) {
                save(console, todo);
            //if the user chooses to do something else, an unknown input error is printed
            } else if (!userChoice.equalsIgnoreCase("q")){
				System.out.println("Unknown input: " + userChoice);
			} 
            //the todos are all listed by the list method 
            list(todo);
            //prompts the user for what they want to do next 
            System.out.println("What would you like to do?");
			System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs, (S)ave TODOs, (Q)uit? ");
			userChoice = console.nextLine();
        }
    }

    // Behavior: 
    //   - This method lists all todos for the day in order, unless there is nothing on the todo
    //   list yet, then it will just tell the user to relax  
    // Parameters:
    //   - List<String>: String array list that contains all the todo items
    // Returns:
    //   - None
    // Exceptions:
    //  - None
    public static void list(List<String> todo) {
        //prints out title
        System.out.println("Today's TODOs:");
        //if there is nothing in the todo list, tells the user to relax
        if(todo.size()<1){
            System.out.println("  You have nothing to do yet today! Relax!");
        //otherwise, each todo list item is printed out chronologically 
        } else {
            for(int i = 0; i < todo.size();  i++){
                System.out.println("  " + (i+1) + ": " + todo.get(i));
            }
        }
    }

    // Behavior: 
    //   - This method adds something to the todo list, if it is not the first thing on the todo
    //   list then it also adds at an index specified by the user, unless the extention flag is on
    //.  then it just asks for a due date instead of an index  
    // Parameters:
    //   - Console: a scanner of the system that takes in user input 
    //   - Todo: String array list that contains all the todo items
    //   - Due Dates: String array list that contains Due Dates of the todo items at same index
    // Returns:
    //   - None
    // Exceptions:
    //  - None
    public static void add(Scanner console, List<String> todo, List<String> dueDates) {
        String index = "";
        //asks the user for the todo they want to add, and the due date (if extention on)
        System.out.print("What would you like to add? ");
        String item = console.nextLine();
        if (EXTENSION_FLAG){
            System.out.print("When is this item due (MM/DD/YY)? ");
            String dueDate = console.nextLine();
            dueDates.add(dueDate);
        //if todo list isn't empty, ask for a specific index for todo item 
        } else if(!todo.isEmpty()){
            System.out.print("Where in the list should it be (1-" + (todo.size()+1) +
            ")? (Enter for end): ");
            index = console.nextLine(); 
        }
        //if no index given, then just add item
        if(index.isEmpty()){
            todo.add(item);
        //else if index given, add item at index 
        } else {
            todo.add(Integer.parseInt(index)-1, item);
        }
    }

    // Behavior: 
    //   - This method marks a todo item as done, and removes it from the todo list, as long as the
    //   todo list is not empty and contains an element at that index  
    // Parameters:
    //   - Console: a scanner of the system that takes in user input 
    //   - Todo: String array list that contains all the todo items
    // Returns:
    //   - None
    // Exceptions:
    //  - None
    public static void markAsDone(Scanner console, List<String> todo) {
        //if the todo list is not empty, it asks the user which element to mark as done
        if(!todo.isEmpty()){
            System.out.print("Which item did you complete (1-" + todo.size() + ")? ");
            String index = console.nextLine();
            int numIndex = Integer.parseInt(index)-1;
            //it removes the todo item from the list
            todo.remove(numIndex);
        //otherwise it tells the user there is nothing left to do 
        } else {
            System.out.println("All done! Nothing left to mark as done!");
        }
    }

    // Behavior: 
    //   - This method takes a user txt file and creates a todo list from the todo list in the file
    // Parameters:
    //   - Console: a scanner of the system that takes in user input 
    //   - Todo: String array list that contains all the todo items
    // Returns:
    //   - None
    // Exceptions:
    //  - None
    public static void load(Scanner console, List<String> todo)
                                throws FileNotFoundException {
        //clears the current todo array of all todo list tasks 
        todo.clear();
        //prompts the user for a file name
        System.out.print("File name? ");
        String fileName = console.nextLine();
        //creates a scanner for the file and adds the todo list items in one by one to list
        Scanner fileScan = new Scanner(new File(fileName));
		while (fileScan.hasNextLine()) {
			String nextTodo = fileScan.nextLine();
			todo.add(nextTodo);
		}
    }

    // Behavior: 
    //   - This method saves the todo list to a txt file 
    // Parameters:
    //   - Console: a scanner of the system that takes in user input 
    //   - Todo: String array list that contains all the todo items
    // Returns:
    //   - None
    // Exceptions:
    //  - None
    public static void save(Scanner console, List<String> todo)
                                throws FileNotFoundException {
        //prompts the user for a file name
        System.out.print("File name? ");
        String fileName = console.nextLine();
        //prints out each todo list item onto the new file 
        PrintStream out = new PrintStream(new File(fileName));
		for (int i = 0; i < todo.size(); i++) {
			out.println(todo.get(i));
        }
    }

 
    // Behavior: 
    //   - This method is called when a new todo item is added to the todo array list and it uses 
    //.    an array list of due dates to figure out where to put the new todo item so the list is
    //.    sorted by due date
    // Parameters:
    //   - DueDates: String array list that contains all the due dates of todo items
    //.    corresponded by index 
    //   - Todo: String array list that contains all the todo items
    // Returns:
    //   - None
    // Exceptions:
    //  - None
    public static void sortByDueDate(List<String> todo, List<String> dueDates){
        //initializes an index variable 
        int index = 0;
        //takes the last todo element and its due date
        String newTodoString = todo.get(todo.size()-1);
        String newDueDate = dueDates.get(dueDates.size()-1);
        //turns the month, date, year into integers (from strings) of the new todo due date
        int month = Integer.parseInt(newDueDate.substring(0,2));
        int date = Integer.parseInt(newDueDate.substring(3,5));
        int year = Integer.parseInt(newDueDate.substring(6,8));
        //removes the last todo from the todos array
        todo.remove(todo.size()-1);
        dueDates.remove(dueDates.size()-1);
        //for loop that iterates through the due dates array 
        for(int i = 0; i < dueDates.size(); i++){  
            //finds the due date at the index and turns the month, date, year into integers
            String otherDueDate = dueDates.get(i);                  
            int monthOther = Integer.parseInt(otherDueDate.substring(0,2));
            int dateOther = Integer.parseInt(otherDueDate.substring(3,5));
            int yearOther = Integer.parseInt(otherDueDate.substring(6,8));
            //if the year of the new element is greater than the due date at this index, increase
            //index by one 
            if(year > yearOther){
                index++;
            //if the year of the new element is the same as the due date at this index, but
            //the month of the new element is greater than the month at this index, increase
            //index by one  
            } else if (year == yearOther && month > monthOther){
                index++;
            //if the month of the new element is the same as the due date at this index, but
            //the date of the new element is greater than the date at this index, increase
            //index by one 
            } else if (month == monthOther && date > dateOther){
                index++;
             //if the date of the new element is the same as the due date at this index
             //increase index by one
            } else if (year == yearOther && month == monthOther && date == dateOther){
                index++;
            }
        }    
        
        //add the new todo item and duedate at the specified index from the for loop
        todo.add(index, newTodoString);
        dueDates.add(index, newDueDate);
    }
}
