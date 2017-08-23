package touhou.players.spheres;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import touhou.enemies.Enemy;

public class SphereBullet extends GameObject implements PhysicsBody {

    private BoxCollider boxCollider;
    private Animation animation;
    private int damage;

    public SphereBullet(){
        this.animation = new Animation(1, false,false,
                SpriteUtils.loadImage("assets/images/sphere-bullets/0.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/1.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/2.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/3.png")
        ) ;
        this.renderer = animation;
        this.boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
        this.setDamage(2);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(0,-10);
        hitEnemy();
        deactiveIfNeeded();
    }

    private void hitEnemy() {
        Enemy enemy = Physics.collideWith(this.boxCollider, Enemy.class);
        if(enemy != null){
            enemy.setEnemyHP(enemy.getEnemyHP() - damage);
            if(enemy.getEnemyHP() <= 0) {
                enemy.setActive(false);
            }
            this.isActive = false;
        }
    }

    private void deactiveIfNeeded() {
        if (this.screenPosition.y < 0) {
            this.isActive = false;
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
