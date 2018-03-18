import java.awt.Robot;
import java.awt.*;
public class MouseData implements Runnable {
    drawingPlane plane;
    public MouseData(drawingPlane p) {
        plane = p;
    }
    public void run() {
        Robot rb;
        double x;
        double y;
        try {
            rb = new Robot();
            while (true) {
                Thread.sleep(10);
                x = MouseInfo.getPointerInfo().getLocation().getX();
                y = MouseInfo.getPointerInfo().getLocation().getY();
                if (x-400 != 0 | y-400 != 0) {
                    plane.look('y', -(x-400)/10);
                    plane.look('x', (y-400)/10);
                }
                rb.mouseMove(400,400);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}