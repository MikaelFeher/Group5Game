import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class GameSounds {
    Clip themeMusic;

    public GameSounds(){
        try {
            themeMusic = AudioSystem.getClip();
//            Thread.sleep(clip.getMicrosecondLength()/1000);
        }catch (Exception e){
        }
    }
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
    public  void gameOver(){
        File game_over = new File("Sounds/gameOver.wav");
        soundPlayer(game_over);
    }

    public void gameMusic(){

        File themeMusic = new File("Sounds/themesong2.wav");
        themePlayer(themeMusic);
}

    public void stopMusic(){
    if (themeMusic.isOpen()){
        themeMusic.stop();
    }

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
    public void themePlayer(File Sound){
        try {
             themeMusic = AudioSystem.getClip();
            themeMusic.open(AudioSystem.getAudioInputStream(Sound));
            themeMusic.start();

//            Thread.sleep(clip.getMicrosecondLength()/1000);
        }catch (Exception e){
        }
    }


}
