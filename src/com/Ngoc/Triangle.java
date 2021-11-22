package com.Ngoc;

import java.awt.*;

public class Triangle {
    public  Triangle(int x1,int y1,int x2,int y2,int x3,int y3){
        Polygon p=new Polygon();
        p.addPoint(x1,y1);
        p.addPoint(x2,y2);
        p.addPoint(x3,y3);
    }
}
