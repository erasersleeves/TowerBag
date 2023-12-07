public class Main {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void main(String[] args) {
        Level demo = new Level("src/arena.txt");

        
        // Create and add bats to the enemy array

        Bat bat1 = new Bat(0, 0);
        bat1.setLevel(demo);
        demo.enemy = bat1;

        // Rest of the code...

        // tower creation
        Tower tower = new Tower(2, 3, 3, 1, 1);
        tower.setLevel(demo);
        demo.tower = tower;

        // player creation
        demo.player = new Player(2, 5);


        // game loop
        while (true) {
            clearScreen();
            System.out.println(demo.player.getX() + " " + demo.player.getY());
            demo.printArena();
            demo.tower.fire();
            if (demo.enemy != null ) demo.enemy.advance();
            try {
                Thread.sleep(1000); // pause for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
