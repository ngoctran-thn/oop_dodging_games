package com.Ngoc;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player {
    private Rectangle shape;
    private int width = 40;
    private int height = 35;
    private int move ;


    public Player(int position_x, int position_y, int move ) {
        this.shape = new Rectangle(position_x,position_y,this.width,this.height);
        this.move = move;
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

    public void moveLeft(){
        if(shape.x-move < 10){
            System.out.println("\b");
        }else{
            shape.x -= move;
        }
    }
    public void moveRight(){
        if(shape.x+move>600-90){
            System.out.println("\b");
        }else{
            shape.x += move;
        }
    }




}
