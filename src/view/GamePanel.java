package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.KeyHandler;
import controller.Menu;
import model.GameConsole;
import model.GameState;
import model.gameobjects.*;
import model.world.*;

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
            Image coinImage = null;
            if (coin.getValue() == 1) coinImage = new ImageIcon("resources/coin.png").getImage();
            else coinImage = new ImageIcon("resources/gold.png").getImage();
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
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/player.png")).getSubimage(0, 0, 32, 32);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (player != null) {
           g2d.drawImage(img, player.getX() * tileSize * scale, (player.getY()) * tileSize * scale,
                   tileSize * scale, tileSize * scale, this);
       }
    }

    public void drawEnmey(Graphics2D g2d) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/bat.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<Enemy> iterator = Level.getInstance().getWave().getEnemies().iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            g2d.drawImage(img, enemy.getX() * tileSize * scale, enemy.getY() * tileSize * scale,
                    tileSize * scale, tileSize * scale, this);
        }
    }

    public void drawBridge(Graphics2D g2d) {
        BufferedImage bridgeImage = null;
        try {
            bridgeImage = ImageIO.read(new File("resources/bridge.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.drawImage(bridgeImage  ,16 * tileSize * scale , 3* tileSize*scale , this);
        g2d.drawImage(bridgeImage  ,16 * tileSize * scale , 9* tileSize*scale , this);

    }


    public void drawTower(Graphics2D g2d) {
        Tower tower = Level.getInstance().getTower();
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/tower.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (tower == null) return;
        g2d.drawImage(img, tower.getX() * tileSize * scale, (tower.getY() - 1) * tileSize * scale, tileSize * scale, tileSize * scale * 2, this);
        
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
        int fee = Level.getInstance().getAltar().getFee();

        // Calculate the position for the first heart
        int coinX = this.getWidth() - coinImage.getWidth(null) - 60;
        int coinY = this.getHeight() - coinImage.getHeight(null) - 25;

        g2d.drawImage(coinImage, coinX, coinY, coinImage.getWidth(null), coinImage.getHeight(null), this);

        g2d.setFont(new Font("Consolas", Font.BOLD, 12));
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawString(" x" + money + "/" + fee, coinX + 15, coinY + 15);

    }

    public void drawUpgradeScreen(Graphics2D g2d) {
        Altar altar = Level.getInstance().getAltar();

        int screenWidth = this.getWidth();
        int screenHeight = this.getHeight();
        int rectSize = 64;
        int rectSpacing = 30;
        int rectX = (screenWidth - (3 * rectSize + 2 * rectSpacing)) / 2;
        int rectY = (screenHeight - rectSize) / 2;
        

        

        // Draw selection area around the first rectangle
        g2d.setColor(Color.WHITE);
        g2d.fillRect((rectX-5) + altar.getIndex() * (rectSpacing + rectSize) , (rectY-5), rectSize+10, rectSize+10);

        Image range = new ImageIcon("resources/range.png").getImage();
        g2d.drawImage(range, rectX, rectY, rectSize, rectSize, this);

        Image damage = new ImageIcon("resources/damage.png").getImage();
        g2d.drawImage(damage, rectX + rectSize + rectSpacing, rectY, rectSize, rectSize, this);

        Image cooldown = new ImageIcon("resources/cooldown.png").getImage();
        g2d.drawImage(cooldown, rectX + 2 * (rectSize + rectSpacing), rectY, rectSize, rectSize, this);

        // Draw text on the menu
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Consolas", Font.TRUETYPE_FONT, 15));
        g2d.drawString("Choose what to upgrade", rectX + 5, rectY - 20);
        g2d.drawString("Range", rectX + 5, rectY + rectSize + 20);
        g2d.drawString("Damage", rectX + rectSize + rectSpacing + 5, rectY + rectSize + 20);
        g2d.drawString("Cooldown", rectX + 2 * (rectSize + rectSpacing) - 5, rectY + rectSize + 20);
    }

    public void greedyEnding(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Consolas", Font.TRUETYPE_FONT, 50));
        g2d.drawString("GREEDY", this.getWidth() / 2 - 150, this.getHeight() / 2);
    }

    public void drawGameOver(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Consolas", Font.TRUETYPE_FONT, 50));
        g2d.drawString("GAME OVER", this.getWidth() / 2 - 150, this.getHeight() / 2);
    }

    public void displayCredits(Graphics2D g2d) {
        BufferedImage titleImage = null;
        try {
            // Load the splash screen image
            titleImage = ImageIO.read(new File("resources/title.png"));
            // Draw the image on the panel
            g2d.drawImage(titleImage, 0, 0, titleImage.getWidth(), titleImage.getHeight(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Consolas", Font.TRUETYPE_FONT, 15));
        String text1 = "This game was designed and developed by:";
        String text2 = "Yacine Benmeziane & Adjlout Amazigh : Binome 48";
        String text3 = "POOIG 2023/2024 © All rights reserved";

        int text1Width = g2d.getFontMetrics().stringWidth(text1);
        g2d.drawString(text1, (getWidth() - text1Width) / 2, getHeight() - 200);

        int text2Width = g2d.getFontMetrics().stringWidth(text2);
        g2d.drawString(text2, (getWidth() - text2Width) / 2, getHeight() - 150);

        int text3Width = g2d.getFontMetrics().stringWidth(text3);
        g2d.drawString(text3, (getWidth() - text3Width) / 2, getHeight() - 100);
        BufferedImage splashImage = null;

        try {
            // Load the splash screen image
            splashImage = ImageIO.read(new File("resources/logo.png"));
            // Draw the image on the center of the panel
            g2d.drawImage(splashImage, getWidth()/2 - splashImage.getWidth()/2, getHeight() - splashImage.getHeight() , splashImage.getWidth(),  splashImage.getHeight(), null);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void displayTitleScreen(Graphics2D g2d){
        BufferedImage titleImage = null;
        try {
            // Load the splash screen image
            titleImage = ImageIO.read(new File("resources/title.png"));
            // Draw the image on the panel
            g2d.drawImage(titleImage, 0, 0, titleImage.getWidth(), titleImage.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Consolas", Font.TRUETYPE_FONT, 15));
        g2d.drawString("Press Space to Start", getWidth()/2 - 92, getHeight() - 50);
        
    }

    public void displayMenu(Graphics2D g2d) {
        Menu menu = GameConsole.getMenu();
        BufferedImage menuImage = null;
        try {
            // Load the menu image
            menuImage = ImageIO.read(new File("resources/title.png"));
            // Draw the image on the panel
            g2d.drawImage(menuImage, 0, 0, menuImage.getWidth(), menuImage.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int screenWidth = this.getWidth();
        int screenHeight = this.getHeight();
        int rectSize = 32;
        int rectSpacing = 10;
        int rectX = (screenWidth - (3 * rectSize + 2 * rectSpacing)) / 2;
        int rectY = (screenHeight) / 2;

        

        // Draw selection area around the first rectangle
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/player.png")).getSubimage(0, 0, 32, 32);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        g2d.drawImage(img, rectX - 30, rectY + menu.getIndex() * (rectSize + rectSpacing), img.getWidth(this), img.getHeight(this), this);

        // Draw text on the menu
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Consolas", Font.TRUETYPE_FONT, 15));
        g2d.drawString("New Game", rectX + 40, rectY + 20);
        g2d.drawString("Credits", rectX + 40, rectY + 20 + rectSize + rectSpacing);
        g2d.drawString("Exit", rectX + 40, rectY + 20 + 2 * (rectSize + rectSpacing));
    }

    public void petrifiedEnding(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Consolas", Font.TRUETYPE_FONT, 50));
        g2d.drawString("PITRIFIED", this.getWidth() / 2 - 150, this.getHeight() / 2);
    }

    public void diplayWinScreen(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Consolas", Font.TRUETYPE_FONT, 50));
        g2d.drawString("YOU WIN", this.getWidth() / 2 - 150, this.getHeight() / 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
       
        if (GameConsole.getState() == GameState.CREDITS) {
            displayCredits(g2d);
            return;
        }
        if (GameConsole.getState() == GameState.GAMEOVER) {
            drawGameOver(g2d);
            return;
        }
        if (GameConsole.getState() == GameState.WIN) {
            diplayWinScreen(g2d);
            return;
        }
        if (GameConsole.getState() == GameState.TITLESCREEN) {
            displayTitleScreen(g2d);
            return;
        }
        if (GameConsole.getState() == GameState.MENU) {
            displayMenu(g2d);
            return;
        }
        if (GameConsole.getState() == GameState.PITRIFIED) {
            petrifiedEnding(g2d);
            return;
        }
        if (GameConsole.getState() == GameState.GREEDY) {
            greedyEnding(g2d);
            return;
        }


        g2d.drawImage(background, 0, 0, this);
        drawAltar(g2d);
        drawEnmey(g2d);
        drawBridge(g2d);
        drawCoin(g2d);
        drawPlayer(g2d);
        drawTower(g2d);
        drawHealth(g2d);
        drawMoneyCounter(g2d);

        if (GameConsole.getState() == GameState.INALTAR) drawUpgradeScreen(g2d);
    }

    @Override
    public void run() {
        // game loop
        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();
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
