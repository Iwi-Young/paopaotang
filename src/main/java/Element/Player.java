package main.java.Element;

import main.java.Keys;
import javax.swing.*;
import java.awt.*;

public class Player extends BaseElement {
    private int speed;
    private int len;
    private int max;
    private final int id;
    private boolean state;
    public Player(int x, int y, int id) {
        this.id=id;
        if(id==1)
            this.image=new ImageIcon("src/main/resources/image/P1.png").getImage();
        else if(id==2)
            this.image=new ImageIcon("src/main/resources/image/P2.png").getImage();
        this.x=x;
        this.y=y;
        this.hei=unit_len;
        this.wid=unit_len;
        this.speed=10;
        this.len=1;
        this.max=1;
        this.state=true;
    }
    public boolean getState(){return state;}
    public void setState(boolean state){this.state=state;}
    public int getMax(){ return max; }
    public void setMax(int max){this.max=max;}
    public int getSpeed(){
        return speed;
    }
    public int  getId(){return id; }
    public void setSpeed(int speed){ this.speed=speed; }
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
    public void collide(Props[] props){
        for(Props prop:props){
            if(prop!=null&&prop.intersects(this)){
                if(prop.getName()==1){
                    this.max++;
                    prop.setName(0);
                    prop.setImage(null);
                }
                else if(prop.getName()==2){
                    this.len++;
                    prop.setName(0);
                    prop.setImage(null);
                }
            }
        }
    }
    public void xMove(Obstacle[] obstacles){
        if(this.id==1) {
            if (Keys.LEFT.use() && this.x > 0 && !(new Player(this.x - 10, this.y, 3).collide(obstacles))) {
                this.x -= speed;
            } else if (Keys.RIGHT.use() && this.x < wid_frame - unit_len && !(new Player(this.x + 10, this.y, 3).collide(obstacles))) {
                this.x += speed;
            }
        }
        else if(id==2){
            if(Keys.left.use()&&this.x>0&&!(new Player(this.x-10,this.y,3).collide(obstacles))){
                this.x-=speed;
            }
            else if(Keys.right.use()&&this.x<wid_frame-unit_len&&!(new Player(this.x+10,this.y,3).collide(obstacles))){
                this.x+=speed;
            }
        }
    }
    public void yMove(Obstacle[] obstacles){
        if(id==1) {
            if (Keys.UP.use() && this.y > 0 && !(new Player(this.x, this.y - 10, 3).collide(obstacles))) {
                this.y -= speed;
            } else if (Keys.DOWN.use() && this.y < hei_frame - unit_len && !(new Player(this.x, this.y + 10, 3).collide(obstacles))) {
                this.y += speed;
            }
        }
        else if(id==2){
            if(Keys.up.use()&&this.y>0&&!(new Player(this.x,this.y-10,3).collide(obstacles))){
                this.y-=speed;
            }
            else if(Keys.down.use()&&this.y<hei_frame-unit_len&&!(new Player(this.x,this.y+10,3).collide(obstacles))){
                this.y+=speed;
            }
        }
    }
    public void action(Obstacle[] obstacles,Props[] props,Bomb... bombs){
        this.xMove(obstacles);
        this.yMove(obstacles);
        this.collide(props);

    }
    public void die(){
        Image image=new ImageIcon("src/main/resources/image/dead.png").getImage();
        this.setImage(image);
        this.setState(false);
    }
}
