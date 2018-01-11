import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class GameSounds {


//    Make sound for steps
public void playerStepsSound(){
    File walk = new File("Sounds/walk.WAV");
    soundPlayer(walk);
}


//    Make sound for death
public void playerDeathSound(){
    File bite = new File("Sounds/bite.WAV");
    soundPlayer(bite);
}


//    Make sound for escCounter
public void escCounterSound() {
    File blipSound = new File("Sounds/blip.WAV");
    soundPlayer(blipSound);
}

public void escBoom(){
    File bomb = new File("Sounds/bomb.WAV");
    soundPlayer(bomb);
}


public void gameMusic(){
    File music = new File("Sounds/gameMusicLoop.wav");
    soundPlayer(music);
}


//    Make music during game

    public void soundPlayer(File Sound){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
//            Thread.sleep(clip.getMicrosecondLength()/1000);
        }catch (Exception e){
        }
    }


}