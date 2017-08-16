package touhou.players;

import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.enemies.Enemy;
import touhou.enemies.EnemyBullet;

import java.awt.*;
import java.util.Vector;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell extends GameObject implements PhysicsBody{
    private BoxCollider boxCollider;
    private int  PlayerSpellDamage = 3;

    public PlayerSpell() {
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/player-spells/a/0.png"));
        this.setPlayerSpellDamage(1);
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(0, -10);
        hitEnemy();
        hitEnemyBullet();
    }

    private void hitEnemy() {
       Enemy enemy =  Physics.collideWithEnemy(this.boxCollider);
       if(enemy != null){
           enemy.setEnemyHP(enemy.getEnemyHP() - PlayerSpellDamage);
           if(enemy.getEnemyHP() == 0) {
               enemy.setActive(false);
           }
           this.isActive = false;
       }
    }


    private void hitEnemyBullet(){
        EnemyBullet enemyBullet = Physics.collideWithEnemyBullet(this.boxCollider);
        if(enemyBullet != null){
            enemyBullet.setActive(false);
            this.isActive = false;
        }

    }
    
    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public int getPlayerSpellDamage() {
        return PlayerSpellDamage;
    }

    public void setPlayerSpellDamage(int playerSpellDamage) {
        PlayerSpellDamage = playerSpellDamage;
    }
}
