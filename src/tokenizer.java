import opennlp.tools.tokenize.SimpleTokenizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class tokenizer {



    public static String[] tokenize(String data) throws IOException {

        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
        String Prenormalized = data.toLowerCase().replaceAll("\\p{Punct}", "");
        String [] normalized= simpleTokenizer.tokenize(Prenormalized);
        List<String> stopwords = loadStopwords();


        //Loop on every item in the token array and added in the hashmap

        StringBuilder builder = new StringBuilder();
        for (String word : normalized) {
            if (!stopwords.contains(word)) {
                builder.append(word);
                builder.append(' ');
            }
        }

        String result = builder.toString().trim();

        String[] tokens = simpleTokenizer.tokenize(result);


        return tokens;

    }

    public static ArrayList<String> sortedTokens(String data) throws IOException {
        String tokens[]=tokenize(data);
        Arrays.sort(tokens);
        List<String> sortedTokens = (List<String>) Arrays.asList(tokens);
        ArrayList<String> arrSortredTokens = new ArrayList<>(sortedTokens);

        return arrSortredTokens;
    }

    public static ArrayList<String> sortedUnrepeatedTokens(String data) throws IOException {
        String tokens[]=tokenize(data);
        Arrays.sort(tokens);
        List<String> sortedTokens = (List<String>) Arrays.asList(tokens);
        ArrayList<String> arrSortredTokens = new ArrayList<>(sortedTokens);

        for(int i=arrSortredTokens.size()-1; i>0; i--) {
            for(int j=i-1; j>=0; j--) {
                if(arrSortredTokens.get(i).equals(arrSortredTokens.get(j))) {
                    arrSortredTokens.remove(i);
                    break;
                }
            }
        }

        return arrSortredTokens;
    }


    public static List<String> loadStopwords() throws IOException {
        List<String> stopwords;
        stopwords = Files.readAllLines(Paths.get("english_stopwords.txt"));
        return stopwords;
    }


}

