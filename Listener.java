import javax.swing.*;
import java.awt.event.*;

public class Listener extends KeyAdapter { 
    drawingPlane current;
    boolean leftpress;
    boolean rightpress;
    boolean spacepress;
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //System.out.println("GOT KEY PRESS:" + key);
        if (key == KeyEvent.VK_A) {
            current.left();
        }
        if (key == KeyEvent.VK_D) {
            current.right();
        }
        if (key == KeyEvent.VK_W) {
            current.forwards();
        }
        if (key == KeyEvent.VK_S) {
            current.backwards();
        }
        if (key == KeyEvent.VK_SPACE) {
            current.up();
        }
        if (key == KeyEvent.VK_CONTROL) {
            current.down();
        }
        /*if (key == KeyEvent.VK_S) {
            current.look('x', .1);
        }
        if (key == KeyEvent.VK_W) {
            current.look('x', -.1);
        }
        if (key == KeyEvent.VK_A) {
            current.look('y', .1);
        }
        if (key == KeyEvent.VK_D) {
            current.look('y', -.1);
        }*/
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        //System.out.println("GOT KEY RELEASE:" + key);
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