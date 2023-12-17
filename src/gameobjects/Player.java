package gameobjects;

import java.awt.event.KeyEvent;
import world.Level;

public class Player extends GameObject {

    private static Player instance = null;
    private int health = 5;
    private int money = 0;
    private int score = 0;
    public int speed = 1;
    private int x ;
    private int y ;
    private Level level = Level.getInstance();

    private Player() {
        this.x = 0;
        this.y = 0;
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
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



}
