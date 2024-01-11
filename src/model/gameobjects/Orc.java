package model.gameobjects;

public class Orc extends Enemy {
    public Orc(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 3;
        this.speed = 1;
        this.damage = 1;
    }
}
