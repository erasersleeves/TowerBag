package model;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip c;
    URL soundURL[] = new URL[6];


    public Sound(){
        soundURL[0] = getClass().getResource("soundResource/footsteps.wav");
        soundURL[1] = getClass().getResource("soundResource/coin.wav");
        soundURL[2] = getClass().getResource("soundResource/select.wav");
        soundURL[3] = getClass().getResource("soundResource/towerLifted.wav");
        soundURL[4] = getClass().getResource("soundResource/payment.wav");
        soundURL[5] = getClass().getResource("soundResource/shoot.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream aud = AudioSystem.getAudioInputStream(soundURL[i]);
            c = AudioSystem.getClip();
            c.open(aud);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){
        c.start();
    }

}
