import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class init {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game window");
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        //frame.setFocusable(false);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //cube:
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(-2,-2,6));
        points.add(new Point(2,-2,6));
        points.add(new Point(-2,2,6));
        points.add(new Point(2,2,6));
        points.add(new Point(-2,-2,10));
        points.add(new Point(2,-2,10));
        points.add(new Point(-2,2,10));
        points.add(new Point(2,2,10));
        drawingPlane plane = new drawingPlane(points);
        frame.add(plane);
        plane.setVisible(true);
        plane.redraw();
        frame.addKeyListener(new Listener(plane));
    }
}