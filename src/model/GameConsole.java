package model;

import java.util.Iterator;

import controller.Menu;
import model.gameobjects.*;
import model.world.EnemyWave;
import model.world.Level;

public class GameConsole {
    static Level level = Level.getInstance();
    static Menu menu = new Menu();
    static private EnemyWave currentWave = level.getWave();
    static GameState state = GameState.TITLESCREEN;

    public static Menu getMenu() {
        return menu;
    }



    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void update() {
        if (state != GameState.INGAME) return;
        // enemy wave
        Iterator<Enemy> iterator = level.getWave().getEnemies().iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (enemy.isAtBase()) {
                level.getPlayer().takeDamage(enemy.getDamage());
                iterator.remove();
            }
            enemy.advance();
            Enemy target = null;
            if (level.getTower().isInRange(enemy)) target = enemy;
            if (target != null) {
                level.getTower().fire(target);
                if (target.getHealth() <= 0) {
                    // remove target
                    iterator.remove();
                    level.getPlayer().increaseKills();
                }
            }   
        }
            


        if (currentWave.noMoreEnemies()) {
            // All enemies in the current wave have been spawned.
            // Replace currentWave with a new wave...
            currentWave.generateNextWave();
        }

        // player collects coin
        if (level.getCoin() != null) {
            if (level.getPlayer().reaches(level.getCoin())) {
                level.getPlayer().increaseMoney();
                level.setCoin(new Coin());
            }
        }

        // trigger altar
        if (level.getPlayer().getMoney() >= level.getAltar().getFee()) level.getAltar().trigger(true);
        else level.getAltar().trigger(false);

        // player upgrades tower
        if (!level.getTower().isLifted() && level.getTower().reaches(level.getAltar()) && level.getPlayer().getMoney() >= level.getAltar().getFee()) {
            state = GameState.INALTAR;
        }

        // game over
        if (level.getPlayer().getHealth() <= 0) {
            if (!level.getPlayer().hasMoved()){
                state = GameState.PITRIFIED;
                return;
            }
            if (level.getPlayer().getKills() == 0 && level.getPlayer().getMoney() > 5) {
                state = GameState.GREEDY;
                return;
            }
            state = GameState.GAMEOVER;
        }
        
    }
    
    public static void print() {
        for (int i = 0; i < level.getHeight(); i++) {
            for (int j = 0; j < level.getWidth(); j++) {
                boolean elementFound = false;
                // check if there is a level.getPlayer() at the current position
                if (level.getPlayer() != null) {
                    if (level.getPlayer().getX() == j && level.getPlayer().getY() == i) {
                        System.out.print("P ");
                        elementFound = true;
                    }
                }

                // check if there is a level.getTower() at the current position
                if (level.getTower() != null) {
                    if (level.getTower().getX() == j && level.getTower().getY() == i) {
                        System.out.print("T ");
                        elementFound = true;
                    } 
                }

                // check if there is an level.getCoin() at the current position
                if (level.getCoin() != null) {
                    if (level.getCoin().getX() == j && level.getCoin().getY() == i) {
                        System.out.print("C ");
                        elementFound = true;
                    }
                }

                // iterate through the enemies and check if there is an enemy at the current position
                if (level.getWave() != null) {
                    for (Enemy enemy : level.getWave().getEnemies()) {
                        if (enemy.getX() == j && enemy.getY() == i) {
                            System.out.print("E ");
                            elementFound = true;
                        }
                    }
                }

                // check if there is an level.getAltar() at the current position
                if (level.getAltar() != null) {
                    if (level.getAltar().getX() == j && level.getAltar().getY() == i) {
                        System.out.print("A ");
                        elementFound = true;
                    }
                }
                // print the arena otherwise
                if (!elementFound) {
                    // switch case to print out the arena
                    switch (level.getArena()[i][j]) {
                        case 0:
                            System.out.print("  ");
                            break;
                        case 1:
                            System.out.print("- ");
                            break;
                        case 3:
                            System.out.print("# ");
                            break;
                        default:
                            System.out.print("  ");
                            break;
                    }
                }

            }
            System.out.println();
        }
        System.out.println("Kills : " + level.getPlayer().getKills() + " , wave : " + level.getWave().getWaveNumber() );
    }

    public static void loop() {
        while (true) {
            // clearScreen();
            // print();
            update();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static GameState getState() {
        return state;
    }

    public static void setState(GameState state) {
        GameConsole.state = state;
    }

}
