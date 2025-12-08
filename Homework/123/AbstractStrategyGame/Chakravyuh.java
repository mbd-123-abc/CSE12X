//Mahika Bagri
//CSE 123 
//15 October 2025

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

//This class is a game (based on an ancient Indian war formation) for two players, who can Both
//can read instructions, make moves, and even win!
public class Chakravyuh extends AbstractStrategyGame{
    public static final char PLAYER_1_TOKEN = '*';
    public static final char PLAYER_2_TOKEN = '8';
    public static final int PLAYER_2_NUM_TOKENS_ALLOWED = 2;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    public static final int TOKEN_SAME_PLACE_LIMIT = 2;

    private int player2CurrentToken;
    private int isGameOver;
    private int tokenPlaceY;
    private int tokenPlaceX;
    private char[][] board;
    private boolean is1Turn;
    private List<Integer> playerPositions;
    private int player2TokenCount;
    private List<Integer> player2TokensSamePlace;

    // Behavior: 
    //   - This method begins a new game for players
    public Chakravyuh() {
        this.board = new char[][]{{'_', '_', '_', '_',  '_', '_', '_', '_',  '_', '_', '_', '_',
                                '_', '_', '_', '_',  '_', '_', '_'},
                             {'|', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                              '0', '0', '0', '0', '0', '|'},
                             {'|', '0', '_', '_',  '_', '_', '_', '_',  '_', '_', '_', '_', 
                              '_', '_', '_', '_',  '_', '0', '|'},
                             {'|', '0', '|', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                              '0', '0', '0', '|', '0', '|'},
                             {'|', '0', '|', '0', '_', '_',  '_', '_', '_', '_',  '_', '_', '_',
                              '_',  '_', '0', '|', '0', '|'},
                             {'|', '0', '|', '0', '|', '0', '0', '0', '0', '0', '0', '0', '0',
                              '0', '|', '0', '|', '0', '|'},
                             {'|', '0', '|', '0', '|', '0', '_', '_',  '_', '_', '_', '_',  '_',
                              '0', '|', '0', '|', '0', '|'},
                             {'|', '0', '|', '0', '|', '0', '|', '0', '0', '0', '0', '0', '|',
                              '0', '|', '0', '|', '0', '|'},
                             {'|', '0', '|', '0', '|', '0', '|', '0', '_', '_',  '_', '0', '|',
                              '0', '|', '0', '|', '0', '|'},
                             {'|', '0', '|', '0', '|', '0', '|', '0', '|', '*', '0', '0', '|',
                              '0', '|', '0', '|', '0', '|'},
                             {'|', '0', '|', '0', '|', '0', '|', '0', '|', '_', '_', '_',  '|',
                              '0', '|', '0', '|', '0', '|'},
                             {'|', '0', '|', '0', '|', '0', '|', '0', '|', '0', '0', '0', '0',
                              '0', '|', '0', '|', '0', '|'},
                             {'|', '0', '|', '0', '|', '0', '|', '0', '|', '0', '_', '_',  '_',
                               '_', '_', '0', '|', '0', '|'},
                             {'|', '0', '|', '0', '0', '0', '|', '0', '|', '0', '|', '0', '0',
                              '0', '0', '0', '|', '0', '|'},
                             {'|', '0', '_',  '_', '_', '_',  '_', '0', '|', '0', '|', '0', '_',
                              '_',  '_', '_', '_',  '0', '|'},
                             {'|', '0', '0', '0', '0', '0', '0', '0', '|', '0', '|', '0', '0', '0',
                              '0', '0', '0', '0', '|'},
                             {'_', '_',  '_', '_', '_',  '_', '_', '_',  '_', '✪', '_', '_',  '_',
                              '_', '_',  '_', '_', '_',  '_',}};              
        this.is1Turn = true;
        isGameOver = 0;
        playerPositions = new ArrayList<Integer>();
        player2TokensSamePlace = new ArrayList<Integer>();
        playerPositions.add(9);
        playerPositions.add(9);
        player2TokenCount = 0; 
    }
    
