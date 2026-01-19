//Mahika Bagri 
//18 January 2026 

import java.util.*;

public class punnettSquare{
    private String P1Genome;
    private String P2Genome;
    private List<String> punnettSquareOfGenes;
    private Map<Character, List<Character>> P1GenomeSplit;
    private Map<Character, List<Character>> P2GenomeSplit;

    public punnettSquare(String P1Gene, String P2Gene){

        this.P1Genome = P1Gene;
        this.P2Genome = P2Gene;
        this.P1GenomeSplit = new HashMap<>();
        this.P2GenomeSplit = new HashMap<>();

        for(int i = 0; i < P1Genome.length(); i++){
            char currentAllele = P1Genome.toLowerCase().charAt(i);

            if(!this.P1GenomeSplit.containsKey(currentAllele)){
                this.P1GenomeSplit.put(currentAllele, new ArrayList<Character>());
                this.P2GenomeSplit.put(currentAllele, new ArrayList<Character>());
            }
            
            this.P1GenomeSplit.get(currentAllele).add(P1Genome.charAt(i));
            this.P2GenomeSplit.get(currentAllele).add(P2Genome.charAt(i));
        }
    }

    public Map<String, Double> getGenotypeFrequencies(){
        //solution container
        Map<String, Double> genotypeFrequencies = new HashMap<>(); 
        Map<Character, Map<String, Double>>  genotypePerLocusFrequencies = getGenotypePerLocusFrequencies();

        getGenotypeFrequencies(genotypeFrequencies, "", 1, genotypePerLocusFrequencies);
        return genotypeFrequencies;
    }

    private void getGenotypeFrequencies(Map<String, Double> genotypeFrequencies, String genotype,
    double percent, Map<Character, Map<String, Double>>  genotypePerLocusFrequencies){
        //base condition
        if(genotype.length() == P1Genome.length()){
            //normalize check
            if(!genotypeFrequencies.containsKey(genotype)){
                genotypeFrequencies.put(genotype, 0.0);
            } 
            genotypeFrequencies.put(genotype,
            (genotypeFrequencies.get(genotype) + percent)); 
            genotype = "";
            percent = 1;  
        }

        if(!genotypePerLocusFrequencies.isEmpty()){
            char allele = genotypePerLocusFrequencies.keySet().iterator().next();

            Map<String, Double> alleleMap = genotypePerLocusFrequencies.get(allele);
            genotypePerLocusFrequencies.remove(allele);
            for(String genotypePerLocus : alleleMap.keySet()){
                getGenotypeFrequencies(genotypeFrequencies, genotype + genotypePerLocus,
                percent*alleleMap.get(genotypePerLocus), genotypePerLocusFrequencies);
            }
            genotypePerLocusFrequencies.put(allele, alleleMap);
        }
    }

    public Map<Character, Map<String, Double>> getGenotypePerLocusFrequencies(){
        Map<Character, Map<String, Double>> genotypePerLocusFrequencies = new HashMap<>();
        
        for(char allele:this.P1GenomeSplit.keySet()){
            int geneLength = this.P1GenomeSplit.get(allele).size();

            getGenotypePerLocusFrequencies(genotypePerLocusFrequencies, "", allele, geneLength);
        }
        return genotypePerLocusFrequencies;
    }

    private void getGenotypePerLocusFrequencies(Map<Character, Map<String, Double>> genotypePerLocusFrequencies, 
    String genotype, char allele, int geneLength){
        //base condition
        if(genotype.length() == geneLength){
            genotype = normalizeGene(genotype, allele);
            
            if(!genotypePerLocusFrequencies.containsKey(allele)){
                genotypePerLocusFrequencies.put(allele, new HashMap<>());
            }
            Map<String, Double> alleleMap = genotypePerLocusFrequencies.get(allele);

            if(!alleleMap.containsKey(genotype)){
                    alleleMap.put(genotype, 0.0);
            }
            alleleMap.put(genotype,
            (alleleMap.get(genotype) + (1.0/(Math.pow(geneLength, 2*(geneLength/2))))));

            return;
        }
        List<Character> P1GenotypeList = this.P1GenomeSplit.get(allele);
        List<Character> P2GenotypeList = this.P2GenomeSplit.get(allele);

        for(char P1GenotypeAllele : P1GenotypeList){                
            for(char P2GenotypeAllele : P2GenotypeList){

                getGenotypePerLocusFrequencies(genotypePerLocusFrequencies, 
                genotype + P1GenotypeAllele + P2GenotypeAllele, allele, geneLength);
            } 
        }
    }

    private String normalizeGene(String genotype, char recessiveAllele){
        for(int i = 0; i < genotype.length(); i++){
            if(genotype.charAt(i) == recessiveAllele){
                genotype = recessiveAllele + 
                genotype.substring(0, i) + genotype.substring(i+1, genotype.length());
            }
        }
        return genotype; 
    }
}
