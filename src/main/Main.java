package main;

import gameobjects.*;
import world.*;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args)  {
        // level creation
        Level demo = Level.getInstance("resources/arena.txt");

        //create ememy list
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Bat(0, 0));
        enemies.add(new Bat(-3, 0));
        enemies.add(new Bat(0, 11));
        enemies.add(new Bat(-5, 11));
        enemies.add(new Bat(-9, 11));

        //create wave
        demo.setWave(new EnemyWave(enemies));
        
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
