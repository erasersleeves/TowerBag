package world;

import gameobjects.Enemy;
import java.util.LinkedList;
import java.util.List;


public class EnemyWave {
    private List<Enemy> enemies;

    public EnemyWave(List<Enemy> enemies) {
        this.enemies = new LinkedList<>(enemies);
    }

    //get enemies
    public List<Enemy> getEnemies() {
        return enemies;
    }

    public boolean hasMoreEnemies() {
        return !enemies.isEmpty();
    }

    public void delete(Enemy enemy) {
        enemies.remove(enemy);
    }

}

