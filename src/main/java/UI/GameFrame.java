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
    int count1=-1,count2=-1,max1=1,max2=1;//count为当前炸弹数,max为最大炸弹数
    Bom[] boms1=new Bom[5],boms2=new Bom[5];
    Bomb[] bomb1x=new Bomb[5],bomb1y=new Bomb[5],bomb2x=new Bomb[5],bomb2y=new Bomb[5];//炸弹火焰分为X,Y方向矩形
    public GameFrame() {
        this.setTitle("tantantang");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(wid_frame+10,hei_frame+40);
        Player1 player1 =new Player1(100,200);
        Player2 player2 =new Player2(800,200);
        GamePanel panel=new GamePanel(player1,player2);
        /*****初始化障碍******/
        Obstacle[] obstacles=new Obstacle[100];
        for(int i=0;i<76;i++){
            if(i<26) {
                obstacles[i] = new Obstacle(-unit_len + i * unit_len, -unit_len);
                panel.add(obstacles[i]);
            }
            else if(i<52) {
                obstacles[i] = new Obstacle(-unit_len + (i - 26) * unit_len, hei_frame + unit_len);
                panel.add(obstacles[i]);
            }
            else if(i<64) {
                obstacles[i] = new Obstacle(10 * unit_len, (i - 52) * unit_len);
                panel.add(obstacles[i]);
            }
            else {
                obstacles[i] = new Obstacle(wid_frame, (i - 64) * unit_len);
                panel.add(obstacles[i]);
            }
        }
        obstacles[76]=null;
        /*****初始化炸弹******/
        for(int i=0;i<max1;i++){
            boms1[i]=new Bom(-unit_len*i-2*unit_len,-unit_len*i-2*unit_len);
            panel.add(boms1[i]);
        }
        for(int i=0;i<max2;i++){
            boms2[i]=new Bom(-unit_len*i-2*unit_len,-unit_len*i-4*unit_len);
            panel.add(boms2[i]);
        }
        /****初始化火焰*******/
        for(int i=0;i<max1;i++){
            bomb1x[i] = new Bomb(-2*unit_len-2*unit_len*i, -6*unit_len-unit_len*i, 2 * player1.getLen() + unit_len, unit_len);
            bomb1y[i] = new Bomb(-2*unit_len-2*unit_len*i, -8*unit_len-unit_len*i, unit_len, 2 * player1.getLen() + unit_len);
            panel.add(bomb1x[i]);
            panel.add(bomb1y[i]);
        }
        for(int i=0;i<max2;i++){
            bomb2x[i] = new Bomb(-2*unit_len-2*unit_len*i, -10*unit_len-unit_len*i, 2 * player2.getLen() + unit_len, unit_len);
            bomb2y[i] = new Bomb(-2*unit_len-2*unit_len*i, -12*unit_len-unit_len*i, unit_len, 2 * player2.getLen() + unit_len);
            panel.add(bomb2x[i]);
            panel.add(bomb2y[i]);
        }
        this.add(panel);
        this.setVisible(true);
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
        /******游戏进程控制*********/
        Timer timer=new Timer();
        TimerTask timertask=new TimerTask() {
            @Override
            public void run() {
                panel.repaint();
                player1.action(obstacles);
                player2.action(obstacles);
                if(Keys.BOMB.use()){
                    count1++;
                    if(count1<max1) {


                        boms1[count1].setX(player1.getX());
                        boms1[count1].setY(player1.getY());

                        /*****延时爆炸*****/
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                int[] a = {0, 0};

                                bomb1x[count1].setX(boms1[count1].getX()- player1.getLen());
                                bomb1x[count1].setY(boms1[count1].getY());
                                bomb1y[count1].setX(boms1[count1].getX());
                                bomb1y[count1].setY(boms1[count1].getY()- player1.getLen());
                                a = bomb1x[count1].intersects(player1, player2,a);
                                a = bomb1y[count1].intersects(player1, player2,a);
                                /******炸弹火焰消除******/
                                Timer timer=new Timer();
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {

                                        bomb1x[count1+1].setX(-2*unit_len-2*unit_len*(count1+1));
                                        bomb1x[count1+1].setY(-6*unit_len-unit_len*(count1+1));
                                        bomb1y[count1+1].setX(-2*unit_len-2*unit_len*(count1+1));
                                        bomb1y[count1+1].setY(-8*unit_len-unit_len*(count1+1));
                                        boms1[count1+1].setX(-unit_len*(count1+1)-2*unit_len);
                                        boms1[count1+1].setY(-unit_len*(count1+1)-2*unit_len);
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

                                count1--;
                            }
                        },2000);
                    }
                    else{
                        count1--;
                    }
                }
                if(Keys.bomb.use()){
                    count2++;
                    if(count2<max2) {
                        boms2[count2].setX(player2.getX());
                        boms2[count2].setY(player2.getY());
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                int[] a = {0, 0};
                                bomb2x[count2].setX(boms2[count2].getX()- player2.getLen());
                                bomb2x[count2].setY(boms2[count2].getY());
                                bomb2y[count2].setX(boms2[count2].getX());
                                bomb2y[count2].setY(boms2[count2].getY()- player2.getLen());
                                a = bomb2x[count2].intersects(player1, player2,a);
                                a = bomb2y[count2].intersects(player1, player2,a);
                                Timer timer=new Timer();
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {

                                        bomb2x[count2+1].setX(-2*unit_len-2*unit_len*(count2+1));
                                        bomb2x[count2+1].setY(-10*unit_len-unit_len*(count2+1));
                                        bomb2y[count2+1].setX(-2*unit_len-2*unit_len*(count2+1));
                                        bomb2y[count2+1].setY(-12*unit_len-unit_len*(count2+1));
                                        boms2[count2+1].setX(-unit_len*(count2+1)-2*unit_len);
                                        boms2
                                                [count2+1].setY(-unit_len*(count2+1)-2*unit_len);
                                    }
                                },1000);
                                if (a[0] == 1&&a[1]==0) {
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
                                    System.out.println("Draw!!!!!!!!!!!"+a[0]+"   "+a[1]);
                                    panel.repaint();
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.exit(0);
                                }

                                count2--;
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
    public static void main(String[] args) throws InterruptedException {
        new GameFrame();
    }
}
