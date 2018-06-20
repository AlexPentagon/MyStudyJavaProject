package Task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static int WIDTH = 400;
    public static int HEIGHT = 400;

    boolean b = true;

    private boolean running;
    private Thread thread;

    private BufferedImage image;
    private Graphics2D g;

    private int FPS = 30;
    private double avarageFPS;
    String win = "";

    private Player player;
    private Map map;
    public ArrayList<Cell> cellMap = new ArrayList<>();
    public static ArrayList<Enemy> enemies;
    ArrayList<Cell> nullCells = new ArrayList<>();
    ArrayList<Cell> oneCells = new ArrayList<>();

    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        addKeyListener(this);
    }

    public void run(){
        map = new Map(WIDTH,HEIGHT);


        cellMap = map.createMap(WIDTH,HEIGHT);

        running = true;
        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        player = new Player();
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(WIDTH/3,HEIGHT/2,1));
        enemies.add(new Enemy(WIDTH-WIDTH/10,HEIGHT-HEIGHT/10,1));


        long startTime;
        long URDTimeMillis;
        long waitTime;
        long totalTime = 0;

        int frameCount = 0;
        int maxFrameCount = 30;

        long targetTime = 1000/FPS;

        while(running){

            startTime = System.nanoTime();

            gameUpdate();
            gameRender();
            gameDraw();

            URDTimeMillis = (System.nanoTime() - startTime)/ 1000000;
            waitTime = targetTime - URDTimeMillis;

            try{
                Thread.sleep(waitTime);
            }catch (Exception e){ }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == maxFrameCount){
                avarageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
            }
            if(map.isWin(cellMap,WIDTH,HEIGHT)) {
                running = false;
                win = "Y O U   A R E   W I N !";

            }
        }

        g.setColor(new Color(0,100,255));
        g.fillRect(0,0,WIDTH,HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        g.drawString(win,WIDTH/2 - WIDTH / 5,HEIGHT/2);
        gameDraw();
    }



    public void gameUpdate(){
        player.update();

        for(Cell elem : cellMap){
            elem.update(player,nullCells,cellMap,enemies);
            if(elem.getStatus() == 1) {

                oneCells.add(elem);

            }



        }
        for(int j = 0;j < enemies.size();j++){
            enemies.get(j).update(oneCells);

            if(enemies.get(j).tryKill(player,nullCells)) nullCells.clear();


        }
        oneCells.clear();

        if(player.isDead()){
            running = false;
            win = "G A M E   O V E R !";
        }

    }

    public void gameRender(){

        for(Cell elem : cellMap){
            elem.draw(g);
        }


        player.draw(g);


        for(int i = 0;i < enemies.size();i++){
            enemies.get(i).draw(g);
        }


    }

    public void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image,0,0,null);
        g2.dispose();

    }

    public void keyTyped(KeyEvent key){}
    public void keyPressed (KeyEvent key){

        int keyCode = key.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT){
            player.setLeft(true);
            player.setDown(false);
            player.setUp(false);
        }
        if(keyCode == KeyEvent.VK_RIGHT){
            player.setRight(true);
            player.setDown(false);
            player.setUp(false);
        }
        if(keyCode == KeyEvent.VK_UP){
            player.setUp(true);
            player.setLeft(false);
            player.setRight(false);
        }
        if(keyCode == KeyEvent.VK_DOWN){
            player.setDown(true);
            player.setLeft(false);
            player.setRight(false);
        }
    }
    public void keyReleased(KeyEvent key) {
        int keyCode = key.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            player.setLeft(false);
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            player.setRight(false);
        }
        if (keyCode == KeyEvent.VK_UP) {
            player.setUp(false);
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            player.setDown(false);
        }
    }
}