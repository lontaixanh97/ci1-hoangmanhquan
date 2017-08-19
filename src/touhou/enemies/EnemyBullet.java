package touhou.enemies;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.players.Player;

public class EnemyBullet extends GameObject implements PhysicsBody {
    private static final float SPEED = 5;
    private int BulletDamage = 1;
    private BoxCollider boxCollider;

    public EnemyBullet(){
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"));
        boxCollider = new BoxCollider(12,12);
        this.children.add(boxCollider);
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        position.addUp(0,SPEED);
        hitPlayer();
    }

    private void hitPlayer(){
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if(player != null){
            player.setPlayerHP(player.getPlayerHP() - BulletDamage );
            if(player.getPlayerHP() == 0) {
                player.setActive(false);
            }
            this.isActive = false;
        }

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
