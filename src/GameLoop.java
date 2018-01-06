import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

public class GameLoop {
    Screen screen = TerminalFacade.createScreen();
    Player player;
    MonsterHandler monsterHandler;
    boolean gameOver;
    int playerX, playerY;
    int playerSpeed;
    private int minX = 30, maxX = 70;
    private int minY = 5, maxY = 25;

    public GameLoop() {
        init();
    }

    public void init() {
        player = new Player();
        monsterHandler = new MonsterHandler();
        gameOver = false;
        createStarterMonsters();
    }

    private void createStarterMonsters() {
        int numberOfMonsters = monsterHandler.numberOfMonstersInTheList();
        while (numberOfMonsters < 3) {
            monsterHandler.addMonster(new Monster(), player);
            numberOfMonsters = monsterHandler.numberOfMonstersInTheList();
            System.out.println(numberOfMonsters);
        }
    }

    public void run() {
        while (!gameOver) {
            screen.clear();
            gameArea();
            renderPlayer();
            monsterHandler.renderMonsters(screen);
            handleKeyPress();
            screen.refresh();
        }
        handleKeyPress();
    }

    private void handleKeyPress() {
        Key key = screen.readInput();
        while (key == null) {
            key = screen.readInput();
        }
        if (!gameOver) {
            switch (key.getKind()) {
                case ArrowUp:
                    handlePlayerMovement("up");
                    monsterHandler.handleMovement(player);
                    collisionDetection();
                    run();
                    break;
                case ArrowDown:
                    handlePlayerMovement("down");
                    monsterHandler.handleMovement(player);
                    collisionDetection();
                    run();
                    break;
                case ArrowLeft:
                    handlePlayerMovement("left");
                    monsterHandler.handleMovement(player);
                    collisionDetection();
                    run();
                    break;
                case ArrowRight:
                    handlePlayerMovement("right");
                    monsterHandler.handleMovement(player);
                    collisionDetection();
                    run();
                    break;
                case NormalKey:
                    handleNormalKeys(key);
                    break;
            }
        }
        switch (key.getKind()) {
            case NormalKey:
                handleNormalKeys(key);
                break;
        }
    }

    private void handleNormalKeys(Key key) {
        if (key.getCharacter() == 'n') {
            System.out.println("Reset knappen tryckt");
            resetGame();
        } else if (key.getCharacter() == 'q') {
            gameOver = true;
            screen.clear();
            screen.putString(40, 12, "Thank you for playing!", Terminal.Color.YELLOW, Terminal.Color.BLACK);
            screen.putString(29, 14, "This window will self destruct in 4 seconds", Terminal.Color.YELLOW, Terminal.Color.BLACK);
            screen.refresh();
            try {
                Thread.sleep(4000);
                screen.stopScreen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (key.getCharacter() == 'd') {
            monsterHandler.deleteAllMonsters();
            run();
        }
    }

    private void collisionDetection() {
        gameOver = monsterHandler.collisionDetectionMonsterVsPlayer(player);
        if (gameOver) {
            gameOver();
        }
    }

    private void handlePlayerMovement(String direction) {
        getPlayerPositionAndSpeed();
        switch (direction) {
            case "up":
                if (playerY - playerSpeed <= minY) {
                    player.setPlayerY(minY + 1);
                } else {
                    player.moveUp();
                }
                break;
            case "down":
                if (playerY + playerSpeed >= maxY) {
                    player.setPlayerY(maxY - 1);
                } else {
                    player.moveDown();
                }
                break;
            case "left":
                if (playerX - playerSpeed <= minX) {
                    player.setPlayerX(minX + 1);
                } else {
                    player.moveLeft();
                }
                break;
            case "right":
                if (playerX + playerSpeed >= maxX) {
                    player.setPlayerX(maxX - 1);
                } else {
                    player.moveRight();
                }
                break;
        }
        // For testing purposes
        getPlayerPositionAndSpeed();
        System.out.println("playerX: " + playerX);
        System.out.println("playerY: " + playerY);
    }

    private void getPlayerPositionAndSpeed() {
        playerX = player.getPlayerX();
        playerY = player.getPlayerY();
        playerSpeed = player.getPlayerSpeed();
    }

    private void renderPlayer() {
        getPlayerPositionAndSpeed();
        screen.putString(playerX, playerY, "X", Terminal.Color.MAGENTA, Terminal.Color.BLACK);
        screen.refresh();
    }

    private void gameOver() {
        gameOver = true;
        screen.clear();
        gameArea();
        screen.putString(46, 15, "GAME OVER", Terminal.Color.RED, Terminal.Color.BLACK);
        screen.refresh();
    }

    private void resetGame() {
        init();
        run();
    }

    //Temporary rendering of gamearea until the gamearea class is implemented...
    public void gameArea() {

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
//Player

//        screen.putString(xPlayer,yPlayer,"X", Terminal.Color.MAGENTA, Terminal.Color.BLACK);
        screen.refresh();

    }
}

//    private void frameCollisionDetection() {
//        getPlayerPosition();
//
//        if (playerX == minX || playerX == maxX || playerY == minY || playerY == maxY) {
//            gameOver = true;
//            screen.clear();
//            gameArea();
//            screen.putString(46, 15, "GAME OVER", Terminal.Color.RED, Terminal.Color.BLACK);
//            screen.refresh();
//            run();
//        }
//    }
