package Task2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import static Task2.Main.*;

//-f D:\\notes5.txt -o D:\\note6.txt
public class Uniq {
    public boolean i;
    public boolean u = false;
    public boolean c = false;
    public boolean s = false;
    public boolean o = false;
    public boolean f = false;
    public HashMap<String, String> flags = new HashMap<>();

    public Uniq(boolean i, boolean u, boolean c, boolean s, boolean o, boolean f, HashMap<String, String> flags) {
        this.i = i;
        this.u = u;
        this.c = c;
        this.s = s;
        this.o = o;
        this.f = f;
        this.flags = flags;
    }

    public static void main(String[] args) {
        Uniq u = new Uniq(false, false, false, false, false, false, new HashMap<>());


        uniq(u);
    }

    public static void uniq(Uniq uniq) {

        ArrayList<String> arr = new ArrayList<>();
        HashSet<String> uarr = new HashSet<>();
        ArrayList<String> carr = new ArrayList<>();
        ArrayList<String> sarr = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String text;
            while (!(text = br.readLine()).equals("")) {
                arr.add(text);
                sarr.add(text);

                String[] ind = flagFinder(text).split(" ");

                for (String anInd : ind) {
                    try {
                        switch (anInd) {
                            case "i":
                                uniq.i = true;
                                break;
                            case "u":
                                uniq.u = true;
                                break;
                            case "c":
                                uniq.c = true;
                                break;
                            case "s":
                                uniq.s = true;
                                uniq.flags.put("-s", text.split("-s")[1].split(" ")[1]);
                                break;
                            case "o":
                                uniq.o = true;
                                uniq.flags.put("-o", text.split("-o")[1].split(" ")[1]);
                                break;
                            case "f":
                                uniq.f = true;
                                uniq.flags.put("-f", text.split("-f")[1].split(" ")[1]);
                                break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }

                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        if (uniq.f) fileReader(arr, uniq);

        int counter = 0;
        String lf = "";
        for (int x = 1; x < arr.size(); x++) {

            if (uniq.i) {
                if (uniq.s) {
                    String a = arr.get(x - 1).substring(Integer.valueOf(uniq.flags.get("-s")));
                    String b = arr.get(x).substring(Integer.valueOf(uniq.flags.get("-s")));
                    if (a.equalsIgnoreCase(b)) sarr.add(a);
                }

                if (arr.get(x - 1).equalsIgnoreCase(arr.get(x))) {
                    lf = arr.get(x);
                    counter = counter + 1;
                    arr.remove(x);
                    x--;
                } else {
                    if (!lf.equalsIgnoreCase(arr.get(x - 1))) uarr.add(arr.get(x - 1));
                    lf = "";
                    carr.add((counter + 1) + arr.get(x-1));
                    counter = 0;
                }

            } else {
                if (uniq.s) {
                    String a = arr.get(x - 1).substring(Integer.valueOf(uniq.flags.get("-s")));
                    String b = arr.get(x).substring(Integer.valueOf(uniq.flags.get("-s")));
                    if (a.equals(b)) sarr.remove(arr.get(x));
                }

                if (arr.get(x - 1).equals(arr.get(x))) {
                    lf = arr.get(x);
                    counter = counter + 1;
                    arr.remove(x);
                    x--;

                } else {
                    if (!lf.equals(arr.get(x - 1))) uarr.add(arr.get(x - 1));
                    lf = "";
                    carr.add((counter + 1) + arr.get(x-1));
                    counter = 0;
                }
            }
        }


        if (uniq.o) fileWriter(arr,carr,sarr,uarr,uniq);

        else {
            if (uniq.c || uniq.u || uniq.s) {
                if (uniq.u) {
                    for (String elem : uarr) {
                        System.out.println(elem);
                    }
                }
                if (uniq.c) {
                    for (String elem : carr) {
                        System.out.println(elem);

                    }
                }
                if (uniq.s) {
                    for (String elem : sarr) {
                        System.out.println(elem);
                    }
                }

                } else {
                    for (String elem : arr) {
                        System.out.println(elem);
                    }
                }

            }

        }
    }



