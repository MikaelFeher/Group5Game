public class Player extends Character {

    //Sets position for player thought super-class constructor.
    public Player() {
        super(15, 30, 2);
    }

    //Reset player position after death.
    public void reset() {
        int playerX, playerY;
        playerY = 15;
        playerX = 50;
    }
}
