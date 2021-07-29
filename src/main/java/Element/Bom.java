package main.java.Element;



import javax.swing.*;

public class Bom extends BaseElement {
    public Bom(int x, int y){
        this.x=x;
        this.y=y;
        this.wid=unit_len;
        this.hei=unit_len;
        this.image= new ImageIcon("src/main/resources/image/bomb.png").getImage();
    }
}
