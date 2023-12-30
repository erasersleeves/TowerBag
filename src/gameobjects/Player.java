package gameobjects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import world.Level;

import javax.imageio.ImageIO;

public class Player extends GameObject {
    private int health = 5;
    private int money = 0;
    private int score = 0;
    public int speed = 1;



    public Player() {
        this.x = 0;
        this.y = 2;
    }

    
    public void takeDamage(float damage) {
        health -= damage;
    }

	public int getHealth() {
		return health;
	}

 
    public void interact(Tower tower) {
        if (reaches(tower)) {
            if (tower.isLifted()) {
                tower.drop();
            } else {
                tower.lift();
            }
        }

    }


    public Image getImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/player.png")).getSubimage(0, 0, 32, 32);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public void moveUp() {
        if (!Level.getInstance().isSolid(x, y - speed)) {
            y -= speed;
        }
    }

    public void moveDown() {
        if (!Level.getInstance().isSolid(x, y + speed)) {
            y += speed;
        }
    }

    public void moveLeft() {
        if (!Level.getInstance().isSolid(x - speed, y)) {
            x -= speed;
        }
    }

    public void moveRight() {
        if (!Level.getInstance().isSolid(x + speed, y)) {
            x += speed;
        }
    }

    public void increaseMoney() {
        money++;
    }

    public int getMoney() {
        return money;
    }

    public void decreaseMoney(int i) {
        money -= i;
    }
}
