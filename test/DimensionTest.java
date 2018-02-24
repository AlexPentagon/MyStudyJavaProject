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
                new Dimension(2.0,"ql").plus(new Dimension(1.0,"ql")));
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
                new Dimension(1.6,"ql").times(2.0));
    }
    @Test
    public void div() {
        assertEquals(new Dimension(1.0,""),
                new Dimension(2.0,"km").div(new Dimension(2.0,"km")));
        assertEquals(new Dimension(10.1,""),
                new Dimension(50.5,"sec").div(new Dimension(5.0,"sec")));
        assertEquals(new Dimension(4.444,""),
                new Dimension(8.888,"ql").div(new Dimension(2.0,"ql")));
    }
    @Test
    public void equals(){
        assertTrue(new Dimension(1.0,"km").equals(new Dimension(1.0,"km")));
        assertTrue(new Dimension(1.2345,"sec").equals(new Dimension(1.2345,"sec")));
        assertFalse(new Dimension(1.0,"sec").equals(new Dimension(1.0,"km")));
        assertFalse(new Dimension(1.0,"km").equals(new Dimension(1.1,"km")));
    }
}