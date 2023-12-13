package gameobjects;
import world.Level;

public class Trunk extends Enemy {
    private int x;
    private int y;
    private boolean goingUp = false;
    private int health = 4;
    private final int speed = 1;
    private int damage = 1;
    public Trunk(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void advance() {
        
        if (x + 1 < level.getArena()[0].length && (level.getArena()[y][x + 1] == 1 || level.getArena()[y][x + 1] == 3)) {
            //go right
            x += speed;
            goingUp = false;
        } else if (y + 1 < level.getArena().length && !goingUp && (level.getArena()[y + 1][x] == 1 || level.getArena()[y + 1][x] == 3)) {
            //go down
            y += speed;   
        } else if (y - 1 >= 0 && (level.getArena()[y - 1][x] == 1 || level.getArena()[y - 1][x] == 3)) {
            //go up
            y -= speed;
            goingUp = true;
        } else if (x - 1 >= 0 && (level.getArena()[y][x - 1] == 1 || level.getArena()[y][x - 1] == 3)) {
            //go left
            x -= speed;
            goingUp = false;
        } 
        inflictDamage();
    }

    @Override
    public void inflictDamage() {
        if (level.getArena()[y][x] == 3){
            level.getPlayer().takeDamage(damage);
            //delete Trunk
            level.setEnemy(null);
        }
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {level.setEnemy(null);}
    }


    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    void setLevel(Level level) {
        this.level = level;
    }
}
