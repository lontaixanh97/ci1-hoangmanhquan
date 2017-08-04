package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSpell {
    public Vector2D position;
    public BufferedImage image;

    public PlayerSpell(Vector2D position) {
        this.position = position;
        image = SpriteUtils.loadImage("C:\\Users\\Quan\\ci-begin\\assets\\images\\players\\spells\\0.png");
    }

    public void run() {
        position.subtractBy(0,10);

    }

    public void render(Graphics2D g2d){
        g2d.drawImage(image , (int)position.x , (int)position.y , null);
    }

}
