import javax.swing.*;
import java.awt.event.*;

public class Listener extends KeyAdapter { 
    drawingPlane current;
    boolean leftpress;
    boolean rightpress;
    boolean spacepress;
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println("GOT KEY PRESS:" + key);
        if (key == KeyEvent.VK_LEFT) {
            current.left();
        }
        if (key == KeyEvent.VK_RIGHT) {
            current.right();
        }
        if (key == KeyEvent.VK_UP) {
            current.forwards();
        }
        if (key == KeyEvent.VK_DOWN) {
            current.backwards();
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println("GOT KEY RELEASE:" + key);
        if (key == KeyEvent.VK_LEFT) {
            //current.leftRelease();
        }
        if (key == KeyEvent.VK_RIGHT) {
            //current.rightRelease();
        }
        if (key == KeyEvent.VK_SPACE) {
            //current.spaceRelease();
        }        
        if (key == KeyEvent.VK_UP) {
            //current.upRelease();
        } 
    }
    public void setDrawingPlane(drawingPlane s) {
        current = s;
    }
    public Listener(drawingPlane s) {
        current = s;
        leftpress = false;
        rightpress = false;
        spacepress = false;
    }
}    