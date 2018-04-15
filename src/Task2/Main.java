package Task2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.*;
public class Main {

    public static void fileReader(ArrayList<String> arr, Uniq uniq) {
        try (BufferedReader br = new BufferedReader(new FileReader(uniq.flags.get("-f")))) {
            String text;
            while ((text = br.readLine()) != null) {
                arr.add(text);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void fileWriter(ArrayList<String> arr,ArrayList<String> carr,
                                  ArrayList<String> sarr, HashSet<String> uarr, Uniq uniq) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(uniq.flags.get("-o")))) {
            if (uniq.c || uniq.u || uniq.s) {
                if (uniq.u) {
                    for (String elem : uarr) {
                        bw.write(elem + "\r\n");
                        bw.flush();
                    }
                }
                if (uniq.c) {
                    for (String elem : carr) {
                        bw.write(elem + "\r\n");
                        bw.flush();

                    }
                }
                if (uniq.s) {
                    for (String elem : sarr) {
                        bw.write(elem + "\r\n");
                        bw.flush();
                    }
                }
            } else {
                for (String elem : arr) {
                    bw.write(elem + "\r\n");
                    bw.flush();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String flagFinder(String str) {
        if (str.split("-").length == 1) return "";
        else {
            String[] arr;
            ArrayList<String> narr = new ArrayList<>();
            arr = str.split("-");
            for (int i = 1; i < arr.length; i++) {
                narr.add(arr[i]);
            }
            String sym = "";
            for (String elem : narr) {
                int i = 0;
                sym = sym + " " + String.valueOf(elem.charAt(0));
            }
            return sym;
        }
    }

    public static String parser(String str) {
        if (str.split("-").length == 1) return str;
        else {
            String[] arr;
            arr = str.split("-");
            String result = arr[0];
            for (int i = 1; i < arr.length; i++) {
                arr[i] = arr[i].substring(1, arr[i].length());

                result = result + arr[i];
            }
            return result;
        }
    }
}