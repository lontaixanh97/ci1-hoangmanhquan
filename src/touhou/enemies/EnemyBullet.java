package touhou.enemies;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class EnemyBullet extends GameObject {
    private static final float SPEED = 5;

    public EnemyBullet(){
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"));
    }

    public void run(){
        position.addUp(0,SPEED);
    }
}
