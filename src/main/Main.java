package main;

import view.GamePanel;

import javax.swing.*;

import model.GameConsole;
import model.gameobjects.*;
import model.world.*;

import java.util.HashSet;
import java.util.Set;

public class Main {


    public static void main(String[] args)  {
        // level creation
        Level demo = Level.getInstance("resources/arena.txt");

        //create ememy list
        Set<Enemy> enemies = new HashSet<>();

        // //create wave
        demo.setWave(new EnemyWave(enemies));
        
        // tower creation
        demo.setTower(new Tower(15, 7));

        // player creation
        demo.setPlayer(new Player(3, 7));

        // coin creation
        demo.setCoin(new Coin());

        // altar creation
        demo.setAltar(new Altar());

        // stats creation

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
