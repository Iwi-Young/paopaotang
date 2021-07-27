package main.java.Element;

import main.java.Element.BaseElement;
import main.java.Keys;

import javax.swing.*;
import java.awt.*;

public class Player2 extends BaseElement {
    private int speed,len;
    public Player2(int x,int y) {
        this.image=new ImageIcon("src/main/resources/image/P2.png").getImage();
        this.hei=unit_len;
        this.wid=unit_len;
        this.x=x;
        this.y=y;
        this.speed=10;
        this.len=unit_len;
    }
    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed=speed;
    }
    public int getLen(){ return len; }
    public void setLen(int len){ this.len=len; }
    public boolean collide(BaseElement... elements){
        for (BaseElement element : elements) {
            if (element != null && element.intersects(this)) {
                return true;
            }
        }
        return false;
    }
    public void xMove(Obstacle[] obstacles){
        if(Keys.left.use()&&this.x>0&&!(new Player2(this.x-10,this.y).collide(obstacles))){
            this.x-=speed;
        }
        else if(Keys.right.use()&&this.x<wid_frame-unit_len&&!(new Player2(this.x+10,this.y).collide(obstacles))){
            this.x+=speed;
        }
    }
    public void yMove(Obstacle[] obstacles){
        if(Keys.up.use()&&this.y>0&&!(new Player2(this.x,this.y-10).collide(obstacles))){
            this.y-=speed;
        }
        else if(Keys.down.use()&&this.y<hei_frame-unit_len&&!(new Player2(this.x,this.y+10).collide(obstacles))){
            this.y+=speed;
        }
    }
    public void action(Obstacle[] obstacles){
        this.xMove(obstacles);
        this.yMove(obstacles);
    }
    public void die(){
        Image image=new ImageIcon("src/main/resources/image/dead.png").getImage();
        this.setImage(image);


    }
}
