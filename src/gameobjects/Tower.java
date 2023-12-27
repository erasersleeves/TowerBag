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

    public Tower() {
        this.range = 3;
        this.damage = 1;
        this.cooldown = 2;
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

    public void setLevel(Level level) {
        this.level = level;
    }

    public void fire() {
        if (isLifted) {
            bullet = null;
            return;
        }
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
