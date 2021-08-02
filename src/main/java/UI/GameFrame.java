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
    private Bomb[][] bomb1=new Bomb[3][20],bomb2=new Bomb[3][20];//炸弹火焰分为X,Y方向矩形
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

                        boms1[count].setX(player1.getX());
                        boms1[count].setY(player1.getY());
                        boms1[count].setPower(player1.getLen());
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                /*****爆炸*****/
                                int len=boms1[count].getPower();
                                for (int i = 0; i < 4*len+1; i++) {

                                    switch ((i+len-1)/len) {
                                        case 0:
                                            if(!new Bom(boms1[count].getX(), boms1[count].getY()).collide(obstacles))
                                                bomb1[count][i].setXY(boms1[count].getX(), boms1[count].getY());
                                            break;
                                        case 1:
                                            if(!new Bom(boms1[count].getX() - unit_len * i, boms1[count].getY()).collide(obstacles))
                                                bomb1[count][i].setXY(boms1[count].getX() - unit_len * i, boms1[count].getY());
                                            break;
                                        case 2:
                                            if(!new Bom(boms1[count].getX() + (i - len)*unit_len, boms1[count].getY()).collide(obstacles))
                                                bomb1[count][i].setXY(boms1[count].getX() + (i - len)*unit_len, boms1[count].getY());
                                            break;
                                        case 3:
                                            if(!new Bom(boms1[count].getX(), boms1[count].getY() + (i - 2 * len) * unit_len).collide(obstacles))
                                                bomb1[count][i].setXY(boms1[count].getX(), boms1[count].getY() + (i - 2 * len) * unit_len);
                                            break;
                                        case 4:
                                            if(!new Bom(boms1[count].getX(), boms1[count].getY() - (i - 3 * len) * unit_len).collide(obstacles))
                                                bomb1[count][i].setXY(boms1[count].getX(), boms1[count].getY() - (i - 3 * len) * unit_len);
                                            break;
                                    }
                                }
                                /*******炸弹消失*******/
                                boms1[count].setXY(-unit_len*count-2*unit_len,-unit_len*count-4*unit_len);
                                count1--;
                                Timer timer=new Timer();
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        for (int i = 0; i < 4*boms1[count].getPower()+1; i++) {

                                            bomb1[count][i].setXY(unit_len * i, -(count+3) * unit_len);
                                        }
                                    }
                                },1000);

                            }
                        },2000);
                    }
                    else
                        count1--;


                }
                if(Keys.bomb.use()){
                    count2++;
                    int count=count2;
                    if(count2<player2.getMax()) {


                        boms2[count].setX(player2.getX());
                        boms2[count].setY(player2.getY());
                        boms2[count].setPower(player2.getLen());
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {


                                /*****爆炸*****/
                                int len=boms2[count].getPower();
                                for (int i = 0; i < 4*len+1; i++) {

                                    switch ((i+len-1)/len) {
                                        case 0:
                                            if(!new Bom(boms2[count].getX(), boms2[count].getY()).collide(obstacles))
                                                bomb2[count][i].setXY(boms2[count].getX(), boms2[count].getY());
                                            break;
                                        case 1:
                                            if(!new Bom(boms2[count].getX() - unit_len * i, boms2[count].getY()).collide(obstacles))
                                                bomb2[count][i].setXY(boms2[count].getX() - unit_len * i, boms2[count].getY());
                                            break;
                                        case 2:
                                            if(!new Bom(boms2[count].getX() + (i - len)*unit_len, boms2[count].getY()).collide(obstacles))
                                                bomb2[count][i].setXY(boms2[count].getX() + (i - len)*unit_len, boms2[count].getY());
                                            break;
                                        case 3:
                                            if(!new Bom(boms2[count].getX(), boms2[count].getY() + (i - 2 * len) * unit_len).collide(obstacles))
                                                bomb2[count][i].setXY(boms2[count].getX(), boms2[count].getY() + (i - 2 * len) * unit_len);
                                            break;
                                        case 4:
                                            if(!new Bom(boms2[count].getX(), boms2[count].getY() - (i - 3 * len) * unit_len).collide(obstacles))
                                                bomb2[count][i].setXY(boms2[count].getX(), boms2[count].getY() - (i - 3 * len) * unit_len);
                                            break;
                                    }
                                }
                                /*******炸弹消失*******/
                                boms2[count].setXY(-unit_len*count-2*unit_len,-unit_len*count-4*unit_len);
                                count2--;
                                Timer timer=new Timer();
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        for (int i = 0; i < 4*boms2[count].getPower()+1; i++) {

                                            bomb2[count][i].setXY(unit_len * i, -(count+3) * unit_len);
                                        }
                                    }
                                },1000);

                            }
                        },2000);
                    }
                    else
                        count2--;

                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 20; j++) {
                        bomb1[i][j].intersects(player1,player2);
                        bomb2[i][j].intersects(player1,player2);
                    }
                }
                boolean state1=player1.getState(),state2=player2.getState();
                if (!state1&&state2) {
                    panel.repaint();
                    System.out.println("P2 WIN!!!!!!!!!!!!");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
                else if (state1&&!state2) {
                    System.out.println("P1 WIN!!!!!!!!!!!!");
                    panel.repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }else if(!state1&&!state2){

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
        };
        timer.schedule(timertask,0,30);
    }

    private GamePanel init(){
        GamePanel panel=new GamePanel(player1,player2);
        count1=-1;count2=-1;
        /*****初始化障碍******/
        for(int i=0;i<12;i++){
            obstacles[i]=new Obstacle(10*(unit_len+1),i*(1+unit_len));
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
        /*****初始化火焰******/
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 20; j++) {
                bomb1[i][j] = new Bomb(unit_len * j, -(i+1)*unit_len);
                bomb2[i][j] = new Bomb(unit_len * j, -(i+3) * unit_len);

                panel.add(bomb1[i][j]);
                panel.add(bomb2[i][j]);
            }

        }

        /*****初始化炸弹******/
        for(int i=0;i<2;i++){
            boms1[i]=new Bom(-unit_len*i-2*unit_len,-unit_len*i-2*unit_len);
            panel.add(boms1[i]);
        }
        for(int i=0;i<2;i++){
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
