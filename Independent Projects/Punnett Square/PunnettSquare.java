//Mahika Bagri 
//March 15 2025 

import java.util.*;

//punnett square creator
public class PunnettSquare {
    public static void main(String args[]){
        Scanner console = new Scanner(System.in);
        String[] parentalInfo = parentalInfo(console);
        String P1Gene = parentalInfo[0];
        String P2Gene = parentalInfo[1];
        int[] geneStartIndex = geneIndexing(P1Gene, P2Gene);
        String[][] punnettSquareOfGenes = seperatingGenes(P1Gene, P2Gene, geneStartIndex);
        for(int i =0; i < punnettSquareOfGenes.length; i++){
            System.out.println(Arrays.deepToString(punnettSquareOfGenes[i]));
        }
    }

    public static String[] parentalInfo(Scanner console){
        //assume 2 parents 
        String[] parentalInfo = new String[2];
        System.out.print("Parent 1 Genotype: ");
        String P1Gene = console.next();
        System.out.print("Parent 2 Genotype: ");
        String P2Gene = console.next();
        while(P1Gene.length() != P2Gene.length()){
            System.out.println();
            System.out.println("Error: Parental genes cannot have different lengths.");
            System.out.println("Try again please.");
            System.out.println();
            System.out.print("Parent 1 Genotype: ");
            P1Gene = console.next();
            System.out.print("Parent 2 Genotype: ");
            P2Gene = console.next();
        }
        parentalInfo[0] = P1Gene;
        parentalInfo[1] = P2Gene;
        return parentalInfo;
    }
    
    public static int[] geneIndexing(String P1Gene, String P2Gene){
        //assume binary alleles and no superscripts
        String geneStartIndexString = "-";
        for(int i = 1; i < P1Gene.length(); i++){
            if(!P1Gene.substring(i, i+1).toLowerCase().equals(P1Gene.substring(i-1, i).toLowerCase())){
                geneStartIndexString += "-";
            }
        }
        int[] geneStartIndex = new int[geneStartIndexString.length()];
        geneStartIndex[0] = 0;
        int x = 1;
        for(int i = 1; i < P1Gene.length(); i++){
            if(!P1Gene.substring(i, i+1).toLowerCase().equals(P1Gene.substring(i-1, i).toLowerCase())){
                geneStartIndex[x] = i;
                x++;
            }
        }
        int[] geneStartIndex2 = new int[geneStartIndexString.length()];
        geneStartIndex2[0] = 0;
        x = 1;
        for(int i = 1; i < P2Gene.length(); i++){
            if(!P2Gene.substring(i, i+1).toUpperCase().equals(P2Gene.substring(i-1, i).toUpperCase())){
                geneStartIndex2[x] = i;
                x++;
            }
        }
        for(int i = 0; i < geneStartIndex.length; i++){
            if(geneStartIndex2[i] != geneStartIndex[i]){
                System.out.println();
                System.out.println("Error: Parental gene indices do not match.");
                System.out.println("Try again please.");
                geneStartIndex = new int[0];
            } 
        }
        return geneStartIndex;
    }

    public static String[][] seperatingGenes(String P1Gene, String P2Gene, int[] geneStartIndex){
        String[][] squareGenes = new String[geneStartIndex.length + 1][geneStartIndex.length + 1];
        for(int i = 1; i < geneStartIndex.length; i++){
            squareGenes[0][i] = P1Gene.substring(geneStartIndex[i-1], geneStartIndex[i]);
        }
        squareGenes[0][geneStartIndex.length] = P1Gene.substring(geneStartIndex[geneStartIndex.length - 1]);
        for(int i = 1; i < geneStartIndex.length; i++){
            squareGenes[i][0] = P2Gene.substring(geneStartIndex[i-1], geneStartIndex[i]);
        }
        squareGenes[geneStartIndex.length][0] = P2Gene.substring(geneStartIndex[geneStartIndex.length - 1]);
        return squareGenes;
    }
}
