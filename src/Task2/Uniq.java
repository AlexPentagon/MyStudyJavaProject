package Task2;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import java.io.*;
import java.util.ArrayList;

public class Uniq {
    private boolean ignoreRegister,uniqLines,countLines,output;
    private Integer searchNum;

    Uniq(boolean ignoreRegister,
         boolean uniqLines,
         boolean countLines,
         Integer searchNum,
         boolean output){

        this.ignoreRegister = ignoreRegister;
        this.uniqLines = uniqLines;
        this.countLines = countLines;
        this.searchNum = searchNum;
        this.output = output;

    }

    ArrayList<Pair<String,Integer>> run(String filePath) throws FileNotFoundException {
        ArrayList<String> lines = read(filePath);
        return uniq(lines);
    }

    ArrayList<String> read(String file) throws FileNotFoundException {
        BufferedReader reader;
        ArrayList<String> result = new ArrayList<>();

            if (!file.equals("")) reader = new BufferedReader(new FileReader(file));
            else reader = new BufferedReader(new InputStreamReader(System.in));
            reader.lines().forEach(result::add);

           return result;
    }


    void write(ArrayList<Pair<String,Integer>> arr,String file) throws IOException{
        BufferedWriter writer = null;

           if(output) writer = new BufferedWriter(new FileWriter(file));
           else writer = new BufferedWriter(new OutputStreamWriter( System.out));
            String indecator = "";
            for (Pair<String,Integer> pair:arr) {

                if (countLines)indecator = pair.getRight()+" ";

                if (!uniqLines || pair.getRight() == 1) {
                    writer.write(indecator + "" + pair.getLeft());
                    writer.newLine();
                    writer.flush();
                }


            }

    }


     ArrayList<Pair<String,Integer>> uniq(ArrayList<String> list){
        String compar1 = "";
        String compar3 = "";
        ArrayList<Pair<String,Integer>> result = new ArrayList<>();
         MutablePair<String,Integer> pair = null;

        for(String line : list){

            compar1 = line;
            if(ignoreRegister) compar1 =  compar1.toLowerCase();


            if(searchNum > 0) {
                if (searchNum > line.length()) compar1 = "";
                else compar1 = (compar1.substring(searchNum, compar1.length()));
            }

            if(result.isEmpty() || !(compar3).equals(compar1)) {
                pair = new MutablePair<>(line,1);
                result.add(pair);
                compar3 = (compar1);
            } else {
                pair.setValue(pair.getValue()+1);
            }

        }
    return result;
    }
}
