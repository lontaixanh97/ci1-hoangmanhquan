package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Point;
import bases.Rectangle;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject {
    private static final float SPEED = 2;
    private FrameCounter frameCounter;

    public Enemy(){
        super();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png")) ;
        frameCounter = new FrameCounter(30);
    }

    //controller
    public void run(){
        super.run();
        fly();
        shoot();
    }

    private void shoot() {
        EnemyBullet enemyBullet = new EnemyBullet();

        if(frameCounter.run()) {
            frameCounter.reset();
            enemyBullet.getPosition().set(this.position.add(0, 10));
            GameObject.add(enemyBullet);
        }
    }

    private void fly() {
        position.addUp(0,SPEED);
    }

}
