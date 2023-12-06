import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Level {
    int height;
    int width;
    int bases;
    int[][] arena = new int[height][width];
    Enemy enemy;
    Tower tower;
    Player player;

    public Level(String filePath) {
        loadArena(filePath);
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

    public boolean canPlaceTower(int x, int y) {
        return arena[y][x] == 0;
    }

    //getters for width and height
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBases() {
        return bases;
    }

    public int[][] getArena() {
        return arena;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Tower getTower() {
        return tower;
    }

    public Player getPlayer() {
        return player;
    }
}
