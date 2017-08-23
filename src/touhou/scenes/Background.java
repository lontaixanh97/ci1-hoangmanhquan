package touhou.scenes;

import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPool;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.settings.Settings;

public class Background extends GameObject {

    private final float SPEED = 4;
    private ImageRenderer imageRenderer;
    private float imageHeight;

    public Background(){
        super();
        this.imageRenderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/background/0.png"));
        this.imageRenderer.getAnchor().set(0,1);
        this.position.set(0, Settings.instance.getGamePlayHeight());
        this.imageHeight = imageRenderer.image.getHeight();
        this.renderer = imageRenderer;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.y += SPEED;
        if(position.y - imageHeight > 0){
            position.y = imageHeight;
        }

    }
}
