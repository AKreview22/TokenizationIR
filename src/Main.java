import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String args[]) throws IOException {

        String query;

        System.out.println("Query is : ");
        Scanner n=new Scanner(System.in);
        query=n.nextLine();

        Run obj=new Run(query);


        /*ArrayList <File> myFiles = fileHandler.createNfiles(5);
        fileHandler.writeFile(myFiles.get(0),"Thought I almost died, breathe in my dream again (Baby, almost died)");
        fileHandler.writeFile(myFiles.get(1),"Fightin' for my life, I smooth couldn't breathe again");
        fileHandler.writeFile(myFiles.get(2),"I'm fallin' into almost new (Oh)");
        fileHandler.writeFile(myFiles.get(3),"Without almost you goin' smooth almost (Fallin' in)");
        fileHandler.writeFile(myFiles.get(4),"'Cause my heart died almost belongs smooth to you");



        TreeMap<String,TreeMap<String,ArrayList<Double>>> map = hashMap.tokensMap(myFiles);
        System.out.println(
                "\nDisplaying the TreeMap:");

        System.out.println(
                "TreeMap: " + map );*/

    }

}
