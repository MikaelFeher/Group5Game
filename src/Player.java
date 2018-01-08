public class Player {
    int playerX, playerY, playerSpeed;

    public Player() {
        playerY = 15;
        playerX = 50;
        playerSpeed = 2;
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

    public void reset() {
        playerY = 15;
        playerX = 50;
    }
}