    // Behavior: 
    //   - This method send the instructions for players to play 
    //Return:
    //   - String: the instructions/rulebook of gameplay
    public String instructions(){
        String instructions = "";
        instructions += "Player 1 is * and is stuck inside the Chakravyuh (Labyrinth).\n";
        instructions += "Player 1's objective is to escape the Chakravyuh (reach the ✪).\n";
        instructions += "Player 2 is 8 and is guarding the Chakravyuh (Labyrinth).\n";
        instructions += "Player 2's objective is to keep Player 1 in the Chakravyuh"+
        " (not let player 1 reach the ✪).\n";
        instructions += "Player 1 follows the path of 0's (moving up/down/right/left)\n";
        instructions += "Player 1 may jump diagonally over Player 2's token to take it out of"+
        " gameplay (jumping up/down/right/left when the board allows)\n";
        instructions += "Player 2 can start on any _'s and |'s and follows the path they create"+
        " (moving up/down/right/left)\n";
        instructions += "Player 2's tokens are each assigned a number."+
        "The first token is 1, and each token after is 1+number before.\n";
        instructions += "Player 2 has a limit of placing "+PLAYER_2_NUM_TOKENS_ALLOWED+
        " tokens.\n";
        instructions += "Player 2 can only leave a token in the same place for "+
        TOKEN_SAME_PLACE_LIMIT+" rounds.\n";
        instructions += "Player 2 may jump orthogonally over Player 1's token to take it out of"+
        " gameplay (jumping up/down/right/left when the board allows)\n";
        instructions += "Both players may only jump over a piece, and not otherwise.\n";
        instructions += "Both players may also win by taking all the other players tokens out of "+
        "gameplay.\n";
        instructions += "FYI: The board is labeled left to right and up to down."+
        "The first space is 0, and each space after is 1+number before.\n";

        return instructions;
    }

    // Behavior: 
    //   - This method gets the move a player wants to make by prompting the user for input 
    //Return:
    //   - String: the desired move a player wants to make
    // Parameters: 
    //   - input: a console that takes in user input
    // Exceptions:
    //   -if the input is not the expected input , an IllegalArgumentException() is throw
    //   -for Player 1 that means options/move, move, right/left/up/down/jumpRightUp/jumpRightDown
    // /jumpLeftUp/jumpLeftDown
    //   -for Player 2 that means options/move, move, right/left/up/down/jumpRight/jumpDown
    // /jumptUp/jumpLeft/place, x,y (if place), token #
    //   -if the scanner is null , an IllegalArgumentException() is throw

    public String getMove(Scanner input){
        if(input == null){
            throw new IllegalArgumentException();
        }
        System.out.println("Type \"options\" to see gameplay options; Type \"move\" to play.");
        String playerInput = input.next().toLowerCase();
        if(!(playerInput.equals("options")) && !(playerInput.equals("move"))){
            throw new IllegalArgumentException();
        }
        if(playerInput.equals("options")){
            if(is1Turn){
                System.out.println("right/left/up/down/jumpRightUp/jumpRightDown/jumpLeftUp"+
                "/jumpLeftDown");
            } else {
                System.out.println("right/left/up/down/jumpUp/jumpDown/jumpLeft/jumpRight/"+
                "place");
            }
        }
        System.out.println("Which move would you like to play?");
        playerInput = input.next();
        if(playerInput.equals("place") && !is1Turn){
            System.out.println("Where would you like to place it (x-value)?");
            tokenPlaceX = input.nextInt();
            System.out.println("Where would you like to place it (y-value)?");
            tokenPlaceY = input.nextInt();
        }
        if(!playerInput.equals("place") && !is1Turn){
            System.out.println("Which token would you like to play it on?");
            player2CurrentToken = input.nextInt();
            for(int i = 0; i < player2CurrentToken-1; i++){
                player2TokensSamePlace.set(i, player2TokensSamePlace.get(i)+ 1);
            }
            for(int i = player2CurrentToken+1; i < player2TokensSamePlace.size(); i++){
                player2TokensSamePlace.set(i, player2TokensSamePlace.get(i)+ 1);
            }
            for(int i = 0; i < player2TokensSamePlace.size(); i++){
            if(player2TokensSamePlace.get(i)%TOKEN_SAME_PLACE_LIMIT == 0 
            && player2TokensSamePlace.get(i) != 0){
                isGameOver = -1;
                is1Turn = !is1Turn;
            }
        }
        }

        return playerInput;
    }

