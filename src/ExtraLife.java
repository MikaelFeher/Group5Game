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

    public void renderLife(Screen screen) {
        tempLife = extraLives.get(0);
        screen.putString(tempLife.lifeX, tempLife.lifeY, "*", yellow, black);
    }

    public void addLife() {
        extraLives.add(new ExtraLife());
    }
}
