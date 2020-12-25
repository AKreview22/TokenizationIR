/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static java.lang.Math.log10;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;



/**
 *
 * @author marti
 */
public class SimilaritiesCalc {

    BigDecimal bd;
    Double lengthQue;

    TreeMap<String, Double> df = new TreeMap<>();
    TreeMap<String, Double> idf = new TreeMap<>();
    TreeMap<String, Double> tfQue = new TreeMap<>();
    ArrayList<Double> similarities = new ArrayList<>();
    TreeMap<String, Double> tf_idfQue = new TreeMap<>();
    TreeMap<String, Double> lengthDoc = new TreeMap<>();
    TreeMap<String, Double> tfWeightQue = new TreeMap<>();
    TreeMap<String, Double> normalizeQue = new TreeMap<>();
    TreeMap<String, TreeMap<String, ArrayList<Double>>> tfDoc = new TreeMap<>();
    TreeMap<String, TreeMap<String, ArrayList<Double>>> tf_idfDoc = new TreeMap<>();
    TreeMap<String, TreeMap<String, ArrayList<Double>>> tfWeightDoc = new TreeMap<>();
    TreeMap<String, TreeMap<String, ArrayList<Double>>> normalizeDoc = new TreeMap<>();

    public synchronized void tfDocCalc(TreeMap<String, TreeMap<String, ArrayList<Double>>> hash) {
        tfDoc.putAll(hash);
        TreeMap<String, ArrayList<Double>> temp;
        ArrayList<Double> temp1;
        for (Map.Entry<String, TreeMap<String, ArrayList<Double>>> m : tfDoc.entrySet()) {
            String outerKey = m.getKey();
            temp = new TreeMap<>();
            temp.putAll(m.getValue());
            for (Map.Entry<String, ArrayList<Double>> n : temp.entrySet()) {
                String innerKey = n.getKey();
                Double x = n.getValue().size() * 1.0;
                temp1 = new ArrayList<>();
                temp1.add(x);
                temp.replace(innerKey, temp1);
            }
            tfDoc.replace(outerKey, temp);
        }
    }

    public synchronized void tfWeightDocCalc(TreeMap<String, TreeMap<String, ArrayList<Double>>> hash) {

        tfWeightDoc.putAll(hash);
        TreeMap<String, ArrayList<Double>> temp;
        ArrayList<Double> temp1;
        for (Map.Entry<String, TreeMap<String, ArrayList<Double>>> m : tfWeightDoc.entrySet()) {
            String outerKey = m.getKey();
            temp = new TreeMap<>();
            temp.putAll(m.getValue());
            for (Map.Entry<String, ArrayList<Double>> n : temp.entrySet()) {
                String innerKey = n.getKey();
                bd = new BigDecimal(1 + log10((float) n.getValue().size())).setScale(3, RoundingMode.HALF_UP);
                double x = bd.doubleValue();
                temp1 = new ArrayList<>();
                temp1.add(x);
                temp.replace(innerKey, temp1);
            }
            tfWeightDoc.replace(outerKey, temp);
        }
    }

    public synchronized void dfAndIdfCalc(TreeMap<String, TreeMap<String, ArrayList<Double>>> hash) {
        for (Map.Entry<String, TreeMap<String, ArrayList<Double>>> m : hash.entrySet()) {
            df.put(m.getKey(), m.getValue().size() * 1.0);
            bd = new BigDecimal(log10(10.0 / m.getValue().size())).setScale(3, RoundingMode.HALF_UP);
            idf.put(m.getKey(), bd.doubleValue());

        }
    }

    public synchronized void tf_idfDoc() {
        tf_idfDoc.putAll(tfWeightDoc);
        TreeMap<String, ArrayList<Double>> temp;
        ArrayList<Double> temp1;
        for (Map.Entry<String, TreeMap<String, ArrayList<Double>>> m : tfWeightDoc.entrySet()) {
            temp = new TreeMap<>();
            temp.putAll(m.getValue());
            for (Map.Entry<String, ArrayList<Double>> n : temp.entrySet()) {
                temp1 = new ArrayList<>();
                temp1.add(n.getValue().get(0) * idf.get(m.getKey()));
                temp.replace(n.getKey(), temp1);

            }
            tf_idfDoc.replace(m.getKey(), temp);

        }
    }

