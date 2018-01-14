import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public class GameLoop {
    // VARIABLES
    private Player player;
    private MonsterHandler monsterHandler;
    private GameArea gameArea = new GameArea();
    private GameSounds gameSounds = new GameSounds();
    private ExtraLife extraLife = new ExtraLife();

    // Removes the need for extra methods in the gameArea class... Check handleKeyPress();


    // Removes the need for extra methods in the gameArea class...

    Screen screen = gameArea.screen;

    boolean gameOver;
    int playerX, playerY, playerSpeed;
    int playerScore, highScore;


    // GAME AREA BORDERS
    private int leftBorder = gameArea.getLeftBorder(), rightBorder = gameArea.getRightBorder();
    private int topBorder = gameArea.getTopBorder(), bottomBorder = gameArea.getBottomBorder();


    // CONSTRUCTOR
    public GameLoop() {
        init();
    }

    // GAME INITIALIZER
    public void init() {
        gameSounds.stopMusic();
        gameSounds.gameMusic();
        player = new Player();
        playerScore = 0;
        extraLife.removeAllExtraLives();
        monsterHandler = new MonsterHandler();
        gameOver = false;
        gameArea.gameAreaReset();
        createStarterMonsters();
    }

    // ACTUAL GAME LOOP
    public void run() {
        while (!gameOver) {
            screen.clear();
            renderGameAssets();
            handleKeyPress();
            gameArea.update();
        }
        handleKeyPress();
    }

    // GAME ASSETS - Game area, players, monsters, displaying scores...
    private void renderGameAssets() {
        gameArea.render();
        gameArea.displayPlayerScore(playerScore);
        gameArea.displayPlayerLives(player);
        renderPlayer();
        addStuffBasedOnPlayerScore();
        monsterHandler.renderMonsters(screen);
        extraLife.renderLife(gameArea);
    }

    // HANDLING PLAYER INPUT
    private void handleKeyPress() {
        Key key = screen.readInput();
        while (key == null) {
            key = screen.readInput();
        }
        if (!gameOver) {
            switch (key.getKind()) {
                case ArrowUp:
                    gameSounds.playerStepsSound();
                    handlePlayerMovement("up");
                    monsterHandler.handleMovement(player);
                    pickUpExtraLives();
                    collisionDetection();
                    run();
                    break;
                case ArrowDown:
                    gameSounds.playerStepsSound();
                    handlePlayerMovement("down");
                    monsterHandler.handleMovement(player);
                    pickUpExtraLives();
                    collisionDetection();
                    run();
                    break;
                case ArrowLeft:
                    gameSounds.playerStepsSound();
                    handlePlayerMovement("left");
                    monsterHandler.handleMovement(player);
                    pickUpExtraLives();
                    collisionDetection();
                    run();
                    break;
                case ArrowRight:
                    gameSounds.playerStepsSound();
                    handlePlayerMovement("right");
                    monsterHandler.handleMovement(player);
                    pickUpExtraLives();
                    collisionDetection();
                    run();
                    break;
                case NormalKey:
                    handleNormalKeys(key);
                    break;
            }
        }else {
            switch (key.getKind()) {
                case NormalKey:
                    handleNormalKeys(key);
                    break;
            }
        }
    }

    private void handleNormalKeys(Key key) {
        if (key.getCharacter() == 'n') {
            resetGame();
        } else if (key.getCharacter() == 'q') {
            gameSounds.stopMusic();
            gameOver = true;
            gameArea.displayPlayerQuitMessage();
        } else if (key.getCharacter() == 'd') {
            monsterHandler.deleteAllMonsters();
            run();
        }
    }

    // COLLISION DETECTION
    private void collisionDetection() {
        boolean collision = monsterHandler.collisionDetectionMonsterVsPlayer(player);
        if (collision && player.getPlayerLife() == 1) {
            gameOver();
        } else if (collision && player.getPlayerLife() > 0) {
            gameSounds.playerDeathSound();
            player.playerLooseLife();
            player.reset();
        }
    }


    // STUFF HAPPENS BASED ON PLAYER SCORE SECTION
    private void addStuffBasedOnPlayerScore() {
        if (playerScore > 20 && playerScore % 5 == 0) {
            monsterHandler.addMonster(new Monster(), player);
            extraLife.addLife();
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
                    player.setY(topBorder + 1);
                } else {
                    player.moveUp();
                }
                break;
            case "down":
                if (playerY + playerSpeed >= bottomBorder) {
                    player.setY(bottomBorder - 1);
                } else {
                    player.moveDown();
                }
                break;
            case "left":
                if (playerX - playerSpeed <= leftBorder) {
                    player.setX(leftBorder + 1);
                } else {
                    player.moveLeft();
                }
                break;
            case "right":
                if (playerX + playerSpeed >= rightBorder) {
                    player.setX(rightBorder - 1);
                } else {
                    player.moveRight();
                }
                break;
        }
        playerScore++;
        extraLife.decreaseDuration();
    }

    private void getPlayerPositionAndSpeed() {
        playerX = player.getX();
        playerY = player.getY();
        playerSpeed = player.getSpeed();
    }

    private void renderPlayer() {
        getPlayerPositionAndSpeed();
        screen.putString(playerX, playerY, "X", Terminal.Color.MAGENTA, Terminal.Color.BLACK);
        gameArea.update();
    }


    private void pickUpExtraLives() {
        boolean pickingUpLives = extraLife.collisionDetectionPlayerVsExtraLife(player);
        if (pickingUpLives) {
            gameSounds.extraLife();
            player.playerWinLife();
        }
    }


    // HELPER METHODS
    private void gameOver() {
        gameSounds.stopMusic();
        boolean newHighScore = calculatingHighScore();
        gameSounds.gameOver();
        gameOver = true;
        screen.clear();
        gameArea.render();
        screen.putString(46, 14, "GAME OVER", Terminal.Color.RED, Terminal.Color.BLACK);
        screen.putString(40, 16, "Your final score: " + playerScore, Terminal.Color.RED, Terminal.Color.BLACK);
        if (newHighScore) {
            screen.putString(77, 8, "NEW HIGH SCORE!!!", Terminal.Color.YELLOW, Terminal.Color.BLACK);
        }
        gameArea.displayCurrentHighScore(highScore);
        gameArea.update();
    }

    private void resetGame() {
        init();
        run();
    }

    private boolean calculatingHighScore() {
        if (playerScore > highScore) {
            highScore = playerScore;
            return true;
        }
        return false;
    }
}
