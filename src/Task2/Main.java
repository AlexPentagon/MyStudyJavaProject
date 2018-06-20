package Task2;

import org.apache.commons.cli.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParseException {

        Option output = new Option("o", "output", false
                , "Задает имя выходного файла");

        Option ignoreRegister = new Option("i"
                , false, "Игнорирует регистр слов");

        Option countLines = new Option("c"
                , false, "Выводит количество строк");

        Option searchWithout = new Option("s"
                , false, "Игнорирует N символов при сравнении");

        Option uniqLines = new Option("u"
                , false, "Выводит количество уникальных строк");


        searchWithout.setArgs(1);
        output.setArgs(1);


        Options options = new Options();
        options.addOption(ignoreRegister);
        options.addOption(countLines);
        options.addOption(searchWithout);
        options.addOption(uniqLines);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        CommandLine lines = parser.parse(options, args);

        boolean input = false;
        String filePath = "";
        try {
            filePath = lines.getArgs()[0];
        }catch(ArrayIndexOutOfBoundsException e){ }

        if (filePath.equals("")) input = true;
        Integer afterS ;


        if(lines.hasOption("s")){
            afterS = Integer.valueOf(lines.getOptionValue("s"));
        }else{
             afterS = 0;
        }

        Uniq uniq1 = new Uniq(lines.hasOption("i"),
                              lines.hasOption("u"),
                              lines.hasOption("c"),
                              afterS,
                              lines.hasOption("o"));

    try {
        uniq1.write(uniq1.run(filePath), lines.getOptionValue("o"));
    }catch(IOException e) { System.out.println("File not found"); }

        System.exit(0);
    }
}
