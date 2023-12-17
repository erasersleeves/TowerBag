package events;

import java.util.ArrayList;

import gameobjects.Enemy;

public class Wave {

    private ArrayList<Enemy> enemyList;

    public Wave(ArrayList<Enemy> enemyList){
        this.enemyList = enemyList;
    }

    public ArrayList<Enemy> getEnemyList(){
        return enemyList;
    }

    public Enemy isEnemyHere(int x, int y){
        for(int i=0; i < enemyList.size();i++){
            if(enemyList.get(i).getX() == x &&  enemyList.get(i).getY() == y)
                return enemyList.get(i);
        }
        return null;
    }

    public void advance(){
        for(int i=0; i< enemyList.size();i++){
            enemyList.get(i).advance();
        }
    }
}
