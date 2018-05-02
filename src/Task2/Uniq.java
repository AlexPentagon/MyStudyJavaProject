package Task2;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import java.io.*;
import java.util.ArrayList;

public class Uniq {
    private boolean ignoreRegister,uniqLines,countLines;
    private Integer searchNum;

    Uniq(boolean ignoreRegister,
         boolean uniqLines,
         boolean countLines,
         Integer searchNum){

        this.ignoreRegister = ignoreRegister;
        this.uniqLines = uniqLines;
        this.countLines = countLines;
        this.searchNum = searchNum;
    }
    ArrayList<Pair<String,Integer>> run(String filePath) throws FileNotFoundException{
        ArrayList<String> lines = read(new File(filePath));
        return uniq(lines);
    }

    ArrayList<String> read(File file) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                ArrayList<String> result = new ArrayList<>();
                reader.lines().forEach(result::add);
                return result;
           } catch (FileNotFoundException e) {
               return readComLine();
           }
    }

    ArrayList<String> readComLine(){
        ArrayList<String> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            reader.lines().forEach(result::add);
        }catch (IOException ex){ System.out.println("Ошибка ввода данных");}
        return result;
    }

    void writeFile(ArrayList<Pair<String,Integer>> arr,String file)throws FileNotFoundException{
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String indecator = "";
            for (Pair<String,Integer> pair:arr) {

                if (countLines)indecator = pair.getRight()+" ";
                if (uniqLines ) {
                    if(pair.getRight()==1){
                            writer.write(indecator+""+pair.getLeft());
                            writer.newLine();
                            writer.flush();
                    }
                }else {
                        writer.write(indecator+""+pair.getLeft());
                        writer.newLine();
                        writer.flush();
                }
            }
        }catch (IOException ex){
            System.out.println("Ошибка вывода данных");
        }
    }

    void writeCons(ArrayList<Pair<String,Integer>> arr) {
        String indecator = "";
        for (Pair<String, Integer> pair : arr) {

            if (countLines) indecator = pair.getRight() + " ";

            if (uniqLines) {
                if (pair.getRight() == 1) System.out.println(indecator + "" + pair.getLeft());
            } else System.out.println(indecator + "" + pair.getLeft());
        }
    }




     ArrayList<Pair<String,Integer>> uniq(ArrayList<String> list){
        int counter = 0;
        ArrayList<Pair<String,Integer>> result = new ArrayList<>();

        for(String line : list){
            if(ignoreRegister) line = line.toLowerCase();

            StringBuffer sb = new StringBuffer(line.subSequence(0, line.length()));

            if(searchNum > 0) {
                final String firstline = line;
                sb.delete(0,searchNum);
                line = sb.toString();
                }
            Pair<String,Integer> pair = new MutablePair<>(line,1);
            Pair<String,Integer> emptyPair = new MutablePair<>(line,0);
            if(result.isEmpty()){
                result.add(emptyPair);
            }
            if(!(result.get(counter).getLeft()).equals(line)) {
                result.add(pair);
                counter++;
            }else {
                Pair<String,Integer> changePair = new MutablePair<>((result.get(counter).getLeft()),(result.get(counter).getValue())+1);
                result.remove(counter);
                result.add(changePair);
            }

        }
    return result;
    }
}
