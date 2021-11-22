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
    private int width = 40;// chieu rong cua hinh vuong
    private int height =35;// chieu cao cua hinh vuong
    private int speed;
    private int WIDTH = 600;// chieu rong cua frame
    private int HEIGHT= 700;// chieu cao cua frame
    private int move = 25;
    private int count = 1;
    private ArrayList <Rectangle> ocar;
    private ArrayList <Polygon> ocar1;
    private Rectangle car;
    private Random rand;
    Timer timer;
    Triangle triangle = new Triangle(395,290,485,290,440,200);
    //Triangle_Shape triangleShape = new Triangle_Shape(new Point2D.Double(440, 200),
            //new Point2D.Double(485, 290), new Point2D.Double(395, 290));

    public PlayGround(){
        timer = new Timer(3,this);
        rand = new Random();
        ocar = new ArrayList<Rectangle>();
        ocar1 = new ArrayList<Polygon>();
        car = new Rectangle(WIDTH/2-90,HEIGHT-100,width,height);
        space =100;
        speed = 1;
        addKeyListener(this);
        setFocusable(true);
        int number = rand.nextInt(4);
        // random 0->4 : 1,2,3,4 mean true(run) . 0 mean false
        addOwnCars(number);
        addOwnCars(number);
        addOwnCars(number);
        addOwnCars(number);
        addOwnCars(number);
        addOwnCars(number);
        addOwnCars(number);
        addOwnCars(number);


        timer.start();
    }
    public void addOwnCars(int number){
        int positionx = rand.nextInt(20);
        int x = 0;
        int y = 0;
        int Height =height;
        int Width =width;
        if(positionx == 0 || positionx == 20){
            x = WIDTH/10;
        }
        else if (positionx == 1 || positionx == 19){
            x = 2*WIDTH/10;
        }
        else if(positionx == 2 || positionx == 18){
            x= 3*WIDTH/10;
        }
        else if(positionx == 3 || positionx == 17){
            x= 4*WIDTH/10;
        }
        else if(positionx == 4 || positionx == 16){
            x= 5*WIDTH/10;
        }
        else if(positionx == 5 || positionx == 15){
            x= 6*WIDTH/10;
        }
        else if(positionx == 6|| positionx == 14){
            x= 7*WIDTH/10;
        }
        else if(positionx == 7 || positionx == 13){
            x= 8*WIDTH/10;
        }
        else if(positionx == 8 || positionx == 12){
            x= 9*WIDTH/10 -5;
        }
        else {
            x = WIDTH/10 + 5;
        }

        if(number == 0||number == 1|| number==2){
            ocar.add(new Rectangle(x,y-100-(ocar.size()*space), Width, Height));
            Triangle newTriangle = new Triangle(x,y-100-(ocar1.size()*space), x+100,y-100-(ocar1.size()*space),(x+100)/2,y-100-(ocar1.size()*space)-Height);
            ocar1.add(newTriangle.drawPolygon());

        }
        else{

            //if(ocar.size()>0){
               // ocar.add( new Rectangle(x,ocar.get(ocar.size()-1).y -300, Width, Height) );
            //}

           //ocar1.add(new Triangle(x,y-100-(ocar1.size()*space), x+Width,y-100-(ocar1.size()*space),(x+Width)/2,y-100-(ocar1.size()*space)-Height, color));
        }

    }
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT );
        g.setColor(Color.red);
        g.fillRect(car.x,car.y,car.width,car.height);
        g.setColor(Color.MAGENTA);
        g.drawRoundRect(110, 200, 90, 90, 200, 200);
        g.drawRect(255,200,90,90);
        //Graphics2D g2d = (Graphics2D) g.create();
        //g2d.draw(triangleShape);
        g.fillPolygon(triangle.drawPolygon());


        for(int i=0;i<ocar.size();i++) {
            g.setColor(Color.MAGENTA);
            Rectangle rect =  ocar.get(i);
            g.fillRect(rect.x,rect.y,rect.width,rect.height);

            g.setColor(Color.MAGENTA);
            Polygon poly = ocar1.get(i);
            g.fillPolygon(poly);

        }

    }
    public void actionPerformed(ActionEvent e){
        Rectangle rect;
        Polygon polyg;
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
            polyg = ocar1.get(i);
            polyg.ypoints[0] += speed;
            polyg.ypoints[1] += speed;
            polyg.ypoints[2] += speed;
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
                addOwnCars(0);
            }
        }

        repaint();
    }

    public void moveLeft(){
        if(car.x-move < 10){
            System.out.println("\b");
        }else{
            car.x -= move;
        }
    }
    public void moveRight(){
        if(car.x+move>WIDTH-90){
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

