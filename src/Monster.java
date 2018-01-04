import java.util.Random;
public class Monster {
    int monsterX, monsterY, monsterSpeed;
    public Monster() {
        Random randomNumber = new Random();
        monsterX = randomNumber.nextInt((38))+31;
        monsterY = randomNumber.nextInt((18))+6;
        monsterSpeed = randomNumber.nextInt((2))+1;
    }
    public void moveUp() {
        this.monsterY -= monsterSpeed;
    }
    public void moveDown() {
        this.monsterY += monsterSpeed;
    }
    public void moveLeft() {
        this.monsterX -= monsterSpeed;
    }
    public void moveRight() {
        this.monsterX += monsterSpeed;
    }
    public int getMonsterX() {
        return monsterX;
    }
    public int getMonsterY() {
        return monsterY;
    }
    public int getMonsterSpeed() {
        return monsterSpeed;
    }
}