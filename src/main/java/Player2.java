package main.java;

import javax.swing.*;
import java.awt.*;

public class Player2 extends BaseElement{
    private int speed,len;
    public Player2() {
        this.image=new ImageIcon("src/main/resources/image/P2.png").getImage();
        this.hei=50;
        this.wid=50;
        this.x=800;
        this.y=200;
        this.speed=10;
        this.len=50;
    }
    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed=speed;
    }
    public int getLen(){ return len; }
    public void setLen(){ this.len=len; }
    public void xMove(){
        if(Keys.left.use()){
            this.x-=speed;
        }
        else if(Keys.right.use()){
            this.x+=speed;
        }
    }
    public void yMove(){
        if(Keys.up.use()){
            this.y-=speed;
        }
        else if(Keys.down.use()){
            this.y+=speed;
        }
    }
    public void action(){
        this.xMove();
        this.yMove();
    }
    public void die(){
        Image image=new ImageIcon("src/main/resources/image/dead.png").getImage();
        setImage(image);
        System.out.println("P1 WIN!!!!!!!!!!!!");

    }
}
