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
        fileHandler.writeFile(files.get(0), "Oh, baby");
        fileHandler.writeFile(files.get(1), "Where are you now when I need you most ?");
        fileHandler.writeFile(files.get(2), "I give it all just to hold you close");
        fileHandler.writeFile(files.get(3), "Sorry that I broke your heart, your heart");
        fileHandler.writeFile(files.get(4), "I said, baby heart");
        fileHandler.writeFile(files.get(5), "I treat you better than I did before");
        fileHandler.writeFile(files.get(6), "I hold you down, when I let you go");
        fileHandler.writeFile(files.get(7), "This time I won't break your heart, your heart, yeah");
        fileHandler.writeFile(files.get(8), "I know it's all my fault");
        fileHandler.writeFile(files.get(9), "Made you put down your guard");
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

        for (int i = 0; i < 10; i++) {
            System.out.printf("Similaritis(q,%dd)= %d\n", i, sc.similarities.get(i));
        }

    }

}