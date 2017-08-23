package touhou.enemies;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import touhou.players.Player;
import touhou.settings.Settings;

public class BossBullet extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    private int dame;
    Settings settings = Settings.instance;
    Animation animation;
    private float x;
    private float y;

    public BossBullet(float x, float y){
        super();
        animation = new Animation(3, false, true,
                SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/cyan.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/green.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/pink.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/red.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/white.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/yellow.png")
        );
        this.renderer = animation;
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
        this.x = x;
        this.y = y;
        dame = 1;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(x,y);
        deactiveIfNeeded();
        hitPlayer();

    }

    private void deactiveIfNeeded() {
        if (this.screenPosition.y < 0
                || this.screenPosition.y > settings.getGamePlayHeight()
                || this.screenPosition.x < 0
                ||this.screenPosition.x > settings.getGamePlayWidth()){
            this.isActive = false;
        }
    }

    private void hitPlayer(){
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if(player != null){
            player.setPlayerHP(player.getPlayerHP() - dame );
            if(player.getPlayerHP() == 0) {
                player.setActive(false);
            }
            this.isActive = false;
        }

    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return null;
    }
}
