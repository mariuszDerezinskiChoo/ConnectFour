/******************************************************************************
 * 
 * 
 * Program:  Connect for(aka Connect Four) {}
 * 
 * Programmer:  Mariusz Derezinski-Choo
 * Date: 05/25/2018
 * School: Northwest Guilford High School
 * 
 * 
 * Description: This program runs a Connect Four game generated with JavaFX. The class in this file contains the main
 *      method, which for Java fx is the start method override in the application class
 *
 * Learned:  I learned how to use the start method in JavaFX's application class
 *              to start an application. I also learned how to use subclasses of Pane such as the VBox
 *              to organize nodes. 
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
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.*;
import javafx.scene.text.*;
public class ConnectFour extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        /*
         * adjust the primaryStage
         */
        primaryStage.setResizable(false);
        primaryStage.setTitle("Final Project");
        
        
        
        
        /*
         * create a bottomLabel, which displays game information, and a board, which shows the game play
         */
        MoveLabel bottomLabel = new MoveLabel();
        GridPane board = new Board(bottomLabel).getBoard();
        
        /*
         * create a header that displays the game name
         */
        Label header = new Label("Connect for(){}");
        header.setPrefWidth(1000);
        header.setPrefHeight(100);
        header.setFont(Font.font("Times New Roman", 50));
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: orchid");
        
        /*
         * Create a VBox to organize all three nodes, add the title, board, and bottom label, and display it in the
         * primary scene
         */
        VBox gameplay = new VBox();
        gameplay.setPrefHeight(1000);
        gameplay.setPrefWidth(800);
        gameplay.getChildren().addAll(header, board, bottomLabel);
        Scene scene = new Scene(gameplay, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}