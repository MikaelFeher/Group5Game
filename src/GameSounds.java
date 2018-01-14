import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class GameSounds {
    Clip themeMusic;

    public void playerStepsSound() {
        File walk = new File("Sounds/walk.WAV");
        soundPlayer(walk);
    }
    public void playerDeathSound() {
        File bite = new File("Sounds/bite.WAV");
        soundPlayer(bite);
    }
    public void escCounterSound() {
        File blipSound = new File("Sounds/blip.WAV");
        soundPlayer(blipSound);
    }
    public void escBoom() {
        File bomb = new File("Sounds/bomb.WAV");
        soundPlayer(bomb);
    }
    public void gameOver() {
        File game_over = new File("Sounds/gameOver.wav");
        soundPlayer(game_over);
    }
    public void extraLife() {
        File extraLife = new File("Sounds/takeLife.wav");
        soundPlayer(extraLife);
    }
    public void stopMusic() {
        if (themeMusic.isOpen()) {
            themeMusic.stop();
        }
    }
    public void gameMusic() {
        File fileThemeMusic = new File("Sounds/themesong2.wav");
        themePlayer(fileThemeMusic);
    }
    public GameSounds() {
        try {
            themeMusic = AudioSystem.getClip();
//            Thread.sleep(clip.getMicrosecondLength()/1000);
        } catch (Exception e) {
        }
    }
    // Players
    public void soundPlayer(File Sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();

//            Thread.sleep(clip.getMicrosecondLength()/1000);
        } catch (Exception e) {
        }
    }
    public void themePlayer(File Sound) {
        try {
            themeMusic = AudioSystem.getClip();
            themeMusic.open(AudioSystem.getAudioInputStream(Sound));
            themeMusic.start();
        } catch (Exception e) {
        }
    }

}
