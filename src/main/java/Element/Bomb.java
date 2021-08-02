package main.java.Element;

import javax.swing.*;
import java.awt.*;


public class Bomb extends BaseElement {
    public Bomb(int x,int y){
        this.x=x;
        this.y=y;
        this.wid=unit_len;
        this.hei=unit_len;
        this.image=new ImageIcon("src/main/resources/image/bom.png").getImage();
    }
public Rectangle getRec(){
        return new Rectangle(x,y,wid,hei);
}
public void intersects(Player p1, Player p2){
        if(p1.getRec().intersects(this.getRec())){
            p1.die();
        }
        if(p2.getRec().intersects(this.getRec())){
            p2.die();
        }

}
public void setXY(int x,int y){
        this.x=x;
        this.y=y;
}
}
