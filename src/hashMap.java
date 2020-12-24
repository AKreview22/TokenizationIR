import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class hashMap {

    public static TreeMap<String,ArrayList<Double>> DocPositions(String tokenName , ArrayList<File> myFiles ) throws IOException {

        TreeMap<String,ArrayList<Double>> map = new TreeMap<>() ;
        ArrayList<String> filesNames = searchFiles.tokenFiles(tokenName,myFiles);

        for ( int i = 0 ; i < filesNames.size() ; i++ )
        {
            String currentDoc = filesNames.get(i);
            ArrayList<Double> positions = searchFiles.tokenPositions(tokenName,myFiles.get(i));
            map.put(currentDoc,positions);
        }
        return map ;
    }

    public static TreeMap<String,TreeMap<String,ArrayList<Double>>> tokensMap(ArrayList<File> myFiles ) throws IOException {

        TreeMap<String,TreeMap<String,ArrayList<Double>>> tokenMap =
                new TreeMap<String,TreeMap<String,ArrayList<Double>>>();

        ArrayList<String> filesContent = fileHandler.readNfiles(myFiles);
        ArrayList<String> tokens = tokenizer.sortedTokens(filesContent.toString());

        for ( int i = 0 ; i <tokens.size() ; i++)
        {
            TreeMap<String,ArrayList<Double>> innerMap = DocPositions(tokens.get(i),myFiles);
            tokenMap.put(tokens.get(i),innerMap);
        }
        return tokenMap;
    }

}
