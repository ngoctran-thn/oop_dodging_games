package com.Ngoc;

import javax.swing.*;

public class Car extends JFrame {
    public static void main(String[] args){
        JFrame app = new JFrame();
        PlayGround playGround = new PlayGround();
        app.add(playGround);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(500,720);
        app.setVisible(true);
    }
}
