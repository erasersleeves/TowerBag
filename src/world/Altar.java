package world;

import gameobjects.Tower;

public class Altar {
    private int x;
    private int y;
    private boolean triggered = false;
    Level level = Level.getInstance();

    public Altar() {
        //spawn at arena tile that is equal to 5
        for (int i = 0; i < Level.getInstance().getArena().length; i++) {
            for (int j = 0; j < Level.getInstance().getArena()[0].length; j++) {
                if (Level.getInstance().getArena()[i][j] == 5) {
                    x = j;
                    y = i;
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


    public void trigger(){
        triggered = true;
    }

    public boolean isTriggered() {
        return triggered;
    }

}