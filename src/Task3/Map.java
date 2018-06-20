package Task3;

import java.util.ArrayList;

public class Map {
    private ArrayList<Cell> map;
    private int WIDTH;
    private int HEIGHT;

    public Map(int height,int width){
        height = HEIGHT;
        width = WIDTH;
    }


    public boolean isWin(ArrayList<Cell> cellMap,int WIDTH,int HEIGHT){
        int counter = 0;
        for (Cell cell : cellMap){
            if(cell.getStatus() == -1 ) counter++;
        }

        if(counter <= WIDTH * HEIGHT / 5000) return true;
        return  false;
    }


    public ArrayList<Cell> createMap(int WIDTH,int HEIGHT){
        ArrayList<Cell> map = new ArrayList<>();
        for(int x = 0;x < WIDTH;x = x + 10){
            for(int y = 0;y < HEIGHT;y = y + 10){
                if(x == 0)map.add(new Cell(x,y,1));
                else if(y == 0)map.add(new Cell(x,y,1));
                else if(x == WIDTH - 10)map.add(new Cell(x,y,1));
                else if(y == HEIGHT - 10)map.add(new Cell(x,y,1));
                else map.add(new Cell(x,y,-1));
            }
        }
        return map;
    }
}