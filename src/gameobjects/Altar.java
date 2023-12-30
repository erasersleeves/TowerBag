package gameobjects;

import world.Level;

public class Altar extends GameObject {
    private boolean triggered = false;
    private int index;

    public Altar() {
        //altar is a 3x3 block of 5s, spawn at the middle of the block
        for (int i = 0; i < Level.getInstance().getArena().length; i++) {
            for (int j = 0; j < Level.getInstance().getArena()[0].length; j++) {
                if (Level.getInstance().getArena()[i][j] == 5) {
                    x = j-1;
                    y = i-1;
                }
            }
        }
        index = 1;
    }

    public int getIndex() {
        return index;
    }

    public void increaseIndex() {
        if (index < 2) {
            index++;
        }
    }

    public void decreaseIndex() {
        if (index > 0) {
            index--;
        }
    }


    public void upgrade(){
        Player player = Level.getInstance().getPlayer();
        Tower tower = Level.getInstance().getTower();
        switch (index){
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
        player.decreaseMoney(3);
    }


    public void trigger(boolean b){
        triggered = b;
    }

    public boolean isTriggered() {
        return triggered;
    }

}