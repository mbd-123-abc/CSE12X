//Mahika Bagri 
//CSE 122 
//May 23 2025

import java.util.*;
import java.io.*;

//Reads and manages tweets 
public class TweetBot{
    private List<Tweet> tweetsAux;
    private int nextTweetCount;  

    // Behavior: 
    // - initializes a list of tweets into the tweet bot class
    // Parameters:
    // - tweets: a list that contains all tweets in a tweet bot 
    // Returns:
    //   - None 
    // Exceptions:
    //   - IllegalArgumentException: throws if there are no tweets in the tweet list
    public TweetBot(List<Tweet> tweets){
        if(tweets.size() < 1){
            throw new IllegalArgumentException();
        }
        tweetsAux = new ArrayList<>();
        tweetsAux.addAll(tweets);
        
        nextTweetCount = 0;
    }

    // Behavior: 
    // - counts and returns how many tweets there are in a tweet bot
    // Parameters:
    // - none 
    // Returns:
    //   - Int: number of tweets in a tweet bot 
    // Exceptions:
    //   - none
    public int numTweets(){
        int amtTweets = tweetsAux.size();
        return amtTweets;
    }

    // Behavior: 
    // - adds a tweet to a tweet bot
    // Parameters:
    // - tweet: a tweet to add  
    // Returns:
    //   - none 
    // Exceptions:
    //   - none
    public void addTweet(Tweet tweet){
        if(nextTweetCount > tweetsAux.size() + 1){
            nextTweetCount = nextTweetCount%tweetsAux.size();
        }
        tweetsAux.add(tweet);
    }

    // Behavior: 
    // - finds and returns the next tweet in a tweet bot
    // Parameters:
    // - none  
    // Returns:
    //   - tweet: the next tweet 
    // Exceptions:
    //   - none
    public Tweet nextTweet(){
        Tweet tweetToReturn = tweetsAux.get(nextTweetCount%tweetsAux.size());
        nextTweetCount++;
        return tweetToReturn;
    }

    // Behavior: 
    // - removes a tweet from a tweet bot
    // Parameters:
    // - tweet: a tweet to remove  
    // Returns:
    //   - none 
    // Exceptions:
    //   - IllegalStateException: thrown when theres only 1 tweet and client wants to remove it
    public void removeTweet(Tweet tweet){
        if(tweetsAux.size() == 1 && tweetsAux.contains(tweet)){
            throw new IllegalStateException();
        }
        if(tweetsAux.contains(tweet)){
            nextTweetCount = nextTweetCount%tweetsAux.size();
            if(nextTweetCount%tweetsAux.size() > tweetsAux.indexOf(tweet)){
                nextTweetCount--;
            }
            tweetsAux.remove(tweet);
        }
    }

    // Behavior: 
    // - resets the next tweet method so the next tweet returned is the first tweet
    // Parameters:
    // - none
    // Returns:
    //   - none 
    // Exceptions:
    //   - none
    public void reset(){
        nextTweetCount = 0;
    }

}  
