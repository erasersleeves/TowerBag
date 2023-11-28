public class Bat extends Enemy{
    private int x;
    private int y;
    private int health = 2;
    private final int speed = 1;

    public Bat(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void advance() {
        x += speed;
    }
    @Override
    public void takeDamage(float damage) {
        health -= damage;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    /*@Override
    void inflictDamage(int damage) {

    }*/

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }
}
