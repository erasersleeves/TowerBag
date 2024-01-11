package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.GameConsole;
import model.GameState;
import model.gameobjects.*;
import model.world.Level;

public class KeyHandler implements KeyListener {
    Player player = Level.getInstance().getPlayer();
    Tower tower = Level.getInstance().getTower();
    Altar altar = Level.getInstance().getAltar();
    Menu  menu = Menu.getInstance();

    public KeyHandler() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (GameConsole.getState() == GameState.CREDITS){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    GameConsole.setState(GameState.MENU);
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
                case KeyEvent.VK_UP:
                    menu.moveUp();
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    menu.moveDown();
                    break;
                case KeyEvent.VK_SPACE:
                case KeyEvent.VK_ENTER:
                    menu.select();
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
                case KeyEvent.VK_LEFT:
                    altar.decreaseIndex();
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    altar.increaseIndex();
                    break;
                case KeyEvent.VK_SPACE:
                case KeyEvent.VK_ENTER:
                    altar.upgrade();
                    GameConsole.setState(GameState.INGAME);
                    break;
                }
            return;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                player.moveUp();
                tower.followPlayer();
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                player.moveDown();
                tower.followPlayer();
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                player.moveLeft();
                tower.followPlayer();
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
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