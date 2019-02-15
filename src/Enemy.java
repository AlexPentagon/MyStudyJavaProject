import java.awt.*;
import java.util.ArrayList;

public class Enemy {
    private int x;
    private int y;
    private int r;

    private double dx;
    private double dy;
    private double speed;

    private int type;
    private Color color;

    public Enemy(int x, int y ,int type){
        this.x = x;
        this.y = y;
        this.type = type;

        speed = 1;
        r = 5;

        if(type == 1) color = Color.RED;

        if(type == 2) color = Color.BLACK;

        if(type == 3) color = Color.ORANGE;

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

    public Cell NumOfStatus(double x,double y,ArrayList<Cell> cells){
        Cell result = new Cell(-1,-1,Cell.Status.HELPER1);

        for (Cell cell:cells) {


            double cX = cell.getX();
            double cY = cell.getY();
            double cS = cell.getS();

            if ((x <= cX + cS && x >= cX) && (y <= cY + cS && y >= cY)) result = cell;

        }
        return result;
    }

    public void destroyFullCell(int x,int y,ArrayList<Cell> cellMap,ArrayList<Cell> oneCells){
        Cell cell1 = NumOfStatus(x,y,oneCells);
        Cell cell2 = NumOfStatus(x+r,y+r,oneCells);
        if(type == 2 ) {
            for (Cell elem:cellMap) {
                if((elem == cell1 || elem == cell2) && elem.getX() != 0 && elem.getX() != GamePanel.WIDTH - 10
                        && elem.getY() != 0 && elem.getY() != GamePanel.HEIGHT - 10) elem.status = Cell.Status.EMPTY;
            }
        }
    }

    public boolean tryKill(Player player,ArrayList<Cell> nullCells){

        boolean result = false;
        double eX = getX();
        double eY = getY();
        double eR = getR();


        if      ((NumOfStatus(eX + eR / 2,eY + eR,nullCells).getX() != -1 ) ||
                (NumOfStatus(eX + eR,eY + eR / 2,nullCells).getX() != -1) ||
                (NumOfStatus(eX,eY + eR / 2,nullCells).getX() != -1) ||
                (NumOfStatus(eX + eR / 2,eY,nullCells)).getX() != -1){
            player.die();

            result = true;
        }
        return result;
    }

    public void update(ArrayList<Cell> oneCells,ArrayList<Cell> cellMap){

        x += dx;
        y += dy;
        double eX = getX();
        double eY = getY();
        double eR = getR();


        if (NumOfStatus(eX + eR / 2,eY + eR,oneCells).getX() != -1){
            destroyFullCell(x,y,cellMap,oneCells);
            y =(int) eY - 2;
            dy = -dy;

        }
        if (NumOfStatus(eX + eR,eY + eR / 2,oneCells).getX() != -1)  {
            destroyFullCell(x,y,cellMap,oneCells);
            x = (int) eX -2;
            dx = -dx;

        }
        if (NumOfStatus(eX,eY + eR / 2,oneCells).getX() != -1) {
            destroyFullCell(x,y,cellMap,oneCells);
            x = (int) eX + 4;
            dx = -dx;

        }
        if (NumOfStatus(eX + eR / 2,eY,oneCells).getX() != -1) {
            destroyFullCell(x,y,cellMap,oneCells);
            y = (int) eY + 4;
            dy = -dy;

        }


    }

    public void draw(Graphics2D g){

        g.setColor(color);
        g.fillOval((int) (x - r),(int)(y - r),2 * r, 2 * r);

        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.fillOval((int) (x - r),(int)(y - r),2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
    }
}