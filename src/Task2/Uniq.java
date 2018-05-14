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

    ArrayList<Pair<String,Integer>> run(boolean input, String filePath) {
        ArrayList<String> lines = read(input,new File(filePath));
        return uniq(lines);
    }

    ArrayList<String> read(boolean input,File file) {
        BufferedReader reader;
        ArrayList<String> result = new ArrayList<>();
        try {
            if (!input) reader = new BufferedReader(new FileReader(file));
            else reader = new BufferedReader(new InputStreamReader(System.in));
            reader.lines().forEach(result::add);
           } catch (FileNotFoundException e) {
               System.out.println("File not found");
           }
           return result;
    }


    void write(ArrayList<Pair<String,Integer>> arr,String file){
        BufferedWriter writer = null;
        try{
           if(output) writer = new BufferedWriter(new FileWriter(file));
            String indecator = "";
            for (Pair<String,Integer> pair:arr) {

                if (countLines)indecator = pair.getRight()+" ";
                if (uniqLines ) {
                    if(pair.getRight()==1){
                        if(!output) System.out.println(indecator + "" + pair.getLeft());
                        else {
                            writer.write(indecator + "" + pair.getLeft());
                            writer.newLine();
                            writer.flush();
                        }
                    }
                }else {
                    if(!output)System.out.println(indecator + "" + pair.getLeft());
                    else{
                        writer.write(indecator+""+pair.getLeft());
                        writer.newLine();
                        writer.flush();
                    }
                }
            }
        }catch (IOException ex){ }
    }


     ArrayList<Pair<String,Integer>> uniq(ArrayList<String> list){
        int counter = 0;
        String compar1 = "";
        String compar2 = "";
        ArrayList<String> comparList = new ArrayList<>();
        ArrayList<Pair<String,Integer>> result = new ArrayList<>();

        for(String line : list){

            compar1 = line;
            if(ignoreRegister) compar1 =  compar1.toLowerCase();


            if(searchNum > 0) {
                if (searchNum > line.length()) compar1 = "";
                else compar1 = (compar1.substring(searchNum, compar1.length()));
            }

            MutablePair<String,Integer> pair = new MutablePair<>(line,1);
            if(result.isEmpty()){
                pair.setValue(0);
                result.add(pair);
                comparList.add(compar1);
                compar2 = line;
            }
            if(!(comparList.get(counter).equals(compar1))) {
                result.add(pair);
                comparList.add(compar1);
                counter++;
                compar2 = line;
            }else {
                pair.setLeft(compar2);
                pair.setValue(result.get(counter).getValue()+1);
                result.remove(counter);
                result.add(pair);
            }

        }
    return result;
    }
}
