package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import gameobjects.*;
import world.Level;

public class KeyHandler implements KeyListener {
    // key listener for player movement using WASD
    Player player;
    Tower tower = Level.getInstance().getTower();

    int tileSize = Level.getInstance().tileSize;

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
                if (Level.getInstance().isSolid(player.getX()/tileSize, (player.getY()- player.speed)/tileSize)) {
                    break;
                }
                player.setY(player.getY()-player.speed);
                if (tower.isLifted()) {
                    tower.setY(tower.getY()-player.speed);
                }
                break;
            case KeyEvent.VK_S:
                if (Level.getInstance().isSolid(player.getX()/tileSize, (player.getY()+player.speed)/tileSize)) {
                    break;
                }
                player.setY(player.getY()+player.speed);
                if (tower.isLifted()) {
                    tower.setY(tower.getY()+player.speed);
                }
                break;
            case KeyEvent.VK_A:
                if (Level.getInstance().isSolid((player.getX()-player.speed)/tileSize, player.getY()/tileSize)) {
                    break;
                }
                player.setX(player.getX()-player.speed);
                if (tower.isLifted()) {
                    tower.setX(tower.getX()-player.speed);
                }
                break;
            case KeyEvent.VK_D:
                if (Level.getInstance().isSolid((player.getX()+player.speed)/tileSize, player.getY()/tileSize)) {
                    break;
                }
                player.setX(player.getX()+player.speed);
                if (tower.isLifted()) {
                    tower.setX(tower.getX()+player.speed);
                }
                break;
            case KeyEvent.VK_SPACE:
                if (player.reaches(tower) && !tower.isLifted()) {
                    tower.lift();
                } else if (tower.isLifted()) {
                    tower.drop();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}