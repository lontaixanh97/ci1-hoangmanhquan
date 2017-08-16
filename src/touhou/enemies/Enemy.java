package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import tklibs.SpriteUtils;
import bases.renderers.ImageRenderer;
import touhou.players.Player;

public class Enemy extends GameObject implements PhysicsBody{
    private static final float SPEED = 2;
    private FrameCounter frameCounter;
    private BoxCollider boxCollider;
    private int EnemyHP;

    public Enemy(){
        super();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png")) ;
        frameCounter = new FrameCounter(30);
        this.setEnemyHP(2);
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
    }

    //controller
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        fly();
        shoot();
        hitPlayer();
    }

    private void shoot() {
        EnemyBullet enemyBullet = new EnemyBullet();

        if(frameCounter.run()) {
            frameCounter.reset();
            enemyBullet.getPosition().set(this.position.add(0, 10));
            GameObject.add(enemyBullet);
        }
    }

    private void hitPlayer(){
        Player player = Physics.collideWithPlayer(this.boxCollider);
        if(player != null){
            player.setPlayerHP(player.getPlayerHP() - EnemyHP );
            if(player.getPlayerHP() <= 0) {
                player.setActive(false);
            }
            this.isActive = false;
        }

    }

    private void fly() {
        position.addUp(0,SPEED);
    }

    @Override
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
