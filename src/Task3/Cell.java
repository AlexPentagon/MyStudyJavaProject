package Task3;

import java.awt.*;
import java.util.ArrayList;

public class Cell {
    private int x;
    private int y;
    private int status = -1;
    private final int size = 10;

    public Cell(int x,int y,int status){
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
        return status;
    }



    public static int coordinates (int x, int y){
        int result = x * 4 + y / 10 ;
        return result;
    }

    public static int circled(int num){
        int result;
        if(num % 10 < 5) result = (num / 10) * 10;
        else result = (num / 10 + 1) * 10;
        return result;
    }

    public void recurcion(ArrayList<Cell> cellMap,Cell cell,int num){

        if(cell.getStatus() == 1 || cell.getStatus() == 0) return;

        else cell.status = 2;
        recurcion(cellMap,cellMap.get(coordinates(cell.getX(),cell.getY())+num),num);
    }

    public void update(Player player,ArrayList<Cell> nullCells,ArrayList<Cell> cellMap,ArrayList<Enemy> enemies){

        double cX = getX();
        double cY = getY();
        double cS = getS();

        double pX = player.getX();
        double pY = player.getY();
        double pR = player.getR();

        if ((pX + pR <= cX + cS && pX >= cX) && (pY + pR <= cY + cS && pY >= cY) && status != 1){
            status = 0;
            nullCells.add(this);
        }
        if(status == 1 && (pX + pR <= cX + cS && pX >= cX) && (pY + pR <= cY + cS && pY >= cY) && !nullCells.isEmpty()) {



            for (Cell elem : nullCells) {
                elem.status = 1;




            }
            //fill space , need to think more ||||||||||||||||||||||||||||
            for(Enemy enemy : enemies) {
                recurcion(cellMap, new Cell(circled(enemy.getX()), circled(enemy.getY()), -1), 1);
                recurcion(cellMap, new Cell(circled(enemy.getX()), circled(enemy.getY()), -1), -1);
                recurcion(cellMap, new Cell(circled(enemy.getX()), circled(enemy.getY()), -1), 40);
                recurcion(cellMap, new Cell(circled(enemy.getX()), circled(enemy.getY()), -1), -40);
            }
for (int i = 0;i<10;i++) {

    for (Cell cell : cellMap) {
        if (cell.status == 2) recurcion(cellMap, cell, +1);
        if (cell.status == 2) recurcion(cellMap, cell, -1);
        if (cell.status == 2) recurcion(cellMap, cell, -40);
        if (cell.status == 2) recurcion(cellMap, cell, +40);
    }


        } for (Cell cell:cellMap){
                if(cell.getStatus() == -1) cell.status=1;
            }

            for (Cell cell:cellMap){
                if(cell.getStatus() == 2) cell.status = -1;
            }
        //||||||||||||||||||||||||||||||||||||||||||||||||
            nullCells.clear();


        }

        if(nullCells.isEmpty()) {
            for(Cell elem : cellMap){
                if (elem.status == 0 ) elem.status = -1;
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

        g.setStroke(new BasicStroke(1));

        if (status == 0) {
            g.setColor(track);
            g.fillRect(x, y, size, size);
        }
        if (status == 1) {
            g.setColor(full);
            g.fillRect(x, y, size, size);
        }
        g.setColor(empty.darker());
        g.drawRect(x,y,size,size);
    }
}