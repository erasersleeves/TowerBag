package main;

import javax.imageio.ImageIO;
import javax.swing.*;

import gameobjects.*;
import world.Level;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GamePanel extends JPanel implements Runnable {
    int FPS = 60;
    Thread gameThread = new Thread(this);
    Player player = Level.getInstance().getPlayer();
    int tileSize = 16;
    int scale = 3;


    KeyHandler keyH = new KeyHandler(player);
    public static GamePanel instance = null;
    private GamePanel() {
        setPreferredSize(new Dimension(tileSize * scale * Level.getInstance().getArena()[0].length, tileSize * scale * Level.getInstance().getArena().length));
        setVisible(true);
        addKeyListener(keyH);
        setFocusable(true);



    }

    public static GamePanel getInstance() {
        if (instance == null) {
            instance = new GamePanel();
        }
        return instance;
    }
    public void startGame() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        Level demo = Level.getInstance();
        demo.getTower().fire();
        if (demo.getEnemy() != null ) demo.getEnemy().advance();
    }


   @Override
   protected void paintComponent(Graphics g) {
    super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        // Draw the background
        int[][] arena = Level.getInstance().getArena();
        BufferedImage[] tiles = new BufferedImage[4]; // Assuming 4 different tiles
        try {
            tiles[0] = ImageIO.read(new File("resources/grass1.png"));
            tiles[1] = ImageIO.read(new File("resources/path1.png"));
            tiles[2] = ImageIO.read(new File("resources/base.png"));
            tiles[3] = ImageIO.read(new File("resources/base.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Iterate over the 2D array
        for (int i = 0; i < arena.length; i++) {
            for (int j = 0; j < arena[i].length; j++) {
                g2d.drawImage(tiles[arena[i][j]], j * tileSize * scale, i * tileSize * scale, tileSize * scale, tileSize * scale, this);
            }
        }

        // Draw the player
        if (player != null) {
            g2d.drawImage(player.getImage(), player.getX() * scale, player.getY() * scale, tileSize * scale, tileSize * scale, this);
        }

        // Draw the enemy
       Bat enemy = (Bat) Level.getInstance().getEnemy();
       if (enemy != null) {
           g2d.drawImage(enemy.getImage(), enemy.getX() * tileSize * scale, enemy.getY() * tileSize * scale, tileSize * scale, tileSize * scale, this);
       }

       // Draw the tower
       Tower tower = Level.getInstance().getTower();
       if (tower != null) {
           g2d.drawImage(tower.getImage(), tower.getX() * tileSize * scale, (tower.getY() - 1) * tileSize * scale, tileSize * scale, tileSize * scale * 2, this);
       }

       // Draw the bullet

       Bullet bullet = Level.getInstance().getTower().getBullet();
       if (bullet != null) {
           Image bulletImage = bullet.getImage();
           int bulletX = bullet.getX() * tileSize * scale + tileSize * scale / 2 - bulletImage.getWidth(null) / 2;
           int bulletY = bullet.getY() * tileSize * scale + tileSize * scale / 2 - bulletImage.getHeight(null) / 2;
           g2d.drawImage(bulletImage, bulletX, bulletY, bulletImage.getWidth(null), bulletImage.getHeight(null), this);
       }
    }
    @Override
    public void run() {
        // game loop
        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
//            update();
            repaint();
            double delta = nextDrawTime - System.nanoTime();
            if (delta > 0) {
                try {
                    Thread.sleep((long) (delta / 1000000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
