package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import tklibs.SpriteUtils;
import bases.Constraints;
import bases.FrameCounter;
import bases.renderers.ImageRenderer;
import touhou.inputs.InputManager;
import touhou.supporters.Supporter;

import java.util.Vector;

/**
 * Created by huynq on 8/2/17.
 */
public class Player extends GameObject implements PhysicsBody {
    private static final int SPEED = 5;
    private InputManager inputManager;
    private Constraints constraints;
    private BoxCollider boxCollider;
    private int PlayerHP;

    private FrameCounter coolDownCounter;
    private boolean spellLock;

    public Player() {
        super();
        this.spellLock = false;
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/players/straight/0.png"));
        boxCollider = new BoxCollider(20,40);
        this.children.add(boxCollider);
        this.setPlayerHP(5);
        coolDownCounter = new FrameCounter(5);
    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (inputManager.upPressed)
            position.addUp(0, -SPEED);
        if (inputManager.downPressed)
            position.addUp(0, SPEED);
        if (inputManager.leftPressed)
            position.addUp(-SPEED, 0);
        if (inputManager.rightPressed)
            position.addUp(SPEED, 0);

        if (constraints != null) {
            constraints.make(position);
        }

        castSpell();
        spawn();
    }

    private void castSpell() {
        if (inputManager.xPressed && !spellLock) {
            PlayerSpell newSpell = new PlayerSpell();
            newSpell.getPosition().set(this.position.add(0, -30));
            GameObject.add(newSpell);

            spellLock = true;
            coolDownCounter.reset();
        }

        unlockSpell();
    }
    public void spawn(){
        Supporter supporter = new Supporter(this.getPosition().x, this.getPosition().y);
        supporter.getPosition().set(this.getPosition().add(20, 10));
        GameObject.add(supporter);
    }

    private void unlockSpell() {
        if (spellLock) {
            if (coolDownCounter.run()) {
                spellLock = false;
            }
        }
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public int getPlayerHP() {
        return PlayerHP;
    }

    public void setPlayerHP(int playerHP) {
        PlayerHP = playerHP;
    }
}
