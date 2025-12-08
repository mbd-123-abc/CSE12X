//Mahika Bagri 
//CSE 122
//15 May 2025

//This is a prolonged game of wordle 
//Where the system responds to the guess the pattern with the most words in the dictionary

import java.util.*;
import java.io.*;

public class Absurdle  {
    public static final String GREEN = "🟩";
    public static final String YELLOW = "🟨";
    public static final String GRAY = "⬜";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the game of Absurdle.");

        System.out.print("What dictionary would you like to use? ");
        String dictName = console.next();

        System.out.print("What length word would you like to guess? ");
        int wordLength = console.nextInt();

        List<String> contents = loadFile(new Scanner(new File(dictName)));
        Set<String> words = pruneDictionary(contents, wordLength);

        List<String> guessedPatterns = new ArrayList<>();
        while (!isFinished(guessedPatterns)) {
            System.out.print("> ");
            String guess = console.next();
            String pattern = recordGuess(guess, words, wordLength);
            guessedPatterns.add(pattern);
            System.out.println(": " + pattern);
            System.out.println();
        }
        System.out.println("Absurdle " + guessedPatterns.size() + "/∞");
        System.out.println();
        printPatterns(guessedPatterns);
    }

    // Behavior: 
    // Prints out the given list of patterns.
    // Parameters:
    // - patterns: list of all patterns from the game
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    public static void printPatterns(List<String> patterns) {
        for (String pattern : patterns) {
            System.out.println(pattern);
        }
    }

    // Behavior: 
    // Returns true if the game is finished, meaning the user guessed the word. Returns
    // false otherwise.
    // Parameters:
    // - patterns: list of all patterns from the game
    // Returns:
    //   - boolean: answers the question is the game finished? 
    // Exceptions:
    //   - None
    public static boolean isFinished(List<String> patterns) {
        if (patterns.isEmpty()) {
            return false;
        }
        String lastPattern = patterns.get(patterns.size() - 1);
        return !lastPattern.contains("⬜") && !lastPattern.contains("🟨");
    }

    // Behavior: 
    // Loads the contents of a given file Scanner into a List<String> and returns it.
    // Parameters:
    // - Scanner dictScan: contains file contents
    // Returns:
    //   - List<String>: containing all words in the dictionary
    // Exceptions:
    //   - None
    public static List<String> loadFile(Scanner dictScan) {
        List<String> contents = new ArrayList<>();
        while (dictScan.hasNext()) {
            contents.add(dictScan.next());
        }
        return contents;
    }

    // Behavior: 
    //   - This method takes a list of words and ensures only the words of a given length are 
    //   a part of the guessable words dictionary  
    // Parameters:
    //   - contents: a list with all the words from a certain dictionary
    //   - wordLength: an integer of how long the words that the user can guess from should be
    // Returns:
    //   - Set<String>: a set of words that is a pruned dictionary of words of the right
    //   length that the user can guess from 
    // Exceptions:
    //   - IllegalArgumentException: thrown when the wordLength is less than 1

    public static Set<String> pruneDictionary(List<String> contents, int wordLength) {
        if(wordLength < 1){
            throw new IllegalArgumentException();
        }
        Set<String> prunedDictionary = new TreeSet<>();
        for(int i = 0; i < contents.size(); i++){
            prunedDictionary.add(contents.get(i));
        }
        Iterator<String> iter = prunedDictionary.iterator();
        while (iter.hasNext()) {
            String word = iter.next();
            if (word.length() != wordLength){
                iter.remove();
            }
        }
        return prunedDictionary;
    }

    // Behavior: 
    //   - This method finds the most occuring pattern in the dictionary when compared to a users 
    //   guess; updates the current set of words based on the guess to be the selected largest set
    // Parameters:
    //   - guess: a string that contains the users guess word
    //   - words: a set of strings that contains the words the user can guess from and gets updated
    //   - wordLength: an integer of how long the words that the user can guess from should be
    // Returns:
    //   - String: the highest occuring pattern of green, yellow, and grey tiles that correlates
    //   to the guess the user made to the most words in the dictionary 
    // Exceptions:
    //   - IllegalArgumentException: thrown when the guess length is not the length of words the
    //   user can guess from or there are no words in the word dictionary 

    public static String recordGuess(String guess, Set<String> words, int wordLength) {
        if(guess.length() != wordLength || words.size() < 1){
            throw new IllegalArgumentException();
        }
        Map<String, Set<String>> targetWordGroups= new TreeMap<>();
        for (String word : words) {
            String pattern = patternFor(word, guess);
            if (!targetWordGroups.containsKey(pattern)) {
                targetWordGroups.put(pattern, new HashSet<>());
            } 
            targetWordGroups.get(pattern).add(word);
        }
        String mostOccuringPattern = findMostOccuringPattern(targetWordGroups);
        Iterator<String> iter = words.iterator();
        while (iter.hasNext()) {
            String word = iter.next();
            if (!targetWordGroups.get(mostOccuringPattern).contains(word)){
                iter.remove();
            }
        }
        return mostOccuringPattern;
    }

    // Behavior: 
    //   - This method is a helper method that also finds the most occuring pattern in the 
    //   dictionary when compared to a users guess 
    // Parameters:
    //   - targetWordGroups: a map that contains all the patterns and a set of words that have that
    //   pattern when patterned against the users guess
    // Returns:
    //   - String: A string that contains the most occuring pattern when all the words in the
    //   dictionary are patterned against the users guess 
    // Exceptions:
    //   - None

    public static String findMostOccuringPattern(Map<String, Set<String>> targetWordGroups){
        String mostOccuringPattern = "";
        int mostOccuringPatternCount = 0;
        for (String pattern : targetWordGroups.keySet()) {
            if(targetWordGroups.get(pattern).size() > mostOccuringPatternCount){
                mostOccuringPatternCount = targetWordGroups.get(pattern).size();
                mostOccuringPattern = pattern;
            }
        }
        return mostOccuringPattern;
    }

    // Behavior: 
    //   - This method finds the pattern of a users guess against one word in the dictionary   
    // Parameters:
    //   - word: a string that contains a word from the pruned dictionary of words
    //   - guess: a string that contains the users guess word
    // Returns:
    //   - String: a pattern of green, yellow, and grey tiles that correlates a guess to a word
    // Exceptions:
    //   - None

    public static String patternFor(String word, String guess) {
        String pattern = "";
        for(int i = 0; i < word.length(); i++){
            
            pattern += "%";
        }
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == guess.charAt(i)){
                pattern = pattern.substring(0, i) + "!" + 
                pattern.substring(i+1, pattern.length());
            }
        }
        for(int i = 0; i < word.length(); i++){
            if (word.contains(guess.substring(i, i+1))){
                int timesCharAppears = 0;
                int timesUsed = 0;
                for(int j = 0; j < word.length(); j++){
                    if(word.charAt(j) == guess.charAt(i)){
                       timesCharAppears++; 
                    }
                }
                for(int j = 0; j < word.length(); j++){
                    if(guess.charAt(j) == guess.charAt(i) && (pattern.charAt(j) == '$'|pattern.charAt(j) == '!')){
                       timesUsed++; 
                    }
                }                
                if(timesCharAppears > timesUsed && pattern.charAt(i) != '!' && pattern.charAt(i) != '$'){
                    pattern = pattern.substring(0, i) + "$" + 
                    pattern.substring(i+1, pattern.length());
                }
            }
        }
        for(int i = 0; i < pattern.length(); i++){
            if(pattern.charAt(i) == '%'){
                pattern = pattern.substring(0, i) + GRAY + 
                pattern.substring(i+1, pattern.length());
            } else if(pattern.charAt(i) == '!'){
                pattern = pattern.substring(0, i) + GREEN + 
                pattern.substring(i+1, pattern.length());
            } else if(pattern.charAt(i) == '$'){
                pattern = pattern.substring(0, i) + YELLOW + 
                pattern.substring(i+1, pattern.length());
            }
        }
        return pattern;
    }
}
