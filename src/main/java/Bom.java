package main.java;

import javax.swing.*;

public class Bom extends BaseElement{
    Bom(int x,int y){
        this.x=x;
        this.y=y;
        this.wid=50;
        this.hei=50;
        this.image= new ImageIcon("src/main/resources/image/bomb.png").getImage();
    }
}
