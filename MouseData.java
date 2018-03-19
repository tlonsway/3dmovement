import java.awt.Robot;
import java.awt.*;
public class MouseData implements Runnable {
    drawingPlane plane;
    boolean lock;
    Robot rb;
    public MouseData(drawingPlane p) {
        plane = p;
    }
    public void run() {
        double x;
        double y;
        try {
            rb = new Robot();
            rb.mouseMove(400,400);            
            while (true) {
                if (!lock) {
                    Thread.sleep(5);
                    x = MouseInfo.getPointerInfo().getLocation().getX();
                    y = MouseInfo.getPointerInfo().getLocation().getY();
                    if (x-400 != 0 | y-400 != 0) {
                        plane.look('y', -(x-400)/200);
                        plane.look('x', (y-400)/200);
                    }
                    rb.mouseMove(400,400);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stop() {
        //STOPS MOUSE LOCK
        lock = true;
    }
    public void go() {
        //CONTINUE MOUSE LOOK
        lock = false;
    }
    public void toggle() {
        //TOGGLE LOCK
        lock = !lock;
        rb.mouseMove(400,400);        
    }
}