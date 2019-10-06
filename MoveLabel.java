/******************************************************************************
 * 
 * 
 * Program:  MoveLabel
 * 
 * Programmer:  Mariusz Derezinski-Choo
 * Date: 05/25/2018
 * School: Northwest Guilford High School
 * 
 * 
 * Description: This class creates a MoveLabel object by extending HBox. it is basically
 *                  a specific creation of an HBox that serves as the bottom toolbar for the
 *                  game, displaying whose move it is or who has won the game and allowing
 *                  the user to start a new game
 *
 * Learned:  I learned how to extend the HBox class to create objects that fit my specific needs
 * 
 *
 * Difficulties:  I had a lot of difficulties with getting the HBox to display the correct colors
 *                      luckily, I was able to use some online resources to teach myself
 *                      CSS and Pane-specific methods related to the Color class
 * 
 *********************************************************************************/
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import javafx.scene.shape.Circle;
import javafx.geometry.*;
import javafx.scene.control.Button;
public class MoveLabel extends HBox
{
    /*
     * Instance variables
     */
    private int moveColor;
    private Label moveLabel;
    private Circle myCircle;
    /*
     * constructor of an HBox. basically the same as a regular HBox except sets a width and by default will
     * make it fill with the gameplay setup
     */
    public MoveLabel()
    {
        super();
        //setAlignment(Pos.CENTER);
        setPrefWidth(1000);
        playSetup();
    }
    /*
     * gameplay setup: makes the HBox appear as it should during a game by displaying whose move it is
     */
    public void playSetup()
    {
        getChildren().clear(); // clear any prior nodes in the HBox
        moveColor = 1;
        
        
        
        /*
         * create a Red circle since red moves first
         */
        myCircle= new Circle();
        myCircle.setStroke(Color.BLACK);
        myCircle.setRadius(50);
        /*
         * create two labels that say it is red's turn
         */
        moveLabel = new Label("RED");
        moveLabel.setFont(Font.font("Times New Roman",FontWeight.BOLD, 50));
        moveLabel.setTextAlignment(TextAlignment.RIGHT);
        moveLabel.setPrefWidth(450);
        setColor();
        getChildren().removeAll();
        Label generic = new Label("To Move: ");
        generic.setPrefWidth(450);
        generic.setFont(Font.font("Times New Roman",50));
        
        /*
         * add all three nodes to the HBox
         */
        generic.setAlignment(Pos.CENTER_RIGHT);
        getChildren().addAll(generic, myCircle, moveLabel);
        setPrefWidth(800);
        setPrefHeight(100);
    }
    /*
     * set color method. changes the information during gameplay by switching between
     * displaying that it is red's turn to move and it is blue turn's to move
     */
    public void setColor()
    {
        if(moveColor % 4 == 1)
        {
            myCircle.setFill(Color.RED);
            moveLabel.setText("RED");
            moveLabel.setTextFill(Color.RED);
        }
        else
        {
           myCircle.setFill(Color.BLUE);
           moveLabel.setText("BLUE       ");
           moveLabel.setTextFill(Color.BLUE);
        }
    }
    /*
     * setWinner method: display's the winner by removing the gameplay commentary and showing a winner 
     * statement. also adds a button that, when clicked, starts a new game
     */
    public void setWinner(Button returnButton, int t)
    {
        getChildren().clear(); // clear out the gameplay nodes
        /*
         * show who has won
         */
        Label winnerLabel = new Label();
        if(t % 4 == 1)  // BLUE wins
        {
            winnerLabel.setText("Red Wins");
            winnerLabel.setTextFill(Color.RED);
        }
        else if (t % 4 == 3) // RED wins
        {
            winnerLabel.setText("Blue Wins");
            winnerLabel.setTextFill(Color.BLUE);
        }
        else // its a tie
        {
            winnerLabel.setText("It's a Tie");
        }
        winnerLabel.setPrefWidth(500);
        winnerLabel.setPrefHeight(200);
        winnerLabel.setFont(Font.font("Times New Roman", 40));
        winnerLabel.setAlignment(Pos.CENTER);
        
        /*
         * edit the play again button and add both nodes to the HBox
         */
        returnButton.setText("PLAY AGAIN");
        returnButton.setStyle("-fx-background-color: green");
        getChildren().addAll(winnerLabel, returnButton);
    }
    /*
     * switch the color by adding 4 and taking a mod2. called after each valid move
     */
    public void switchColor()
    {
        moveColor += 2;
        moveColor %= 4;
        setColor();
    }
        
}

