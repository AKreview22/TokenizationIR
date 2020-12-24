import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*;

public class searchFiles {

    public static ArrayList<String> tokenFiles(String tokenName , ArrayList<File> myFiles ) throws IOException {
        ArrayList<String> filesNames = new ArrayList<String>();

        for ( int i=0 ; i<myFiles.size() ;i++) {
            String fileContent = fileHandler.readFile(myFiles.get(i));
            boolean isFound = fileContent.contains(tokenName);
            if (isFound)
            {
                filesNames.add(myFiles.get(i).getName());
            }
        }
        return filesNames ;
    }

    public static ArrayList<Double> tokenPositions(String tokenName , File myFile ) throws IOException {
        int counter = 0 ;
        ArrayList<Double> positions = new ArrayList<Double>();
        String fileContent = fileHandler.readFile(myFile);
        tokenName = tokenName.toLowerCase();
        Pattern regex = Pattern.compile("\\b(?:("+tokenName+")|(\\S+))\\b");
        Matcher regexMatcher = regex.matcher(fileContent);
        while (regexMatcher.find()) {
            counter++;

            if(tokenName.equals(regexMatcher.group())){
                positions.add((double) counter);
            }
        }
        return positions;
    }

}
