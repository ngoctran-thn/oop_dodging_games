package com.Ngoc;

import java.awt.*;

public class Player {
    private Rectangle shape;
    private int width = 40;
    private int height = 35;

    public Player(int x, int y) {
        this.shape = new Rectangle(x,y,this.width,this.height);
    }

    public Rectangle getShape() {
        return shape;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
