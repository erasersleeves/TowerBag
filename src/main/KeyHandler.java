package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import gameobjects.*;
import world.Level;

public class KeyHandler implements KeyListener {
    // key listener for player movement using WASD
    Player player;
    Tower tower = Level.getInstance().getTower();

    public KeyHandler(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (GameConsole.isPaused) {
            switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                GameConsole.Pause();
                break;
            default:
                break;
            }
            return;
        } 

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.moveUp();
                tower.followPlayer();
                break;
            case KeyEvent.VK_S:
                player.moveDown();
                tower.followPlayer();
                break;
            case KeyEvent.VK_A:
                player.moveLeft();
                tower.followPlayer();
                break;
            case KeyEvent.VK_D:
                player.moveRight();
                tower.followPlayer();
                break;
            case KeyEvent.VK_SPACE:
                player.interact(tower);
                break;
            case KeyEvent.VK_ESCAPE:
                GameConsole.Pause();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}