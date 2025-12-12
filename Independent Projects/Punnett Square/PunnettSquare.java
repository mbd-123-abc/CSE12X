//Mahika Bagri
//December 11 2025

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
        Map<String, Double> genotypeFrequencies = new HashMap<>();

        double percent = 1.0; 
        for(char allele: P1GenomeSplit.keySet()){
            percent = percent*Math.pow(this.P1GenomeSplit.get(allele).size(), 2);
        }
        percent = 1/percent; 

        getGenotypeFrequencies(genotypeFrequencies, "", percent);
        return genotypeFrequencies;
    }

    private void getGenotypeFrequencies(Map<String, Double> genotypeFrequencies, String genotype, 
    double percent){
        if(genotype.length() == P1Genome.length()){
            if(!genotypeFrequencies.containsKey(genotype)){
                genotypeFrequencies.put(genotype, 0.0);
            } 

            genotypeFrequencies.put(genotype,
            (genotypeFrequencies.get(genotype) + percent));
            
        }
        List<Character> alleleKeys = new ArrayList<>(P1GenomeSplit.keySet());

        for(char allele: alleleKeys){

            List<Character> P1GenotypeList = this.P1GenomeSplit.get(allele);
            List<Character> P2GenotypeList = this.P2GenomeSplit.get(allele);
            P1GenomeSplit.remove(allele);
            P2GenomeSplit.remove(allele);

            int alleleSize = P1GenotypeList.size();

            for(int i = 0; i < alleleSize; i++){
                char allele1 = P1GenotypeList.get(i);
                genotype += allele1;

                for(int j = 0; j < alleleSize; j++){
                    char allele2 = P2GenotypeList.get(j);
                    genotype += allele2;

                    getGenotypeFrequencies(genotypeFrequencies, genotype, percent);
                    genotype = genotype.substring(0, genotype.length()-1);
                }
                genotype = genotype.substring(0, genotype.length()-1);
            }
            P1GenomeSplit.put(allele, P1GenotypeList);
            P2GenomeSplit.put(allele, P2GenotypeList);
        }
    }

    public Map<String, Double> getGenotypePerLocusFrequencies(){
        Map<String, Double> genotypePerLocusFrequencies = new HashMap<>();
 
        for(char allele:this.P1GenomeSplit.keySet()){
            int geneSize = this.P1GenomeSplit.get(allele).size();
            
            for(int i = 0; i < geneSize; i++){
                for(int j = 0; j < geneSize; j++){
                    String genotype = "" + this.P1GenomeSplit.get(allele).get(i) +
                    this.P2GenomeSplit.get(allele).get(j);

                    if(!genotypePerLocusFrequencies.containsKey(genotype)){
                        genotypePerLocusFrequencies.put(genotype, 0.0);
                    }
                    genotypePerLocusFrequencies.put(genotype,
                    (genotypePerLocusFrequencies.get(genotype) + (1.0/(geneSize*geneSize))));
                }
            }
        }
        return genotypePerLocusFrequencies;
    }

}
