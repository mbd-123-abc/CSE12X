//Mahika Bagri 
//CSE 122 
//April 17 2025

// This is an model stock market, where you can buy, sell, display, and save stocks in a portfolio 

import java.util.*;
import java.io.*;

public class Stonks {
    //initilize class constant for file with market information 
    public static final String STOCKS_FILE_NAME = "stonks.tsv";

    public static void main(String[] args) throws FileNotFoundException {
        //initialize both a system scanner and a file scanner 
        Scanner console = new Scanner(System.in);
        Scanner fileScan = new Scanner(new File(STOCKS_FILE_NAME));
        //initialize all variables using the number of stocks, stated at the top of the file, as
        //the length of all arrays 
        int numStocks = Integer.parseInt(fileScan.nextLine());
        String[] stocks = new String[numStocks];
        double[] prices = new double[numStocks];
        double[] portfolio = new double[numStocks];
        //calls upin the load file method to actually fill correct values into the arrays
        loadFile(fileScan, stocks, prices);
        //welcomes user and tells them how many stocks there are
        System.out.println("Welcome to the CSE 122 Stocks Simulator!");
        System.out.println("There are " + numStocks + " stocks on the market:");
        //diplays all available stocks with their prices using a for loop(for each stock in market)
        for(int i = 0; i < numStocks; i++){
            System.out.println(stocks[i] + ": " + prices[i]);
        }
        //initializes an empty string for user input
        String selection = "";
        //as long as the string isn't q 
        while (!selection.equalsIgnoreCase("Q")) {
            //the program will keep prompting the user to pick a menu option of what to do
            System.out.println();
            System.out.println("Menu: (B)uy, (Se)ll, (D)isplay, (S)ave, (Q)uit");
            System.out.print("Enter your choice: ");
            selection = console.next();
            //based on what the user picks, an action occurs
            if(selection.equalsIgnoreCase("B")) {
                //if the user picks b/B (buy), the buy method is called allowing a user to buy and
                //add shares of a stock to their portfolio
                buy(console, stocks, prices, portfolio);
            }else if(selection.equalsIgnoreCase("Se")){
                //if the user picks SE/Se/se/sE (sell), the sell method is called allowing a user
                //to sell and remove shares of a stock from their portfolio
                sell(console, stocks, portfolio);
            }else if(selection.equalsIgnoreCase("D")){
                //if the user picks d/D (display), the display method is called, displaying their
                //stocks with amount of shares in portfolio 
                display(stocks, portfolio);
            }else if(selection.equalsIgnoreCase("S")){
                //if the user picks s/S (save), the save method is called, allowing a user to save
                //their portfolio into a file
                save(console, stocks, portfolio);
            }else if(!selection.equalsIgnoreCase("Q")){
                //if the selection is none of the above, nor q, the user is entering an invalid
                //option and will be told to try again
                System.out.println("Invalid choice: " + selection);
                System.out.println("Please try again");
            }
        }
        //the rest of main only occurs if the user chooses q/Q (quit) 
        //adds an extra line space
        System.out.println();
        //initializes a portfolio value storing variable 
        double portfolioValue = 0; 
        //for each stock, the for loop adds the value(shares*price) to the portfolio value
        for(int i = 0; i < numStocks; i++){
            portfolioValue += portfolio[i]*prices[i];
        }
        //prints out the portfolio value when the user quits 
        System.out.println("Your portfolio is currently valued at: $" + portfolioValue);        
    }

    // Behavior: 
    //   - This method populates the stocks and prices arrays from the file   
    // Parameters:
    //   - Scanner: a scanner of a file 
    //   - String[]: an array of stock tickers  
    //   - double[]: an array of stock prices
    // Returns:
    //   - None
    // Exceptions:
    //  - None
    public static void loadFile(Scanner fileScan, String[] stocks, double[] prices){
        //initilizes an indexing variable for the arrays
        int index = 0;
        //skips over a line because the first line is the headers of the columns
        fileScan.nextLine();
        //while the file has a next line 
        while(fileScan.hasNextLine()) {
            //the line is stored as a string, and that string gets a new string scanner
            String line = fileScan.nextLine();
            Scanner lineScan = new Scanner(line);
            //the first token is stored in the stocks (tickers) array at the index variable
            stocks[index] = lineScan.next();
            //the second token is stored in the prices array at the index variable
            prices[index] = lineScan.nextDouble();
            //adds oone to the index after each line so tokens tokens don't get stored over each
            //other
            index++;
        }
    }  

