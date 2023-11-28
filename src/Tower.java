public class Tower {
    private int x;
    private int y;
    private int range;
    private float damage;
    private float cooldown;
    private float cooldownRemaining;

    public Tower(int x, int y, int range, int damage, int cooldown) {
        this.x = x;
        this.y = y;
        this.range = range;
        this.damage = damage;
        this.cooldown = cooldown;
        this.cooldownRemaining = 0;
    }

    public void advance() {
        if (cooldownRemaining > 0) {
            cooldownRemaining--;
        }
    }

    public void attack(Enemy e) {
        if (cooldownRemaining == 0) {
            if (Math.abs(e.getX() - x) + Math.abs(e.getY() - y) <= range) {
                e.takeDamage(damage);
                cooldownRemaining = cooldown;
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
