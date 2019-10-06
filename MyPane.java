/******************************************************************************
 * 
 * 
 * Program:  MyPane
 * 
 * Programmer:  Mariusz Derezinski-Choo
 * Date: 05/25/2018
 * School: Northwest Guilford High School
 * 
 * 
 * Description: This class extends stackPane by allowing a pane to be constructed with instnace variables describing
 *              the row and column it will be located in on the grid pane.
 *
 * Learned:  I learned how to extend subclasses of Pane as needed for my programs
 * 
 *
 * Difficulties:  I initially tried to avoid creating this class, but i realized it was necessary to have the 
 *                  row and column instance variables present in the panes since it is complicated
 *                  to access them from getChildren()
 * 
 *********************************************************************************/
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.layout.StackPane;
public class MyPane extends StackPane
{
    private int row, column; // instance variables
    /*
     * constructer of my pane. does everything a stack pane does plus stores the row and column as instance variables
     */
    public MyPane(int r, int c)
    {
        super();
        row = r;
        column = c;
    }
    /*
     * accessors
     */
    public int getRow()
    {
        return row;
    }
    public int getColumn()
    {
        return column;
    }
}