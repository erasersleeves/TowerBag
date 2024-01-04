package model.gameobjects;

import model.world.Level;

public abstract class Enemy extends GameObject {
    protected int health;
    protected int speed;
    protected boolean goingUp;
    protected int damage;


    public void advance() {
        if (x<0) {
            x++;
            return;
        } 
        if (y - 1 >= 0 && (Level.getInstance().getArena()[y - 1][x] == 1 || Level.getInstance().getArena()[y - 1][x] == 3)) {
            //go up
            y -= speed;
            goingUp = true;
        } else if (x + 1 < Level.getInstance().getArena()[0].length && (Level.getInstance().getArena()[y][x + 1] == 1 || Level.getInstance().getArena()[y][x + 1] == 3)) {
            //go right
            x += speed;
            goingUp = false;
        } else if (y + 1 < Level.getInstance().getArena().length && !goingUp && (Level.getInstance().getArena()[y + 1][x] == 1 || Level.getInstance().getArena()[y + 1][x] == 3)) {
            //go down
            y += speed;   
        } 
    }
    
    
    
    public boolean isAtBase() {
        if (x < 0 ) return false;
        if (x >= Level.getInstance().getWidth()-1) return true;
        return false;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

}
