package world;

import gameobjects.Enemy;
import gameobjects.Player;
import gameobjects.Tower;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Level {
    private int height;
    private int width;
    private int bases;
    private int[][] arena = new int[height][width];
    private Enemy enemy;
    private Tower tower;
    private Player player;
    private static Level instance = null;

    public int tileSize = 16;

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

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getBases() {
        return bases;
    }

    public void setBases(int bases) {
        this.bases = bases;
    }

    public int[][] getArena() {
        return arena;
    }

    public void setArena(int[][] arena) {
        this.arena = arena;
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

    public static void setInstance(Level instance) {
        Level.instance = instance;
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

    public void printArena() {
        System.out.println("Health: " + player.getHealth());
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean elementFound = false;
                // check if there is a player at the current position
                if (player != null) {
                    if (player.getX()/tileSize == j && player.getY()/tileSize == i) {
                        System.out.print("P ");
                        elementFound = true;
                    }
                }

                // check if there is a tower at the current position
                if (tower != null) {
                    if (tower.getX() == j && tower.getY() == i) {
                        System.out.print("T ");
                        elementFound = true;
                    } else if (tower.getBullet() != null && tower.getBullet().getX() == j && tower.getBullet().getY() == i) {
                        System.out.print(". ");
                        elementFound = true;
                    }
                }

                // check if there is an enemy at the current position
                if (enemy != null) {
                    if (enemy.getX() == j && enemy.getY() == i) {
                        System.out.print("B ");
                        elementFound = true;
                    }
                }
                // print the arena otherwise
                if (!elementFound) {
                    // switch case to print out the arena
                    switch (arena[i][j]) {
                        case 0:
                            System.out.print("  ");
                            break;
                        case 1:
                            System.out.print("- ");
                            break;
                        case 2:
                            System.out.print("> ");
                            break;
                        case 3:
                            System.out.print("| ");
                            break;
                        case 4:
                            System.out.print("T ");
                            break;
                    }
                }

            }
            System.out.println();
        }
    }

    public boolean isSolid(int x, int y) {
        return (arena[y][x] == 1 || arena[y][x] == 3 || arena[y][x] == 4);
    }
}
