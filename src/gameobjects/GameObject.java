package gameobjects;

import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected int y;

    public boolean reaches(GameObject other) {
        if (other.getX() == this.x && other.getY() == this.y) System.out.println("reaches");
        return (other.getX() == this.x && other.getY() == this.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
