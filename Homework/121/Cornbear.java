//Mahika Bagri
//CSE 121
//January 19 2025

//Creates a bill for Cornbear's Cafe 
public class Cornbear{
    public static void main(String[] args){
        
        //prices of items as variables (doubles)
        double espressoPrice = 4.08;
        double lattePrice = 6.25;
        double fogPrice = 4.87;

        //amount of item bought as variables (int)
        int espressoQuantity = 2; 
        int latteQuantity = 7; 
        int fogQuantity = 4;
        int totalNumberOfItemsPurchased = 
        espressoQuantity 
        + latteQuantity 
        + fogQuantity;

        //calculates subtotals as a variable
        double espressoSubtotal = espressoPrice*espressoQuantity;
        double sesameLatteSubtotal = lattePrice*latteQuantity;
        double seattleFogSubtotal = fogPrice*fogQuantity;
        double subtotalOfEverything = espressoSubtotal + sesameLatteSubtotal + seattleFogSubtotal;
        
        //calculates taxes and fees as a variable
        double tax = (int)(subtotalOfEverything*.1035*100)/100.0;
        double serviceFee = 1.21;
        double cornbearFee = (int)(subtotalOfEverything%totalNumberOfItemsPurchased*100)/100.0;

        //calculates total as a variable
        double total = (int)((subtotalOfEverything + tax + serviceFee + cornbearFee)*100)/100.0;

        //calculates date and time as a variable 
        double numberOfHours = 121;
        int daysSinceJanuary1st = (int)numberOfHours/24;
        //date
        int month = 1;
        int date = daysSinceJanuary1st+1;
        int year = 2025;
        //current time
        int hourOfCurrentTime = (int)numberOfHours%24;
        int minuteOfCurrentTime = (int)((numberOfHours - 
        (int)numberOfHours)*60);

        //prints the reciept
        //header
        System.out.println("-------------------------");
        System.out.println("     Cornbear's Café");
        System.out.println(" University of Washington"); 
        System.out.println("        \"Seattle\"");
        System.out.println("-------------------------");
        //items
        System.out.println(espressoQuantity + " " + "Espresso" + 
        "         $" + espressoSubtotal);
        System.out.println(latteQuantity + " " + "Sesame Latte" + 
        "     $" + sesameLatteSubtotal);
        System.out.println(fogQuantity + " " + "Seattle Fog" + 
        "      $" + seattleFogSubtotal);
        //subtotal
        System.out.println("-------------------------");
        System.out.println("Subtotal           $" + subtotalOfEverything);
        System.out.println("-------------------------");
        //taxes and fees 
        System.out.println("Tax                $" + tax);
        System.out.println("Service fee        $" + serviceFee);
        System.out.println("Cornbear fee       $" + cornbearFee);
        //total
        System.out.println("-------------------------");
        System.out.println("Total              $" + total);
        System.out.println("-------------------------");
        //date and time
        System.out.println("       thank you <3");
        System.out.println("      " + hourOfCurrentTime + ":" + "00" + 
        " " + month + "/" + date + "/" + year);  
    }
}
