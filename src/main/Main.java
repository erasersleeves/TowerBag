package main;

import gameobjects.*;
import world.*;

import javax.swing.*;

public class Main {


    public static void main(String[] args)  {
        // level creation
        Level demo = Level.getInstance("resources/arena.txt");

        // Create and add bats to the enemy array
        demo.setEnemy(new Bat());
        
        // tower creation
        demo.setTower(new Tower());

        // player creation
        demo.setPlayer(new Player());

        // coin creation
        demo.setCoin(new Coin());

        // altar creation
        demo.setAltar(new Altar());

        // GUI creation
        JFrame window = new JFrame("Tower Defense");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel gp = GamePanel.getInstance();
        window.setContentPane(gp);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
        gp.startGame();




        // game loop
        GameConsole.loop();
    }

}
