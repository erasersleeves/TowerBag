import java.util.Scanner;

public class Main {

    private static void printArena(Level demo) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
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
        Level demo = new Level(5, 10);
        demo.enemies = new Enemy[2];
        // first row of arena is all 1
        for (int i = 0; i < 7; i++) {
            demo.arena[0][i] = 1;
        }

        for (int i = 0; i < 2; i++) {
            demo.arena[i][6] = 1;
        }


        // last row of arena is all 1
        for (int i = 6; i < 10; i++) {
            demo.arena[2][i] = 1;
        }

        demo.arena[4][1] = 1;
        demo.arena[4][2] = 1;
        demo.arena[3][2] = 1;
        demo.arena[2][2] = 1;
        demo.arena[2][3] = 1;
        demo.arena[2][4] = 1;
        demo.arena[3][4] = 1;
        demo.arena[4][4] = 1;
        demo.arena[4][5] = 1;
        demo.arena[4][6] = 1;
        demo.arena[3][6] = 1;
        demo.arena[2][6] = 1;

        // base and spawn point
        demo.arena[0][0] = 2;
        demo.arena[4][0] = 2;
        demo.arena[2][9] = 3;
        
        
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