    // Behavior: 
    //   - This method makes the move, the player specifies, on the board 
    // Parameters: 
    //   - input: the move a player wants to mak
    // Exceptions:
    //   -if the input is null, an IllegalArguementException() is thrown  
    //   -if the move is not a given/specified move or the parameters are not following 
    //rules/given path, an IllegalArguementException() is throw.
    //   -for Player 1 that means options/move, move, right/left/up/down/jumpRightUp/jumpRightDown
    // /jumpLeftUp/jumpLeftDown on a 0 only
    //   -for Player 2 that means options/move, move, right/left/up/down/jumpRight/jumpDown
    // /jumptUp/jumpLeft/place, x,y (if place), token # on a _ or | only

    public void makeMove(String input){
        if(input == null){
            throw new IllegalArgumentException();
        }
        int playerPositionY = 0;
        int playerPositionX = 0;

        if(is1Turn){
            if(!input.equals("jumpLeftUp")&&!input.equals("right")&&!input.equals("down")&&
            !input.equals("jumpRightUp")&&!input.equals("up")&&!input.equals("jumpRightDown")&&
            !input.equals("jumpLeftDown")&&!input.equals("left")){
                throw new IllegalArgumentException();
            }
            playerPositionY = this.playerPositions.get(0);
            playerPositionX = this.playerPositions.get(1);
            if(input.equals("up")){
                if(this.board[playerPositionY-1][playerPositionX]!='0'){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY-1][playerPositionX] = PLAYER_1_TOKEN;
                this.board[playerPositionY][playerPositionX] = '0';
                this.playerPositions.set(0, playerPositionY-1);
            } else if(input.equals("down")){
                if((this.board[playerPositionY+1][playerPositionX]!='0')
                &&(this.board[playerPositionY+1][playerPositionX]!='✪')){
                    throw new IllegalArgumentException();
                }
                if(this.board[playerPositionY][playerPositionX]=='✪'){
                    isGameOver = -1;
                }
                this.board[playerPositionY+1][playerPositionX] = PLAYER_1_TOKEN;
                this.board[playerPositionY][playerPositionX] = '0';
                this.playerPositions.set(0, playerPositionY+1);
            } else if(input.equals("left")){
                if(this.board[playerPositionY][playerPositionX-1]!='0'){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY][playerPositionX-1] = PLAYER_1_TOKEN;
                this.board[playerPositionY][playerPositionX] = '0';
                this.playerPositions.set(1, playerPositionX-1);
            } else if(input.equals("right")){
                if(this.board[playerPositionY][playerPositionX+1]!='0'){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY][playerPositionX+1] = PLAYER_1_TOKEN;
                this.board[playerPositionY][playerPositionX] = '0';
                this.playerPositions.set(1, playerPositionX+1);
            } else if(input.equals("jumpLeftDown")){ 
                if(((this.board[playerPositionY+2][playerPositionX-2]!='0')&&
                (this.board[playerPositionY+2][playerPositionX-2]!='✪'))||
                this.board[playerPositionY+1][playerPositionX-1]!=PLAYER_2_TOKEN){
                    throw new IllegalArgumentException();
                }
                for(int i = 2; i < playerPositions.size(); i++){
                    if(this.playerPositions.get(i) == playerPositionY+1 && 
                    this.playerPositions.get(i+1) == playerPositionX-1){
                        this.playerPositions.set(i, -1);
                        this.playerPositions.set(i+1, -1);
                    }
                }
                if(this.board[playerPositionY][playerPositionX]=='✪'){
                    isGameOver = -1;
                }
                this.board[playerPositionY+2][playerPositionX-2] = PLAYER_1_TOKEN;
                this.board[playerPositionY][playerPositionX] = '0';
                this.board[playerPositionY+1][playerPositionX-1] = '|';
                this.playerPositions.set(0, playerPositionY+2);
                this.playerPositions.set(1, playerPositionX-2);
            }
            else if(input.equals("jumpLeftUp")){
                if(this.board[playerPositionY-2][playerPositionX-2]!='0'||
                this.board[playerPositionY-1][playerPositionX-1]!= PLAYER_2_TOKEN){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY-2][playerPositionX-2] = PLAYER_1_TOKEN;
                this.board[playerPositionY][playerPositionX] = '0';
                this.board[playerPositionY-1][playerPositionX-1] = '_';
                this.playerPositions.set(0, playerPositionY-2);
                this.playerPositions.set(1, playerPositionX-2);
                for(int i = 2; i < playerPositions.size(); i++){
                    if(this.playerPositions.get(i) == playerPositionY-1 && 
                    this.playerPositions.get(i+1) == playerPositionX-1){
                        this.playerPositions.set(i, -1);
                        this.playerPositions.set(i+1, -1);
                    }
                }
            }
            else if(input.equals("jumpRightDown")){
                if(((this.board[playerPositionY+2][playerPositionX+2]!='0')&&
                (this.board[playerPositionY+2][playerPositionX+2]!='✪'))||
                this.board[playerPositionY+1][playerPositionX+1]!= PLAYER_2_TOKEN){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY+2][playerPositionX+2] = PLAYER_1_TOKEN;
                this.board[playerPositionY][playerPositionX] = '0';
                this.board[playerPositionY+1][playerPositionX+1] = '|';
                this.playerPositions.set(0, playerPositionY+2);
                this.playerPositions.set(1, playerPositionX+2);
                if(this.board[playerPositionY][playerPositionX]=='✪'){
                    isGameOver = -1;
                }
                for(int i = 2; i < playerPositions.size(); i++){
                    if(this.playerPositions.get(i) == playerPositionY+1 && 
                    this.playerPositions.get(i+1) == playerPositionX+1){
                        this.playerPositions.set(i, -1);
                        this.playerPositions.set(i+1, -1);
                    }
                }
            } else {
                if(this.board[playerPositionY-2][playerPositionX+2]!='0'||
                this.board[playerPositionY-1][playerPositionX+1]!= PLAYER_2_TOKEN){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY-2][playerPositionX+2] = PLAYER_1_TOKEN;
                this.board[playerPositionY][playerPositionX] = '0';
                this.playerPositions.set(0, playerPositionY-2);
                this.playerPositions.set(1, playerPositionX+2);
                for(int i = 2; i < playerPositions.size(); i++){
                    if(this.playerPositions.get(i) == playerPositionY-1 && 
                    this.playerPositions.get(i+1) == playerPositionX+1){
                        this.playerPositions.set(i, -1);
                        this.playerPositions.set(i+1, -1);
                    }
                }
            }
            if(playerPositions.size()==2+2*PLAYER_2_NUM_TOKENS_ALLOWED){
                int negatives = 0;
                for(int i = 2; i < playerPositions.size(); i+=2){
                        negatives+=playerPositions.get(i);
                }
                if(negatives == -1*PLAYER_2_NUM_TOKENS_ALLOWED){
                    isGameOver = -1; 
                }
            }    
        } else {
            if(!input.equals("jumpUp")&&!input.equals("right")&&!input.equals("down")&&
            !input.equals("jumpLeft")&&!input.equals("place")&&!input.equals("up")&&
            !input.equals("jumpRight")&&!input.equals("jumpDown")&&!input.equals("left")||
             (!input.equals("place") && (player2CurrentToken*2 > playerPositions.size() - 1))){
                throw new IllegalArgumentException();
            }
            playerPositionY = this.playerPositions.get(player2CurrentToken*2);
            playerPositionX = this.playerPositions.get(player2CurrentToken*2+1);
            if(input.equals("place")){
                if(player2TokenCount == PLAYER_2_NUM_TOKENS_ALLOWED||
                this.board[tokenPlaceY][tokenPlaceX] != '_'&&
                this.board[tokenPlaceY][tokenPlaceX] != '|'){
                    throw new IllegalArgumentException();
                }
                player2TokensSamePlace.add(0);
                player2TokenCount+=1;
                playerPositions.add(tokenPlaceY);
                playerPositions.add(tokenPlaceX);
                this.board[tokenPlaceY][tokenPlaceX] = PLAYER_2_TOKEN;
            } else if(input.equals("up")){
                if(this.board[playerPositionY-1][playerPositionX]!='|'&&
                this.board[playerPositionY-1][playerPositionX]!='_'){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY-1][playerPositionX] = PLAYER_2_TOKEN;
                this.board[playerPositionY][playerPositionX] = '|';
                this.playerPositions.set(player2CurrentToken*2, playerPositionY-1);
            } else if(input.equals("down")){
                if(this.board[playerPositionY+1][playerPositionX]!='|'&&
                this.board[playerPositionY+1][playerPositionX]!='_'){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY+1][playerPositionX] = PLAYER_2_TOKEN;
                this.board[playerPositionY][playerPositionX] = '|';
                this.playerPositions.set(player2CurrentToken*2, playerPositionY+1);
            } else if(input.equals("left")){
                if(this.board[playerPositionY]
                [playerPositionX-1]!='|'&&
                this.board[playerPositionY]
                [playerPositionX-1]!='_'){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY]
                [playerPositionX-1] =
                PLAYER_2_TOKEN;
                this.board[playerPositionY]
                [playerPositionX] = '_';
                this.playerPositions.set(player2CurrentToken*2+1, playerPositionX-1);
            } else if(input.equals("right")){
                if(this.board[playerPositionY][playerPositionX+1]!='|'&&
                this.board[playerPositionY][playerPositionX+1]!='_'){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY][playerPositionX+1] = PLAYER_2_TOKEN;
                this.board[playerPositionY][playerPositionX] = '_';
                this.playerPositions.set(player2CurrentToken*2+1, playerPositionX+1);
            } else if(input.equals("jumpUp")){
                if(this.board[playerPositionY-2][playerPositionX]!='|'&&
                this.board[playerPositionY-2][playerPositionX]!='_'||
                this.board[playerPositionY-1][playerPositionX]!=PLAYER_1_TOKEN){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY-2][playerPositionX] = PLAYER_2_TOKEN;
                this.board[playerPositionY][playerPositionX] = '_';
                this.playerPositions.set(player2CurrentToken*2, playerPositionY-2);
                this.board[playerPositionY-1][playerPositionX]='0';
                isGameOver = -1;
            } else if(input.equals("jumpDown")){
                if((this.board[playerPositionY+2][playerPositionX]!='|'&&
                this.board[playerPositionY+2][playerPositionX]!='_')||
                this.board[playerPositionY+1][playerPositionX]!= PLAYER_1_TOKEN){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY+2][playerPositionX] = PLAYER_2_TOKEN;
                this.board[playerPositionY][playerPositionX] = '_';
                this.playerPositions.set(player2CurrentToken*2, playerPositionY+2);
                this.board[playerPositionY+1][playerPositionX]='0';
                isGameOver = -1;
            } else if(input.equals("jumpRight")){
                if(this.board[playerPositionY][playerPositionX+2]!='|'&&
                this.board[playerPositionY][playerPositionX+2]!='_'||
                this.board[playerPositionY][playerPositionX+1]!=PLAYER_1_TOKEN){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY][playerPositionX+2] = PLAYER_2_TOKEN;
                this.board[playerPositionY][playerPositionX] = '|';
                this.playerPositions.set(player2CurrentToken*2+1, playerPositionX+2);
                this.board[playerPositionY][playerPositionX+1]='0';
                isGameOver = -1;
            } else {
                if(this.board[playerPositionY][playerPositionX-2]!='|'&&
                this.board[playerPositionY][playerPositionX-2]!='_'||
                this.board[playerPositionY][playerPositionX-1]!=PLAYER_1_TOKEN){
                    throw new IllegalArgumentException();
                }
                this.board[playerPositionY][playerPositionX-2] = PLAYER_2_TOKEN;
                this.board[playerPositionY][playerPositionX] = '|';
                this.playerPositions.set(player2CurrentToken*2+1, playerPositionX-2);
                this.board[playerPositionY][playerPositionX-1]='0';
                isGameOver = -1;
            }
        }
        is1Turn = !is1Turn;
    } 

    // Behavior: 
    //   - This method returns a number that represents who the next player is
    //Return:
    //   - int: -1 gameplay has stopped, 1 if player 1 is the next player, 
    // 2 if player 2 is the next player 
    public int getNextPlayer(){
        if(isGameOver != -1){
            if(is1Turn){
                return PLAYER_1; 
            } else {
                return PLAYER_2;
            }
        } else {
            return -1;
        }
    }

    // Behavior: 
    //   - This returns the current state of the board
    //Return:
    //   - String: the current board 
    public String toString(){
        String board = "";
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board[i].length; j++){
                board += this.board[i][j];
            }
            board += " \n";
        }
        return board; 
    }

    // Behavior: 
    //   - This method returns a number representing the winning player
    //Return:
    //   - int: -1 if no one has won yet, 1 if player 1, 2 if player 2
    public int getWinner(){
        if(isGameOver == -1){
            if(is1Turn){
                return PLAYER_2; 
            } else {
                return PLAYER_1; 
            }
        } else {
            return -1;
        }
    }

}
