package gameobjects;
import world.Level;


public class Tower extends GameObject{
    private int x;
    private int y;
    private int range;
    private int damage;
    private float cooldown;
    private float cooldownRemaining;
    private Bullet bullet;

    private Level level = Level.getInstance();

    private boolean isLifted = false;
    public Tower(int x, int y, int range, int damage, int cooldown) {
        this.x = x;
        this.y = y;
        this.range = range;
        this.damage = damage;
        this.cooldown = cooldown;
        this.cooldownRemaining = 0;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void fire() {
        if (bullet == null || disTowBull() > range) bullet = new Bullet(x, y, 1, damage,null);
        bullet.advance();
        Enemy e = level.getEnemyWave().isEnemyHere(bullet.getX(),bullet.getY()); // getting the enemy with the same position as the bullet
        if (level.getEnemyWave().getEnemyList().size() != 0 && e != null) {
            e.takeDamage(damage);
            bullet = new Bullet(x, y, 1, damage,e);
        }
    }

    private int disTowBull() {
        // return distance between x and y of tower and bullet
        return Math.abs(bullet.getY()-y);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bullet getBullet() {
        return bullet;
    }
}
