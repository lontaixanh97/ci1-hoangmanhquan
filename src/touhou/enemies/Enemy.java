package touhou.enemies;

import tklibs.SpriteUtils;
import touhou.bases.FrameCounter;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;
import touhou.inputs.InputManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy {
    private ImageRenderer renderer;
    public Vector2D position;

    ArrayList<EnemyBullet> enemyBullets = new ArrayList<>();

    
    public Enemy(int x, int y) {
        this.position = new Vector2D(x,y);
        BufferedImage image = SpriteUtils.loadImage("C:\\Users\\Quan\\ci-begin\\assets\\images\\enemies\\level0\\black\\0.png");
        renderer = new ImageRenderer(image);
    }

    public void run(){
        position.addUp(0,3);
    }


    public void render(Graphics2D g2d){
        renderer.render(g2d,position);
    }
}
