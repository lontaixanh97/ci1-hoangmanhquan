package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.Constraints;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class PlayerSpell {
    private ImageRenderer renderer;
    Vector2D position;
    
    PlayerSpell() {
        this.position = new Vector2D();
        BufferedImage image = SpriteUtils.loadImage("assets/images/players/spells/0.png");
        renderer = new ImageRenderer(image);
    }

    public void run() {
        position.subtractBy(0,5);
    }

    public void render(Graphics2D g2d){
        renderer.render(g2d,position);
    }
}
