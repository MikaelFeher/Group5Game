import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

public class GameArea {

    Screen screen = TerminalFacade.createScreen();

    public void render() {
        screen.startScreen();
        screen.setCursorPosition(null);
        screen.putString(45, 2, "THE GAME-NAME", Terminal.Color.YELLOW, Terminal.Color.BLACK, ScreenCharacterStyle.Underline);

//      GameArea Left and right
        for (int i = 30; i <= 70; i += 2) {
            screen.putString(i, 5, "*", Terminal.Color.GREEN, Terminal.Color.BLACK);
            screen.putString(i, 25, "*", Terminal.Color.GREEN, Terminal.Color.BLACK);
        }

//      GameArea over and under
        for (int j = 5; j <= 25; j++) {
            screen.putString(30, j, "*", Terminal.Color.GREEN, Terminal.Color.BLACK);
            screen.putString(70, j, "*", Terminal.Color.GREEN, Terminal.Color.BLACK);
        }

        screen.putString(33, 27, " [N] NEW GAME             [Q] QUIT", Terminal.Color.GREEN, Terminal.Color.BLACK);

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
        screen.putString(79, 6, "HIGH SCORE: " + highScore, Terminal.Color.YELLOW, Terminal.Color.BLACK);
    }

    public void displayPlayerScore(int playerScore) {
        screen.putString(73, 6, "Score: " + playerScore, Terminal.Color.YELLOW, Terminal.Color.BLACK);
    }

    public void displayPlayerQuitMessage() {
        screen.clear();
        screen.putString(40, 12, "Thank you for playing!", Terminal.Color.YELLOW, Terminal.Color.BLACK);
        screen.putString(29, 14, "This window will self destruct in 4 seconds", Terminal.Color.YELLOW, Terminal.Color.BLACK);
        update();
        try {
            Thread.sleep(1000);
            screen.putString(29, 14, "This window will self destruct in 3 seconds", Terminal.Color.YELLOW, Terminal.Color.BLACK);
            update();
            Thread.sleep(1000);
            screen.putString(29, 14, "This window will self destruct in 2 seconds", Terminal.Color.YELLOW, Terminal.Color.BLACK);
            update();
            Thread.sleep(1000);
            screen.putString(29, 14, "This window will self destruct in 1 seconds", Terminal.Color.YELLOW, Terminal.Color.BLACK);
            update();
            Thread.sleep(1000);
            screen.clear();
            screen.putString(47, 14, "Bye Bye!", Terminal.Color.YELLOW, Terminal.Color.BLACK);
            update();
            Thread.sleep(1500);
            screen.stopScreen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}