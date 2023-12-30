package main;

import javax.imageio.ImageIO;
import javax.swing.*;

import gameobjects.*;
import world.*;


import java.util.Iterator;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class GamePanel extends JPanel implements Runnable {
    int FPS = 60;
    Thread gameThread = new Thread(this);
    Player player = Level.getInstance().getPlayer();
    int tileSize = 32;
    int scale = 1;
    private BufferedImage background;

    KeyHandler keyH = new KeyHandler(player);
    public static GamePanel instance = null;

    
    private GamePanel() {
        setPreferredSize(new Dimension(tileSize * scale * Level.getInstance().getArena()[0].length, tileSize * scale * Level.getInstance().getArena().length));
        setVisible(true);
        addKeyListener(keyH);
        setFocusable(true);

        // Initialize the background image and draw the background
        background = new BufferedImage(tileSize * scale * Level.getInstance().getArena()[0].length, tileSize * scale * Level.getInstance().getArena().length, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dBackground = background.createGraphics();
        drawBackground(g2dBackground);
        g2dBackground.dispose();
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
    }

    public void drawBackground(Graphics2D g2d) {
        // Draw the background
       int[][] arena = Level.getInstance().getArena();

       // Iterate over the 2D array
       for (int i = 0; i < arena.length; i++) {
           for (int j = 0; j < arena[i].length; j++) {
               g2d.drawImage(pickTile(arena[i][j]), j * tileSize * scale, i * tileSize * scale, tileSize * scale,
                       tileSize * scale, this);
           }
       }
    }

    public BufferedImage pickTile(int i){
        switch (i) {
            case 0:
            case 4:
            case 5:
                return pickRandomTile("resources/grass.png");
            case 1:
            case 2:
            case 3:
                return pickRandomTile("resources/path.png");
            default:
                return null;
        }
    }
    
    public BufferedImage pickRandomTile(String path){
        try {
        // Load the PNG file
        BufferedImage image = ImageIO.read(new File(path));

        // Define the size of a tile
        int tileSize = 32; // Replace with the actual tile size

        // Calculate the number of tiles in the image
        int tilesX = image.getWidth() / tileSize;
        int tilesY = image.getHeight() / tileSize;

        // Pick a random tile
        Random random = new Random();
        int tileX = random.nextInt(tilesX);
        int tileY = random.nextInt(tilesY);

        // Select the tile
        return image.getSubimage(tileX * tileSize, tileY * tileSize, tileSize, tileSize);
    } catch (IOException e) {
        e.printStackTrace();
    }

    return null;
    }

    public void drawCoin(Graphics2D g2d) {
        Coin coin = Level.getInstance().getCoin();
        if (coin != null) {
            Image coinImage = new ImageIcon("resources/coin.png").getImage();
            int scaledWidth = tileSize * scale / 2;
            int scaledHeight = tileSize * scale / 2;
            int coinX = coin.getX() * tileSize * scale + (tileSize * scale - scaledWidth) / 2;
            int coinY = coin.getY() * tileSize * scale + (tileSize * scale - scaledHeight) / 2;
            g2d.drawImage(coinImage, coinX, coinY, scaledWidth, scaledHeight, this);
        }
    }

    public void drawAltar(Graphics2D g2d) {
        Altar altar = Level.getInstance().getAltar();
        if (altar != null) {
            Image altarImage = null;
            if (altar.isTriggered()) altarImage = new ImageIcon("resources/altartriggered.png").getImage();
            else altarImage = new ImageIcon("resources/altar.png").getImage();
            int altarX = (altar.getX()-1) * tileSize * scale;
            int altarY = (altar.getY()-1) * tileSize * scale;
            g2d.drawImage(altarImage, altarX, altarY, altarImage.getWidth(this), altarImage.getHeight(this), this);
        }
    }

    public void drawPlayer(Graphics2D g2d) {
        if (player != null) {
           g2d.drawImage(player.getImage(), player.getX() * tileSize * scale, (player.getY()) * tileSize * scale,
                   tileSize * scale, tileSize * scale, this);
       }
    }

    public void drawEnmey(Graphics2D g2d) {
        Iterator<Enemy> iterator = Level.getInstance().getWave().getEnemies().iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            g2d.drawImage(enemy.getImage(), enemy.getX() * tileSize * scale, enemy.getY() * tileSize * scale,
                    tileSize * scale, tileSize * scale, this);
        }
    }

    public void drawBullet(Graphics2D g2d){
        Bullet bullet = Level.getInstance().getTower().getBullet();
        if (bullet != null) {
            Image bulletImage = bullet.getImage();
            int bulletX = bullet.getX() * tileSize * scale + tileSize * scale / 2 - bulletImage.getWidth(null) / 2;
            int bulletY = bullet.getY() * tileSize * scale + tileSize * scale / 2 - bulletImage.getHeight(null) / 2;
            g2d.drawImage(bulletImage, bulletX, bulletY, bulletImage.getWidth(null), bulletImage.getHeight(null), this);
        }
    }

    public void drawTower(Graphics2D g2d) {
        Tower tower = Level.getInstance().getTower();
        if (tower == null) return;
        g2d.drawImage(tower.getImage(), tower.getX() * tileSize * scale, (tower.getY() - 1) * tileSize * scale, tileSize * scale, tileSize * scale * 2, this);
        
        if (tower.isLifted()){
    
            // Draw circle around the tower with range as radius
            int towerCenterX = tower.getX() * tileSize * scale + tileSize * scale / 2;
            int towerCenterY = (tower.getY() - 1) * tileSize * scale + tileSize * scale;
            int circleRadius = tower.getRange() * tileSize * scale;

            g2d.setColor(Color.GRAY);
            g2d.drawOval(towerCenterX - circleRadius, towerCenterY - circleRadius, circleRadius * 2, circleRadius * 2);
        }
        
    }
    
    public void drawHealth(Graphics2D g2d) {
        // Load the heart image
        Image heartImage = new ImageIcon("resources/heart.png").getImage();

        // Get the player's health
        int health = player.getHealth();

        // Calculate the position for the first heart
        int heartX = this.getWidth() - heartImage.getWidth(null) - 10;
        int heartY = this.getHeight() - heartImage.getHeight(null) - 10;

        // Draw the hearts
        int heartSpacing = -2; // Adjust the spacing between hearts

        for (int i = 0; i < health; i++) {
            int currentHeartX = heartX - i * (heartImage.getWidth(null) + heartSpacing);
            g2d.drawImage(heartImage, currentHeartX, heartY, heartImage.getWidth(null), heartImage.getHeight(null), this);
        }

    }

    public void drawMoneyCounter(Graphics2D g2d) {
        // Load the coin image
        Image coinImage = new ImageIcon("resources/coin.png").getImage();

        // Get the player's money
        int money = player.getMoney();

        // Calculate the position for the first heart
        int coinX = this.getWidth() - coinImage.getWidth(null) - 40;
        int coinY = this.getHeight() - coinImage.getHeight(null) - 25;

        g2d.drawImage(coinImage, coinX, coinY, coinImage.getWidth(null), coinImage.getHeight(null), this);

        g2d.setFont(new Font("Consolas", Font.BOLD, 15));
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawString(" x " + money, coinX + 15, coinY + 15);

    }

    public void drawUpgradeScreen(Graphics2D g2d) {
        Altar altar = Level.getInstance().getAltar();

        int screenWidth = this.getWidth();
        int screenHeight = this.getHeight();
        int rectSize = 64;
        int rectSpacing = 15;
        int rectX = (screenWidth - (3 * rectSize + 2 * rectSpacing)) / 2;
        int rectY = (screenHeight - rectSize) / 2;

        

        // Draw selection area around the first rectangle
        g2d.setColor(Color.YELLOW);
        g2d.fillRect((rectX-5) + altar.getIndex() * (rectSpacing + rectSize) , (rectY-5), rectSize+10, rectSize+10);

        g2d.setColor(Color.RED);
        g2d.fillRect(rectX, rectY, rectSize, rectSize);

        g2d.setColor(Color.GREEN);
        g2d.fillRect(rectX + rectSize + rectSpacing, rectY, rectSize, rectSize);

        g2d.setColor(Color.BLUE);
        g2d.fillRect(rectX + 2 * (rectSize + rectSpacing), rectY, rectSize, rectSize);
    }

    public void drawGameOver(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Consolas", Font.BOLD, 50));
        g2d.drawString("GAME OVER", this.getWidth() / 2 - 150, this.getHeight() / 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;

    //    drawBackground(g2d);
        g2d.drawImage(background, 0, 0, this);
        drawCoin(g2d);
        drawAltar(g2d);
        drawPlayer(g2d);
        drawEnmey(g2d);
        drawBullet(g2d);
        drawTower(g2d);
        drawHealth(g2d);
        drawMoneyCounter(g2d);
        if (GameConsole.getState() == GameState.INALTAR) drawUpgradeScreen(g2d);
        if (GameConsole.getState() == GameState.GAMEOVER) drawGameOver(g2d);
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