    // Behavior: 
    //   - This method finds the index of a specific ticker in the stocks array
    // Parameters:
    //   - String :a ticker of a specific stock
    //   - String[]: the stocks array that contains the tickers of all stocks 
    // Returns:
    //   - Index: the int index of the ticker
    // Exceptions:
    //  - None
    public static int findIndex(String ticker, String[] stocks){
        //initializes index variable to return
        int index = -1; 
        //for each stock in the stocks array
        for(int i = 0; i < stocks.length; i++){
            //if the ticker in the stock array at this index is the same as the ticker that we
            //need to find the index of
            if(stocks[i].equals(ticker)){
                //set the index value to this index of the stock array
                index = i;
            }
        }
        //return the index 
        return index; 
    }

    // Behavior: 
    //   - This method allows a user to buy shares of a stock with a given budget 
    //budget must be more than $5
    // Parameters:
    //   - Scanner: a scanner of System 
    //   - String[]: an array of stock tickers  
    //   - double[]: an array of stock prices
    //   - double[]: an array of shares owned of each stock  
    // Returns:
    //   - None
    // Exceptions:
    //  - None
    public static void buy(Scanner console, String[] stocks, double[] prices, double[] portfolio){
        //asks the user for the stock they want to buy and their budget
        System.out.print("Enter the stock ticker: ");
        String toBuy = console.next();
        System.out.print("Enter your budget: ");
        double budget = console.nextDouble();
        //if the budget is less than $5
        if(budget < 5){
            //let the user know that's not possible, budget must be over $5
            System.out.println("Budget must be at least $5");
        } else {
            //otherwise, find the index of the stock ticker
            int indexBuy = findIndex(toBuy, stocks);
            //add amount of shares user can buy in budget to the portfolio array of the same index
            portfolio[indexBuy] += budget/prices[indexBuy];
            //let the user know they bought shares of the stock 
            System.out.println("You successfully bought " + toBuy + ".");
        }
    }

    // Behavior: 
    //   - This method allows a user to sell shares of a stock unless they don't have enough stocks   
    // Parameters:
    //   - Scanner: a scanner of System 
    //   - String[]: an array of stock tickers  
    //   - double[]: an array of shares owned of each stock  
    // Returns:
    //   - None
    // Exceptions:
    //  - None
    public static void sell(Scanner console, String[] stocks, double[] portfolio){
        //asks the user for the stock they want to sell and how many shares they wanted to sell
        System.out.print("Enter the stock ticker: ");
        String toSell = console.next();
        System.out.print("Enter the number of shares to sell: ");
        double sharesSell = console.nextDouble(); 
        //find the index of the ticker of the stock in the stocks array
        int indexSell = findIndex(toSell, stocks);
        //if the user doesn't have enough shares
        if(portfolio[indexSell] < sharesSell){
            //let the user know that's not possible, they don't have enough shares to sell
            System.out.println("You do not have enough shares of " + toSell + " to sell " + 
            sharesSell + " shares.");
        }else{
            //otherwise, remove the sold shares from the portfolio
            portfolio[indexSell] -= sharesSell;
            //let the user know they successfully sold the shares 
            System.out.println("You successfully sold " + sharesSell + " shares of " + toSell + ".");
        }
    }

    // Behavior: 
    //   - This method diplays a persons portfolio to them by printing out the contents
    // Parameters:  
    //   - String[]: an array of stock tickers  
    //   - double[]: an array of shares owned of each stock  
    // Returns:
    //   - None
    // Exceptions:
    //  - None
    public static void display(String[] stocks, double[] portfolio){
        //Prints out the title 
        System.out.println("Portfolio: ");
        System.out.println();
        //for each stock in the stocks array
        for(int i = 0; i < stocks.length; i++){
            //if the portfolio contains shares of that stock 
            if(portfolio[i] > 0){
                //print out the stock and the number of shares the user has 
                System.out.println(stocks[i] + " " + portfolio[i]);
            }
        }
    }

    // Behavior: 
    //   - This method allows the user to save their portfolio in a file 
    // Parameters:
    //   - Scanner: a scanner of System 
    //   - String[]: an array of stock tickers  
    //   - double[]: an array of shares owned of each stock  
    // Returns:
    //   - None
    // Exceptions:
    //  - None
    public static void save(Scanner console, String[] stocks, double[] portfolio)
                                                    throws FileNotFoundException{
        //Prompts the user for a file name 
        System.out.print("Enter new portfolio file name: ");
        String fileName = console.next();
        //creates a new file and print stream in the file 
        File savedFile = new File(fileName);
        PrintStream out = new PrintStream(savedFile);
        //for each stock in the stocks array
        for(int i = 0; i < stocks.length; i++){
            //if the portfolio contains shares of that stock 
            if(portfolio[i] > 0){
                //print out the stock and the number of shares the user has in the file
                out.println(stocks[i] + " " + portfolio[i]);
            }
        }
    }

}
