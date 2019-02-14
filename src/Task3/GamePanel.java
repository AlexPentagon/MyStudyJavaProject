package Task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static int WIDTH = 400;
    public static int HEIGHT = 400;

    private boolean running;
    private Thread thread;

    private BufferedImage image;
    private Graphics2D g;

    private int lives = 5;
    private int counter = 2;
    private int FPS = 30;
    private double avarageFPS;
    String win = "";

    private Player player1;
    //private Player player2;
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
        cellMap = map.createMap();

        running = true;
        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        player1 = new Player(lives,0,0,Color.ORANGE);
        //player2 = new Player(lives,390,390,Color.GREEN);
        enemies = new ArrayList<>();
        for(int i = 1; i < counter; i++) {
            if(i < 3)enemies.add(new Enemy(10 + new Random().nextInt(380),10 + new Random().nextInt(380), 1));

            if(i >= 3) enemies.add(new Enemy(10 + new Random().nextInt(380), 10 + new Random().nextInt(380), 2));
        }

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
            if(map.isWin(cellMap)) {
                lives = player1.getLives();
                running = false;
                counter++;
                if (counter >= 6){
                    running = false;
                    win = "Y O U   A R E   W I N !";
                }else run();
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
        player1.update();
        //player2.update();
        //player1.AIplayer(enemies,cellMap);

        for(Cell elem : cellMap){
            elem.update(player1,nullCells,cellMap,enemies);
            //elem.update(player2,nullCells,cellMap,enemies);
            if(elem.getStatus() == 1) {

                oneCells.add(elem);
            }
        }

        for(int j = 0;j < enemies.size();j++){
            enemies.get(j).update(oneCells,cellMap);

            if(enemies.get(j).tryKill(player1,nullCells)) nullCells.clear();
            //if(enemies.get(j).tryKill(player2,nullCells)) nullCells.clear();
        }

        oneCells.clear();

        if(player1.isDead()){
            running = false;
            win = "G A M E   O V E R !";
        }

    }

    public void gameRender(){

        for(Cell elem : cellMap){
            elem.draw(g);
        }


        player1.draw(g);
        //player2.draw(g);


        for(int i = 0;i < enemies.size();i++){
            enemies.get(i).draw(g);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        g.drawString("Progress:" + map.getCounter()/16 + "%   Lives:" + player1.getLives(),270,10);

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
            player1.setLeft(true);
            player1.setDown(false);
            player1.setUp(false);
        }
        if(keyCode == KeyEvent.VK_RIGHT){
            player1.setRight(true);
            player1.setDown(false);
            player1.setUp(false);
        }
        if(keyCode == KeyEvent.VK_DOWN){
            player1.setDown(true);
            player1.setLeft(false);
            player1.setRight(false);
        }
        if(keyCode == KeyEvent.VK_UP){
            player1.setUp(true);
            player1.setLeft(false);
            player1.setRight(false);
        }

    }
    public void keyReleased(KeyEvent key) {
        int keyCode = key.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            player1.setUp(false);
        }
        if(keyCode == KeyEvent.VK_LEFT){
            player1.setLeft(false);
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            player1.setRight(false);
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            player1.setDown(false);
        }


    }


}