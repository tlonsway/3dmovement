import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Listener extends KeyAdapter { 
    drawingPlane current;
    boolean leftpress;
    boolean rightpress;
    boolean spacepress;
    MouseData mouse;
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //System.out.println("GOT KEY PRESS:" + key);
        if (key == KeyEvent.VK_A) {
            current.move('x', 2.0);
        }
        if (key == KeyEvent.VK_D) {
            current.move('x',-2.0);
        }
        if (key == KeyEvent.VK_W) {
            current.move('z', -2.0);
        }
        if (key == KeyEvent.VK_S) {
            current.move('z', 2.0);
        }
        if (key == KeyEvent.VK_SPACE) {
            current.move('y', 2.0);
        }
        if (key == KeyEvent.VK_CONTROL) {
            current.move('y', -2.0);
        }
        if (key == KeyEvent.VK_Q) {
            current.look('z', .1);
        }
        if (key == KeyEvent.VK_E) {
            current.look('z', -.1);
        }
        if (key == KeyEvent.VK_ESCAPE) {
            mouse.toggle();
        }
    }
    public void setDrawingPlane(drawingPlane s) {
        current = s;
    }
    public Listener(drawingPlane s, MouseData mo) {
        current = s;
        leftpress = false;
        rightpress = false;
        spacepress = false;
        mouse = mo;
    }
}    