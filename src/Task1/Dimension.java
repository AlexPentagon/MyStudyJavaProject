package Task1;

public class Dimension {
    private final double number;
    private final String dimension;

    public Dimension(double number, String dimension) {
        this.number = number;
        this.dimension = dimension;
    }

    public Dimension(String st){
        String[] a = st.split(" ");
        if (a.length != 2) throw new IllegalArgumentException ("Format must be :\"number dimension\"");
        this.number = Double.parseDouble(a[0]);
        this.dimension = a[1];
    }

    public double getNumber(){
        return number;
    }

    public String getDimension() {
        return dimension;
    }

    public Dimension plus (Dimension other){
        double result;
        if (!dimension.equals(other.dimension)) throw new ArithmeticException("Dimension must be the same");
        else  result = number + other.number;
        return new Dimension(result,dimension);
    }

    public Dimension minus(Dimension other){
        double result;
        if (!dimension.equals(other.dimension)) throw new ArithmeticException("Dimension must be the same");
        else  result = number - other.number;
        return new Dimension(result,dimension);
    }

    public Dimension simpleDiv(double div){
        return new Dimension(number / div,dimension);
    }

    public Dimension times(double time){
        return new Dimension(number * time,dimension);
    }

    public double div (Dimension other){
        double result;
        if (!dimension.equals(other.dimension)) throw new ArithmeticException("Dimension must be the same");
        else  result = number / other.number;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Dimension) {
            Dimension other = (Dimension) obj;
            return  number == other.number && dimension.equals(other.dimension);
        }
        return false;
    }

    public int compareTo(Dimension other){
        int result = 0;
        if (!dimension.equals(other.dimension)) throw new ArithmeticException("Dimension must be the same");
        else {
            if (number == other.number) result = 0;
            else if (number < other.number) result = -11;
            else if (number > other.number) result = 11;
        }
        return result;
    }

    public int compareZer(){
        int result = 0;
        if (number == 0) result = 0;
        else if (number < 0) result = -11;
        else if (number > 0) result = 11;
        return result;
    }

    @Override
    public String toString(){
        return "" + number + " " + dimension + "";
    }
}


