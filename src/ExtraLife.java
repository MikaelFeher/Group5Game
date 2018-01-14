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
        this.lifeX = randNumGen.nextInt((38)) + 31;
        this.lifeY = randNumGen.nextInt((18)) + 6;
        this.duration = 30;
    }

    public void renderLife(GameArea gameArea) {
        if (extraLives.size() != 0) {
            displayLivesInList();
            for (int i = 0; i < extraLives.size(); i++) {
                tempLife = extraLives.get(i);
                gameArea.screen.putString(tempLife.lifeX, tempLife.lifeY, "*", yellow, black);
                gameArea.update();
            }
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

    public void decreaseDuration() {
        if (extraLives.size() != 0) {
            for (int i = 0; i < extraLives.size(); i++) {
                tempLife = extraLives.get(i);
                tempLife.duration--;
                System.out.println("templife " + i + " duration: " + tempLife.duration);
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

    public boolean collisionDetectionPlayerVsExtraLife(Player player) {
        int pX = player.getX();
        int pY = player.getY();
        int pSpeed = player.getSpeed();
        int lifeX, lifeY;
        boolean pickUpMovingLeft, pickUpMovingRight, pickUpMovingUp, pickUpMovingDown;

        for (int i = 0; i < extraLives.size(); i++) {
            tempLife = extraLives.get(i);
            lifeX = tempLife.getLifeX();
            lifeY = tempLife.getLifeY();
            // Player move right
            pickUpMovingRight = (pX + pSpeed == lifeX && pY == lifeY) || (pX + pSpeed == lifeX + 1 && pY == lifeY);
            // Player move left
            pickUpMovingLeft = (pX - pSpeed == lifeX && pY == lifeY) || (pX - pSpeed == lifeX - 1 && pY == lifeY);
            // Player move up
            pickUpMovingUp = (pY - pSpeed == lifeY && pX == lifeX) || (pY - pSpeed == lifeY - 1 && pX == lifeX);
            // Player move down
            pickUpMovingDown = (pY + pSpeed == lifeY && pX == lifeX) || (pY + pSpeed == lifeY + 1 && pX == lifeX);

            if (pickUpMovingLeft || pickUpMovingRight || pickUpMovingUp || pickUpMovingDown) {
                extraLives.remove(tempLife);
                return true;
            }
        }
        return false;
    }

    public int getLifeX() {
        return lifeX;
    }

    public int getLifeY() {
        return lifeY;
    }

    public void removeAllExtraLives() {
        extraLives.clear();
    }
}
