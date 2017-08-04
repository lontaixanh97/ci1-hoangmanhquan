package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.Constraints;
import touhou.bases.Vector2D;
import touhou.inputs.InputManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
    public Vector2D position;
    public BufferedImage image;
    public InputManager inputManager;
    public Constraints constraints;
    private final int SPEED = 5;
    
    public ArrayList<PlayerSpell> playerSpells;

    public Player(){     // ham tao
        position = new Vector2D(192,600);
        image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
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
        castSpell();
    }

    private void castSpell() {
        if(inputManager.xPressed) {
            PlayerSpell newSpell = new PlayerSpell(position.add(10,10));
            playerSpells.add(newSpell);
        }
    }

    public void render(Graphics2D g2d){
        g2d.drawImage(image , (int)position.x , (int)position.y , null);
    }

}
