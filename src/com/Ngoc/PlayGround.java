package com.Ngoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class PlayGround extends JPanel implements ActionListener, KeyListener {
    private int space;
    private int width = 80;
    private int height =70;
    private int speed;
    private int WIDTH = 500;
    private int HEIGHT= 700;
    private int move = 20;
    private int count = 1;
    private ArrayList <Rectangle> ocar;
    private Rectangle car;
    private Random rand;
    Timer t;


    public PlayGround(){
        t = new Timer(20,this);
        rand = new Random();
        ocar = new ArrayList<Rectangle>();
        car = new Rectangle(WIDTH/2-90,HEIGHT-100,width,height);
        space =300;
        speed = 2;
        addKeyListener(this);
        setFocusable(true);
        addOwnCars(true);
        addOwnCars(true);
        addOwnCars(true);
        t.start();
    }
    public void addOwnCars(boolean first){
        int positionx = rand.nextInt()%2;
        int x = 0;
        int y = 0;
        int Height =height;
        int Width =width;
        if(positionx == 0){
            x = WIDTH/2-90;
        }
        else{
            x=WIDTH/2+10;
        }
        if(first){
            ocar.add(new Rectangle(x,y-100-(ocar.size()*space), Width, Height));
        }
        else{
            ocar.add( new Rectangle(x,ocar.get(ocar.size()-1).y-300,Width,Height) );
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.setColor(Color.cyan);
        g.fillRect(0,0,WIDTH,HEIGHT );
        g.setColor(Color.gray);
        g.fillRect(WIDTH/2-100, 0, 200,HEIGHT);
        g.setColor(Color.red);
        g.fillRect(car.x,car.y,car.width,car.height);
        g.setColor(Color.blue);
        g.drawLine(WIDTH/2,0,WIDTH/2,HEIGHT);
        g.setColor(Color.MAGENTA);
        for(Rectangle rect:ocar){
            g.fillRect(rect.x,rect.y,rect.width,rect.height);
        }

    }
    public void actionPerformed(ActionEvent e){
        Rectangle rect;
        count++;
        for(int i =0; i <ocar.size(); i++){
            rect =ocar.get(i);
            if(count%1000==0){
                speed++;
                if(move<50){
                    move+=10;
                }
            }
            rect.y+=speed;

        }
        //car crashing with oponents
        for(Rectangle r:ocar){
            if(r.intersects(car)){
                car.y = r.y+height;
            }
        }
        for(int i = 0; i <ocar.size(); i++){
            rect = ocar.get(1);
            if(rect.y+rect.height >HEIGHT){
                ocar.remove(rect);
                addOwnCars(false);
            }
        }

        repaint();
    }

    public void moveUp(){
        if(car.y-move <0){
            System.out.println("\b");
        }else{
            car.y -= move;
        }
    }

    public void moveDown(){
        if(car.y+move+car.height >HEIGHT -1){
            System.out.println("\b");
        }else{
            car.y += move;
        }
    }

    public void moveLeft(){
        if(car.x-move < WIDTH/2-90){
            System.out.println("\b");
        }else{
            car.x -= move;
        }
    }
    public void moveRight(){
        if(car.x+move>WIDTH/2+10){
            System.out.println("\b");
        }else{
            car.x += move;
        }
    }
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
            case KeyEvent.VK_UP:
                moveUp();
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
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

