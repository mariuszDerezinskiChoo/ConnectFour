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
import javafx.scene.control.Button;
import java.util.*;
public class mainFrame extends VBox
{
    private ArrayList<String> possibleLocations;
    public mainFrame(ArrayList<String> p, Button setLocations)
    {
        super();
        
        possibleLocations = p;
        
        
        Label spot = new Label();
        spot.setPrefHeight(400);
        spot.setPrefWidth(1000);
        spot.setAlignment(Pos.CENTER);
        spot.setFont(Font.font("Times New Roman", 80));
        
        Button newGame = new Button("New Game");
        newGame.setFont(Font.font("Times New Roman", 80));
        


       
        newGame.setPrefHeight(400);
        newGame.setPrefWidth(500);
        newGame.setOnAction(e -> {
            setText(spot);
        });
        
        setLocations.setPrefWidth(500);
        
        HBox bottom = new HBox();
        bottom.getChildren().addAll(newGame, setLocations);
        
        setPrefHeight(800);
        setPrefWidth(1000);
        getChildren().addAll(spot, bottom);
    }
    public void setText(Label b)
    {
        int index = (int) (Math.random() * possibleLocations.size());
        b.setText(possibleLocations.get(index));
    }
}