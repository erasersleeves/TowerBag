package model.gameobjects;

import model.world.Level;

public class Altar extends GameObject {
    private boolean triggered = false;
    private int index;
    private int fee = 3;

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

    public int getFee() {
        return fee;
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
                tower.increaseRange();
                break;
            case 1:
                tower.increaseDamage();
                break;
            case 2:
                tower.decreaseCooldown();
                break;
        }
        player.decreaseMoney(fee);
        fee += 3;
        tower.lift();
    }

    public void trigger(boolean b){
        triggered = b;
    }

    public boolean isTriggered() {
        return triggered;
    }

}