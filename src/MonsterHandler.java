import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.LinkedList;

public class MonsterHandler {
    private LinkedList<Monster> monsters = new LinkedList<>();

    private Monster tempMonster;

    public boolean collisionDetectionMonsterVsPlayer(Player player) {
        int pX = player.getX();
        int pY = player.getY();
        int mX, mY;

        for (int i = 0; i < monsters.size(); i++) {
            tempMonster = monsters.get(i);
            mX = tempMonster.getX();
            mY = tempMonster.getY();

            if (pX == mX && pY == mY) {
                return true;
            }
        }
        return false;
    }

    public void handleMovement(Player player) {
        int pX = player.getX();
        int pY = player.getY();

        for (int i = 0; i < monsters.size(); i++) {
            tempMonster = monsters.get(i);
            int diffX = getPositionDifference(pX, tempMonster.getX());
            int diffY = getPositionDifference(pY, tempMonster.getY());

            if (diffX == 0 && diffY < 0) {
                tempMonster.moveUp();
            } else if (diffX == 0 && diffY > 0) {
                tempMonster.moveDown();
            } else if (diffY == 0 && diffX < 0) {
                tempMonster.moveLeft();
            } else if (diffY == 0 && diffX > 0) {
                tempMonster.moveRight();
            } else {
                if (diffX < 0 && diffY < 0) {
                    tempMonster.moveLeft();
                    tempMonster.moveUp();
                } else if (diffX < 0 && diffY > 0) {
                    tempMonster.moveLeft();
                    tempMonster.moveDown();
                } else if (diffX > 0 && diffY < 0) {
                    tempMonster.moveRight();
                    tempMonster.moveUp();
                } else {
                    tempMonster.moveRight();
                    tempMonster.moveDown();
                }
            }
        }
    }

    private int getPositionDifference(int playerPos, int monsterPos) {
        return playerPos - monsterPos;
    }

    public void addMonster(Monster monster, Player player) {
        if (monster.getX() != player.getX() && monster.getY() != player.getY()) {
            this.monsters.add(monster);
        }
    }

    public void deleteAllMonsters() {
        this.monsters.clear();
    }

    public int numberOfMonstersInTheList() {
        return this.monsters.size();
    }

    public void renderMonsters(Screen screen) {
        for (int i = 0; i < monsters.size(); i++) {
            tempMonster = monsters.get(i);
            screen.putString(tempMonster.getX(), tempMonster.getY(), "M", Terminal.Color.RED, Terminal.Color.BLACK);
        }
        screen.refresh();
    }
}
