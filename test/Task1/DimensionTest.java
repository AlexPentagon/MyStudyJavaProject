package Task1;

import Task1.Dimension;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class DimensionTest {

    @Test
    public void plus() {
        assertEquals(new Dimension(1.0,"km"),
                new Dimension(0.5,"km").plus(new Dimension(0.5,"km")));
        assertEquals(new Dimension(10.0,"sec"),
                new Dimension(2.0,"sec").plus(new Dimension(8.0,"sec")));
        assertEquals(new Dimension(3.0,"ql"),
                new Dimension(2.0,"ql").plus(new Dimension("1.0 ql")));
        try{
                new Dimension(1.0, "km").plus(new Dimension("1.0  1 sec"));
        fail("Format must be :\"number dimension\"");
        }catch (IllegalArgumentException e){
        }
    }



    @Test
   public void minus() {
        assertEquals(new Dimension(1.0,"km"),
                new Dimension(1.5,"km").minus(new Dimension(0.5,"km")));
        assertEquals(new Dimension(10.0,"sec"),
                new Dimension(18.0,"sec").minus(new Dimension(8.0,"sec")));
        assertEquals(new Dimension(3.14,"ql"),
                new Dimension(4.0,"ql").minus(new Dimension(0.86,"ql")));
    }

    @Test
    public void simpleDiv() {
        assertEquals(new Dimension(2.5,"km"),
                new Dimension(5.0,"km").simpleDiv(2.0));
        assertEquals(new Dimension(22.22,"sec"),
                new Dimension(44.44,"sec").simpleDiv(2.0));
        assertEquals(new Dimension(3.0,"ql"),
                new Dimension(6.0,"ql").simpleDiv(2.0));
    }
    @Test
    public void times() {
        assertEquals(new Dimension(1.0,"km"),
                new Dimension(0.5,"km").times(2.0));
        assertEquals(new Dimension(10.0,"sec"),
                new Dimension(2.0,"sec").times(5.0));
        assertEquals(new Dimension(3.2,"ql"),
                new Dimension("1.6 ql").times(2.0));
    }
    @Test
    public void div() {
        assertEquals((1.0),
                new Dimension(2.0,"km").div(new Dimension(2.0,"km")));
        assertEquals((10.1),
                new Dimension(50.5,"sec").div(new Dimension(5.0,"sec")));
        assertEquals((4.444),
                new Dimension(8.888,"ql").div(new Dimension(2.0,"ql")));
        try {
            assertEquals((4.444),
                    new Dimension(8.888,"qlr").div(new Dimension(2.0,"ql")));
        }catch (ArithmeticException e){
            System.out.println("Dimension must be the same");
        }
    }
    @Test
    public void equals(){
        assertTrue(new Dimension(1.0,"km").equals(new Dimension(1.0,"km")));
        assertTrue(new Dimension(1.2345,"sec").equals(new Dimension(1.2345,"sec")));
        assertFalse(new Dimension(1.0,"sec").equals(new Dimension(1.0,"km")));
        assertFalse(new Dimension(1.0,"km").equals(new Dimension(1.1,"km")));
    }

    @Test
    public void compare() {
        assertEquals((0),
                new Dimension(2.0,"km").compareTo(new Dimension(2.0,"km")));
        assertEquals((11),
                new Dimension(5.0,"km").compareTo(new Dimension(2.0,"km")));
        assertEquals((-11),
                new Dimension(2.0,"km").compareTo(new Dimension(3.0,"km")));
    }

    @Test
    public void compareZer() {
        assertEquals((0),
                new Dimension(0,"km").compareZer());
        assertEquals((11),
                new Dimension(5.0,"km").compareZer());
        assertEquals((-11),
                new Dimension(-2.0,"km").compareZer());
    }

    @Test
    public void exception(){
        try {
            new Dimension(1.0, "km").plus(new Dimension(1.0, "sec"));
            fail("Dimension must be the same");
            new Dimension(1.0, "km").minus(new Dimension("1.0  1 sec"));
            fail("Format must be :\"number dimension\"");
        }catch (ArithmeticException e){
        }
    }

    @Test
    public void getNumber(){
        assertEquals((1.0),
        new Dimension(1.0,"km").getNumber());
    }

    @Test
    public void getDimension(){
        assertEquals(("km"),
                new Dimension(1.0,"km").getDimension());
    }
    @Test
    public void hashcode(){
        assertEquals((3427),
                new Dimension(1.0,"km").hashcode());
        assertEquals((3210),
                new Dimension(1.0,"dm").hashcode());
        assertEquals((3430),
                new Dimension(4.98,"km").hashcode());
    }

}