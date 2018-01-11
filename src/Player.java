
public class Player extends Character {

    //Sets position for player thought super-class constructor.
    public Player() {
        super(50, 15, 2);
    }

    //Reset player position after death.
    public void reset() {
        int playerX, playerY;
        playerY = 15;
        playerX = 50;
    }
}
