package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;
import touhou.inputs.InputManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
    private Vector2D position;
    private InputManager inputManager;
    private Constraints constraints;
    private final int SPEED = 5;
    
    public ArrayList<PlayerSpell> playerSpells;

    private ImageRenderer renderer;

    private FrameCounter coolDownCounter;
    private boolean spellLock;
    private void unlockSpell(){
        if(spellLock){
            if(coolDownCounter.run()){
                spellLock = false;
            }
        }
    }

    public Player(){     // ham tao
        position = new Vector2D(192,600);
        BufferedImage image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        renderer = new ImageRenderer(image);
        coolDownCounter = new FrameCounter(7);
    }

    public void setConstraints(Constraints constraints){
        this.constraints = constraints ;
    }

    public void run()
    {
        if(inputManager.upPressed)
            position.addUp(0,-SPEED);
        if(inputManager.downdPressed)
            position.addUp(0,SPEED);
        if(inputManager.rightPressed)
            position.addUp(SPEED,0);
        if(inputManager.leftPressed)
            position.addUp(-SPEED,0);
        if(constraints != null){
            constraints.make(position);
        }
        
        /*if(coolDownCounter.run()) {
            coolDownCounter.reset();
        } */
            castSpell();
    }

    private void castSpell() {
        if (inputManager.xPressed && !spellLock) {
            PlayerSpell newSpell = new PlayerSpell();
            newSpell.position.set(this.position);
            playerSpells.add(newSpell);
            spellLock = true;
            coolDownCounter.reset();
        }
        unlockSpell();
    }

    public void render(Graphics2D g2d){
      renderer.render(g2d,position);
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}
