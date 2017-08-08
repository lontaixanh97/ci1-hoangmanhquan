package touhou.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static java.awt.event.KeyEvent.*;

public class InputManager {
    public boolean upPressed;
    public boolean downdPressed;
    public boolean rightPressed;
    public boolean leftPressed;
    public boolean xPressed;
    public boolean zPressed;

    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case VK_RIGHT:
                rightPressed = true;
                break;
            case VK_LEFT:
                leftPressed = true;
                break;
            case VK_UP:
                upPressed = true;
                break;
            case VK_DOWN:
                downdPressed = true;
                break;
            case VK_X:
                xPressed = true;
                break;
            case VK_Z:
                zPressed = true;
        }
    }
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case VK_RIGHT:
                rightPressed = false;
                break;
            case VK_LEFT:
                leftPressed = false;
                break;
            case VK_UP:
                upPressed = false;
                break;
            case VK_DOWN:
                downdPressed = false;
                break;
            case VK_X :
                xPressed = false;
                break;
            case VK_Z:
                zPressed = false;

        }
    }
}
