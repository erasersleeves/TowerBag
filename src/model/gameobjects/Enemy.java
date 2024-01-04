package model.gameobjects;

import java.util.ArrayList;
import java.util.Random;

import model.world.Level;

public abstract class Enemy extends GameObject {
    protected int health;
    protected int speed;
    protected int damage;
    protected boolean goingUp = false;
    protected boolean goingDown = false;


    public void advance() {

        int[][] arena = Level.getInstance().getArena();
        ArrayList<Direction> validDirections = new ArrayList<Direction>();
        if (x<0) {
            x++;
            return;
        } 


        if (y - 1 >= 0 && !goingDown && (arena[y - 1][x] == 1 || arena[y - 1][x] == 3)) {
            validDirections.add(Direction.UP);
        } 
         if (x + 1 < arena[0].length && (arena[y][x + 1] == 1 || arena[y][x + 1] == 3)) {
            validDirections.add(Direction.RIGHT);

        } 
         if (y + 1 < arena.length  && !goingUp && (arena[y + 1][x] == 1 || arena[y + 1][x] == 3)) {
            validDirections.add(Direction.DOWN);
        } 
        if (validDirections.size() > 0) {
            Random rand = new Random();
            Direction direction = validDirections.get(rand.nextInt(validDirections.size()));

            switch (direction) {
                case UP:
                    y -= speed;
                    goingUp = true;
                    goingDown = false;
                    break;
                case RIGHT:
                    x += speed;
                    goingDown = false;
                    goingUp = false;
                    break;
                case DOWN:
                    y += speed;
                    goingUp = false;
                    goingDown = true;
                    break;
                default:
                    break;
            }
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
