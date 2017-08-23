package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import javafx.scene.shape.Sphere;
import tklibs.SpriteUtils;
import bases.Constraints;
import bases.FrameCounter;
import bases.renderers.ImageRenderer;
import touhou.inputs.InputManager;
import touhou.players.spheres.PlayerSphere;

import java.util.Vector;

/**
 * Created by huynq on 8/2/17.
 */
public class Player extends GameObject implements PhysicsBody {
    private static final int SPEED = 5;
    private int PlayerHP;

    private InputManager inputManager;
    private Constraints constraints;
    private BoxCollider boxCollider;

    private FrameCounter coolDownCounter;
    private boolean spellLock;

    private Vector2D velocity;

    private PlayerAnimator animator;

    public Player() {
        super();
        this.spellLock = false;
        this.animator = new PlayerAnimator();
        this.renderer = animator;
        this.coolDownCounter = new FrameCounter(3);
        this.velocity = new Vector2D();
        boxCollider = new BoxCollider(20,40);
        this.children.add(boxCollider);
        this.setPlayerHP(20);
        addSpheres();
    }

    private void addSpheres() {
        PlayerSphere leftSphere = new PlayerSphere();
        leftSphere.getPosition().set(-20,0);
        PlayerSphere rightSphere = new PlayerSphere();
        rightSphere.getPosition().set(20,0);
        rightSphere.setReverse(true);
        this.children.add(leftSphere);
        this.children.add(rightSphere);
    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }

    public void run(Vector2D parentPostion) {
        super.run(parentPostion);

        velocity.set(0, 0);

        if (inputManager.upPressed)
            velocity.y -= SPEED;
        if (inputManager.downPressed)
            velocity.y += SPEED;
        if (inputManager.leftPressed)
            velocity.x -= SPEED;
        if (inputManager.rightPressed)
            velocity.x += SPEED;

        if (constraints != null) {
            constraints.make(position);
        }

        position.addUp(velocity);
        animator.update(this);

        castSpell();
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    private void castSpell() {
        if (inputManager.xPressed && !spellLock) {
            PlayerSpell newSpell = GameObjectPool.recycle(PlayerSpell.class);
            newSpell.getPosition().set(this.position.add(0, -30));

            spellLock = true;
            coolDownCounter.reset();
        }

        unlockSpell();
    }



    private void unlockSpell() {
        if (spellLock) {
            if (coolDownCounter.run()) {
                spellLock = false;
            }
        }
    }

    public int getPlayerHP() {
        return PlayerHP;
    }

    public void setPlayerHP(int playerHP) {
        PlayerHP = playerHP;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
