package com.Ngoc;

import java.awt.*;
import javax.swing.JFrame;

public class MyCanvas extends Canvas{

    public void paint(Graphics g) {
        Toolkit t=Toolkit.getDefaultToolkit();
        Image i=t.getImage("/com/Ngoc/images/p3.gif");
        g.drawImage(i, 50,60,this);


    }
    public static void main(String[] args) {
        MyCanvas m=new MyCanvas();
        JFrame f=new JFrame();
        f.add(m);
        f.setSize(400,400);
        //f.setLayout(null);
        f.setVisible(true);
    }

}
