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

}