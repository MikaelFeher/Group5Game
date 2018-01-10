import com.googlecode.lanterna.screen.Screen;
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
            displayLivesInList();
            tempLife = extraLives.get(0);
            gameArea.screen.putString(tempLife.lifeX, tempLife.lifeY, "*", yellow, black);
            gameArea.update();
        }
    }

    public void addLife() {
        chanceToSpawn = randNumGen.nextInt(100);
        System.out.println("chanceToSpawn: " + chanceToSpawn);
        if (chanceToSpawn <= 10) {
            extraLives.add(new ExtraLife());
            System.out.println("EXTRA LIFE ADDED");
        }
    }

    public void displayLivesInList() {
        System.out.println("Lives in list:" + extraLives.size());
        System.out.println("LifeX:" + extraLives.get(0).lifeX);
        System.out.println("LifeY:" + extraLives.get(0).lifeY);
    }
}
