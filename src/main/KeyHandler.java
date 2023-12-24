package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import gameobjects.Player;
import world.Level;

public class KeyHandler implements KeyListener {
    // key listener for player movement using WASD
    Player player;

    public KeyHandler(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                if (Level.getInstance().isSolid(player.getX(), player.getY()-1)) {
                    break;
                }
                player.setY(player.getY()-1);
                break;
            case KeyEvent.VK_S:
                if (Level.getInstance().isSolid(player.getX(), player.getY()+1)) {
                    break;
                }
                player.setY(player.getY()+1);
                break;
            case KeyEvent.VK_A:
                if (Level.getInstance().isSolid(player.getX()-1, player.getY())) {
                    break;
                }
                player.setX(player.getX()-1);
                break;
            case KeyEvent.VK_D:
                if (Level.getInstance().isSolid(player.getX()+1, player.getY())) {
                    break;
                }
                player.setX(player.getX()+1);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}