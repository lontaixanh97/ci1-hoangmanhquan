package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import touhou.EnemyExplosion;
import touhou.settings.Settings;

import java.util.Random;

public class Boss extends GameObject implements PhysicsBody {

    private BoxCollider boxCollider;
    Animation animation;
    private int Hp;
    private FrameCounter frameCounter;
    private int count;
    private int a;
    private Random random;
    Settings settings = Settings.instance;

    public Boss(){
        super();
        animation = new Animation(
                SpriteUtils.loadImage("assets/images/enemies/level0/black/0.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/1.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/2.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/4.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/5.png")
        );
        this.renderer = animation;
        boxCollider = new BoxCollider(30,45);
        this.children.add(boxCollider);
        this.setHp(50);
        frameCounter = new FrameCounter(5);
        random = new Random();
    }

    public void run(Vector2D parentPosition ){
        super.run(parentPosition);
        shoot();
        skill();

    }

    private void skill() {
        if(a == 0) {
            if (Hp == 40) {
                a++;
                Hp -= 1;
                this.position.set(random.nextInt(settings.getGamePlayWidth()), random.nextInt(settings.getGamePlayHeight()/2));
            }
            if (Hp == 30) {
                a++;
                Hp -= 1;
                this.position.set(random.nextInt(settings.getGamePlayWidth()), random.nextInt(settings.getGamePlayHeight()/2));
            }
            if (Hp == 20) {
                a++;
                Hp -= 1;
                this.position.set(random.nextInt(settings.getGamePlayWidth()), random.nextInt(settings.getGamePlayHeight()/2));
            }
            if (Hp == 10) {
                a++;
                Hp -= 1;
                this.position.set(random.nextInt(settings.getGamePlayWidth()), random.nextInt(settings.getGamePlayHeight()/2));
            }
        }
        if(a >= 1){
            a++;
        }
        if(a==3){
            a = 0;
        }

    }

    private void shoot() {
        count++;
        if(count<=30) {
            if (frameCounter.run()) {
                frameCounter.reset();
                createBullet(-2, 5, -20, 20);
                createBullet(-1, 5, -10, 20);
                createBullet(0, 5, 0, 20);
                createBullet(1, 5, 10, 20);
                createBullet(2, 5, 20, 20);
            }
        }
        if(count >= 80 && count <= 110) {
            if (frameCounter.run()) {
                frameCounter.reset();
                createBullet(-5, 5, -20, 20);
                createBullet(0, 5, 0, 20);
                createBullet(5, 5, 20, 20);
                createBullet(5, 0, 20, 0);
                createBullet(5, -5, 20, -20);
                createBullet(0, -5, 0, -20);
                createBullet(-5, -5, -20, -20);
                createBullet(-5, 0, -20, 0);
            }
        }
        if(count == 150){
            count = 0;
        }


    }

    private void createBullet(float x, float y, float dx, float dy) {
        BossBullet newBullet = GameObjectPool.recycle(BossBullet.class);
            if(newBullet == null){
                newBullet = new BossBullet(x, y);
            }
            newBullet.setPosition(this.position.add(dx, dy));
            newBullet.setX(x);
            newBullet.setY(y);
            GameObject.add(newBullet);

    }

    public void getHit(int playerSpellDamage) {
        this.Hp -= playerSpellDamage;
        if(Hp <= 0) {
            this.setActive(false);
            EnemyExplosion explosion = GameObjectPool.recycle(EnemyExplosion.class);
            explosion.getPosition().set(this.position);
        }
    }

    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        Hp = hp;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
