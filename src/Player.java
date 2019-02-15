import java.awt.*;
import java.util.ArrayList;

public class Player {

    private int x;
    private int y;
    private int r;

    private int dx;
    private int dy;
    private int speed;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    public int lives;
    private Color color;

    public Player(int lives,int x,int y, Color color){
        this.lives = lives;

        this.x = x;
        this.y = y;
        r = 5;

        dx = 0;
        dy = 0;
        speed = 5;

        this.color = color;
    }

    public void setLeft(boolean b){left = b;}
    public void setRight(boolean b){right = b;}
    public void setUp(boolean b){up = b;}
    public void setDown(boolean b){down = b;}

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getR(){
        return r;
    }
    public int getLives(){return lives;}

    public boolean isDead(){
        if(lives <= 0)return true;
        else return false;
    }

    public boolean die(){
        lives -= 1;
        x = r;
        y = r;

        return true;
    }


    public void update(){

        if(left){
            dx = -speed;
        }
        if(right){
            dx = speed;
        }
        if(up){
            dy = -speed;
        }
        if(down){
            dy = speed;
        }

        x += dx;
        y += dy;
        if(x < r) x = r;
        if(y < r) y = r;
        if(x > GamePanel.WIDTH - r) x = GamePanel.WIDTH - r;
        if(y > GamePanel.HEIGHT - r) y = GamePanel.HEIGHT - r;

        dx = 0;
        dy = 0;

    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillOval(x-r,y-r,r *2, r * 2);

        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval(x - r,y - r,r * 2,r * 2);
        g.setStroke(new BasicStroke(1));


    }

    public void AIplayer(ArrayList<Enemy> enemies, ArrayList<Cell> cellmap){
        if(y < 200)this.setDown(true);
        else this.setDown(false);
    }
}