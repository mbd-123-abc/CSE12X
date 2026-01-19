//Mahika Bagri 
//January 18 2026 

import java.util.*;

public class Client{
    public static void main(String args[]){
        Scanner console = new Scanner(System.in);
        //takes in user input
        System.out.print("Parent 1 Genotype: ");
        String P1Gene = console.next();
        System.out.print("Parent 2 Genotype: ");
        String P2Gene = console.next();

        console.close();
        //throws exceptions
        if(P1Gene.length() != P2Gene.length()){
            throw new IllegalArgumentException("Error: Parental genes cannot"+
            " have different lengths.");
        }
        if(!P1Gene.toLowerCase().equals(P2Gene.toLowerCase())){
            throw new IllegalArgumentException("Error: Parents cannot have"+
            " a different number of alleles in a gene.");
        }
        //creates punnett object 
        punnettSquare punnett = new punnettSquare(P1Gene, P2Gene);
        //prints genotype frequencies 
        System.out.println();
        Map<String, Double> genotypeFrequencies = punnett.getGenotypeFrequencies();
        for(String genotype: genotypeFrequencies.keySet()){
            System.out.println("Genotype: " + genotype + " Frequency: "+
            genotypeFrequencies.get(genotype));
        }
        //prints gene frequencies 
        System.out.println();
        Map<Character, Map<String, Double>> genotypePerLocusFrequencies = punnett.getGenotypePerLocusFrequencies();
        for(char allele:genotypePerLocusFrequencies.keySet()){
            Map<String, Double> alleleMap = genotypePerLocusFrequencies.get(allele);
            for(String genotype: alleleMap.keySet()){
                System.out.println("Genotype: " + genotype + " Frequency: "+
                alleleMap.get(genotype));
            }
        }
    }
}
