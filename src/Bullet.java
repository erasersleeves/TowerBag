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

            if (Math.sqrt(Math.pow(Math.abs(target.getX() - x) + Math.abs(target.getY() - y),2)) <= speed) {
                target.takeDamage(damage);
            } else {
                if (target.getX() > x) {
                    x += speed;
                } else if (target.getX() < x) {
                    x -= speed;
                }
                if (target.getY() > y) {
                    y += speed;
                } else if (target.getY() < y) {
                    y -= speed;
                }
            }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
