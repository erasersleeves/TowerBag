package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.GameConsole;
import model.GameState;
import model.Sound;
import model.gameobjects.*;
import model.world.Level;

public class KeyHandler implements KeyListener {
    // key listener for player movement using WASD
    Player player;
    Tower tower = Level.getInstance().getTower();
    Altar altar = Level.getInstance().getAltar();
    Menu  menu = GameConsole.getMenu();
    Sound sound = new Sound();
    public KeyHandler(Player player) {
        this.player = player;
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
                    GameConsole.playSound(2);
                    break;
                }
            return;
        }

        if (GameConsole.getState() == GameState.MENU) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    menu.moveUp();
                    GameConsole.playSound(2);
                    break;
                case KeyEvent.VK_S:
                    menu.moveDown();
                    GameConsole.playSound(2);
                    break;
                case KeyEvent.VK_SPACE:
                    menu.mainSelect();
                    GameConsole.playSound(2);
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
                    GameConsole.playSound(2);
                    break;
                case KeyEvent.VK_D:
                    altar.increaseIndex();
                    GameConsole.playSound(2);
                    break;
                case KeyEvent.VK_SPACE:
                    altar.upgrade();
                    GameConsole.setState(GameState.INGAME);
                    GameConsole.playSound(4);
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
                GameConsole.playSound(0);
                break;
            case KeyEvent.VK_S:
                player.moveDown();
                tower.followPlayer();
                GameConsole.playSound(0);
                break;
            case KeyEvent.VK_A:
                player.moveLeft();
                tower.followPlayer();
                GameConsole.playSound(0);
                break;
            case KeyEvent.VK_D:
                player.moveRight();
                tower.followPlayer();
                GameConsole.playSound(0);
                break;
            case KeyEvent.VK_SPACE:
                player.interact(tower);
                if(player.reaches(tower))
                GameConsole.playSound(3);
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