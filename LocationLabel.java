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
public class LocationLabel extends Label
{
    private boolean isOn;
    public LocationLabel(String str)
    {
        super(str);
        setStyle("-fx-background-color: green");
        isOn = true;
    }
    public void change()
    {
        isOn = !isOn;
        if(isOn)
        {
            setStyle("-fx-background-color: green");
        }
        else
            setStyle("-fx-background-color: red");
    }
    public boolean isOn()
    {
        return isOn;
    }
}