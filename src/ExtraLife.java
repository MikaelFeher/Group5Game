import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.LinkedList;
import java.util.Random;

public class ExtraLife {
    private int lifeX, lifeY;
    private int duration;
    private Terminal.Color yellow = Terminal.Color.YELLOW;
    private Terminal.Color black = Terminal.Color.BLACK;
    private ExtraLife tempLife;
    private LinkedList<ExtraLife> extraLives = new LinkedList<>();

    public ExtraLife() {
        Random randomNumber = new Random();
        this.lifeX = randomNumber.nextInt((38))+31;
        this.lifeY = randomNumber.nextInt((18))+6;
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
        extraLives.add(new ExtraLife());
    }

    public void displayLivesInList() {
        System.out.println("Lives in list:" + extraLives.size());
        System.out.println("LifeX:" + extraLives.get(0).lifeX);
        System.out.println("LifeY:" + extraLives.get(0).lifeY);
    }
}
