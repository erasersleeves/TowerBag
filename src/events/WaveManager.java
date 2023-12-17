package events;

import java.util.ArrayList;
import java.util.Arrays;

import gameobjects.Bat;
import gameobjects.BigTrunk;
import gameobjects.Enemy;
import gameobjects.Ghost;
import gameobjects.Trunk;

public class WaveManager {
    private ArrayList<Wave> waves = new ArrayList<Wave>();
    public WaveManager(){
        createWaves();
    }

    private void createWaves() {  // Predifined waves
        creatBatWave();
        creatTrWave();
        creatGWave();
        creatBTrWave();
    }

    private void creatBatWave(){                    //Creating the BatWave = 10 Bats
        ArrayList<Enemy> BatWave = new ArrayList<>();
        for(int i=0 ;i < 10;i++){
            BatWave.add(new Bat(0, 0));
        }
        waves.add(new Wave(BatWave));
    }

    private void creatTrWave(){                     //Creating the TrunkWave = 5 Trunks
        ArrayList<Enemy> TrWave = new ArrayList<>();
        for(int i=0 ;i <5;i++){
            TrWave.add(new Trunk(0, 0));
        }
        waves.add(new Wave(TrWave));
    }

    private void creatGWave(){                     //Creating the GhostWave = 2 Ghosts
        ArrayList<Enemy> GWave = new ArrayList<>();
        for(int i=0 ;i <2;i++){
            GWave.add(new Ghost(0, 0));
        }
        waves.add(new Wave(GWave));
    }

    private void creatBTrWave(){                   //Creating the BigTrunkWave = 1 BigTrunk
        ArrayList<Enemy> BTrWave = new ArrayList<>();
        BTrWave.add(new BigTrunk(0, 0));
        waves.add(new Wave(BTrWave));
    }


    public ArrayList<Wave> getWaves(){
        return waves;
    }

    public Wave NextWave(){
        return waves.remove(0);
    }
}
