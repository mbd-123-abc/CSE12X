//Mahika Bagri
//CSE 123 
//5 November 2025

import java.util.*;
import java.awt.*;

// This class is an algorithmic Mondrian Art Generator that can make both simple and complex 
// Mondrian Art on a specified canvas.
public class Mondrian {
    private static final int MAX = 300;
    private static final Random RANDY = new Random();

    // Behavior: 
    //   - This method initializes a basic Mondrian Art painting.
    // Parameters:
    //   - pixles: the grid of pixels the user wants to create Mondrian Art in. 
    // Exceptions:
    //   - if the pixel grid is null or it's dimensions (length/width) are less than 300,
    // an IllegalArgumentException is thrown.
    public void paintBasicMondrian(Color[][] pixels){
        if(pixels == null||pixels.length < MAX||pixels[0].length < MAX){
            throw new IllegalArgumentException();
        }
        paintMondrian(pixels, 0, pixels[0].length, 0, pixels.length, false);
    }

    // Behavior: 
    //   - This method initializes a complex Mondrian Art painting.
    //   - This is a Mondrain that further splits each section into 2 triangles or 4 squares 
    // Parameters:
    //   - pixles: the grid of pixels the user wants to create Mondrian Art in. 
    // Exceptions:
    //   - if the pixel grid is null or it's dimensions (length/width) are less than 300,
    // an IllegalArgumentException is thrown.
    public void paintComplexMondrian(Color[][] pixels){
        if(pixels == null||pixels.length < 300||pixels[0].length < 300){
            throw new IllegalArgumentException();
        }
        paintMondrian(pixels, 0, pixels[0].length, 0, pixels.length, true);
    }

    // Behavior: 
    //   - This method creates the Mondrian art onto a given canvas.
    // The simple methos returns a canvas randomly divided into regions of at least ten pixels
    // and colored in at random. 
    // The complex method returns a canvas randomly divided into regions of at least ten pixels
    // and colored in either with random colors and shapes of either squares or triangles. 
    // Parameters:
    //   - pixels: the grid of pixels the user wants the modrian art on. 
    //   - x1: the integer that is the lower bound of columns to be altered in the pixel grid. 
    //   - x2: the integer that is the upper bound of columns to be altered in the pixel grid.
    //   - y1: the integer that is the lower bound of rows in to be altered the pixel grid.
    //   - y2: the integer that is the upper bound of rows in to be altered the pixel grid. 
    //   - complex: the boolean that signifies wether the user wants a complex art piece or not. 
    private void paintMondrian(Color[][] pixels, int x1, int x2, int y1, int y2, boolean complex){
        int width = x2-x1;
        int length = y2-y1;
        int canvasWidth = pixels[0].length; 
        int canvasLength = pixels.length;
        if(width < canvasWidth/4 && length < canvasLength/4){
            if(complex){  
                if(length == width){
                    int xMid = (x2+x1)/2;
                    int yMid = (y2+y1)/2;

                    fill(pixels, x1, xMid+1, y1, yMid+1, Color.WHITE);
                    fill(pixels, x1, xMid+1, yMid-1, y2, Color.YELLOW);
                    fill(pixels, xMid-1, x2, y1, yMid+1, Color.CYAN);
                    fill(pixels, xMid-1, x2, yMid-1, y2, Color.RED);
                }else{
                    fillTriangle(pixels, x1, x2, y1, y2);
                }
            }else{
                Color shapeColor = colorPicker();
                fill(pixels, x1, x2, y1, y2, shapeColor);
            }
        }else if(width >= canvasWidth/4 && length >= canvasLength/4){
            int xMid = RANDY.nextInt(x2-x1-20) + x1+10;
            int yMid = RANDY.nextInt(y2-y1-20) + y1+10;

            paintMondrian(pixels, x1, xMid, y1, yMid, complex);
            paintMondrian(pixels, x1, xMid, yMid, y2, complex);
            paintMondrian(pixels, xMid, x2, y1, yMid, complex);
            paintMondrian(pixels, xMid, x2, yMid, y2, complex);
        }else if(length >= canvasLength/4){
            int yMid = RANDY.nextInt(y2-y1-20) + y1+10;

            paintMondrian(pixels, x1, x2, y1, yMid, complex);
            paintMondrian(pixels, x1, x2, yMid, y2, complex);
        }else if(width >= canvasWidth/4){
            int xMid = RANDY.nextInt(x2-x1-20) + x1+10;

            paintMondrian(pixels, x1, xMid, y1, y2, complex);
            paintMondrian(pixels, xMid, x2, y1, y2, complex);
        }
    } 

    // Behavior: 
    //   - This method fills a given region of the pixel canvas with a given color.
    // Parameters:
    //   - pixels: the grid of pixels the user wants to color in leaving a one pixel border. 
    //   - x1: the integer that is the lower bound of columns to be altered in the pixel grid. 
    //   - x2: the integer that is the upper bound of columns to be altered in the pixel grid.
    //   - y1: the integer that is the lower bound of rows in to be altered the pixel grid.
    //   - y2: the integer that is the upper bound of rows in to be altered the pixel grid.
    //   - shapeColor: the color that should be used to fill the area.
    private static void fill(Color[][] pixels, int x1, int x2, int y1, int y2, Color shapeColor){
        for (int i = y1+1; i < y2-1; i++) {
            for (int j = x1+1; j < x2-1; j++) {
                pixels[i][j] = shapeColor;
            }
        }
    }
    
    // Behavior: 
    //   - This method fills divides a given region of the pixel canvas into two triangles and
    // fills it in with a two different random colors.
    // Parameters:
    //   - pixels: the grid of pixels the user wants to divide into 2 triangles and color in. 
    //   - x1: the integer that is the lower bound of columns to be altered in the pixel grid. 
    //   - x2: the integer that is the upper bound of columns to be altered in the pixel grid.
    //   - y1: the integer that is the lower bound of rows in to be altered the pixel grid.
    //   - y2: the integer that is the upper bound of rows in to be altered the pixel grid.
    private static void fillTriangle(Color[][] pixels, int x1, int x2, int y1, int y2){
        Color triangleOne = colorPicker();
        Color triangleTwo = colorPicker();
        while(triangleOne == triangleTwo){
            triangleOne = colorPicker();
            triangleTwo = colorPicker();
        }
        for (int i = y1+1; i < y2-1; i++) {
            for (int j = x1+1; j < x2-1; j++) {
                if ((i - y1)>=((double)(y2-y1)/(x2-x1))*(j - x1)){
                    pixels[i][j] = triangleOne;
                }else if((i - y1)<=((double)(y2-y1)/(x2-x1))*(j - x1)){
                    pixels[i][j] = triangleTwo;
                }
            }
        }
    }

    // Behavior: 
    //   - This method picks a random color from white, yellow, cyan, and red.
    // Returns:
    //   - Color: the random color that was selected by the method.  
    private static Color colorPicker(){
        int color = RANDY.nextInt(4);
        Color shapeColor;

        if(color == 0){
            shapeColor = Color.WHITE;
        }else if(color == 1){
            shapeColor = Color.YELLOW;
        }else if(color == 2){
            shapeColor = Color.CYAN;
        }else{
            shapeColor = Color.RED;
        }

        return shapeColor;
    }       
}
