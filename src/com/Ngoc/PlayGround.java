package com.Ngoc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class PlayGround extends JPanel implements ActionListener, KeyListener {
    private int WIDTH = 600;// chieu rong cua frame
    private int HEIGHT = 700;// chieu cao cua frame
    private int MOVE = 25;
    private int count = 1;
    private int speed = 1;
    //private ArrayList <Rectangle> obstacle;
    //private Random rand;
    BufferedImage rectangle;
    BufferedImage triangle;
    BufferedImage circle;
    BufferedImage star;
    BufferedImage user;
    Timer timer;
    long startTime;
    long deadTime = 0;
    Triangle_Shape triangleShape = new Triangle_Shape(new Point2D.Double(440, 200),
            new Point2D.Double(485, 290), new Point2D.Double(395, 290));
    Player player = new Player(WIDTH / 2 - 90, HEIGHT - 100, MOVE);
    Obstacle obstacle = new Obstacle(WIDTH, HEIGHT, MOVE);

    public PlayGround() throws IOException {
        rectangle = ImageIO.read(new File("D:\\GoogleDrive\\JavaProgramming\\RacingGame\\images\\rectangle.png"));
        triangle = ImageIO.read(new File("D:\\GoogleDrive\\JavaProgramming\\RacingGame\\images\\triangle.png"));
        star = ImageIO.read(new File("D:\\GoogleDrive\\JavaProgramming\\RacingGame\\images\\star.png"));
        circle = ImageIO.read(new File("D:\\GoogleDrive\\JavaProgramming\\RacingGame\\images\\circle.png"));
        user = ImageIO.read(new File("D:\\GoogleDrive\\JavaProgramming\\RacingGame\\images\\user.png"));
        timer = new Timer(3, this);

        addKeyListener(this);
        setFocusable(true);
        for (int i = 0; i < 6; i++) {
            obstacle.addObstacles(true);
        }

        timer.start();
        this.startTime = System.currentTimeMillis();
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(user, player.getShape().x, player.getShape().y, null);
        g.setColor(Color.MAGENTA);
        g.drawRoundRect(110, 200, 90, 90, 200, 200);
        g.drawRect(255, 200, 90, 90);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.draw(triangleShape);
        for (int i = 0; i < obstacle.getListOfObstacles().size(); i++) {
            Rectangle rect = obstacle.getListOfObstacles().get(i);
            int option = i % 3;
            if (option == 0) {
                g.drawImage(rectangle, rect.x, rect.y, null);
            } else if (option == 1) {
                g.drawImage(star, rect.x, rect.y, null);
            } else if (option == 2) {
                g.drawImage(circle, rect.x, rect.y, null);
            } else {
                g.drawImage(triangle, rect.x, rect.y, null);
            }
        }
        showResult(g);
    }

    public void actionPerformed(ActionEvent e) {
        Rectangle rect;
        count++;
        for (int i = 0; i < obstacle.getListOfObstacles().size(); i++) {
            rect = obstacle.getListOfObstacles().get(i);
            if (count % 1000 == 0) {
                speed++;
                if (MOVE < 50) {
                    MOVE += 10;
                }
            }
            rect.y += speed;
        }
        //car crashing with oponents
        for (Rectangle r : obstacle.getListOfObstacles()) {
            if (r.intersects(player.getShape())) {
                player.getShape().y = r.y + player.getHeight();
                this.deadTime = e.getWhen();
            }
        }
        for (int i = 0; i < obstacle.getListOfObstacles().size(); i++) {
            rect = obstacle.getListOfObstacles().get(1);
            if (rect.y + rect.height > WIDTH) {
                obstacle.getListOfObstacles().remove(rect);
                obstacle.addObstacles(false);
            }
        }

        repaint();
    }

    private void showResult(Graphics g) {
        if (deadTime > 0) {
            long surviveTime = deadTime - this.startTime;
            g.drawString(String.format("Game over.\nYou survived %.2f seconds", (double) surviveTime / 1000), 200, 400);
            timer.stop();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // no imp
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // no imp
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                player.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                player.moveRight();
                break;
            default:
                break;
        }
    }


}

