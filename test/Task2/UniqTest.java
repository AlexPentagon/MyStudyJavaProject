package Task2;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UniqTest {
Uniq uniq;


    @Test
    void nothing() throws IOException{
        uniq = new Uniq(false,false,false,0);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<Pair<String,Integer>> expected = new ArrayList<>();

        actual.add("aa");
        actual.add("aa");
        actual.add("ac");
        actual.add("aa");
        actual.add("aa");
        Pair<String,Integer> pair1 = new MutablePair<>("aa",2);
        expected.add(pair1);
        Pair<String,Integer> pair2 = new MutablePair<>("ac",1);
        expected.add(pair2);
        Pair<String,Integer> pair3 = new MutablePair<>("aa",2);
        expected.add(pair3);
        assertEquals(  expected, uniq.uniq(actual));
        }
    @Test
    void flags_i() throws IOException{
        uniq = new Uniq(true,false,false,0);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<Pair<String, Integer>> expected = new ArrayList<>();
        actual.add("aa");
        actual.add("aA");
        actual.add("aC");
        actual.add("AA");
        actual.add("aa");
        Pair<String,Integer> pair1 = new MutablePair<>("aa",2);
        expected.add(pair1);
        Pair<String,Integer> pair2 = new MutablePair<>("ac",1);
        expected.add(pair2);
        Pair<String,Integer> pair3 = new MutablePair<>("aa",2);
        expected.add(pair3);
        assertEquals(  expected, uniq.uniq(actual));

    }
    @Test
    void flags_c() throws IOException{



    }
    @Test
    void flags_s() throws IOException{

        uniq = new Uniq(false,false,false,1);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<Pair<String, Integer>> expected = new ArrayList<>();
        actual.add("aa");
        actual.add("aa");
        actual.add("ca");
        actual.add("aa");
        actual.add("aa");
        Pair<String,Integer> pair = new MutablePair<>("a",5);
        expected.add(pair);
        assertEquals(  expected, uniq.uniq(actual));

    }
    @Test
    void flags_u() throws IOException{
    }

    @Test
    void flags_i_s() throws IOException{

        uniq = new Uniq(true,false,false,1);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<Pair<String, Integer>> expected = new ArrayList<>();
        actual.add("aa");
        actual.add("aA");
        actual.add("ca");
        actual.add("AA");
        actual.add("aa");
        Pair<String,Integer> pair = new MutablePair<>("a",5);
        expected.add(pair);
        assertEquals(  expected, uniq.uniq(actual));

    }
    @Test
    void flags_i_c() throws IOException{


    }@Test
    void flags_i_u() throws IOException{
    }
    @Test
    void flags_i_c_s() throws IOException{


    }@Test
    void flags_i_c_u() throws IOException{


    }@Test
    void flags_i_c_u_s() throws IOException{

    }
}