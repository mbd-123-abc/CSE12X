//Mahika Bagri
//CSE 123 
//1 October 2025

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This class is a book object. It has title, authors, book content, and ratings. You can even use
//it to compare a book to another. 
public class Book implements Media, Comparable<Book>{

    private String title;
    private List<String> authors;
    private List<String> contentList; 
    private List<Integer> ratings;

    // Behavior: 
    //   - This method constructs a new book and stores its content. 
    // Parameters:
    //   - title: the title of the book. 
    //   - authors: a list of authors of the book. 
    //   - content: contains the content of the book. 
    public Book(String title, List<String> authors, Scanner content){
        this.title = title;
        this.authors = authors;
        this.contentList = new ArrayList<>();
        this.ratings = new ArrayList<>();
        while(content.hasNext()){
            contentList.add(content.next());
        }
    }

    // Behavior: 
    //   - This method returns the title of the book. 
    // Returns:
    //   - String: the title of the book.  
    public String getTitle(){
        return this.title;
    }

    // Behavior: 
    //   - This method returns the authors of the book.  
    // Returns:
    //   - List: the authors of the book.  
    public List<String> getArtists(){
        return this.authors;
    }

    // Behavior: 
    //   - This method adds a rating to a new book and updates average rating to reflect the same. 
    // Parameters:
    //   - score: the rating the user wants to add to the book. 
    public void addRating(int score){
        ratings.add(score);
    }

    // Behavior: 
    //   - This method returns the number of ratings of a book.
    // Returns:
    //   - int: number of ratings in a book. 
    public int getNumRatings(){
        return this.ratings.size();
    }

    // Behavior: 
    //   - This method returns the average ratings of a book. If there are no ratings it returns 0.
    // Returns:
    //   - double: the average rating of a book 
    public double getAverageRating(){
        double averageRating = 0;

        if(this.ratings.size() > 0){
            for(int i = 0; i <this.ratings.size(); i++){
                averageRating += this.ratings.get(i);
            }
            averageRating = averageRating/this.ratings.size();
        }
        return averageRating;
    }

    // Behavior: 
    //   - This method returns the content of a book.
    // Returns:
    //   - List<String>: the content/tokens in a book. 
    public List<String> getContent(){
        return this.contentList;
    }

    // Behavior: 
    //   - This method turns all information in a book to a string and returns it for easy access.
    // Returns:
    //   - String: the string version of a book. 
    //   - if there are no rating you just get the book title and author, 
    public String toString(){
        String bookString = "";
        bookString += (this.title + " by " + this.authors);
        if(this.ratings.size() > 0){
            double roundedAverageRating = (int) Math.round(this.getAverageRating()*100)/100.0;
            bookString += (": " + roundedAverageRating +" (" + this.ratings.size() + " ratings)");
        }
        return bookString; 
    }

    // Behavior: 
    //   - This method compares 2 books based on their average rating.
    // Returns:
    //   - int: positive if the other book has a higher rating; negative if this book has a higher
    //rating; 0 otherwise.
    public int compareTo(Book other){
        double difference = other.getAverageRating() - this.getAverageRating();
        if(difference>0){
            return 1;
        } else if(difference == 0){
            return 0;
        } else{
            return -1;
        }
    }

}
