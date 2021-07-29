package main.java.Element;

import javax.swing.*;


public class Obstacle extends BaseElement{

    public Obstacle(int x,int y){
        this.image=new ImageIcon("src/main/resources/image/Obstacle.png").getImage();
        this.wid=unit_len;
        this.hei=unit_len;
        this.x=x;
        this.y=y;
    }
}
