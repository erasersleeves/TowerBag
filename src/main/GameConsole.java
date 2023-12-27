package main;

import world.Level;
import gameobjects.*;

public class GameConsole {
    static Level level = Level.getInstance();

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void update() {
        // tower fires
        level.getTower().fire();
        // enemy advances
        if (level.getEnemy() != null ) level.getEnemy().advance();
        // player moves

        // player collects coin
        if (level.getCoin() != null) {
            if (level.getPlayer().reaches(level.getCoin())) {
                level.getPlayer().increaseMoney();
                level.setCoin(new Coin());
            }
        }

        // player upgrades tower
        if (!level.getTower().isLifted() && level.getTower().getX() == level.getAltar().getX() && level.getTower().getY() == level.getAltar().getY() && level.getPlayer().getMoney() >= 5) {
            level.getAltar().trigger();
            level.getAltar().upgrade(0);
            level.getPlayer().decreaseMoney(5);
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
                    } else if (level.getTower().getBullet() != null && level.getTower().getBullet().getX() == j && level.getTower().getBullet().getY() == i) {
                        System.out.print(". ");
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

                // check if there is an level.getEnemy() at the current position
                if (level.getEnemy() != null) {
                    if (level.getEnemy().getX() == j && level.getEnemy().getY() == i) {
                        System.out.print("B ");
                        elementFound = true;
                    }
                }
                // print the arena otherwise
                if (!elementFound) {
                    // switch case to print out the arena
                    switch (level.getArena()[i][j]) {
                        case 0:
                        case 4:
                        case 5:
                            System.out.print("  ");
                            break;
                        case 1:
                            System.out.print("- ");
                            break;
                        case 2:
                            System.out.print("> ");
                            break;
                        case 3:
                            System.out.print("| ");
                            break;
                    }
                }

            }
            System.out.println();
        }
        String enemyInfo = "";
        if (level.getEnemy() != null)  enemyInfo = " Enemy HP: " + level.getEnemy().getHealth();
        System.out.println("HP: " + level.getPlayer().getHealth() + " Money: " + level.getPlayer().getMoney() + " Range : " + level.getTower().getRange() + enemyInfo );
    }

    public static void loop() {
        while (true) {
            clearScreen();
            print();
            update();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
