package gameobjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bat extends Enemy {  

    public Bat(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 2;
        this.speed = 1;
        this.damage = 1;
    }

    @Override
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

}
