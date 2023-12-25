package main;

import world.Level;

public class GameConsole {
    static Level level = Level.getInstance();

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void updateArena() {
        level.getTower().fire();
        if (level.getEnemy() != null ) level.getEnemy().advance();
    }
    public static void printArena() {
        System.out.println("Health: " + level.getPlayer().getHealth());
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
    }

    public static void loop() {
        while (true) {
            clearScreen();
            printArena();
            updateArena();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
