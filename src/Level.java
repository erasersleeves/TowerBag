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

    public void printArena() {
        System.out.println("Health: " + player.getHealth());
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean elementFound = false;
                // check if there is a tower at the current position
                if (tower != null) {
                    if (tower.getX() == j && tower.getY() == i) {
                        System.out.print("T ");
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
}
