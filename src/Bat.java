public class Bat extends Enemy{
    private int x;
    private int y;
    private boolean goingUp = false;
    private int health = 2;
    private final int speed = 1;
    private Level level;


    public Bat(int x, int y) {
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
        
        if (x + 1 < level.arena[0].length && level.arena[y][x + 1] == 1) {
            //go right
            x += speed;
            goingUp = false;
        } else if (y + 1 < level.arena.length && level.arena[y + 1][x] == 1 && !goingUp) {
            //go down
            y += speed;   
        } else if (y - 1 >= 0 && level.arena[y - 1][x] == 1) {
            //go up
            y -= speed;
            goingUp = true;
        } else if (x - 1 >= 0 && level.arena[y][x - 1] == 1) {
            //go left
            x -= speed;
            goingUp = false;
        } 
        inflictDamage();
    }

    @Override
    public void inflictDamage() {
        if (level.arena[y][x] == 3) level.player.takeDamage(damage); 
    }

    @Override
    public void takeDamage(float damage) {
        health -= damage;
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
