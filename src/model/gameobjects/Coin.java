package model.gameobjects;
import model.world.Level;

public class Coin extends GameObject{
    private int value;

    public Coin() {
        //spawn at free tile (equals 0)
        int[][] arena = Level.getInstance().getArena();
        int x, y;
        do {
            x = (int) (Math.random() * arena[0].length);
            y = (int) (Math.random() * arena.length);
        } while (arena[y][x] != 0);
        this.x = x;
        this.y = y;
        this.value = (Math.random() < 0.2) ? 3 : 1; // 1/5 chance for value 3, else 1
    }

    public int getValue() {
        return value;
    }
}
