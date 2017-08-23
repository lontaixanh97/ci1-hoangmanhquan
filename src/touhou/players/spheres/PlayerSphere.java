package touhou.players.spheres;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

public class PlayerSphere extends GameObject implements PhysicsBody {
    private FrameCounter frameCounter;
    Animation animation;
    public PlayerSphere(){
        super();
        frameCounter = new FrameCounter(7);
        this.animation = new Animation(
                2,
                false, true,
                SpriteUtils.loadImage("assets/images/sphere/0.png"),
                SpriteUtils.loadImage("assets/images/sphere/1.png"),
                SpriteUtils.loadImage("assets/images/sphere/2.png"),
                SpriteUtils.loadImage("assets/images/sphere/3.png")
        );
        this.renderer = animation;
    }

    public void setReverse(boolean reverse){
        this.animation.setReverse(reverse);
    }

    

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        //sphereShoot();
    }

    public void sphereShoot(){
        if(frameCounter.run()){
            frameCounter.reset();
            SphereBullet sphereBullet = GameObjectPool.recycle(SphereBullet.class);
            sphereBullet.getPosition().set(this.screenPosition.subtract(0,30));
        }

    }

    @Override
    public BoxCollider getBoxCollider() {
        return null;
    }
}
