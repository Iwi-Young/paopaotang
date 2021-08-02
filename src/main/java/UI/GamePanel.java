package main.java.UI;

import main.java.Element.BaseElement;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private BaseElement[] draws;
    private Image image;//buffer
    private int i;
    public GamePanel(BaseElement... draw){
        this.i=0;
        draws=new BaseElement[200];
        for(BaseElement d : draw) {
            this.draws[i]=d;
            i++;
        }
        this.draws[i]=null;
    }
    public void DrawBuffer(){
        image=createImage(this.getWidth(),this.getHeight());
        Graphics g=image.getGraphics();
        for(int j=0;j<i;j++){
            draws[j].drawImage(g);
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        DrawBuffer();
        g.drawImage(image,0,0, this);

    }
    public void add(BaseElement draw){
        this.draws[i]=draw;
        this.draws[++i]=null;
    }


}

