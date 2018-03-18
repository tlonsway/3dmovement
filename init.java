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
        //build map
        int[][][] map = MapGen.generate(30);
        //cube:
        /*ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(-2,-2,6));
        points.add(new Point(2,-2,6));
        points.add(new Point(-2,2,6));
        points.add(new Point(2,2,6));
        points.add(new Point(-2,-2,10));
        points.add(new Point(2,-2,10));
        points.add(new Point(-2,2,10));
        points.add(new Point(2,2,10));
        ArrayList<Vector> vectors = new ArrayList<Vector>();
        vectors.add(new Vector(points.get(0),points.get(1)));
        vectors.add(new Vector(points.get(0),points.get(4)));        
        vectors.add(new Vector(points.get(0),points.get(2))); 
        vectors.add(new Vector(points.get(1),points.get(3))); 
        vectors.add(new Vector(points.get(1),points.get(5))); 
        vectors.add(new Vector(points.get(2),points.get(3))); 
        vectors.add(new Vector(points.get(2),points.get(6))); 
        vectors.add(new Vector(points.get(3),points.get(7))); 
        vectors.add(new Vector(points.get(4),points.get(5))); 
        vectors.add(new Vector(points.get(4),points.get(6))); 
        vectors.add(new Vector(points.get(5),points.get(7))); 
        vectors.add(new Vector(points.get(6),points.get(7)));*/
        //build drawing plane
        drawingPlane plane = new drawingPlane(map);
        frame.add(plane);
        plane.setVisible(true);
        plane.redraw();
        frame.addKeyListener(new Listener(plane));
        //frame.getContentPane().setBackground(Color.CYAN);
        frame.getContentPane().setBackground(Color.yellow);
        (new Thread(new MouseData(plane))).start();

    }
}