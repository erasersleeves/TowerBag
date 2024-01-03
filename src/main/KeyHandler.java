package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import gameobjects.*;
import world.Level;

public class KeyHandler implements KeyListener {
    // key listener for player movement using WASD
    Player player;
    Tower tower = Level.getInstance().getTower();
    Altar altar = Level.getInstance().getAltar();
    Menu  menu = GameConsole.getMenu();

    public KeyHandler(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (GameConsole.getState() == GameState.SPLASHSCREEN) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    GameConsole.setState(GameState.TITLESCREEN);
                    break;
                }
            return;
        }

        if (GameConsole.getState() == GameState.TITLESCREEN) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    GameConsole.setState(GameState.MENU);
                    break;
                }
            return;
        }

        if (GameConsole.getState() == GameState.MENU) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    menu.moveUp();
                    break;
                case KeyEvent.VK_S:
                    menu.moveDown();
                    break;
                case KeyEvent.VK_SPACE:
                    menu.mainSelect();
                    break;
                }
            return;
        }

        if (GameConsole.getState() == GameState.PAUSED) {
            switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                GameConsole.setState(GameState.INGAME);
                break;
            default:
                break;
            }
            return;
        } 

        if (GameConsole.getState() == GameState.INALTAR) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    altar.decreaseIndex();
                    break;
                case KeyEvent.VK_D:
                    altar.increaseIndex();
                    break;
                case KeyEvent.VK_SPACE:
                    altar.upgrade();
                    GameConsole.setState(GameState.INGAME);
                    break;
                }
            return;
        }

        if (GameConsole.getState() == GameState.GAMEOVER) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE:
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
                GameConsole.setState(GameState.PAUSED);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}