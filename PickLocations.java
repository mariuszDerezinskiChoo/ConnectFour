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
public class PickLocations extends VBox
{
    public PickLocations(ArrayList<String> posLocations)
    {
        super();
        setPrefHeight(800);
        setPrefWidth(1000);
        
        for(int i = 0; i < posLocations.size(); i++)
        {
            LocationLabel location = new LocationLabel(posLocations.get(i));

            location.setOnMouseClicked(e -> {
                location.change();
                if(location.isOn())
                    posLocations.add(location.getText());
                else
                    posLocations.remove(posLocations.indexOf(location.getText()));
            });
            getChildren().add(location);
        }
    }
    public void addBottomButton(Button b)
    {
        getChildren().add(b);
    }
}