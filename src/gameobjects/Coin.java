package gameobjects;
import world.Level;

public class Coin extends GameObject{
    private int x;
    private int y;
    private int value;
    private Level level;

    public Coin(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }
}
