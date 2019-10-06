import java.util.*;
import java.io.*;
public class graduation
{
    public static void main(String[] args)
    {
        String getting = new String();
        int i = 0;
        Scanner kbReader = null;
        try
        {
        kbReader = new Scanner(new File("README.TXT"));
        }
        catch (FileNotFoundException e)
        {
            System.exit(0);
        }
        while(kbReader.hasNext())
        {
            getting = kbReader.nextLine();
            i += getting.length();
        }
        System.out.println(i);
    }
}