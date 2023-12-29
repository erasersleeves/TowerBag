package gameobjects;
import world.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet extends GameObject{
    private int speed;
    private int damage;
    private Level level = Level.getInstance();
    private Enemy target;

    public Bullet(int x, int y, int speed, int damage, Enemy target) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
    }

    public void advance() {
        y -= speed;
    }

    public Enemy getTarget() {
        return target;
    }

    public Image getImage() {
        //return bullet.png scaled by 2
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/bullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
