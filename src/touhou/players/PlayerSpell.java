package touhou.players;

import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.enemies.Boss;
import touhou.enemies.Enemy;

import javax.swing.*;
import java.awt.*;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell extends GameObject implements PhysicsBody {

    private BoxCollider boxCollider;
    Animation animation;
    private int  PlayerSpellDamage = 1;

    public PlayerSpell() {
        super();

        this.animation = new Animation(
                2,
                false, false,
                SpriteUtils.loadImage("assets/images/player-spells/a/0.png"),
                SpriteUtils.loadImage("assets/images/player-spells/a/1.png"),
                SpriteUtils.loadImage("assets/images/player-spells/a/2.png"),
                SpriteUtils.loadImage("assets/images/player-spells/a/3.png")
        );
        this.renderer = animation;

        this.boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(0, -10);
        hitEnemy();
        hitBoss();
        deactiveIfNeeded();
    }

    private void hitEnemy() {
        Enemy enemy = Physics.collideWith(this.boxCollider, Enemy.class);
        if(enemy != null){
            enemy.setEnemyHP(enemy.getEnemyHP() - PlayerSpellDamage);
            if(enemy.getEnemyHP() <= 0) {
                enemy.getHit(PlayerSpellDamage);
            }
            this.isActive = false;
        }
    }

    private void hitBoss() {
        Boss boss = Physics.collideWith(this.boxCollider, Boss.class);
        if(boss != null){
            boss.setHp(boss.getHp() - PlayerSpellDamage);
            if(boss.getHp() <= 0) {
                boss.getHit(PlayerSpellDamage);
            }
            this.isActive = false;
        }
    }

    private void deactiveIfNeeded() {
        if (this.screenPosition.y < 0) {
            this.isActive = false;
        }
    }

    public int getPlayerSpellDamage() {
        return PlayerSpellDamage;
    }

    public void setPlayerSpellDamage(int playerSpellDamage) {
        PlayerSpellDamage = playerSpellDamage;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
