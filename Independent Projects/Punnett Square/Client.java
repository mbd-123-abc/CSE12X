//Mahika Bagri 
//March 15 2025 

import java.util.*;

public class Client{
    public static void main(String args[]){
        Scanner console = new Scanner(System.in);
        
        System.out.print("Parent 1 Genotype: ");
        String P1Gene = console.next();
        System.out.print("Parent 2 Genotype: ");
        String P2Gene = console.next();

        while(P1Gene.length() != P2Gene.length()){
            System.out.println();
            throw new IllegalArgumentException("Error: Parental genes cannot have different lengths.");
        }

        punnettSquare punnet = new punnettSquare(P1Gene, P2Gene);
    }
}
