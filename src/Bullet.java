public class Bullet {
    private int x;
    private int y;
    private int speed;
    private float damage;
    private Enemy target;

    public Bullet(int x, int y, int speed, float damage, Enemy target) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
    }

    public void advance() {
        y -= speed;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
