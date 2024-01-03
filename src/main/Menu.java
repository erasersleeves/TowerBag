package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import world.Level;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Menu {

public GamePanel gp;

public Menu(GamePanel gp){
    this.gp = gp;
}


    public void render(Graphics g){

        BufferedImage p = null;
        try {
            p = ImageIO.read(new File("resources/Play.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (p != null){
        Image play = p.getScaledInstance((int)(p.getWidth()*0.4)*gp.scale,(int)(p.getHeight()*0.4)*gp.scale,Image.SCALE_DEFAULT); 

        g.drawImage(play,(int)((gp.tileSize * gp.scale * Level.getInstance().getArena()[0].length)*0.7),(int)((gp.tileSize*gp.scale*Level.getInstance().getArena().length)*0.7), null);
        }

        try {
            p = ImageIO.read(new File("resources/exit.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (p != null){
        Image play = p.getScaledInstance((int)(p.getWidth()*0.04)*gp.scale,(int)(p.getHeight()*0.04)*gp.scale,Image.SCALE_DEFAULT); 

        g.drawImage(play,(int)((gp.tileSize * gp.scale * Level.getInstance().getArena()[0].length)*0.93),(int)((gp.tileSize*gp.scale*Level.getInstance().getArena().length)*0.025), null);
        }

        try {
            p = ImageIO.read(new File("resources/settings.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (p != null){
        Image play = p.getScaledInstance((int)(p.getWidth()*0.032)*gp.scale,(int)(p.getHeight()*0.032)*gp.scale,Image.SCALE_DEFAULT); 

        g.drawImage(play,(int)((gp.tileSize * gp.scale * Level.getInstance().getArena()[0].length)*0.855),(int)((gp.tileSize*gp.scale*Level.getInstance().getArena().length)*0.02), null);
        }



        
    }
}
