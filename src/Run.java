/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author marti
 */
public class Run {

    public String query;

    public Run(String query) throws IOException {

        ArrayList<String> queToken = new ArrayList<>();
        ArrayList<File> files = new ArrayList<>();
        TreeMap<String, TreeMap<String, ArrayList<Double>>> tokensMap = new TreeMap<>();
        fileHandler fh = new fileHandler();
        hashMap hm = new hashMap();
        tokenizer t = new tokenizer();
        files.addAll(fileHandler.createNfiles(10));
        fileHandler.writeFile(files.get(0), "An information retrieval process begins when a user enters a query into the system");
        fileHandler.writeFile(files.get(1), "Queries are formal statements of information needs");
        fileHandler.writeFile(files.get(2), "for example search strings in web search engines");
        fileHandler.writeFile(files.get(3), "In information retrieval a query does not uniquely identify a single object in the collection");
        fileHandler.writeFile(files.get(4), "instead, several objects may match the query, perhaps with different degrees of relevancy.");
        fileHandler.writeFile(files.get(5), "An object is an entity that is represented by information in a content collection or database");
        fileHandler.writeFile(files.get(6), "User queries are matched against the database information");
        fileHandler.writeFile(files.get(7), "However, as opposed to classical SQL queries of a database");
        fileHandler.writeFile(files.get(8), "in information retrieval the results returned may or may not match the query");
        fileHandler.writeFile(files.get(9), "so results are typically ranked");
        tokensMap.putAll(hashMap.tokensMap(files));
        for(Map.Entry<String, TreeMap<String, ArrayList<Double>>> m:tokensMap.entrySet()){
            System.out.println(m);
        }

        SimilaritiesCalc sc = new SimilaritiesCalc();
        sc.tfDocCalc(tokensMap);
        sc.tfWeightDocCalc(tokensMap);
        sc.dfAndIdfCalc(tokensMap);
        sc.tf_idfDoc();
        sc.lengthDoc();
        sc.normalizeDoc();

        this.query = query;
        queToken.addAll(tokenizer.sortedTokens(query));

        sc.tfQue(tokensMap, queToken);
        sc.tfWeightQue();
        sc.tf_idfAndLengthQue();
        sc.normalizeQue();
        sc.similarities();


        for(int i=0;i<sc.similarities.size();i++){
            System.out.println("Similariries(q,d"+ i+") = "+sc.similarities.get(i));
        }

    }

}