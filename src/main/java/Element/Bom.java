package main.java.Element;



import javax.swing.*;

public class Bom extends BaseElement {
    private int power;
    public Bom(int x, int y){
        this.x=x;
        this.y=y;
        this.wid=unit_len;
        this.hei=unit_len;
        this.image= new ImageIcon("src/main/resources/image/bomb.png").getImage();
    }
    public void setPower(int power){
        this.power=power;
    }
    public int getPower(){return power;}
    public void setXY(int x,int y){
        this.x=x;
        this.y=y;
    }
}
