package main.java;

import java.awt.*;

public class BaseElement implements MyDraw{
    protected int x,y,wid,hei;
    protected Image image;
    public int getX() {
        return x;
    }
    public  int getY() {
        return y;
    }
    public void setX(int x) {
        this.x=x;
    }
    public void setY(int y) {
        this.y=y;
    }
    public Image getImage(){
        return image;
    }
    public void setImage(Image image){
        this.image=image;
    }
    public void setWid(int wid){
        this.wid=wid;
    }
    public void setHei(int hei){
        this.hei=hei;
    }
    public int getWid(){
        return wid;
    }
    public int getHei(){
        return hei;
    }
    public void drawImage(Graphics g){
        g.drawImage(this.getImage(),this.getX(),this.getY(),this.getWid(),this.getHei(),null);
    }
    public Rectangle getRec(){
        return new Rectangle(this.x,this.y,wid,hei);
    }
    public<E extends BaseElement> boolean  intersects(E element){
        return this.getRec().intersects(element.getRec());
    }
}
