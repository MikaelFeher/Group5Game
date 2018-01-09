public class Player {
    private int playerX, playerY, playerSpeed, playerLife;

    public Player() {
        playerY = 15;
        playerX = 50;
        playerSpeed = 2;
        playerLife = 3;
    }

    public void moveUp() {
        this.playerY -= playerSpeed;
    }

    public void moveDown() {
        this.playerY += playerSpeed;
    }

    public void moveLeft() {
        this.playerX -= playerSpeed;
    }

    public void moveRight() {
        this.playerX += playerSpeed;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public void reset() {
        playerY = 15;
        playerX = 50;
    }

    public void playerLooseLife() {
        playerLife--;
    }

    public void playerWinLife() {
        playerLife++;
    }
}
