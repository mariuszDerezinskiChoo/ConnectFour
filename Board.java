/******************************************************************************
 * 
 * 
 * Program:  Board
 * 
 * Programmer:  Mariusz Derezinski-Choo
 * Date: 05/25/2018
 * School: Northwest Guilford High School
 * 
 * 
 * Description: This class creates a board object, which consists of a 7x6 gridpane of circles that
 *          serve as the places for the connect 4 pieces to land
 *
 * Learned:  I learned how to use the start method in JavaFX's application class
 *              to start an application
 * 
 *
 * Difficulties:  I initially had difficulties with trying to learn about using JavaFX, since there was no advice
 *  given on where to start/what to read. Luckily, this is the final project of the year so I am well
 *  accustomed to these types of situations by now
 * 
 *********************************************************************************/
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.animation.PathTransition;
import javafx.util.Duration;
import javafx.scene.text.*;
public class Board
{
    /*
     * Instance variables
     */
    private GridPane board;
    private int[][] mockBoard;
    private int turn;
    private MoveLabel bottomLabel;
    private boolean canWin;
    /*
     * board constructor. parameter is the label created in the start method
     * 
     * the label must be initialized in the start method because it must be added to the VBox to create the scene
     */
    public Board(MoveLabel b)
    {
        bottomLabel = b;
        board = new GridPane();
        
        /*
         * set style and make all the nodes white
         */
        board.setPrefWidth(800);
        board.setPrefHeight(600);
        board.setStyle("-fx-background-color: yellow");
        setWhite();
    }
    /*
     * setWhite method: resets the game by making all the slots empty again
     */
    public void setWhite()
    {
        /*
         * reset instance variables
         */
        canWin = true;;
        turn = 1;
        mockBoard = new int[6][7];
        
        /*
         * go through the grid and create a new white circle to add back into the grid
         */
        int i = 0, j = 0;
        for(i = 0; i < 7; i++)
        {
            for(j = 0; j < 6; j++)
            {
                /*
                 * create a circle, place it in a myPane so that if any part of the pane is clicked
                 * the piece will drop
                 */
                Circle addingCircle = new Circle();
                addingCircle.setStroke(Color.BLACK);
                addingCircle.setFill(Color.WHITE);
                addingCircle.setRadius(50);
                MyPane p = new MyPane(j,i);
                
                /*
                 * set the onActionEvent for the pane that makes the piece drop, style the pane and add it to the board
                 */
                p.setOnMouseClicked(e -> {
                    dropPiece(p.getColumn());
                });
                p.setPrefHeight(100);
                p.setPrefWidth(200);
                p.setAlignment(Pos.CENTER);
                p.getChildren().add(addingCircle);
                //System.out.println(addingCircle.getXCoor());
                board.add(p, i, j);
            }
        }
    }
    /*
     * Drop piece method: drops a piece into the column as specified by the user. called when the user
     * click on one the board
     */
    public void dropPiece(int column)
    {
        /*
         * if the game still has not been won, traverse the column to find the next open slot, start an animation
         * of the piece falling, and check to see if there is a winner
         */
        if(canWin)
        for(int i = 5; i >= 0; i--)
        {
            if(mockBoard[i][column] == 0)
            {
                animateDrop(i, column, turn);
                bottomLabel.switchColor();
                mockBoard[i][column] = turn;
                checkWinner(column, i, turn);
                turn += 2;
                turn %= 4;
                
                break;
            }
        }
    }
    /*
     * addPiece methhod: adds in a piece that can be clicked at the specified location in the board
     */
    public void addPiece(int i, int column, int t)
    {
        /*
         * create a circle, set its color based on the current move, put it in a pane and pute the pane in the board
         */
        Circle addingCircle = new Circle();
        addingCircle.setStroke(Color.BLACK);
        if(t % 4 == 1)
            addingCircle.setFill(Color.RED);
        else
                addingCircle.setFill(Color.BLUE);
        addingCircle.setRadius(50);
        MyPane p = new MyPane(i,column);
        p.setOnMouseClicked(e -> {
            dropPiece(p.getColumn());
        });
        
        /*
         * Style the Pane
         */
        p.setPrefHeight(100);
        p.setPrefWidth(200);
        p.setAlignment(Pos.CENTER);
        p.getChildren().add(addingCircle);
        board.add(p, column, i);
                


        //printArray(mockBoard);

                
    }
    /*
     * animate Drop method: creates an animation of a piece falling to the specified location
     */
    public void animateDrop(int row, int column, int t)
    {
        /*
         * Create the circle to be animated and set its style
         */
        double pixelsPerSection = board.getWidth() / 14.0;
        Circle addingCircle = new Circle();
        board.getChildren().add(addingCircle);
        addingCircle.setStroke(Color.BLACK);
        if(t % 4 == 1)
            addingCircle.setFill(Color.RED);
        else
            addingCircle.setFill(Color.BLUE);
        addingCircle.setRadius(50);
        
        
        
        /*
         * create a linear path for the circle to fall, create a pathTransition object to animate the circle along the path
         */
        Line path = new Line((2 * column)*pixelsPerSection + 20, 0, (2 * column) * pixelsPerSection + 20, 100.0 * row + 5); 
        PathTransition drop = new PathTransition();
        //when the drop is finished, remove the animated circle and add in a duplicated myPane
        drop.setOnFinished(e -> { 
            board.getChildren().remove(board.getChildren().indexOf(addingCircle));
            addPiece(row, column, t);
        });
        drop.setDuration(Duration.millis(1000));
        drop.setPath(path);
        drop.setNode(addingCircle);
        drop.setCycleCount(1);
        

        drop.play(); // play the animation

    }

