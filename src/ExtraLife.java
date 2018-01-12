import com.googlecode.lanterna.terminal.Terminal;

import java.util.LinkedList;
import java.util.Random;

public class ExtraLife {
    private int lifeX, lifeY;
    private int duration, chanceToSpawn;
    private Terminal.Color yellow = Terminal.Color.YELLOW;
    private Terminal.Color black = Terminal.Color.BLACK;
    private ExtraLife tempLife;
    private Random randNumGen = new Random();

    private LinkedList<ExtraLife> extraLives = new LinkedList<>();

    public ExtraLife() {
        this.lifeX = randNumGen.nextInt((38))+31;
        this.lifeY = randNumGen.nextInt((18))+6;
        this.duration = 30;
    }

    public void renderLife(GameArea gameArea) {
        if (extraLives.size() != 0) {
            for (int i = 0; i < extraLives.size(); i++) {
                tempLife = extraLives.get(i);
                gameArea.screen.putString(tempLife.lifeX, tempLife.lifeY, "*", yellow, black);
                gameArea.update();
            }
        }
    }

    public void addLife() {
        chanceToSpawn = randNumGen.nextInt(100);
        if (chanceToSpawn <= 10) {
            extraLives.add(new ExtraLife());
            System.out.println("EXTRA LIFE ADDED");
        }
    }

    public void decreaseDuration() {
        if (extraLives.size() != 0) {
            for (int i = 0; i < extraLives.size(); i++) {
                tempLife = extraLives.get(i);
                tempLife.duration--;
            }
        }
        removeExtraLifeFromListIfNoDuration();
    }

    private void removeExtraLifeFromListIfNoDuration() {
        for (int i = 0; i < extraLives.size(); i++) {
            tempLife = extraLives.get(i);
            if (tempLife.duration < 1) {
                extraLives.remove(tempLife);
            }
        }
    }

    public void removeAllExtraLives() {
        for (int i = 0; i < extraLives.size(); i++) {
            tempLife = extraLives.get(i);
            extraLives.remove(tempLife);
        }
    }
}
