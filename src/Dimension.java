
public class Dimension {
    private final double number;
    private final String dimension;

    public Dimension(double number, String dimension) {
        this.number = number;
        this.dimension = dimension;
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

    public Dimension div (Dimension other){
        double result;
        if (!dimension.equals(other.dimension)) throw new ArithmeticException("Dimension must be the same");
        else  result = number / other.number;
        return new Dimension(result,"");
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

    @Override
    public String toString(){
        return "" + number + " " + dimension + "";
    }
}


