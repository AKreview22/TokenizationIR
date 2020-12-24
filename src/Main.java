import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class Main {

    public static void main(String args[]) throws IOException {



        ArrayList <File> myFiles = fileHandler.createNfiles(10);
        fileHandler.writeFile(myFiles.get(0),"Thought I almost died, in my dream again (Baby, almost died)");
        fileHandler.writeFile(myFiles.get(1),"Fightin' for my life, I couldn't breathe again\n");
        fileHandler.writeFile(myFiles.get(2),"I'm fallin' into almost new (Oh)\n");
        fileHandler.writeFile(myFiles.get(3),"Without you goin' smooth (Fallin' in)\n");
        fileHandler.writeFile(myFiles.get(4),"'Cause my heart almost belongs to you\n");
        fileHandler.writeFile(myFiles.get(5),"'Cause my heart almost belongs to you\n");
        fileHandler.writeFile(myFiles.get(6),"'Cause my heart almost belongs to you\n");
        fileHandler.writeFile(myFiles.get(7),"'Cause my heart almost belongs to you\n");
        fileHandler.writeFile(myFiles.get(8),"'Cause my heart almost belongs to you\n");
        fileHandler.writeFile(myFiles.get(9),"'Cause my heart almost belongs to you\n");

        TreeMap<String,TreeMap<String,ArrayList<Double>>> map = hashMap.tokensMap(myFiles);

        System.out.println(map);


    }

}