    /*
     * checkWinner method for a new move. checks to see if somebody has won on the latest move
     */
    public void checkWinner(int j, int i, int t)
    {
        if(canWin)
        {
        /*
         * create arrays that show the horizontal, vertical, and diagonal paths that go throught the latest move
         */
        int[] horizontal = new int[7];
        for(int index = 0; index < 7; index++)
        {
            horizontal[index] = mockBoard[i][index];
        }
        int[] vertical = new int[6];
        for(int index = 0; index < 6; index++)
        {
            vertical[index] = mockBoard[index][j];
        }
        int difference = j - i;
        int[] mainDiagonal = new int[7];
        for(int index = 0; index < 7; index++)
        {
            try
            {
                mainDiagonal[index] = mockBoard[index - difference][index];
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
            }
        }
        int[] backupMainDiagonal = new int[7];
        int sum = i + j;
        for(int index = 0; index < 7; index++)
        {
            try
            {
                backupMainDiagonal[index] = mockBoard[sum - index][index];
            }
            catch(ArrayIndexOutOfBoundsException e)
            { 
            }
        }
        /*
         * check each path to see if it has 4 of the same piece in a row. if it does, announce the winner
         */
        if(checkWinner(horizontal) != -1)
            announceWinner(checkWinner(horizontal), t);
        else if(checkWinner(vertical) != -1)
            announceWinner(checkWinner(vertical), t);
        else if(checkWinner(mainDiagonal) != -1)
            announceWinner(checkWinner(mainDiagonal), t);
        else if(checkWinner(backupMainDiagonal) != -1)
            announceWinner(checkWinner(backupMainDiagonal), t);
        else
        {
            /*
             * check for tie
             */
            canWin = false;
            for (int a = 0; a < 6; a++)
                for(int b = 0; b < 7; b++)
                    if(mockBoard[a][b] == 0)
                        canWin = true;
                        
            if(!canWin)
            {
                announceWinner(0, -1);
            }
           
        }
                
        }
    }
    /*
     * checkWinner method for an individual pathway. called by the main checkWinner method. traverses an array
     * and returns 1 IFF it has 4 red or 4 blue pieces in a row
     */
    public int checkWinner(int[] pathway)
    {
        for(int index = 0; index + 3 < pathway.length; index++)
        {
            if(pathway[index] != 0 && pathway[index] == pathway[index + 1] &&
                pathway[index + 1] == pathway[index + 2] && pathway[index + 2] == pathway[index + 3])
            {
                System.out.println("accomplished");
                return pathway[index];
                
            }
        }
        System.out.println("whoops");
        return -1;
    }
    /*
     * announceWinner method. announces the winner on the screen
     */
    public void announceWinner(int i, int t)
    {
        canWin = false; // makes it so that no more moves can be played
        
        /*
         * create a reset button
         */
        Button returnButton = new Button();
        returnButton.setPrefWidth(500);
        returnButton.setPrefHeight(200);
        returnButton.setFont(Font.font("Times New Roman", 40));
        returnButton.setOnMouseClicked(e -> {
                bottomLabel.playSetup();
                setWhite();
                
            });
        bottomLabel.setWinner(returnButton, t); // make the bottom label display the reset button and the winner
    }
    /*
     * Accessor methods for turn and Board
     */
    public int getTurn()
    {
        return turn;
    }
    public GridPane getBoard()
    {
        return board;
    }
    /*
     * print Array methods. for debugging purposes only
     */
    public void printArray(int[][] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr[i].length; j++)
                System.out.print(arr[i][j] + ", ");
            System.out.println("");
        }
    }
    void printArray(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i]);
        System.out.println("\n\n");
    }
}