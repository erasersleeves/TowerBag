package main;

import gameobjects.Bat;
import gameobjects.Player;
import gameobjects.Tower;
import world.Level;

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
