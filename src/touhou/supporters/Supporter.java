package touhou.supporters;

import bases.Constraints;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.enemies.Enemy;
import touhou.inputs.InputManager;
import touhou.players.Player;


public class Supporter extends GameObject implements PhysicsBody {
    private InputManager inputManager;
    private Constraints constraints;
    private BoxCollider boxCollider;
    Player player;

    public Supporter(float x, float y){
        super();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/sphere/0.png"));
        boxCollider = new BoxCollider(x, y , 15,15);
        this.children.add(boxCollider);
        
    }

    public void setContraints(Constraints constraints) {
        this.constraints = constraints;
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
    }


    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }



}
