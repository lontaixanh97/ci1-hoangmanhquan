package touhou.scenes;

import bases.Constraints;
import bases.GameObject;
import tklibs.SpriteUtils;
import touhou.enemies.Boss;
import touhou.enemies.EnemySpawner;
import touhou.inputs.InputManager;
import touhou.players.Player;
import touhou.settings.Settings;


/**
 * Created by huynq on 8/19/17.
 */
public class Level1Scene {
    Player player = new Player();
    EnemySpawner enemySpawner = new EnemySpawner(); // TODO: Viec cua lop: sua thanh game object     7


    Settings settings = Settings.instance;

    public void init() {
        addBackground();
        addPlayer();
        addBoss();
    }

    private void addBoss() {
        Boss boss = new Boss();
        boss.getPosition().set(
                settings.getGamePlayWidth()/2,
                settings.getGamePlayWidth()/5);
        GameObject.add(boss);

    }

    private void addBackground() {
        GameObject.add(new Background());
    }

    private void addPlayer() {
        player.setInputManager(InputManager.instance);
        player.setContraints(new Constraints(
                settings.getWindowInsets().top,
                settings.getGamePlayHeight(),
                settings.getWindowInsets().left,
                settings.getGamePlayWidth())
        );
        player.getPosition().set(
                settings.getGamePlayWidth() / 2,
                settings.getGamePlayHeight() * 3 / 4);

        GameObject.add(player);
    }
}
