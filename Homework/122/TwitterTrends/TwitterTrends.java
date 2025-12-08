//Mahika Bagri 
//CSE 122 
//May 23 2025

import java.util.*;
import java.io.*;

public class TwitterTrends {
    private TweetBot auxBot;

    // Behavior: 
    // - Initialized a tweet bot as a field in the twitter trends class
    // Parameters:
    // - bot: a tweet bot
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    public TwitterTrends(TweetBot bot){
        auxBot = bot; 
    }   
     
    // Behavior: 
    // - gets the most frequent word in all tweet captions of a tweet bot
    // Parameters:
    // - None
    // Returns:
    //   - String: a string that contains the most frequent word 
    // Exceptions:
    //   - None
    public String getMostFrequentWord(){
        Map<String, Integer> wordFrequency = new HashMap<>();

        for(int i = 0; i < auxBot.numTweets(); i++){
            Tweet currentTweet = auxBot.nextTweet();
            String caption = currentTweet.getCaption();
            Scanner processor = new Scanner(caption);

            while(processor.hasNext()){
                String currentWord = processor.next().toLowerCase(); 
                if(!wordFrequency.containsKey(currentWord)){
                    wordFrequency.put(currentWord, 1);
                }else{
                    int currentWordFrequency = wordFrequency.get(currentWord);
                    wordFrequency.put(currentWord, currentWordFrequency+1);
                }
            } 
        }

        String mostFrequentWord = " ";
        int mostFrequentWordCount = 0;
        for (String word : wordFrequency.keySet()) {
            if(wordFrequency.get(word) > mostFrequentWordCount) {
                mostFrequentWord = word;
                mostFrequentWordCount = wordFrequency.get(word);
            }
        }

        return mostFrequentWord;
    }

    // Behavior: 
    // - gets the most frequent hashtag in all tweet captions of a tweet bot
    // Parameters:
    // - None
    // Returns:
    //   - String: a string that contains the most frequent hashtag 
    // Exceptions:
    //   - None
    public String getMostTrendyHashtag(){
        Map<String, List<Double>> hastagTrendiness = new HashMap<>();

        for(int i = 0; i < auxBot.numTweets(); i++){
            Tweet currentTweet = auxBot.nextTweet();

            int likes = currentTweet.getLikes();
            int retweets = currentTweet.getRetweets();
            double trendinessScore = (likes+retweets)/2.0;

            String caption = currentTweet.getCaption();
            Scanner processor = new Scanner(caption);
            
            while(processor.hasNext()){
                String currentWord = processor.next().toLowerCase();
                if(currentWord.substring(0,1).equals("#")){
                    if(!hastagTrendiness.containsKey(currentWord)){
                        hastagTrendiness.put(currentWord, new ArrayList());
                    }
                    hastagTrendiness.get(currentWord).add(trendinessScore);
                } 
            } 
        }

        String mostTrendyHashtag = " ";
        double mostTrendyHashtagCount = 0;

        for(String hashtag : hastagTrendiness.keySet()) {
            List<Double> currentList = hastagTrendiness.get(hashtag);
            double currentHashtagCount = 0;

            for(double trendiness : currentList) {
			    currentHashtagCount += trendiness;
		    }

            currentHashtagCount = currentHashtagCount/currentList.size();

            if(currentHashtagCount > mostTrendyHashtagCount) {
                mostTrendyHashtag = hashtag;
                mostTrendyHashtagCount = currentHashtagCount;
            }
        }

        return mostTrendyHashtag;
    }

}
