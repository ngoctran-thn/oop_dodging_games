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
    private int space;
    private int speed;
    private int WIDTH = 600;// chieu rong cua frame
    private int HEIGHT= 700;// chieu cao cua frame
    private int move = 25;
    private int count = 1;
    private ArrayList <Rectangle> obstacle;
    private Random rand;
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
    Player player = new Player(WIDTH/2-90,HEIGHT-100);
    public PlayGround() throws IOException {
        rectangle = ImageIO.read(new File("D:\\GoogleDrive\\JavaProgramming\\RacingGame\\images\\rectangle.png"));
        triangle = ImageIO.read(new File("D:\\GoogleDrive\\JavaProgramming\\RacingGame\\images\\triangle.png"));
        star = ImageIO.read(new File("D:\\GoogleDrive\\JavaProgramming\\RacingGame\\images\\star.png"));
        circle = ImageIO.read(new File("D:\\GoogleDrive\\JavaProgramming\\RacingGame\\images\\circle.png"));
        user = ImageIO.read(new File("D:\\GoogleDrive\\JavaProgramming\\RacingGame\\images\\user.png"));
        timer = new Timer(3,this);
        rand = new Random();
        obstacle = new ArrayList<Rectangle>();
        space =100;
        speed = 1;
        addKeyListener(this);
        setFocusable(true);
        addObstacles(true);
        addObstacles(true);
        addObstacles(true);
        addObstacles(true);
        addObstacles(true);
        addObstacles(true);

        timer.start();
        this.startTime = System.currentTimeMillis();
    }
    public void addObstacles(boolean first){
        int positionx = rand.nextInt(20);
        int x = 0; // x of rectangle
        int x2 = 0; // x of polygon
        int y = 0;
        int Height = player.getHeight();
        int Width = player.getWidth();
        if(positionx == 0 || positionx == 20){
            x = WIDTH/10;
            x2 =  WIDTH/10 + 5;
        }
        else if (positionx == 1 || positionx == 19){
            x = 2*WIDTH/10;
            x2 =  9*WIDTH/10 -5;
        }
        else if(positionx == 2 || positionx == 18){
            x= 3*WIDTH/10;
            x2 = 8*WIDTH/10;
        }
        else if(positionx == 3 || positionx == 17){
            x= 4*WIDTH/10;
            x2 = 7*WIDTH/10;
        }
        else if(positionx == 4 || positionx == 16){
            x= 5*WIDTH/10;
            x2 = 6*WIDTH/10;
        }
        else if(positionx == 5 || positionx == 15){
            x= 6*WIDTH/10;
            x2 = 5*WIDTH/10;
        }
        else if(positionx == 6|| positionx == 14){
            x= 7*WIDTH/10;
            x2 = 4*WIDTH/10;
        }
        else if(positionx == 7 || positionx == 13){
            x= 8*WIDTH/10;
            x2 = 3*WIDTH/10;
        }
        else if(positionx == 8 || positionx == 12){
            x= 9*WIDTH/10 -5;
            x2 = 2*WIDTH/10;
        }
        else {
            x = WIDTH/10 + 5;
            x2 = WIDTH/10;
        }

        if(first){
            obstacle.add(new Rectangle(x,y-100-(obstacle.size()*space),Width,Height));
        }
        else{

            if(obstacle.size()>0){
                obstacle.add(new Rectangle(x,obstacle.get(obstacle.size()-1).y-300,Width,Height));
            }

        }

    }
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT );
        g.drawImage(user, player.getShape().x, player.getShape().y,null);
        g.setColor(Color.MAGENTA);
        g.drawRoundRect(110, 200, 90, 90, 200, 200);
        g.drawRect(255,200,90,90);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.draw(triangleShape);
        for(int i=0;i<obstacle.size();i++) {
            Rectangle rect =  obstacle.get(i);
            int option = i % 3;
            if(option == 0){
                g.drawImage(rectangle,rect.x,rect.y,null);
            }
            else if(option == 1){
                g.drawImage(star,rect.x,rect.y,null);
            }
            else if(option == 2){
                g.drawImage(circle,rect.x,rect.y,null);
            }
            else{
                g.drawImage(triangle,rect.x,rect.y,null);
            }
        }
        showResult(g);
    }

    private void showResult(Graphics g) {
        if (deadTime > 0) {
            long surviveTime = deadTime - this.startTime;
            g.drawString(String.format("Game over.\nYou survived %.2f seconds", (double) surviveTime / 1000),200,400);
            timer.stop();
            repaint();
        }
    }


    public void actionPerformed(ActionEvent e){
        Rectangle rect;
        count++;
        for(int i =0; i <obstacle.size(); i++){
            rect =obstacle.get(i);
            if(count%1000==0){
                speed++;
                if(move<50){
                    move+=10;
                }
            }
            rect.y+=speed;
        }
        //car crashing with oponents
        //for(Rectangle r:obstacle){
            //if(r.intersects(player)){
                //player.getShape().y = r.y+ player.getHeight();
                //this.deadTime = e.getWhen();
           // }
       // }
        for(int i = 0; i <obstacle.size(); i++){
            rect = obstacle.get(1);
            if(rect.y+rect.height >HEIGHT){
                obstacle.remove(rect);
                addObstacles(false);
            }
        }

        repaint();
    }

    public void moveLeft(){
        if(player.getShape().x-move < 10){
            System.out.println("\b");
        }else{
            player.getShape().x -= move;
        }
    }
    public void moveRight(){
        if(player.getShape().x+move>WIDTH-90){
            System.out.println("\b");
        }else{
            player.getShape().x += move;
        }
    }

    // Count Score: Score = millisecond;
    //public int countScore(){

    //}
    @Override
    public void keyTyped(KeyEvent e){

    }
    @Override
    public void keyPressed(KeyEvent e){

    }
    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            default:
                break;
        }
    }
}

