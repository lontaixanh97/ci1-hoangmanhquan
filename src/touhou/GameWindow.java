package touhou;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;
    private Graphics2D windowGraphics;
    private BufferedImage backbufferImage;
    private Graphics2D backbufferGraphics;
    private BufferedImage background;
    private BufferedImage player;
    private int playerX = 192 ;
    private int playerY = 600 ;
    private int backgroundY=-2400;

    public GameWindow() {
        background = SpriteUtils.loadImage("./assets/images/background/0.png");
        player = SpriteUtils.loadImage("./assets/images/players/straight/1.png");
        setupGameLoop();
        setupWindow();
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(1024, 768);
        this.setTitle("Touhou-Remade by Quan");
        this.setVisible(true);
        this.backbufferImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();
        this.windowGraphics = (Graphics2D) this.getGraphics();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                {
                    playerX=playerX+5;
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    playerX=playerX-5;
                }
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    playerY=playerY-5;
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    playerY+=5;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void loop() {
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.currentTimeMillis();
            }
            currentTime = System.currentTimeMillis();
            if (currentTime - lastTimeUpdate > 17) {
                run();
                render();
                lastTimeUpdate = currentTime;
            }
        }
    }

    private void run() {


    }

    private void render() {
        backbufferGraphics.setColor(Color.black);
        backbufferGraphics.fillRect(0, 0, 1024, 768);
        backbufferGraphics.drawImage(background, 0, backgroundY, null);
        backgroundY+=3;
        backbufferGraphics.drawImage(player, playerX,playerY,null);
        windowGraphics.drawImage(backbufferImage,0,0,null);

    }
}
