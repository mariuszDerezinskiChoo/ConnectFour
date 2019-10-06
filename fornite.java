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
public class fornite extends Application
{
    public static final String[] referencePacket = new String[]{"Junk Junction", "Haunted Hills", "Pleasant Park", "Snobby Shores", "Tilted Towers", "Greasy Grove", "Shifty Shafts", "Flush Factory", "Lucky Landing", "Salty Springs", "Retail Row", "Moisty Mibe", "Dusty Divot", "Tomato Town", "Anarchy Acres", "Wailing Woods", "Risky Reels", "Lonely Lodge"};
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setResizable(false);
        primaryStage.setTitle("fortnite");
        
        ArrayList<String> possibleLocations = new ArrayList<String>();
        for(int i = 0; i < referencePacket.length; i++)
        {
            possibleLocations.add(referencePacket[i]);
        }
        
                
        PickLocations test = new PickLocations(possibleLocations);
        Scene picking = new Scene(test, 1000, 800);
        
        
        
        
        Button setLocations = new Button("Set Possible Locations");
        setLocations.setFont(Font.font("Times New Roman", 80));
        setLocations.setPrefHeight(400);
        setLocations.setOnAction(e -> {
            primaryStage.setScene(picking);
        });
        mainFrame mainframe = new mainFrame(possibleLocations, setLocations);
        Scene scene = new Scene(mainframe, 1000, 800);
        
        
        Button goToMain = new Button("Update");
        goToMain.setFont(Font.font("Times New Roman", 40));
        goToMain.setOnAction(e -> {
            primaryStage.setScene(scene);
        });
        test.getChildren().add(goToMain);
        
        

        primaryStage.setScene(scene);

        
        primaryStage.show();
    }
}
