package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.Constraints;
import touhou.bases.Vector2D;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class PlayerSpell {
    public Vector2D position;
    public BufferedImage image1;
    public Vector2D position1;
    public BufferedImage image3;
    public Vector2D position3;
    public BufferedImage image4;
    public Vector2D position4;

    public Constraints constraints;

    public PlayerSpell(Vector2D position) {
        this.position = position;
        image1 = SpriteUtils.loadImage("assets/images/players/spells/0.png");
        position1 = position.add(-1,-18);
        image3 = SpriteUtils.loadImage("assets/images/players/spells/0.png");
        position3 = position.add(-2,-17);
        image4 = SpriteUtils.loadImage("assets/images/players/spells/0.png");
        position4 = position.add(2,-17);

    }

    public void run() {
        position1.subtractBy(0,5);
        position3.subtractBy(3,4);
        position4.subtractBy(-3,4);
        if(constraints != null){
            constraints.make(position4);
            constraints.make(position3);
        }

    }

    public void render(Graphics2D g2d){
        g2d.drawImage(image1 , (int)position1.x , (int)position1.y , null);
        g2d.drawImage(image3 , (int)position3.x , (int)position3.y, null);
        g2d.drawImage(image4 , (int)position4.x , (int)position4.y, null);
    }

}
