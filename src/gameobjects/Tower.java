package gameobjects;
import world.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Tower extends GameObject{
    private int x;
    private int y;
    private int range;
    private int damage;
    private int cooldown;
    private int cooldownRemaining;
    private Bullet bullet;

    private Level level = Level.getInstance();

    private boolean isLifted = false;

    public Tower() {
        this.range = 3;
        this.damage = 1;
        this.cooldown = 5;
        this.cooldownRemaining = 0;
        // spawn at arena tile that is equal to 4
        for (int i = 0; i < level.getArena().length; i++) {
            for (int j = 0; j < level.getArena()[0].length; j++) {
                if (level.getArena()[i][j] == 4) {
                    x = j;
                    y = i;
                }
            }
        }
    }



    public void fire() {
        if (!isLifted && cooldownRemaining <= 0) {
            Enemy target = null;
            if (target != null && Math.abs(target.getX() - x) <= range && Math.abs(target.getY() - y) <= range) {
                target.takeDamage(damage);
                cooldownRemaining = cooldown;
            }
        }
        cooldownRemaining--;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void lift() {
        isLifted = true;
    }

    public void drop() {
        isLifted = false;
    }

    public Image getImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/tower.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public boolean isLifted() {
        return isLifted;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getRange() {
        return range;
    }

    public void increaseRange() {
        range++;
    }

    public void increaseDamage() {
        damage++;
    }

    public void decreaseCooldown() {
        cooldown--;
    }
}
