package world;

import gameobjects.Tower;

public class Altar {
    private int x;
    private int y;
    private boolean triggered = false;
    Level level = Level.getInstance();

    public Altar() {
        //altar is a 3x3 block of 5s, spawn at the middle of the block
        for (int i = 0; i < level.getArena().length; i++) {
            for (int j = 0; j < level.getArena()[0].length; j++) {
                if (level.getArena()[i][j] == 5) {
                    x = j-1;
                    y = i-1;
                }
            }
        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void upgrade(int choice){
        Tower tower = level.getTower();
        switch (choice){
            case 0:
                //increase tower's range
                tower.increaseRange();
                break;
            case 1:
                //increase tower's damage
                tower.increaseDamage();
                break;
            case 2:
                //decrease tower's cooldown
                tower.decreaseCooldown();
                break;
        }
    }


    public void trigger(boolean b){
        triggered = b;
    }

    public boolean isTriggered() {
        return triggered;
    }

}