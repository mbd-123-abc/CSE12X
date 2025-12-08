//Mahika Bagri 
//CSE 121
//February 4 2025 

//Simulates an election with known stats
import java.util.*;

public class ElectionSimulator {

    //class constants 
    public static final String PURPLE = "🟪";
    public static final String YELLOW = "🟡";
    public static final int NUM_SIMS = 5;
    public static final int NUM_DISTS = 10;
    public static final double PURPLE_POLL_AVG = .51;
    public static final double YELLOW_POLL_AVG = 1.0 - PURPLE_POLL_AVG;
    public static final double PURPLE_POLL_ERR = .05;
    public static final double YELLOW_POLL_ERR = -1.0 * .05;

    public static void main(String[] args) {
        //Welcome note + stats 
        System.out.println("Welcome to the Election Simulator!");
        System.out.println("Running " + NUM_SIMS + " simulations of " + NUM_DISTS + " districts.");
        System.out.println();
        System.out.println("The Purple Party is polling at " + PURPLE_POLL_AVG*100 + "%");
        System.out.println("The Yellow Party is polling at " + YELLOW_POLL_AVG*100 + "%");
        System.out.println();

        //Defining variables for this scope
        double purplePercentTotal = 0;
        double yellowPercentTotal = 0; 
        Random random = new Random();


        for (int simNum = 1; simNum <= NUM_SIMS; simNum++) {
            //Prints simulation #
            System.out.println("Running Simulation " + simNum + ":");
            //Defining variables for this scope 
            int totalPurpleVotes = 0;
            int totalYellowVotes = 0;
            int totalVotes = 0;

            //Loop for distributions within simulation 
            for (int i = 1; i <= NUM_DISTS; i++) {
                //Random district values 
                int districtTurnout = random.nextInt(1000)+1;
                double districtError = random.nextGaussian() * 0.5;
                //District poll percents 
                double purpleVotePercent = districtError * PURPLE_POLL_ERR + PURPLE_POLL_AVG;
                double yellowVotePercent = districtError * YELLOW_POLL_ERR + YELLOW_POLL_AVG;
                //Number of votes   
                int purpleVotes = (int) Math.round(districtTurnout*purpleVotePercent);
                int yellowVotes = (int) Math.round(districtTurnout*yellowVotePercent);  
                //Prints out the district # of votes per party     
                System.out.println("  District #" + i + " - "+PURPLE+" " + purpleVotes + "  "+YELLOW+" " + yellowVotes);
                //cumSum for all district votes 
                totalPurpleVotes += purpleVotes;
                totalYellowVotes += yellowVotes;
                totalVotes += purpleVotes + yellowVotes;
            }

            //Prints out simulation results 
            System.out.println();
            System.out.println("Results for Simulation " + simNum + ":");
            System.out.println("  Total Turnout: " + totalVotes);
            //calculates vote percents 
            double totalPurpleVotesPercentage = Math.round(10000.0*totalPurpleVotes/totalVotes)/100.0;
            double totalYellowVotesPercentage = Math.round(10000.0*totalYellowVotes/totalVotes)/100.0;
            System.out.println("  Purple Party's votes: " + totalPurpleVotes + " (" + totalPurpleVotesPercentage + "%)");
            System.out.println("  Yellow Party's votes: " + totalYellowVotes + " (" + totalYellowVotesPercentage + "%)");
            //prints out visuals 
            System.out.print("  Visualization: ");
            int purpleVoteVisual = totalPurpleVotes/100;
            int yellowVoteVisual = totalYellowVotes/100;
            //Loop for purple party votes, in 100's
            for (int i = 1; i <= purpleVoteVisual; i++) {
                System.out.print(PURPLE);
            }
            System.out.println();
            System.out.print("                 ");
            //Loop for yellow party votes, in 100's
            for (int i = 1; i <= yellowVoteVisual; i++) {
                System.out.print(YELLOW);
            }
            System.out.println();
            System.out.println();
            System.out.println();
            //all simulation percent cumSum 
            purplePercentTotal += totalPurpleVotesPercentage;
            yellowPercentTotal += totalYellowVotesPercentage;
        }
        //Election results
        System.out.println("Election Simulator Results:");
        double purpleAvgPercent = Math.round((purplePercentTotal/NUM_SIMS)*100)/100.0;
        double yellowAvgPercent = Math.round((yellowPercentTotal/NUM_SIMS)*100)/100.0;
        boolean purpleWin = purpleAvgPercent >= 50; 
        boolean yellowWin = yellowAvgPercent >= 50;
        System.out.println(PURPLE+" Win = " + purpleWin + " (" + purpleAvgPercent + "%)");
        System.out.println(YELLOW+" Win = " + yellowWin + " (" + yellowAvgPercent + "%)");
    }
}
