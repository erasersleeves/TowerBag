import java.util.Scanner;

public class Main {

    private static void printArena(Level demo) {
        for (int i = 0; i < demo.height; i++) {
            for (int j = 0; j < demo.width; j++) {
                boolean enemyFound = false;
                for (int k = 0; k < demo.enemies.length; k++) {
                    int y = demo.enemies[k].getY();
                    int x = demo.enemies[k].getX();
                    if (x == j && y  == i) {
                        System.out.print("B ");
                        enemyFound = true;
                        break;
                    }
                }
                if (!enemyFound) {
                    // switch case to print out the arena
                    switch (demo.arena[i][j]) {
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
                    }
                }

            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Level demo = new Level("src/arena.txt");
        demo.enemies = new Enemy[2];
        
        // Create and add bats to the enemy array

        Bat bat1 = new Bat(0, 0);
        bat1.setLevel(demo);
        demo.enemies[0] = bat1;

        Bat bat2 = new Bat(0, 4);
        bat2.setLevel(demo);
        demo.enemies[1] = bat2;

        // Rest of the code...
        
        Scanner scanner = new Scanner(System.in);
        int cmd = 0;

        // player creation

        while (cmd != 1) {
            printArena(demo);
            for (int i = 0; i < demo.enemies.length; i++) {
                demo.enemies[i].advance();
            }
            System.out.println("press 0 to continue, 1 to exit");
            cmd = scanner.nextInt();
        }
        scanner.close();
    }
}
