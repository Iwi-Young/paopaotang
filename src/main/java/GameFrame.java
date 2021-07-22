package main.java;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class GameFrame extends JFrame {
    int count1=0,count2=0,max1=1,max2=1;//count为当前炸弹数,max为最大炸弹数
    Bom[] boms1=new Bom[5],boms2=new Bom[5];
    Bomb[] bomb1x=new Bomb[5],bomb1y=new Bomb[5],bomb2x=new Bomb[5],bomb2y=new Bomb[5];//炸弹火焰分为X,Y方向矩形
    public GameFrame() throws InterruptedException {
        this.setTitle("tantantang");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,600);
        Player1 player1 =new Player1();
        Player2 player2 =new Player2();
        GamePanel panel=new GamePanel(player1,player2);
        /*****初始化炸弹******/
        for(int i=0;i<max1;i++){
            boms1[i]=new Bom(-50*i-100,-50*i-100);
            panel.add(boms1[i]);
        }
        for(int i=0;i<max2;i++){
            boms2[i]=new Bom(-50*i-100,-50*i-200);
            panel.add(boms2[i]);
        }
        /****初始化火焰*******/
        for(int i=0;i<max1;i++){
            bomb1x[i] = new Bomb(-100-100*i, -300-50*i, 2 * player1.getLen() + 50, 50);
            bomb1y[i] = new Bomb(-100-100*i, -400-50*i, 50, 2 * player1.getLen() + 50);
            panel.add(bomb1x[i]);
            panel.add(bomb1y[i]);
        }
        for(int i=0;i<max2;i++){
            bomb2x[i] = new Bomb(-100-100*i, -500-50*i, 2 * player2.getLen() + 50, 50);
            bomb2y[i] = new Bomb(-100-100*i, -600-50*i, 50, 2 * player2.getLen() + 50);
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
                player1.action();
                player2.action();
                if(Keys.BOMB.use()){
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
                                a = bomb1x[count1].intersects(player1, player2);
                                if (a[0] == 1) {
                                    player1.die();
                                    System.out.println("1");
                                    System.exit(0);
                                }
                                if (a[1] == 1) {
                                    player2.die();
                                    System.out.println("2");

                                    System.exit(0);
                                }
                                a = bomb1y[count1].intersects(player1, player2);
                                if (a[0] == 1) {
                                    player1.die();
                                    System.out.println("3");

                                    System.exit(0);
                                }
                                if (a[1] == 1) {
                                    player2.die();
                                    System.out.println("4");

                                    System.exit(0);
                                }
                            }
                        },2000);
                    }
                }
                if(Keys.bomb.use()){
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
                                a = bomb2x[count2].intersects(player1, player2);
                                if (a[0] == 1) {
                                    player1.die();
                                    System.out.println("5");

                                    System.exit(0);
                                }
                                if (a[1] == 1) {
                                    player2.die();
                                    System.out.println("6");

                                    System.exit(0);
                                }
                                a = bomb2y[count2].intersects(player1, player2);
                                if (a[0] == 1) {
                                    player1.die();
                                    System.out.println("7");

                                    System.exit(0);
                                }
                                if (a[1] == 1) {
                                    player2.die();
                                    System.out.println("8");

                                    System.exit(0);
                                }
                            }
                        },2000);
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
