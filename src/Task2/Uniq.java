package Task2;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class Uniq {
    private boolean ignoreRegister,uniqLines,countLines,searchWithout,output;
    private Integer searchNum;

    Uniq(boolean ignoreRegister,
         boolean uniqLines,
         boolean countLines,
         boolean searchWithout,
         Integer searchNum,
         boolean output){

        this.ignoreRegister = ignoreRegister;
        this.uniqLines = uniqLines;
        this.countLines = countLines;
        this.searchWithout = searchWithout;
        this.searchNum = searchNum;
        this.output = output;
    }
    ArrayList<String> run(String filePath) throws FileNotFoundException {
        ArrayList<String> lines = read(new File(filePath));
        return uniq(lines);
    }

    ArrayList<String> read(File file) throws FileNotFoundException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> result = new ArrayList<>();
            reader.lines().forEachOrdered(result::add);
            return result;
        }catch(FileNotFoundException e){
            return readComLine();
        }
    }

    void writeFile(ArrayList<String> arr,String file)throws FileNotFoundException{
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String line:arr) {
                writer.write(line +"\r\n");
                writer.flush();
            }
        }catch (IOException ex){
            ex.getMessage();
        }
    }


    ArrayList<String> readComLine(){
        ArrayList<String> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            reader.lines().forEachOrdered(result::add);
        }catch (IOException ex){ ex.getMessage();}
        return result;
    }

     ArrayList<String> uniq(ArrayList<String> list){
        ArrayList<String> result = new ArrayList<>();
        Stack<Pair<String,Integer>> stack = new Stack<>();
        Stack<Pair<String,Integer>> invertStack = new Stack<>();
        Integer counter = 1;

        for(String line : list){
            if(ignoreRegister) line = line.toLowerCase();
            StringBuffer sb = new StringBuffer(line.subSequence(0, line.length()));

            if(searchWithout) {
                sb.delete(0,searchNum);
                line = sb.toString();
                }
            Pair<String,Integer> pair = new MutablePair<>(line,counter);
            Pair<String,Integer> emptyPair = new MutablePair<>(line,0);
            if(stack.empty())stack.push(emptyPair);
            if(!stack.peek().getLeft().equals(line)) {
                stack.push(pair);
                counter = 1;
            }else {
                Pair<String,Integer> changePair = new MutablePair<>((stack.peek().getLeft()),(stack.peek().getValue())+1);
                stack.pop();
                stack.push(changePair);
            }

        }
        while (!stack.empty()){
            invertStack.push(stack.pop());
        }

         while (!invertStack.empty()){
             if(uniqLines && countLines){
               if(invertStack.peek().getRight() == 1) result.add(invertStack.peek().getRight()+""+invertStack.pop().getLeft());
               else invertStack.pop();
             }
             else if(uniqLines || countLines){
                if(uniqLines){
                    if(invertStack.peek().getRight() == 1) {
                        result.add(invertStack.pop().getLeft());
                    }else invertStack.pop();
                }else result.add(invertStack.peek().getRight()+""+invertStack.pop().getLeft());
             }
             else result.add(invertStack.pop().getLeft());
         }
    return result;
    }
}
