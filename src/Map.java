import java.util.ArrayList;

public class Map {
    private int width;
    private int height;
    private int counter;

    public Map(int width,int height){
        this.width = width;
        this.height = height;
    }

    public boolean isWin(ArrayList<Cell> cellMap){
        int counter = 0;
        for (Cell cell : cellMap){
            if(cell.getStatus() == 1 ) counter++;
        }
        this.counter = counter;
        if(counter >= 1600-64) return true;
        return  false;
    }

    public  int getCounter(){
        return counter;
    }

    public ArrayList<Cell> createMap(){
        ArrayList<Cell> map = new ArrayList<>();
        for(int x = 0;x < width;x = x + 10){
            for(int y = 0;y < height;y = y + 10){
                if(x == 0)map.add(new Cell(x,y,Cell.Status.FULL));
                else if(y == 0)map.add(new Cell(x,y,Cell.Status.FULL));
                else if(x == width - 10)map.add(new Cell(x,y,Cell.Status.FULL));
                else if(y == height - 10)map.add(new Cell(x,y,Cell.Status.FULL));
                else map.add(new Cell(x,y,Cell.Status.EMPTY));
            }
        }
        return map;
    }
}