    public synchronized void lengthDoc() {
        TreeMap<String, ArrayList<Double>> temp;
        for (int j = 0; j < 10; j++) {
            Double sum = 0.0;
            for (Map.Entry<String, TreeMap<String, ArrayList<Double>>> m : tf_idfDoc.entrySet()) {
                temp = new TreeMap<>();
                temp.putAll(m.getValue());
                for (Map.Entry<String, ArrayList<Double>> n : temp.entrySet()) {
                    String str = "Doc" + j + ".txt";
                    if (n.getKey().equals(str)) {
                        sum += Math.pow(n.getValue().get(0), 2);
                        break;
                    }

                }
            }
            lengthDoc.put("Doc" + j + ".txt" , Math.sqrt(sum));
        }
    }

    public synchronized void normalizeDoc() {
        normalizeDoc.putAll(tf_idfDoc);
        TreeMap<String, ArrayList<Double>> temp;
        ArrayList<Double> temp1;
        for (Map.Entry<String, TreeMap<String, ArrayList<Double>>> m : tf_idfDoc.entrySet()) {
            String outerKey = m.getKey();
            temp = new TreeMap<>();
            temp.putAll(m.getValue());
            for (Map.Entry<String, ArrayList<Double>> n : temp.entrySet()) {
                temp1 = new ArrayList<>();
                String innerKey = n.getKey();
                temp1.add(0,lengthDoc.get(n.getKey())/n.getValue().get(0));
                temp.replace(innerKey,temp1 );

            }
            normalizeDoc.replace(outerKey, temp);
        }
    }

    public synchronized void tfQue(TreeMap<String, TreeMap<String, ArrayList<Double>>> hash, ArrayList<String> array) {
        for (Map.Entry<String, TreeMap<String, ArrayList<Double>>> m : hash.entrySet()) {
            Double x = 0.0;
            for (int i = 0; i < array.size(); i++) {
                if (m.getKey().equals(array.get(i))) {
                    x++;
                }
            }
            if (x > 0) {
                tfQue.put(m.getKey(), x);
            }

        }
    }

    public synchronized void tfWeightQue() {
        tfWeightQue.putAll(tfQue);
        for (Map.Entry<String, Double> m : tfWeightQue.entrySet()) {
            bd = new BigDecimal(1 + log10(m.getValue())).setScale(3, RoundingMode.HALF_UP);
            tfWeightQue.replace(m.getKey(), bd.doubleValue());
        }
    }

    public synchronized void tf_idfAndLengthQue() {
        tf_idfQue.putAll(tfWeightQue);
        Double sum1 = 0.0;
        tf_idfQue.putAll(tfWeightQue);
        for (Map.Entry<String, Double> m : tf_idfQue.entrySet()) {
            for (Map.Entry<String, Double> n : idf.entrySet()) {
                if (m.getKey().equals(n.getKey())) {
                    sum1 += Math.pow((m.getValue() * n.getValue()), 2);
                    tf_idfQue.replace(m.getKey(), m.getValue() * n.getValue());
                }
            }
        }
        lengthQue = Math.sqrt(sum1);
    }

    public synchronized void normalizeQue() {
        normalizeQue.putAll(tf_idfQue);
        for (Map.Entry<String, Double> m : normalizeQue.entrySet()) {
            normalizeQue.replace(m.getKey(), m.getValue() / lengthQue);
        }
    }

    public synchronized void similarities() {

        ArrayList<Double> temp;
        for (int i = 0; i < 10; i++) {
            Double sum = 0.0;
            for (Map.Entry<String, Double> m : normalizeQue.entrySet()) {
                if (normalizeDoc.get(m.getKey()).get("Doc" + i + ".txt") != null) {
                    temp = new ArrayList<>();
                    temp.addAll(normalizeDoc.get(m.getKey()).get("Doc" + i + ".txt"));
                    sum += temp.get(0) * m.getValue();
                }

            }
            similarities.add(i, sum);
        }

    }

}