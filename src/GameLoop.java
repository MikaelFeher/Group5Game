import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.LinkedList;

public class GameLoop {
    Screen screen = TerminalFacade.createScreen();
    Player player = new Player();
    boolean gameOver = false;
    int playerX, playerY;
    int playerSpeed = player.getPlayerSpeed();
    int minX = 30;
    int maxX = 70;
    int minY = 5;
    int maxY = 25;

    public GameLoop() {
    }

    /********************** MONSTER TEST AREA ****************************/



    int[] monsters = new int[10];

    public void renderMonster() {
        Monster monster1 = new Monster();
        Monster monster2 = new Monster();
        Monster monster3 = new Monster();

        monster1.getMonsterX();
        monster1.getMonsterY();
        monster1.getMonsterSpeed();
        monster2.getMonsterX();
        monster2.getMonsterY();
        monster2.getMonsterSpeed();
        monster3.getMonsterX();
        monster3.getMonsterY();
        monster3.getMonsterSpeed();


//        monsters[0] = "firstMonster";

//        for(int i = 0; i < monsters.length; i++) {
//            System.out.println("monster: " + monsters[i].getMonsterX());
//        }

//        Object test = monsters[0];
        System.out.println(monsters[0]);
    }








    /********************** MONSTER TEST AREA End ************************/

    public void run(){
        while(!gameOver) {
            screen.clear();
            gameArea();
            renderPlayer();
            renderMonster();
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
        switch (key.getKind()) {
            case ArrowUp:
                handlePlayerMovement("up");
                //Monster.moveUp();
                run();
                break;
            case ArrowDown:
                handlePlayerMovement("down");
                //Monster.moveDown();
                run();
                break;
            case ArrowLeft:
                handlePlayerMovement("left");
                //Monster.moveLeft();
                run();
                break;
            case ArrowRight:
                handlePlayerMovement("right");
                //Monster.moveRight();
                run();
                break;
            case NormalKey:
                if (key.getCharacter() == 'n'){
                    System.out.println("Reset knappen tryckt");
                    player.reset();
                    gameOver = false;
                    run();
                } else if (key.getCharacter() == 'q') {
                    gameOver = true;
                    screen.clear();
                    screen.putString(40, 12, "Thank you for playing!", Terminal.Color.YELLOW, Terminal.Color.BLACK);
                    screen.putString(29, 14, "This window will self destruct in 4 seconds", Terminal.Color.YELLOW, Terminal.Color.BLACK);
                    screen.refresh();
                    try{
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    screen.stopScreen();
                }
                break;
        }
    }

    private void handlePlayerMovement(String direction) {
        getPlayerPosition();
        switch (direction) {
            case "up":
                if (!(playerY <= minY + playerSpeed)){
                    player.moveUp();
                }
                break;
            case "down":
                if (!(playerY >= maxY - playerSpeed)) {
                    player.moveDown();
                }
                break;
            case "left":
                if(!(playerX <= minX + playerSpeed)){
                    player.moveLeft();
                }
                break;
            case "right":
                if (!(playerX >= maxX - playerSpeed)) {
                    player.moveRight();
                }
                break;
        }
        // For testing purposes
        getPlayerPosition();
        System.out.println("playerX: " + playerX);
        System.out.println("playerY: " + playerY);
    }

    private void getPlayerPosition() {
        playerX = player.getPlayerX();
        playerY = player.getPlayerY();
    }

    private void renderPlayer() {
        getPlayerPosition();
        screen.putString(playerX,playerY,"X", Terminal.Color.MAGENTA, Terminal.Color.BLACK);
        screen.refresh();
    }

    //Temporary rendering of gamearea until the gamearea class is implemented...
    public void gameArea(){

        screen.startScreen();
        screen.setCursorPosition(null);
        screen.putString(45,2,"THE GAME-NAME", Terminal.Color.YELLOW, Terminal.Color.BLACK, ScreenCharacterStyle.Underline);

//      GameArea Left and right

        for (int i =30; i <=70; i+=2){
            screen.putString(i,5,"*", Terminal.Color.GREEN, Terminal.Color.BLACK);
            screen.putString(i,25,"*", Terminal.Color.GREEN, Terminal.Color.BLACK);
        }

//      GameArea over and under

        for (int j = 5; j<=25; j++){
            screen.putString(30,j,"*", Terminal.Color.GREEN, Terminal.Color.BLACK);
            screen.putString(70,j,"*", Terminal.Color.GREEN, Terminal.Color.BLACK);
        }

        screen.putString(33,27," [N] NEW GAME             [Q] QUIT", Terminal.Color.GREEN, Terminal.Color.BLACK);
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
