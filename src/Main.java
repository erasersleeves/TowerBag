import java.util.Scanner;

public class Main {

    private static void printArena(Level demo) {
        System.out.println("Health: " + demo.player.getHealth());
        for (int i = 0; i < demo.height; i++) {
            for (int j = 0; j < demo.width; j++) {
                boolean elementFound = false;
                // check if there is a tower at the current position
                if (demo.tower != null) {
                    if (demo.tower.getX() == j && demo.tower.getY() == i) {
                        System.out.print("T ");
                        elementFound = true;
                    }
                }

                // check if there is an enemy at the current position
                if (demo.enemy != null) {
                    if (demo.enemy.getX() == j && demo.enemy.getY() == i) {
                        System.out.print("B ");
                        elementFound = true;
                    }
                }
                // print the arena otherwise
                if (!elementFound) {
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
                        case 4:
                            System.out.print("T ");
                            break;
                    }
                }

            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Level demo = new Level("src/arena.txt");

        
        // Create and add bats to the enemy array

        Bat bat1 = new Bat(0, 0);
        bat1.setLevel(demo);
        demo.enemy = bat1;

        // Rest of the code...
        
        Scanner scanner = new Scanner(System.in);
        int cmd = 0;

        // tower creation
//        Tower tower = new Tower(4, 3, 2, 1, 1);
//        demo.tower = tower;

        // player creation
        Player player = new Player(0, 0);
        demo.player = player;

        while (cmd != 1 && demo.enemy != null) {
            demo.enemy.advance();
            printArena(demo);
            System.out.println("press 0 to continue, 1 to exit");
            cmd = scanner.nextInt();
        }
        printArena(demo);
        scanner.close();
    }
}
