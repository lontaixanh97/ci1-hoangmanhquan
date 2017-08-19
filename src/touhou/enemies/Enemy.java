package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.players.Player;

import java.awt.*;

/**
 * Created by huynq on 8/9/17.
 */
public class Enemy extends GameObject implements PhysicsBody {
    private static final float SPEED = 3;
    private BoxCollider boxCollider;
    private int EnemyHP;
    private FrameCounter frameCounter ;

    public Enemy() {
        super();
        renderer = new Animation(
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/1.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/2.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/3.png")
        );
        this.setEnemyHP(3);
        boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
        frameCounter = new FrameCounter(70);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        fly();
        shoot();
        hitPlayer();
        deactiveIfNeeded();
    }

    private void shoot() {
        EnemyBullet enemyBullet = new EnemyBullet();

        if(frameCounter.run()) {
            frameCounter.reset();
            enemyBullet.getPosition().set(this.position.add(0, 10));
            GameObject.add(enemyBullet);
        }
        // TODO: create enemy bullet and shoot

    }

    private void hitPlayer(){
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if(player != null){
            player.setPlayerHP(player.getPlayerHP() - EnemyHP );
            if(player.getPlayerHP() <= 0) {
                player.setActive(false);
            }
            this.isActive = false;
        }

    }

    private void fly() {
        position.addUp(0, SPEED);
    }

    private void deactiveIfNeeded() {
        if (this.screenPosition.y >= 768) {
            this.isActive = false;
        }
    }

    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public void setEnemyHP(int enemyHP) {
        EnemyHP = enemyHP;
    }

    public int getEnemyHP() {
        return EnemyHP;
    }
}
