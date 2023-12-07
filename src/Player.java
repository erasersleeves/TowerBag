import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener{
    private int health = 5;
    private int money = 0;
    private int score = 0;
    public int speed = 1;
    private int x ;
    private int y ;
    private Level level;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // key listener for player movement using WASD

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                y -= speed;
                break;
            case KeyEvent.VK_S:
                y += speed;
                break;
            case KeyEvent.VK_A:
                x -= speed;
                break;
            case KeyEvent.VK_D:
                x += speed;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

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

	public int getHealth() {
		return health;
	}



}
