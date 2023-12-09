import gameobjects.Bat;
import gameobjects.Player;
import gameobjects.Tower;
import world.Level;

public class Main {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void main(String[] args) {
        // level creation
        Level demo = Level.getInstance("resources/arena.txt");

        
        // Create and add bats to the enemy array
        demo.setEnemy(new Bat(0, 0));

        // Rest of the code...

        // tower creation
        demo.setTower(new Tower(2, 3, 3, 1, 1));

        // player creation
        demo.setPlayer(new Player(2, 5));


        // game loop
        while (true) {
            clearScreen();
            System.out.println(demo.getPlayer().getX() + " " + demo.getPlayer().getY());
            demo.printArena();
            demo.getTower().fire();
            if (demo.getEnemy() != null ) demo.getEnemy().advance();
            try {
                Thread.sleep(1000); // pause for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
