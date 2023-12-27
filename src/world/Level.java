package world;

import gameobjects.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Level {
    private int height;
    private int width;
    private int[][] arena = new int[height][width];
    private Enemy enemy;
    private Tower tower;
    private Player player;
    private Coin coin;
    private Altar altar;
    private static Level instance = null;

    private Level(String filePath) {
        loadArena(filePath);
    }

    public static Level getInstance(String filePath) {
        if (instance == null) {
            instance = new Level(filePath);
        }
        return instance;
    }

    public static Level getInstance() {
        return instance;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


    public int[][] getArena() {
        return arena;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    //set width and height to the file dimensions
    private void setDimensions(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                if (lineCount == 0) width = line.length()/2 + 1;
                lineCount++;
            }
            height = lineCount;
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //fill arena with values from file
    public void loadArena(String filePath) {
        setDimensions(filePath);
        arena = new int[height][width];
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int row = 0;
            while ((line = bufferedReader.readLine()) != null) {
                for (int col = 0; col < line.length(); col += 2) { // increment by 2 to skip spaces
                    arena[row][col / 2] = Character.getNumericValue(line.charAt(col)); // divide by 2 to adjust for spaces
                }
                row++;
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isSolid(int x, int y) {
        return (arena[y][x] == 1 || arena[y][x] == 3);
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Coin getCoin() {
        return coin;
    }

    public Altar getAltar() {
        return altar;
    }

    public void setAltar(Altar altar) {
        this.altar = altar;
    }
}
