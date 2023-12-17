import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import gameobjects.Player;

public class KeyHandler implements KeyListener {
    // key listener for player movement using WASD
    Player player = Player.getInstance();
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setY(player.getY()-1);
                break;
            case KeyEvent.VK_S:
                player.setY(player.getY()+1);
                break;
            case KeyEvent.VK_A:
                player.setX(player.getX()-1);
                break;
            case KeyEvent.VK_D:
                player.setX(player.getX()+1);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
