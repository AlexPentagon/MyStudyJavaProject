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

   public void update(Player player,ArrayList<Cell> nullCells,ArrayList<Cell> cellMap){



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
        g.setColor(empty.darker());
        g.drawRect(x - size,y - size,size * 2,size * 2);
        g.setStroke(new BasicStroke(1));

        if (status == 0) {
            g.setColor(track);
            g.fillRect(x, y, size, size);
        }
        if (status == 1) {
            g.setColor(full);
            g.fillRect(x, y, size, size);
        }
    }
}
