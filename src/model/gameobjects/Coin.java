package model.gameobjects;
import model.world.Level;

public class Coin extends GameObject{
    private int value;

    public Coin(){
        //spawn at free tile (equals 0)
        int[][] arena = Level.getInstance().getArena();
        int x = (int) (Math.random() * arena[0].length);
        int y = (int) (Math.random() * arena.length);
        while (arena[y][x] != 0) {
            x = (int) (Math.random() * arena[0].length);
            y = (int) (Math.random() * arena.length);
        }
        this.x = x;
        this.y = y;
        this.value = 1;
    }

    public int getValue() {
        return value;
    }
}
