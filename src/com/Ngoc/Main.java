package com.Ngoc;

import javax.swing.*;
import java.io.IOException;

public class Main extends JFrame {
    public static void main(String[] args) throws IOException {
        JFrame app = new JFrame();
        PlayGround playGround = new PlayGround();
        app.add(playGround);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(600,720);
        app.setVisible(true);


    }
}
