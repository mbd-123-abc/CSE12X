//Mahika Bagri
//CSE 121
//January 28 2025

//creates ASCII artwork, specifically a heart, for all to enjoy
import java.util.*;
public class AsciiArt {
    public static void main(String[] args) {
        //Draws a ASCII Heart with the word करुणा
        System.out.println("Task 1: Draws a ASCII Heart with the word Karuna (Compassion)");
        System.out.println();

        System.out.println("        Karuna        Karuna        ");
        System.out.println("     KarunaKaruna  KarunaKaruna     ");
        System.out.println("   KarunaKarunaKarunaKarunaKaruna   ");
        System.out.println("   KarunaKarunaKarunaKarunaKaruna   ");
        System.out.println("   KarunaKarunaKarunaKarunaKaruna   ");
        System.out.println("      KarunaKarunaKarunaKaruna      ");
        System.out.println("         KarunaKarunaKaruna         ");
        System.out.println("            KarunaKaruna            ");
        System.out.println("               Karuna               ");
        System.out.println("                 **                 ");
        System.out.println();

        // Task 2: Draws a ASCII Heart multiple times vertically
        //To change the number of hearts drawn change the "numberOfHeartsVertical<5" statement
        System.out.println("Task 2: Draws a ASCII Heart multiple times vertically.");
        System.out.println();

        int numberOfHeartsVertical = 0;
        for(int i=0; i<5; i++){
            numberOfHeartsVertical++;
            System.out.println("        Karuna        Karuna        ");
            System.out.println("     KarunaKaruna  KarunaKaruna     ");
            System.out.println("   KarunaKarunaKarunaKarunaKaruna   ");
            System.out.println("   KarunaKarunaKarunaKarunaKaruna   ");
            System.out.println("   KarunaKarunaKarunaKarunaKaruna   ");
            System.out.println("      KarunaKarunaKarunaKaruna      ");
            System.out.println("         KarunaKarunaKaruna         ");
            System.out.println("            KarunaKaruna            ");
            System.out.println("               Karuna               ");
            System.out.println("                 **                 ");
            System.out.println();
        }

        System.out.println("Number of Shapes: " + numberOfHeartsVertical);
        System.out.println();

        // Task 3: Draws a ASCII Heart multiple times horizontally
        //To change the number of hearts drawn change the "numberOfHeartsHorizontal" variable

        System.out.println("Task 3: Draws a ASCII Heart multiple times horizontally.");
        System.out.println();

        //Note to grader: Please extend the console as necessary
        int numberOfHeartsHorizontal = 3;
        for(int line1OfHeart=0; line1OfHeart<numberOfHeartsHorizontal; line1OfHeart++){
          System.out.print("        Karuna        Karuna        ");
        }
        System.out.println();
        for(int line2OfHeart=0; line2OfHeart<numberOfHeartsHorizontal; line2OfHeart++){
            System.out.print("     KarunaKaruna  KarunaKaruna     ");
        }
        System.out.println();
        for(int line3OfHeart=0; line3OfHeart<numberOfHeartsHorizontal; line3OfHeart++){
            System.out.print("   KarunaKarunaKarunaKarunaKaruna   ");
        }
        System.out.println();
        for(int line4OfHeart=0; line4OfHeart<numberOfHeartsHorizontal; line4OfHeart++){
            System.out.print("   KarunaKarunaKarunaKarunaKaruna   ");
        }
        System.out.println();
        for(int line5OfHeart=0; line5OfHeart<numberOfHeartsHorizontal; line5OfHeart++){
            System.out.print("   KarunaKarunaKarunaKarunaKaruna   ");
        }
        System.out.println();
        for(int line6OfHeart=0; line6OfHeart<numberOfHeartsHorizontal; line6OfHeart++){
            System.out.print("      KarunaKarunaKarunaKaruna      ");
        }
        System.out.println();
        for(int line7OfHeart=0; line7OfHeart<numberOfHeartsHorizontal; line7OfHeart++){
            System.out.print("         KarunaKarunaKaruna         ");
        }
        System.out.println();
        for(int line8OfHeart=0; line8OfHeart<numberOfHeartsHorizontal; line8OfHeart++){
            System.out.print("            KarunaKaruna            ");
        }
        System.out.println();
        for(int line9OfHeart=0; line9OfHeart<numberOfHeartsHorizontal; line9OfHeart++){
            System.out.print("               Karuna               ");
        }
        System.out.println();
        for(int line10OfHeart=0; line10OfHeart<numberOfHeartsHorizontal; line10OfHeart++){
            System.out.print("                 **                 ");
        }
        System.out.println();
        
        System.out.println();
        System.out.println("Number of Shapes: " + numberOfHeartsHorizontal);
        System.out.println();

        // Creative Extension
        //Draws a ASCII Heart, with preffered word random number of times
        System.out.println("Creative Option 2: Draws a ASCII Heart, with preffered word,"+
        " random number of times.");
        System.out.println();

        //Note to user: 6 letter words are preffered
        String wordInHeart = "Mahika";
        //Randomizes number of hearts drawn 
        Random randomInt = new Random();
        int numberOfHearts = randomInt.nextInt(1000);

        for(int i=0; i<(int)numberOfHearts; i++){
            System.out.println("        "+wordInHeart+"        "+wordInHeart+"        ");
            System.out.println("     "+wordInHeart+wordInHeart+"  "+wordInHeart+wordInHeart+"     ");
            System.out.println("   "+wordInHeart+wordInHeart+wordInHeart+wordInHeart+wordInHeart+"   ");
            System.out.println("   "+wordInHeart+wordInHeart+wordInHeart+wordInHeart+wordInHeart+"   ");
            System.out.println("   "+wordInHeart+wordInHeart+wordInHeart+wordInHeart+wordInHeart+"   ");
            System.out.println("      "+wordInHeart+wordInHeart+wordInHeart+wordInHeart+"      ");
            System.out.println("         "+wordInHeart+wordInHeart+wordInHeart+"         ");
            System.out.println("            "+wordInHeart+wordInHeart+"            ");
            System.out.println("               "+wordInHeart+"               ");
            System.out.println("                 **                 ");
        }

        System.out.println();
        System.out.println("Number of Shapes: " + numberOfHearts);
    }
}
