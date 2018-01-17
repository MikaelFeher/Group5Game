import java.util.Random;

public class Monster extends Character {
    //starts methods to set x-, y-position and speed for monster.
    public Monster() {
        setMonsterX();
        setMonsterY();
        setSpeed(1);

    }

    //generates x-value and sends it to super class
    public void setMonsterX() {
        Random randomNumber = new Random();
        int x = randomNumber.nextInt((38)) + 31;
        super.setX(x);
    }

    //generates y-value and sends it to super class
    public void setMonsterY() {
        Random randomNumber = new Random();
        int y = randomNumber.nextInt((18)) + 6;
        super.setY(y);
    }

}