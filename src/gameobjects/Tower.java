package gameobjects;
import world.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Tower extends GameObject{
    private int range;
    private int damage;
    private int cooldown;
    private int cooldownRemaining;
    private Bullet bullet;

    private boolean isLifted = false;

    public Tower() {
        this.range = 3;
        this.damage = 1;
        this.cooldown = 4;
        this.cooldownRemaining = 0;
        // spawn at arena tile that is equal to 4
        for (int i = 0; i < Level.getInstance().getArena().length; i++) {
            for (int j = 0; j < Level.getInstance().getArena()[0].length; j++) {
                if (Level.getInstance().getArena()[i][j] == 4) {
                    x = j;
                    y = i;
                }
            }
        }
    }

    public boolean isInRange(Enemy target) {
        if (target != null) {
            int xDistance = Math.abs(target.getX() - x);
            int yDistance = Math.abs(target.getY() - y);
            return xDistance <= range && yDistance <= range;
        }
        return false;
    }

    public void fire(Enemy target) {
        if (!isLifted && cooldownRemaining <= 0) {
            target.takeDamage(damage);
            cooldownRemaining = cooldown;
        }
        cooldownRemaining--;
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

    public void followPlayer() {
        if (isLifted()) {
            x = Level.getInstance().getPlayer().getX();
            y = Level.getInstance().getPlayer().getY();
        }
    }
}
