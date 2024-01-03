package main;

public class Menu {
    private int index;
    private boolean mute;
    private int difficulty;
    private int scale;
    private int options = 2;

    public Menu() {
        index = 0;
        mute = false;
        difficulty = 0;
        scale = 0;
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

    public void mainSelect() {
        switch (index) {
            case 0:
                GameConsole.setState(GameState.INGAME);
                break;
            case 1:
                GameConsole.setState(GameState.INGAME);
                break;
            case 2:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public void newGameSelect(){

    }

    public void settingsSelect() {
        
    }

}