//Mahika Bagri
//CSE 123 
//12 November 2025

import java.util.*;

// A client class for creating scenarios (list of regions) and allocating relief to said regions.
public class Client {
    private static final Random RAND = new Random();

    public static void main(String[] args) throws Exception {
        // List<Region> scenario = createRandomScenario(10, 10, 100, 1000, 100000);
        List<Region> scenario = createSimpleScenario();
        System.out.println(scenario);
        
        double budget = 2000;
        Allocation allocation = allocateRelief(budget, scenario);
        printResult(allocation, budget);
    }

    // Behavior: 
    //   - This method initializes relief allocation to regions in the provided list of regions
    // within the given budget
    //   - It prioritizes regions with highest populations and after that regions with lowest cost
    //Return:
    //   - Allocation: the finalized allocation of funds to regions within the given budget
    // Parameters: 
    //   - budget: a double that signifies the maximum amount of money that can be spent on 
    // disaster relief 
    //   - sites: a list of regions that are in need of diaster relief
    // Exceptions:
    //   -if the list of regions is null, an IllegalArgumentException() is throw
    public static Allocation allocateRelief(double budget, List<Region> sites) {
       Allocation regionsChosen = allocateRelief(budget, sites, new Allocation());
       return regionsChosen;
    }

    // Behavior: 
    //   - This method initializes relief allocation to regions in the provided list of regions
    // within the given budget by exploring each branch of possibilities
    //   - It prioritizes regions with highest populations and after that regions with lowest cost
    //Return:
    //   - Allocation: the finalized allocation of funds to regions within the given budget
    // Parameters:
    //   - budget: a double that signifies the maximum amount of money that can be spent on 
    // disaster relief 
    //   - sites: a list of regions that are in need of diaster relief
    //   - regionsChosen: an Allocation that signifies the regions chosen previously 
    // in A branch of the allocation process; in simple terms, the partial solution as the method
    // thinks through all possiblities
    // Exceptions:
    //   -if the list of regions is null, an IllegalArgumentException() is throw
    private static Allocation allocateRelief(double budget, List<Region> sites, Allocation regionsChosen){
        if(sites == null){
            throw new IllegalArgumentException();
        }
        if(sites.isEmpty()||budget < lowestRegionCost(sites)){
            return regionsChosen;
        }
        Allocation best = null;
        for(int i=0; i < sites.size(); i++){
            Region site = sites.remove(i);
            double cost = site.getCost();
            if(budget-cost>=0){
                Allocation result = allocateRelief(budget-cost, sites, 
                regionsChosen.withRegion(site));
                if(result != null){
                    if(best == null||result.totalPeople()>best.totalPeople()||
                    (result.totalPeople()==best.totalPeople()&&
                    result.totalCost()<best.totalCost())){
                        best = result; 
                    }
                }
            }
            sites.add(i, site);
        }
        return best;
    }

    // Behavior: 
    //   - This method finds the lowest cost of one region in a given list of regions
    //Return:
    //   - double: the cost of the lowest costing region(s)
    // Parameters: 
    //   - sites: a list of regions that are in need of diaster relief
    private static double lowestRegionCost(List<Region> sites){
        double minCost = sites.getFirst().getCost();
        for(int i =1; i < sites.size(); i++){
            if(sites.get(i).getCost()<minCost){
                minCost = sites.get(i).getCost();
            }
        }
        return minCost; 
    }

    ///////////////////////////////////////////////////////////////////////////
    // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!** //
    ///////////////////////////////////////////////////////////////////////////
    
    /**
    * Prints each allocation in the provided set. Useful for getting a quick overview
    * of all allocations currently in the system.
    * @param allocations Set of allocations to print
    */
    public static void printAllocations(Set<Allocation> allocations) {
        System.out.println("All Allocations:");
        for (Allocation a : allocations) {
            System.out.println("  " + a);
        }
    }

    /**
    * Prints details about a specific allocation result, including the total people
    * helped, total cost, and any leftover budget. Handy for checking if we're
    * within budget limits!
    * @param alloc The allocation to print
    * @param budget The budget to compare against
    */
    public static void printResult(Allocation alloc, double budget) {
        System.out.println("Result: ");
        System.out.println("  " + alloc);
        System.out.println("  People helped: " + alloc.totalPeople());
        System.out.printf("  Cost: $%.2f\n", alloc.totalCost());
        System.out.printf("  Unused budget: $%.2f\n", (budget - alloc.totalCost()));
    }

    /**
    * Creates a scenario with numRegions regions by randomly choosing the population 
    * and cost of each region.
    * @param numRegions Number of regions to create
    * @param minPop Minimum population per region
    * @param maxPop Maximum population per region
    * @param minCostPer Minimum cost per person
    * @param maxCostPer Maximum cost per person
    * @return A list of randomly generated regions
    */
    public static List<Region> createRandomScenario(int numRegions, int minPop, int maxPop,
                                                    double minCostPer, double maxCostPer) {
        List<Region> result = new ArrayList<>();

        for (int i = 0; i < numRegions; i++) {
            int pop = RAND.nextInt(maxPop - minPop + 1) + minPop;
            double cost = (RAND.nextDouble(maxCostPer - minCostPer) + minCostPer) * pop;
            result.add(new Region("Region #" + i, pop, round2(cost)));
        }

        return result;
    }

    /**
    * Manually creates a simple list of regions to represent a known scenario.
    * @return A simple list of regions
    */
    public static List<Region> createSimpleScenario() {
        List<Region> result = new ArrayList<>();

        result.add(new Region("Region #1", 50, 500));
        result.add(new Region("Region #2", 100, 700));
        result.add(new Region("Region #3", 60, 1000));
        result.add(new Region("Region #4", 20, 1000));
        result.add(new Region("Region #5", 200, 900));

        return result;
    }    

    /**
    * Rounds a number to two decimal places.
    * @param num The number to round
    * @return The number rounded to two decimal places
    */
    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}
