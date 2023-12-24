package gameobjects;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import main.GamePanel;
import main.KeyHandler;
import world.Level;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Player extends GameObject {
    private int health = 5;
    private int money = 0;
    private int score = 0;
    public int speed = 1;
    private int x ;
    private int y ;
    private Level level = Level.getInstance();


    public Player() {
        this.x = 0;
        this.y = 2;

    }


    //getters for x and y
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //setters for x and y
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void takeDamage(float damage) {
        health -= damage;
    }

	public int getHealth() {
		return health;
	}

    public boolean reaches(Tower tower) {
        return (tower.getX() == x && tower.getY() == y);
    }


    public Image getImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaledImg = img.getSubimage(0, 0, 16, 16).getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        return scaledImg;
    }
}
