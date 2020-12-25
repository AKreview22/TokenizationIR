import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class fileHandler {


    public static File createFile(String fileName) throws IOException {
        File myFile = new File(fileName+".txt");
        myFile.createNewFile();
        return myFile;
    }

    public static ArrayList<File> createNfiles(int filesNo) throws IOException{
        ArrayList<File> myFiles = new ArrayList<File>();
        for (int i=0 ; i<filesNo ; i++) {
            String fileName = "Doc" + i ;
            myFiles.add(createFile(fileName));
        }
        return myFiles;
    }


    public static void writeFile(File myFile , String paragraph) throws IOException {

        FileWriter myWriter;
        {
            try {
                myWriter = new FileWriter(myFile, true);
                myWriter.write(paragraph+"\n");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("Error in writing");
                e.printStackTrace();
            }
        }

    }

    public static ArrayList<File> writeNfiles(ArrayList<File> myFiles , String
            Paragraph ) throws IOException{

        for ( int i=0 ; i<myFiles.size();i++)
        {
            writeFile(myFiles.get(i),Paragraph);
        }
        return myFiles;
    }


    public static String readFile(File myFile){
        String data = "\n" ;
        try {
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
                data += "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        data = data.toLowerCase();
        return data ;
    }

    public static ArrayList<String> readNfiles(ArrayList<File> myFiles ) throws IOException{
        ArrayList<String> data = new ArrayList<String>();

        for ( int i=0 ; i<myFiles.size() ;i++)
        {
            data.add(readFile(myFiles.get(i)));
        }
        return data;
    }

}
