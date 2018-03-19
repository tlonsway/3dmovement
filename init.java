import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.BufferedImage;
public class init {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game window");
        frame.setVisible(true);
        frame.setSize(1920, 1080);
        //frame.setFocusable(false);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //build map
        Map map = MapGen.generate(10);
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
        drawingPlane plane = new drawingPlane(map.getMap(),map.getDepth(),1920,1080,75);
        MouseData mousethread = new MouseData(plane);        
        frame.add(plane);
        plane.setVisible(true);
        plane.redraw();
        frame.addKeyListener(new Listener(plane, mousethread));
        //frame.getContentPane().setBackground(Color.CYAN);
        frame.getContentPane().setBackground(Color.darkGray);
        //STARTING MOUSE SCANNER
        (new Thread(mousethread)).start();
        //HIDING CURSOR
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        plane.look('y', -.6);
        plane.move('y',5.0);
        plane.look('x', .3);
        mousethread.toggle();
        //frame.setUndecorated(true);
    }
}