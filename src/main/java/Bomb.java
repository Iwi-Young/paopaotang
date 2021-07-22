package main.java;

import javax.swing.*;
import java.awt.*;


public class Bomb extends BaseElement{
    public Bomb(int x,int y,int wid,int hei){
        this.x=x;
        this.y=y;
        this.wid=wid;
        this.hei=hei;
        this.image=new ImageIcon("src/main/resources/image/bom.png").getImage();
    }
public Rectangle getRec(){
        return new Rectangle(x,y,wid,hei);
}
public int[] intersects(Player1 p1,Player2 p2){
        int[] a={0,0};
        if(p1.getRec().intersects(this.getRec())){
            a[0]=1;
        }
        if(p2.getRec().intersects(this.getRec())){
            a[1]=1;
        }
        return a;
}
}
