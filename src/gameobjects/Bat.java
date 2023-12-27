package gameobjects;
import world.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bat extends Enemy {
    private int x;
    private int y;
    private boolean goingUp = false;
    private int health = 3;
    private final int speed = 1;
    private int damage = 1;

    private Level level = Level.getInstance();

    public Bat() {
        // spawn at arena tile that is equal to 2
        for (int i = 0; i < level.getArena().length; i++) {
            for (int j = 0; j < level.getArena()[0].length; j++) {
                if (level.getArena()[i][j] == 2) {
                    x = j;
                    y = i;
                }
            }
        }

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void advance() {
        
        if (x + 1 < level.getArena()[0].length && (level.getArena()[y][x + 1] == 1 || level.getArena()[y][x + 1] == 3)) {
            //go right
            x += speed;
            goingUp = false;
        } else if (y + 1 < level.getArena().length && !goingUp && (level.getArena()[y + 1][x] == 1 || level.getArena()[y + 1][x] == 3)) {
            //go down
            y += speed;   
        } else if (y - 1 >= 0 && (level.getArena()[y - 1][x] == 1 || level.getArena()[y - 1][x] == 3)) {
            //go up
            y -= speed;
            goingUp = true;
        } else if (x - 1 >= 0 && (level.getArena()[y][x - 1] == 1 || level.getArena()[y][x - 1] == 3)) {
            //go left
            x -= speed;
            goingUp = false;
        } 
        inflictDamage();
    }

    @Override
    public void inflictDamage() {
        if (level.getArena()[y][x] == 3){
            level.getPlayer().takeDamage(damage);
            //delete bat
            level.setEnemy(null);
        }
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {level.setEnemy(null);}
    }


    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }


    public Image getImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/bat.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaledImg = img.getSubimage(0, 0, 16, 16).getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        return scaledImg;
    }

    @Override
    void setLevel(Level level) {
        this.level = level;
    }
}
