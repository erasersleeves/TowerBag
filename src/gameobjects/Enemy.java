package gameobjects;
import world.Level;

import java.awt.*;

public abstract class Enemy extends GameObject {

    public abstract void advance();
    abstract void takeDamage(int damage);
    public abstract int getX();
    public abstract int getY();
    abstract void setLevel(Level level);

    public abstract int getHealth();
    public abstract Image getImage();
    public abstract boolean isAtBase();
    public abstract int getDamage();
}
