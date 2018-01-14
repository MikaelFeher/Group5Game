
public class Player extends Character {
    private int playerLife;

    //Sets position for player thought super-class constructor.
    public Player() {
        super(50, 15, 2);
        this.playerLife = 3;
    }

    //Reset player position after death.
    public void reset() {
        this.setY(15);
        this.setX(50);
    }

    public int getPlayerLife() {
        return playerLife;
    }

    public void playerLooseLife() {
        if (playerLife > 0) {
            playerLife--;
        }
    }

    public void playerWinLife() {
        if (playerLife < 3) {
            playerLife++;
        }
    }
}
