import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.LinkedList;

public class MonsterHandler {
    public LinkedList<Monster> monsters = new LinkedList<>();

    private Monster tempMonster;

    // collisionDetectionMonsterVsPlayer();

     public void handleMovement(Player player) {
         int pX = player.getPlayerX();
         int pY = player.getPlayerY();

        for (int i = 0; i < monsters.size(); i++) {
            tempMonster = monsters.get(i);
            int diffX = getPositionDifference(pX, tempMonster.getMonsterX());
            int diffY = getPositionDifference(pY, tempMonster.getMonsterY());

            if (diffX == 0 && diffY < 0){
                tempMonster.moveUp();
            } else if(diffX == 0 && diffY > 0){
                tempMonster.moveDown();
            } else if(diffY == 0 && diffX < 0) {
                tempMonster.moveLeft();
            } else if(diffY == 0 && diffX > 0) {
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
        if (monster.getMonsterX() != player.getPlayerX() && monster.getMonsterY() != player.getPlayerY()){
            this.monsters.add(monster);
        }
    }

    public void deleteAllMonsters(){
        this.monsters.clear();
    }

    public int numberOfMonstersInTheList() {
        return this.monsters.size();
    }

    public void renderMonsters(Screen screen) {
        for (int i = 0; i < monsters.size(); i++) {
            tempMonster = monsters.get(i);
            screen.putString(tempMonster.getMonsterX(), tempMonster.getMonsterY(), "M", Terminal.Color.RED, Terminal.Color.BLACK);
        }
        screen.refresh();
    }

    // TEMPORARY for testing...
    public void displayMonsterX() {
        for (int i = 0; i < monsters.size(); i++) {
            tempMonster = monsters.get(i);
            System.out.println("MonsterX: " + tempMonster.getMonsterX());
        }
    }
}
