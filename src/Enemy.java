public abstract class Enemy {
    int x;
    int y;
    int health;
    int speed;
    Level level;

    int damage;

    abstract void advance();
    abstract void takeDamage(float damage);
    abstract int getX();
    abstract int getY();
    abstract void setLevel(Level level);

    abstract void inflictDamage();       
    
}
