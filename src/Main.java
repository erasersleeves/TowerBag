import java.util.Scanner;

public class Main {
    
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


        // game loop
        while (cmd != 1 && demo.enemy != null) {
            demo.printArena();
            demo.enemy.advance();
            System.out.println("press 0 to continue, 1 to exit");
            cmd = scanner.nextInt();
        }
        scanner.close();
    }
}
