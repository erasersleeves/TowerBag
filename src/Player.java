public class Player {
    private int health = 5;
    private int money = 0;
    private int score = 0;
    public int speed = 1;
    private int x ;
    private int y ;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //getters for x and y
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void takeDamage(float damage) {
        health -= damage;
    }



}
