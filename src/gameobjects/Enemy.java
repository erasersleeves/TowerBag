package gameobjects;
import world.Level;

import java.awt.*;

public abstract class Enemy extends GameObject {
    int x;
    int y;
    int health;
    int speed;
    Level level = Level.getInstance();

    int damage;

    public abstract void advance();
    abstract void takeDamage(int damage);
    public abstract int getX();
    public abstract int getY();
    abstract void setLevel(Level level);

    abstract void inflictDamage();
    
}
