package main.java;

import javax.swing.*;
import java.awt.*;

public class Player1 extends BaseElement{
    private int speed,len;
    public Player1() {
        this.image=new ImageIcon("src/main/resources/image/P1.png").getImage();
        this.x=200;
        this.y=200;
        this.hei=50;
        this.wid=50;
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
        if(Keys.LEFT.use()){
            this.x-=speed;
        }
        else if(Keys.RIGHT.use()){
            this.x+=speed;
        }
    }
    public void yMove(){
        if(Keys.UP.use()){
            this.y-=speed;
        }
        else if(Keys.DOWN.use()){
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
        System.out.println("P2 WIN!!!!!!!!!!!!");
    }
}
