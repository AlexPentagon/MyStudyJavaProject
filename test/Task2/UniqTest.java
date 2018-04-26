package Task2;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.jupiter.api.Test;
import Task2.Uniq;
import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UniqTest {
Uniq uniq;




    @Test
    void nothing() throws IOException{
        uniq = new Uniq(false,false,false,false,0,false);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        actual.add("aa");
        actual.add("aa");
        actual.add("ac");
        actual.add("aa");
        actual.add("aa");
        expected.add("aa");
        expected.add("ac");
        expected.add("aa");
        assertEquals(  expected, uniq.uniq(actual));
        }
    @Test
    void flags_i() throws IOException{
        uniq = new Uniq(true,false,false,false,0,false);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        actual.add("aa");
        actual.add("aA");
        actual.add("aC");
        actual.add("AA");
        actual.add("aa");
        expected.add("aa");
        expected.add("ac");
        expected.add("aa");
        assertEquals(  expected, uniq.uniq(actual));

    }
    @Test
    void flags_c() throws IOException{

        uniq = new Uniq(false,false,true,false,0,false);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        actual.add("aa");
        actual.add("aa");
        actual.add("ac");
        actual.add("aa");
        actual.add("aa");
        expected.add("2aa");
        expected.add("1ac");
        expected.add("2aa");
        assertEquals(  expected, uniq.uniq(actual));

    }
    @Test
    void flags_s() throws IOException{

        uniq = new Uniq(false,false,false,true,1,false);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        actual.add("aa");
        actual.add("aa");
        actual.add("ca");
        actual.add("aa");
        actual.add("aa");
        expected.add("a");
        assertEquals(  expected, uniq.uniq(actual));

    }
    @Test
    void flags_u() throws IOException{
        uniq = new Uniq(false,true,false,false,0,false);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        actual.add("aa");
        actual.add("aa");
        actual.add("ac");
        actual.add("aa");
        actual.add("aa");
        expected.add("ac");
        assertEquals(  expected, uniq.uniq(actual));

    }

    @Test
    void flags_i_s() throws IOException{

        uniq = new Uniq(true,false,false,true,1,false);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        actual.add("aa");
        actual.add("aA");
        actual.add("ca");
        actual.add("AA");
        actual.add("aa");
        expected.add("a");
        assertEquals(  expected, uniq.uniq(actual));

    }
    @Test
    void flags_i_c() throws IOException{

        uniq = new Uniq(true,false,true,false,0,false);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        actual.add("Aa");
        actual.add("AA");
        actual.add("aC");
        actual.add("aa");
        actual.add("aA");
        expected.add("2aa");
        expected.add("1ac");
        expected.add("2aa");
        assertEquals(  expected, uniq.uniq(actual));

    }@Test
    void flags_i_u() throws IOException{

        uniq = new Uniq(true,true,false,false,0,false);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        actual.add("aa");
        actual.add("AA");
        actual.add("aC");
        actual.add("aa");
        actual.add("aA");
        expected.add("ac");
        assertEquals(  expected, uniq.uniq(actual));

    }@Test
    void flags_i_c_s() throws IOException{

        uniq = new Uniq(true,false,true,true,1,false);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        actual.add("aA");
        actual.add("AA");
        actual.add("cA");
        actual.add("aa");
        actual.add("AA");
        expected.add("5a");
        assertEquals(  expected, uniq.uniq(actual));

    }@Test
    void flags_i_c_u() throws IOException{

        uniq = new Uniq(true,true,true,false,0,false);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        actual.add("AA");
        actual.add("aa");
        actual.add("aC");
        actual.add("aa");
        actual.add("aA");
        expected.add("1ac");
        assertEquals(  expected, uniq.uniq(actual));

    }@Test
    void flags_i_c_u_s() throws IOException{

        uniq = new Uniq(true,true,true,true,1,false);
        ArrayList<String> actual = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        actual.add("Aa");
        actual.add("AA");
        actual.add("aC");
        actual.add("aa");
        actual.add("aA");
        expected.add("1c");
        assertEquals(  expected, uniq.uniq(actual));

    }
}