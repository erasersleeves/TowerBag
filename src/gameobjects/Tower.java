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
    private float cooldown;
    private float cooldownRemaining;
    private Bullet bullet;

    private Level level = Level.getInstance();

    private boolean isLifted = false;

    public Tower(int x, int y, int range, int damage, int cooldown) {
        this.x = x;
        this.y = y;
        this.range = range;
        this.damage = damage;
        this.cooldown = cooldown;
        this.cooldownRemaining = 0;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void fire() {
        if (bullet == null || disTowBull() > range) bullet = new Bullet(x, y, 1, damage, level.getEnemy());
        bullet.advance();
        if (level.getEnemy() != null && bullet.getX() == bullet.getTarget().getX() && bullet.getY() == bullet.getTarget().getY()) {
            bullet.getTarget().takeDamage(damage);
            bullet = new Bullet(x, y, 1, damage, level.getEnemy());
        }
    }

    private int disTowBull() {
        // return distance between x and y of tower and bullet
        return Math.abs(bullet.getY()-y);

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
}
