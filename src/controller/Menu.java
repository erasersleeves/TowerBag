package controller;

import model.GameConsole;
import model.GameState;

public class Menu {
    private int index;
    private int options = 4;
    private static Menu instance = null;

    private Menu() {
        index = 0;
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public int getIndex() {
        return index;
    }

    public void setOptions(int options) {
        this.options = options;
    }

    public void moveUp() {
        index--;
        if (index < 0) {
            index = 0;
        }
    }

    public void moveDown() {
        index++;
        if (index > options) {
            index = options;
        }
    }

    public void select() {
        switch (index) {
            case 0:
                GameConsole.setState(GameState.INGAME);
                break;
            case 1:
                GameConsole.toggleMarathon();
                break;
            case 2:
                GameConsole.toggleSpeed();
                break;
            case 3:
                GameConsole.setState(GameState.CREDITS);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                break;
        }
    }

}