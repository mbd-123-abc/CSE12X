//Mahika Bagri
//CSE 123 
//5 December 2025

import java.util.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the CSE 123 Collection Manager! " +
                           "To begin, enter your desired mode of operation:");
        System.out.println();
        System.out.println("1) Start with an empty collection manager");
        System.out.println("2) Load collection from file");
        System.out.print("Enter your choice here: ");

        int choice = Integer.parseInt(console.nextLine());
        while (choice != 1 && choice != 2) {
            System.out.println("Invalid choice! Try again");
            choice = Integer.parseInt(console.nextLine());
        }

        CollectionManager collectionManager = null;
        if (choice == 1) {
            collectionManager = new CollectionManager();
        } else { // choice == 2
            System.out.print("Enter file to read: ");
            String inFileName = console.nextLine();
            File inFile = new File(inFileName);
            while (!inFile.exists()) {
                System.out.println("  File does not exist. Please try again.");
                System.out.print("Enter file to read: ");
                inFileName = console.nextLine();
                inFile = new File(inFileName);
            }
    
            collectionManager = new CollectionManager(new Scanner(inFile));
            System.out.println("Collection manager created!");
            System.out.println();
        }

        menu();
        String option = console.nextLine();
        while (!option.equalsIgnoreCase("quit")) {
            System.out.println();

            if (option.equalsIgnoreCase("add")) {
                collectionManager.add(Chapter.parse(console));
                System.out.println();
            } else if (option.equalsIgnoreCase("contains")) {
                System.out.println("Enter chapter number: ");
                int number = console.nextInt();
                console.nextLine();
                System.out.println(collectionManager.contains(number));
                System.out.println();
            } else if (option.equalsIgnoreCase("print")) {
                System.out.println(collectionManager.toString());
                System.out.println();
            } else if (option.equalsIgnoreCase("filter")) {
                System.out.println("By [number], [pov], or [setting]?");
                String filterOption = console.nextLine();
                List<Chapter> filter = new ArrayList<Chapter>();
                if(filterOption.equalsIgnoreCase("number")){
                    System.out.println("Enter chapter number: ");
                    int number = console.nextInt();
                    console.nextLine();
                    filter = collectionManager.filterNumber(number);
                    filterPrint(filter);
                }else if(filterOption.equalsIgnoreCase("pov")){
                    System.out.println("Enter character name: ");
                    String pov = console.nextLine();
                    filter = collectionManager.filterPOV(pov);
                    filterPrint(filter);
                }else if(filterOption.equalsIgnoreCase("setting")){
                    System.out.println("Enter setting: ");
                    String setting = console.nextLine();
                    filter = collectionManager.filterSetting(setting);
                    filterPrint(filter);
                }else{
                    System.out.println("  Invalid choice. Please try again.");
                }
                System.out.println();
            } else if (option.equalsIgnoreCase("save")) {
                System.out.print("Enter file to save to: ");
                String outFileName = console.nextLine();
                PrintStream outFile = new PrintStream(new File(outFileName));
                collectionManager.save(outFile);
                System.out.println("Collection Manager exported!");
                System.out.println();
            } else if (!option.equalsIgnoreCase("quit")) {
                System.out.println("  Invalid choice. Please try again.");
                System.out.println();
            }

            menu();
            option = console.nextLine();
        }
    }
 
    private static void menu() {
        System.out.println("What would you like to do? Choose an option in brackets.");
        System.out.println("  [add] item");
        System.out.println("  [contains] item");
        System.out.println("  [print] my collection");
        System.out.println("  [save] my collection");
        System.out.println("  [creative] extension");
        System.out.println("  [quit] program");
    }

    //prints out all the chapters that were chosen by the given filter 
    private static void filterPrint(List<Chapter> filter){
        for(Chapter chapter:filter){
            System.out.println(chapter.toString());
        }
    }
}
