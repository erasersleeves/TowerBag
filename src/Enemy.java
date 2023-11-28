public abstract class Enemy {
    int x;
    int y;
    int health;
    int speed;

    int damage;

    abstract void advance();
    abstract void takeDamage(float damage);
    abstract int getX();
    abstract int getY();

    /*void inflictDamage(){
        if (x == base.getX() && y == base.getY()){
            player.takeDamage(damage);
        }
    };*/
    
    
}
