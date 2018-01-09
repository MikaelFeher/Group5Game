import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

public class GameLoop {
    // VARIABLES
    Player player;
    MonsterHandler monsterHandler;
    GameArea gameArea = new GameArea();
    boolean gameOver;
    int playerX, playerY, playerSpeed;

    // GAME AREA BORDERS
    private int leftBorder = 30, rightBorder = 70;
    private int topBorder = 5, bottomBorder = 25;

    // CONSTRUCTOR
    public GameLoop() {
        init();
    }

    // GAME INITIALIZER
    public void init() {
        player = new Player();
        monsterHandler = new MonsterHandler();
        gameOver = false;
        gameArea.gameAreaReset();
        createStarterMonsters();
    }

    // ACTUAL GAME LOOP
    public void run() {
        while (!gameOver) {
            gameArea.screen.clear();
            gameArea.render();
            renderPlayer();
            monsterHandler.renderMonsters(gameArea.screen);
            handleKeyPress();
            gameArea.update();
        }
        handleKeyPress();
    }

    // HANDLING PLAYER INPUT
    private void handleKeyPress() {
        Key key = gameArea.screen.readInput();
        while (key == null) {
            key = gameArea.screen.readInput();
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
            resetGame();
        } else if (key.getCharacter() == 'q') {
            gameOver = true;
            gameArea.screen.clear();
            gameArea.screen.putString(40, 12, "Thank you for playing!", Terminal.Color.YELLOW, Terminal.Color.BLACK);
            gameArea.screen.putString(29, 14, "This window will self destruct in 4 seconds", Terminal.Color.YELLOW, Terminal.Color.BLACK);
            gameArea.update();
            try {
                Thread.sleep(4000);
                gameArea.screen.stopScreen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (key.getCharacter() == 'd') {
            monsterHandler.deleteAllMonsters();
            run();
        }
    }

    // COLLISION DETECTION
    private void collisionDetection() {
        boolean collision = monsterHandler.collisionDetectionMonsterVsPlayer(player);
        if (collision && player.getPlayerLife() > 0) {
            player.playerLooseLife();
        } else if (collision && player.getPlayerLife() == 0) {
            gameOver();
        }
    }

    // MONSTER SECTION
    private void createStarterMonsters() {
        int numberOfMonsters = monsterHandler.numberOfMonstersInTheList();
        while (numberOfMonsters < 3) {
            monsterHandler.addMonster(new Monster(), player);
            numberOfMonsters = monsterHandler.numberOfMonstersInTheList();
        }
    }

    // PLAYER SECTION
    private void handlePlayerMovement(String direction) {
        getPlayerPositionAndSpeed();
        switch (direction) {
            case "up":
                if (playerY - playerSpeed <= topBorder) {
                    player.setPlayerY(topBorder + 1);
                } else {
                    player.moveUp();
                }
                break;
            case "down":
                if (playerY + playerSpeed >= bottomBorder) {
                    player.setPlayerY(bottomBorder - 1);
                } else {
                    player.moveDown();
                }
                break;
            case "left":
                if (playerX - playerSpeed <= leftBorder) {
                    player.setPlayerX(leftBorder + 1);
                } else {
                    player.moveLeft();
                }
                break;
            case "right":
                if (playerX + playerSpeed >= rightBorder) {
                    player.setPlayerX(rightBorder - 1);
                } else {
                    player.moveRight();
                }
                break;
        }
    }

    private void getPlayerPositionAndSpeed() {
        playerX = player.getPlayerX();
        playerY = player.getPlayerY();
        playerSpeed = player.getPlayerSpeed();
    }

    private void renderPlayer() {
        getPlayerPositionAndSpeed();
        gameArea.screen.putString(playerX, playerY, "X", Terminal.Color.MAGENTA, Terminal.Color.BLACK);
        gameArea.update();
    }

    // HELPER METHODS
    private void gameOver() {
        gameOver = true;
        gameArea.screen.clear();
        gameArea.render();
        gameArea.screen.putString(46, 15, "GAME OVER", Terminal.Color.RED, Terminal.Color.BLACK);
        gameArea.update();
    }

    private void resetGame() {
        init();
        run();
    }
}
