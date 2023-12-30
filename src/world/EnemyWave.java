package world;

import gameobjects.Bat;
import gameobjects.Enemy;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class EnemyWave {
    private List<Enemy> enemies;
    private static int waveNumber = 0;

    public EnemyWave(List<Enemy> enemies) {
        this.enemies = new LinkedList<>(enemies);
    }

    //get enemies
    public List<Enemy> getEnemies() {
        return enemies;
    }

    public boolean noMoreEnemies() {
        return enemies.isEmpty();
    }

    public void delete(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void generateNextWave() {
        //generate next wave
        int numberOfEnemies = waveNumber * 4 + 2;
        for (int i = 0; i < numberOfEnemies; i++) {
            Random random = new Random();
            int spawn = random.nextInt(2) * 12; // spawn at top or bottom
            enemies.add(new Bat(-4*i, spawn));
        }
        waveNumber++;
    }

    public int getWaveNumber() {
        return waveNumber;
    }

}

