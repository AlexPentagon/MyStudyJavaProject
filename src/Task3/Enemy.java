package Task3;

import java.awt.*;
import java.util.ArrayList;

public class Enemy {
    private int x;
    private int y;
    private int r;

    private double dx;
    private double dy;
    private double rad;
    private double speed;

    private int type;
    private Color color1;

    public Enemy(int x, int y ,int type){
        this.type = type;
        this.y = y;
        this.x = x;

        if(type == 1){
            color1 = Color.RED;
            speed = 1;
            r = 5;
        }

        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;

        double angle = Math.random() * 140 * 20;
        rad = Math.toRadians(angle);

        dx =  speed;
        dy =  speed * 2;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getR(){
        return r;
    }

    public boolean NumOfStatus(double x,double y,ArrayList<Cell> cells){
        boolean result = false;

        for (Cell cell:cells) {


            double cX = cell.getX();
            double cY = cell.getY();
            double cS = cell.getS();

            if ((x <= cX + cS && x >= cX) && (y <= cY + cS && y >= cY)) result = true;

        }
        return result;
    }

    public boolean tryKill(Player player,ArrayList<Cell> nullCells){

        boolean result = false;
        double eX = getX();
        double eY = getY();
        double eR = getR();


        if      ((NumOfStatus(eX + eR / 2,eY + eR,nullCells)) ||
                (NumOfStatus(eX + eR,eY + eR / 2,nullCells)) ||
                (NumOfStatus(eX,eY + eR / 2,nullCells)) ||
                (NumOfStatus(eX + eR / 2,eY,nullCells))){
            player.die();
            result = true;
        }
        return result;
    }

    public void update(ArrayList<Cell> oneCells){

        x += dx;
        y += dy;
        double eX = getX();
        double eY = getY();
        double eR = getR();


        if (NumOfStatus(eX + eR / 2,eY + eR,oneCells)){
            y =(int) eY - 2;
            dy = -dy;

        }
        if (NumOfStatus(eX + eR,eY + eR / 2,oneCells))  {
            x = (int) eX -2;
            dx = -dx;

        }
        if (NumOfStatus(eX,eY + eR / 2,oneCells)) {
            x = (int) eX + 3;
            dx = -dx;

        }
        if (NumOfStatus(eX + eR / 2,eY,oneCells)) {
            y = (int) eY + 3;
            dy = -dy;

        }

    }

    public void draw(Graphics2D g){

        g.setColor(color1);
        g.fillOval((int) (x - r),(int)(y - r),2 * r, 2 * r);

        g.setStroke(new BasicStroke(3));
        g.setColor(color1.darker());
        g.fillOval((int) (x - r),(int)(y - r),2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
    }
}