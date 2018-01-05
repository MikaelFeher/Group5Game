import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.LinkedList;

public class MonsterHandler {
    public LinkedList<Monster> monsters = new LinkedList<>();

    private Monster tempMonster;

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
