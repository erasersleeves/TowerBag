package main;

import gameobjects.Enemy;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;


public class EnemyWave {
    private Queue<Enemy> enemies;

    public EnemyWave(List<Enemy> enemies) {
        this.enemies = new LinkedList<>(enemies);
    }

    public Enemy getNextEnemy() {
        return enemies.poll();
    }

    //get enemies
    public Queue<Enemy> getEnemies() {
        return enemies;
    }

    public boolean hasMoreEnemies() {
        return !enemies.isEmpty();
    }

}

