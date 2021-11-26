package com.Ngoc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.image.ImageObserver.WIDTH;

public class Obstacle {
    private ArrayList<Rectangle> listOfObstacles;
    private int space = 100;
    private int Height = 35;
    private int Width = 40;
    private int horizontal;
    private int vertical;
    private int Move;
    Random rand = new Random();
    Player player1 = new Player(this.horizontal, this.vertical, Move);

    public Obstacle(int WIDTH, int HEIGHT, int Move) {
        this.horizontal = WIDTH;
        this.vertical = HEIGHT;
        this.Move = Move;
        this.listOfObstacles = new ArrayList<>();
    }

    public ArrayList<Rectangle> getListOfObstacles() {
        return listOfObstacles;
    }

    public int getSpace() {
        return space;
    }

    public Random getRand() {
        return rand;
    }

    public void addObstacles(boolean first) {
        int position_x = rand.nextInt(20);
        int x = 0; // x of rectangle
        int y = 0;
        if (position_x == 0) {
            x = this.horizontal / 10;
        } else if (position_x == 1 || position_x == 19) {
            x = 2 * this.horizontal / 10;
        } else if (position_x == 2 || position_x == 18) {
            x = 3 * this.horizontal / 10;
        } else if (position_x == 3 || position_x == 17) {
            x = 4 * this.horizontal / 10;
        } else if (position_x == 4 || position_x == 16) {
            x = 5 * this.horizontal / 10;
        } else if (position_x == 5 || position_x == 15) {
            x = 6 * this.horizontal / 10;
        } else if (position_x == 6 || position_x == 14) {
            x = 7 * this.horizontal / 10;
        } else if (position_x == 7 || position_x == 13) {
            x = 8 * this.horizontal / 10;
        } else if (position_x == 8 || position_x == 12) {
            x = 9 * this.horizontal / 10 - 5;
        } else {
            x = this.horizontal / 10 + 5;
        }

        if (first) {
            listOfObstacles.add(new Rectangle(x, y - 100 - (listOfObstacles.size() * space), Width, Height));
        } else {

            if (listOfObstacles.size() > 0) {
                listOfObstacles.add(new Rectangle(x, listOfObstacles.get(listOfObstacles.size() - 1).y - 300, Width, Height));
            }

        }

    }


}
