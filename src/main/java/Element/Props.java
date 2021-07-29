package main.java.Element;

import javax.swing.*;

public class Props extends BaseElement{
    private int name;//1增加max，2增加len
    public Props(int x,int y,int name){
        this.name=name;
        if(name==1)
            this.image=new ImageIcon("src/main/resources/image/Prop1.png").getImage();
        else
            this.image=new ImageIcon("src/main/resources/image/Prop2.png").getImage();
        this.x=x;
        this.y=y;
        this.wid=unit_len;
        this.hei=unit_len;
    }
    public int getName(){
        return name;
    }
    public void setName(int name){
        this.name=name;
    }
}
