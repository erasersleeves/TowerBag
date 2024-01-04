package model.gameobjects;

public class Bat extends Enemy {  

    public Bat(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 2;
        this.speed = 1;
        this.damage = 1;
    }

}
