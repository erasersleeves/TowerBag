package model.world;


import java.util.Random;
import java.util.Set;
import java.util.HashSet;

import model.gameobjects.Bat;
import model.gameobjects.Enemy;
import model.gameobjects.Orc;


public class EnemyWave {
    private Set<Enemy> enemies;
    private static int countdown = 5;
    private static int waveNumber = 0;


    public EnemyWave(Set<Enemy> enemies) {
        this.enemies = new HashSet<>(enemies);
    }

    //get enemies
    public Set<Enemy> getEnemies() {
        return enemies;
    }

    public boolean noMoreEnemies() {
        return enemies.isEmpty();
    }

    public void delete(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void generateNextWave() {
        // countdown
        if (countdown > 0) {
            countdown--;
            return;
        }
        //generate next wave
        int numberOfEnemies = waveNumber * 4 + 2;
        for (int i = 0; i < numberOfEnemies; i++) {
            Random random = new Random();
            int spawn = random.nextInt(2) * (Level.getInstance().getHeight()-1); // spawn at top or bottom
            Enemy enemy = random.nextInt() % 4 == 0 ? new Orc(-8*i, spawn) : new Bat(-8*i, spawn) ;
            enemies.add(enemy);
        }
        waveNumber++;
        countdown = 5;
    }

    public int getWaveNumber() {
        return waveNumber;
    }

}

