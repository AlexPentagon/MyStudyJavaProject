package Task2;


import org.apache.commons.cli.*;
import org.apache.commons.lang3.tuple.Pair;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
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

        String filePath = args[args.length - 1];
        Integer afterS ;

        if(lines.hasOption("s")){
            afterS = Integer.valueOf(lines.getOptionValue("s"));
        }else{
             afterS = 0;
        }

        Uniq uniq1 = new Uniq(lines.hasOption("i"),
                              lines.hasOption("u"),
                              lines.hasOption("c"),
                              lines.hasOption("s"),
                              afterS,
                              lines.hasOption("o"));

        if(lines.hasOption("o")){
            uniq1.writeFile(uniq1.run(filePath),lines.getOptionValue("o"));
        }else{
            for (String line:uniq1.run(filePath)) {
                 System.out.println(line);
            }
        }
        System.exit(0);
    }
}
