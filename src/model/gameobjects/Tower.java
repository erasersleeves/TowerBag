package model.gameobjects;

import model.world.Level;

public class Tower extends GameObject{
    private int range;
    private int damage;
    private int cooldown;
    private int cooldownRemaining;

    private boolean isLifted = false;

    public Tower(int x, int y) {
        this.range = 3;
        this.damage = 1;
        this.cooldown = 4;
        this.cooldownRemaining = 0;
        this.x = x;
        this.y = y;
    }

    public boolean isInRange(Enemy target) {
        if (target != null) {
            int xDistance = Math.abs(target.getX() - x);
            int yDistance = Math.abs(target.getY() - y);
            return xDistance <= range && yDistance <= range;
        }
        return false;
    }

    public void fire(Enemy target) {
        if (!isLifted && cooldownRemaining <= 0) {
            target.takeDamage(damage);
            cooldownRemaining = cooldown;
        }
        cooldownRemaining--;
    }

    public void lift() {
        isLifted = true;
    }

    public void drop() {
        isLifted = false;
    }


    public boolean isLifted() {
        return isLifted;
    }

    public int getRange() {
        return range;
    }

    public void increaseRange() {
        range++;
    }

    public void increaseDamage() {
        damage++;
    }

    public void decreaseCooldown() {
        cooldown--;
    }

    public void followPlayer() {
        Player player = Level.getInstance().getPlayer();
        if (isLifted()) {
            x = player.getX();
            y = player.getY();
        }
    }
}
