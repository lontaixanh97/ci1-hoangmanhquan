package touhou;

import tklibs.SpriteUtils;
import touhou.bases.Constraints;
import touhou.inputs.InputManager;
import touhou.players.Player;
import touhou.players.PlayerSpell;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

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

    private int backgroundY=-2400;

    Player player = new Player();
    ArrayList<PlayerSpell> playerSpells = new ArrayList<>();
    InputManager inputManager = new InputManager();


    public GameWindow() {
        background = SpriteUtils.loadImage("assets/images/background/0.png");
        player.inputManager = this.inputManager;
        player.playerSpells = this.playerSpells;
        player.constraints = new Constraints(30,768-60,0,384-24);
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
                inputManager.keyPressed(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
               inputManager.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               inputManager.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

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
       player.run();
       for(PlayerSpell playerSpell : playerSpells){
           //lay moi con spell
           playerSpell.run();
       }
    }

    private void render() {
        backbufferGraphics.setColor(Color.orange);
        backbufferGraphics.fillRect(0, 0, 1024, 768);
        backbufferGraphics.drawImage(background, 0, backgroundY , null);
        if(backgroundY < 0) backgroundY += 3;
        player.render(backbufferGraphics);

        for(PlayerSpell playerSpell : playerSpells){
            playerSpell.render(backbufferGraphics);
        }
        
        windowGraphics.drawImage(backbufferImage,0,0,null);
    }
}
