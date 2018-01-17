import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;


public class GameArea {

    private int leftBorder = 30, rightBorder = 70;
    private int topBorder = 5, bottomBorder = 25;
    private Terminal.Color green = Terminal.Color.GREEN;
    private Terminal.Color black = Terminal.Color.BLACK;
    private Terminal.Color yellow = Terminal.Color.YELLOW;
    private String borderCharacter = "*";
    private GameSounds gameSounds = new GameSounds();

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

        screen.putString(33, 27, " [N] NEW GAME             [Q] QUIT", green, black);

        update();
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
    }

    public void displayPlayerLives(Player player) {
        screen.putString(73, 8, "Lives: " + player.getPlayerLife(), yellow, black);
        update();
    }
    public void displayPlayerQuitMessage() {
        for (int i = 3; i >= 0; i--) {
            sleep(1000);
            gameSounds.escCounterSound();
            screen.clear();
            screen.putString(40, 12, "Thank you for playing!", yellow, black);
            screen.putString(29, 14, "This window will self destruct in " + i + " seconds", yellow, black);
            update();
        }
        screen.clear();
        screen.putString(47, 14, "Bye Bye!", yellow, black);
        update();
        sleep(1500);
        gameSounds.escBoom();
        screen.stopScreen();
        update();
        screen.stopScreen();

    }

    public void sleep(int milliSec) {
        try {
            Thread.sleep(milliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}