import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.Random;

import static com.googlecode.lanterna.screen.ScreenCharacterStyle.Blinking;
import static com.googlecode.lanterna.screen.ScreenCharacterStyle.Bold;
import static com.googlecode.lanterna.screen.ScreenCharacterStyle.Underline;
import static com.googlecode.lanterna.screen.ScreenCharacterStyle.Reverse;

public class GameArea {

    private int leftBorder = 30,rightBorder = 70;
    private int topBorder = 5,bottomBorder =25;
    private Terminal.Color green = Terminal.Color.GREEN;
    private Terminal.Color black = Terminal.Color.BLACK;
    private Terminal.Color yellow = Terminal.Color.YELLOW;
    private Terminal.Color cyan = Terminal.Color.CYAN;
    private Terminal.Color red = Terminal.Color.RED;
    private String borderCharacter = "*";

    public int getTopBorder() {
        return topBorder;
    }

    public int getBottomBorder() {
        return bottomBorder;
    }

    public int getLeftBorder() {
        return leftBorder;
    }

    public int getRightBorder() {
        return rightBorder;
    }

    Screen screen = TerminalFacade.createScreen();

    public void render() {
        screen.startScreen();
        screen.setCursorPosition(null);
        screen.putString(45, 2, "THE GAME-NAME", yellow, black, ScreenCharacterStyle.Underline);

//      GameArea Left and right
        for (int i = leftBorder; i <= rightBorder; i += 2) {
            screen.putString(i, topBorder, borderCharacter, green, black);
            screen.putString(i, bottomBorder, borderCharacter, green, black);
        }

//      GameArea over and under
        for (int j = topBorder; j <= bottomBorder; j++) {
            screen.putString(leftBorder, j, borderCharacter, green, black);
            screen.putString(rightBorder, j, borderCharacter, green, black);
        }

        screen.putString(30, 27, "[N] NEW GAME  [I] INSTRUCTIONS  [Q] QUIT", green, black);
//        displayGameInstructions();
        update();
    }

    public void displayGameInstructions(boolean show) {
        if (show) {
            screen.putString(1, 8, "Instructions", cyan, black, Bold, Underline);
            screen.putString(1, 10, "Use arrow keys to move.", cyan, black);
            screen.putString(1, 12, "M", red, black);
            screen.putString(2, 12, " - Monster. RUN AWAY", cyan, black);
            screen.putString(1, 14, "*", yellow, black);
            screen.putString(2, 14, " - Extra life. Hurry up...", cyan, black);
            update();
        } else {
            render();
        }
    }

    public void update() {
        screen.refresh();
    }

    public void gameAreaReset() {
        screen.clear();
        render();
    }

    public void displayCurrentHighScore(int highScore) {
        screen.putString(78, 6, "HIGH SCORE: " + highScore, yellow, black);
    }

    public void displayPlayerScore(int playerScore) {
        screen.putString(73, 6, "Score: " + playerScore, yellow, black);
        update();
    }
    public void displayPlayerLives(Player player) {
        screen.putString(73, 8, "Lives: " + player.getPlayerLife(), yellow, black);
        update();
    }

    public void displayPlayerDeathMessage(Player player) {
        displayPlayerScore(player.getPlayerLife());
        try {
            for (int j = 6; j < 25; j++){
                for (int i = 31; i < 70; i++) {
                    screen.putString(i, j, " ", red, red);
                }
            }
            screen.putString(38, 14, " Oh no, a monster got you! ", red, black);
            screen.putString(44, 16, " " + player.getPlayerLife() + " lives left ", red, black);
            update();
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        screen.clear();
        render();
    }

    public void displayPlayerQuitMessage() {
        screen.clear();
        screen.putString(40, 12, "Thank you for playing!", yellow, black);
        screen.putString(29, 14, "This window will self destruct in 4 seconds", yellow, black);
        update();
        try {
            for(int i = 3; i >= 0; i--) {
                Thread.sleep(1000);
                screen.putString(29, 14, "This window will self destruct in " + i + " seconds", yellow, black);
                update();
            }
            screen.clear();
            screen.putString(47, 14, "Bye Bye!", yellow, black);
            update();
            Thread.sleep(1500);
            screen.stopScreen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}