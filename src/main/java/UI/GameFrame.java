package main.java.UI;

import main.java.ConstNumber;
import main.java.Element.*;
import main.java.Keys;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class GameFrame extends JFrame implements ConstNumber {
   private int count1,count2;//count为当前炸弹数
    private Bom[] boms1=new Bom[5],boms2=new Bom[5];
    private Bomb[] bomb1x=new Bomb[5],bomb1y=new Bomb[5],bomb2x=new Bomb[5],bomb2y=new Bomb[5];//炸弹火焰分为X,Y方向矩形
    private Player player1,player2;
    private Obstacle[] obstacles=new Obstacle[100];
    private Props[] props=new Props[4];


    private void start(GamePanel panel){
        /******游戏进程控制*********/
        Timer timer=new Timer();
        TimerTask timertask=new TimerTask() {
            @Override
            public void run() {
                panel.repaint();
                player1.action(obstacles,props);
                player2.action(obstacles,props);
                if(Keys.BOMB.use()){
                    count1++;
                    int count=count1;
                    if(count1<player1.getMax()) {
                        /****初始化火焰*******/
                        bomb1x[count] = new Bomb(-2*unit_len-2*unit_len*count, -6*unit_len-unit_len*count, 2 * player1.getLen() + unit_len, unit_len);
                        bomb1y[count] = new Bomb(-2*unit_len-2*unit_len*count, -8*unit_len-unit_len*count, unit_len, 2 * player1.getLen() + unit_len);
                        panel.add(bomb1x[count]);
                        panel.add(bomb1y[count]);

                        boms1[count].setX(player1.getX());
                        boms1[count].setY(player1.getY());
                        /*****延时爆炸*****/
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                int[] a = {0, 0};

                                bomb1x[count].setX(boms1[count].getX()- player1.getLen());
                                bomb1x[count].setY(boms1[count].getY());
                                bomb1y[count].setX(boms1[count].getX());
                                bomb1y[count].setY(boms1[count].getY()- player1.getLen());
                                a = bomb1x[count].intersects(player1, player2,a);
                                a = bomb1y[count].intersects(player1, player2,a);
                                /******炸弹火焰消除******/
                                Timer timer=new Timer();
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        bomb1x[count].setX(-2*unit_len-2*unit_len*(count+1));
                                        bomb1x[count].setY(-6*unit_len-unit_len*(count+1));
                                        bomb1y[count].setX(-2*unit_len-2*unit_len*(count+1));
                                        bomb1y[count].setY(-8*unit_len-unit_len*(count+1));
                                        boms1[count].setX(-unit_len*(count+1)-2*unit_len);
                                        boms1[count].setY(-unit_len*(count+1)-2*unit_len);
                                        count1--;
                                    }
                                },1000);

                                if (a[0] == 1&&a[1]==0) {
                                    player1.die();
                                    panel.repaint();
                                    System.out.println("P2 WIN!!!!!!!!!!!!");
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.exit(0);
                                }
                                else if (a[1] == 1&&a[0]==0) {
                                    player2.die();
                                    System.out.println("P1 WIN!!!!!!!!!!!!");
                                    panel.repaint();
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.exit(0);
                                }else if(a[0]==1&&a[1]==1){
                                    player1.die();
                                    player2.die();
                                    System.out.println("Draw!!!!!!!!!");
                                    panel.repaint();
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.exit(0);
                                }
                            }
                        },2000);
                    }
                    else{
                        count1--;
                    }
                }
                if(Keys.bomb.use()){
                    count2++;
                    int count=count2;
                    if(count2<player2.getMax()) {
                        /****初始化火焰*******/
                        bomb2x[count] = new Bomb(-2*unit_len-2*unit_len*count, -10*unit_len-unit_len*count, 2 * player2.getLen() + unit_len, unit_len);
                        bomb2y[count] = new Bomb(-2*unit_len-2*unit_len*count, -12*unit_len-unit_len*count, unit_len, 2 * player2.getLen() + unit_len);
                        panel.add(bomb2x[count]);
                        panel.add(bomb2y[count]);

                        boms2[count].setX(player2.getX());
                        boms2[count].setY(player2.getY());
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                boolean[] a = {false, false};
                                bomb2x[count].setX(boms2[count].getX()- player2.getLen());
                                bomb2x[count].setY(boms2[count].getY());
                                bomb2y[count].setX(boms2[count].getX());
                                bomb2y[count].setY(boms2[count].getY()- player2.getLen());
                                a[0] = player1.collide(bomb1x)|| player1.collide(bomb1y)|| player1.collide(bomb2x)|| player1.collide(bomb2y);
                                a[1] = player2.collide(bomb1x)|| player2.collide(bomb1y)|| player2.collide(bomb2x)|| player2.collide(bomb2y);
                                if (a[0] && !a[1]) {
                                    player1.die();
                                    System.out.println("P2 WIN!!!!!!!!!!!!");
                                    panel.repaint();
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.exit(0);
                                }
                                else if (a[1] && !a[0]) {
                                    player2.die();
                                    System.out.println("P1 WIN!!!!!!!!!!!!");
                                    panel.repaint();
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.exit(0);
                                }else if(!a[0] && a[1]){
                                    player1.die();
                                    player2.die();
                                    System.out.println("Draw!!!!!!!!!!!"+a[0]+"   "+a[1]);
                                    panel.repaint();
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.exit(0);
                                }
                                Timer timer=new Timer();
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {

                                        bomb2x[count].setX(-2*unit_len-2*unit_len*(count+1));
                                        bomb2x[count].setY(-10*unit_len-unit_len*(count+1));
                                        bomb2y[count].setX(-2*unit_len-2*unit_len*(count+1));
                                        bomb2y[count].setY(-12*unit_len-unit_len*(count+1));
                                        boms2[count].setX(-unit_len*(count+1)-2*unit_len);
                                        boms2[count].setY(-unit_len*(count+1)-2*unit_len);
                                        count2--;
                                    }
                                },1000);

                            }
                        },2000);
                    }
                    else{
                        count2--;
                    }
                }
            }
        };
        timer.schedule(timertask,0,25);
    }

    private GamePanel init(){
        GamePanel panel=new GamePanel(player1,player2);
        count1=-1;count2=-1;
        /*****初始化障碍******/
        for(int i=0;i<12;i++){
            obstacles[i]=new Obstacle(10*unit_len,i*unit_len);
            panel.add(obstacles[i]);
        }
        /******初始化道具*****/
        props[0]=new Props(200,500,2);
        props[1]=new Props(100,100,1);
        props[2]=new Props(1000,100,1);
        props[3]=new Props(1100,500,2);

        for (int i = 0; i < 4; i++) {
            panel.add(props[i]);
        }
        /*****初始化炸弹******/
        for(int i=0;i<4;i++){
            boms1[i]=new Bom(-unit_len*i-2*unit_len,-unit_len*i-2*unit_len);
            panel.add(boms1[i]);
        }
        for(int i=0;i<4;i++){
            boms2[i]=new Bom(-unit_len*i-2*unit_len,-unit_len*i-4*unit_len);
            panel.add(boms2[i]);
        }


        this.add(panel);
        this.setVisible(true);
        return panel;
    }
    public GameFrame() {
        this.setTitle("tantantang");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(wid_frame+10,hei_frame+40);
        player1 =new Player(100,200,1);
        player2 =new Player(800,200,2);
        GamePanel panel=init();

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            /*********用Set装按键********/
            public void keyPressed(KeyEvent e) {
               Keys.add(e.getKeyCode());
            }
            @Override
            public void keyReleased(KeyEvent e) {
                Keys.remove(e.getKeyCode());
            }
            });
        start(panel);

    }
    public static void main(String[] args) throws InterruptedException {
        new GameFrame();
    }
}
