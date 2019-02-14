package Task3;

import java.awt.*;
import java.util.ArrayList;

public class Cell {

    enum Status{
        FULL(1),TRACK(0),EMPTY(-1),HELPER1(2);

        Status(int i) {

        }
    }
    private int x;
    private int y;
    Status status;
    private final int size = 10;



    public Cell(int x,int y,Status status){
        this.x = x;
        this.y = y;
        this.status = status;

    }

    int[][] map = new int[][]{};

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getS(){
        return size;
    }

    public int getStatus(){
        int result = -1;
        switch (status){
        case HELPER1: result = 2;
            break;
        case TRACK: result = 0;
            break;
        case FULL: result = 1;
            break;
        }
        return result;
    }

    private static int coordinates (int x, int y){
        return x * 4 + y / 10 ;
    }

    private static int circled(int num){
        int result;
        if(num % 10 < 5) result = (num / 10) * 10;
        else result = (num / 10 + 1) * 10;
        return result;
    }

    public void fillSpace(ArrayList<Cell> cellMap,Cell cell){
        if (cellMap.get(coordinates(cell.getX(),cell.getY())).getStatus() != -1) return;

        cellMap.get(coordinates(cell.getX(),cell.getY())).status = Status.HELPER1;
        fillSpace(cellMap,cellMap.get(coordinates(cell.getX(),cell.getY())+1));
        fillSpace(cellMap,cellMap.get(coordinates(cell.getX(),cell.getY())-1));
        fillSpace(cellMap,cellMap.get(coordinates(cell.getX(),cell.getY())+40));
        fillSpace(cellMap,cellMap.get(coordinates(cell.getX(),cell.getY())-40));
    }

    public void update(Player player,ArrayList<Cell> nullCells,ArrayList<Cell> cellMap,ArrayList<Enemy> enemies){

        double cX = getX();
        double cY = getY();
        double cS = getS();

        double pX = player.getX();
        double pY = player.getY();
        double pR = player.getR();

        if ((pX + pR <= cX + cS && pX >= cX) && (pY + pR <= cY + cS && pY >= cY) && status != Status.FULL){
            status = Status.TRACK;
            nullCells.add(this);
        }

        if(status == Status.FULL && (pX + pR <= cX + cS && pX >= cX) && (pY + pR <= cY + cS && pY >= cY) && !nullCells.isEmpty()) {
            for (Cell elem : nullCells) {
                elem.status = Status.FULL;
            }

            for(Enemy enemy : enemies) {
                fillSpace(cellMap, cellMap.get(coordinates(circled(enemy.getX()), circled(enemy.getY()))));
            }

            for (Cell cell:cellMap){
                if(cell.getStatus() == -1) cell.status=Status.FULL;
                if(cell.getStatus() == 2) cell.status = Status.EMPTY;

            }

            nullCells.clear();
        }

        if(nullCells.isEmpty()) {
            for(Cell elem : cellMap){
                if (elem.status == Status.TRACK ) elem.status = Status.EMPTY;
            }
        }
    }

    public void draw(Graphics2D g){
        Color empty = new Color(0, 191, 255);
        Color track = new Color(0, 0, 255);
        Color full = new Color(0, 0, 139);

        g.setColor(empty);
        g.fillRect(x, y, size, size);

        g.setStroke(new BasicStroke(1));

        if (status == Status.TRACK) {
            g.setColor(track);
            g.fillRect(x, y, size, size);
        }
        if (status == Status.FULL) {
            g.setColor(full);
            g.fillRect(x, y, size, size);
        }

        g.setColor(empty.darker());
        g.drawRect(x,y,size,size);


    }
}