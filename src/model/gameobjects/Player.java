package model.gameobjects;

import model.world.Level;

public class Player extends GameObject {
    private int health = 5;
    private int money = 0;
    public int speed = 1;
    private int kills = 0;
    private boolean hasMoved = false;



    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
    public void takeDamage(float damage) {
        health -= damage;
    }

	public int getHealth() {
		return health;
	}

    public void increaseKills() {
        kills++;
    }

    public int getKills() {
        return kills;
    }
 
    public void interact(Tower tower) {
        if (reaches(tower)) {
            if (tower.isLifted()) {
                tower.drop();
            } else {
                tower.lift();
            }
        }

    }

    public void moveUp() {
        if (!Level.getInstance().isSolid(x, y - speed)) {
            y -= speed;
            hasMoved = true;
        }
    }

    public void moveDown() {
        if (!Level.getInstance().isSolid(x, y + speed)) {
            y += speed;
            hasMoved = true;
        }
    }

    public void moveLeft() {
        if (!Level.getInstance().isSolid(x - speed, y)) {
            x -= speed;
            hasMoved = true;
        }
    }

    public void moveRight() {
        if (!Level.getInstance().isSolid(x + speed, y)) {
            x += speed;
            hasMoved = true;
        }
    }

    public void increaseMoney() {
        money++;
    }

    public int getMoney() {
        return money;
    }

    public void decreaseMoney(int i) {
        money -= i;
    }

    public boolean hasMoved() {
        return hasMoved;
    }
}
