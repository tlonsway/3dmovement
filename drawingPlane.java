import java.util.*;
import javax.swing.*;
import java.awt.*;
public class drawingPlane extends JComponent{
    ArrayList<Point> points = new ArrayList<Point>(); // ArrayList of currently existed points, unprojected
    
    
    public drawingPlane(ArrayList<Point> alp) {
        super();
        points = alp;
        
    }
    
    public void redraw() {
        repaint();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        double tempx;
        double tempy;
        double[] temp;
        for (Point p : points) {
            temp = project.project2D(new double[]{p.getX(),p.getY(),p.getZ(),1},75.0,1.0,5.0,100.0);
            tempx = temp[0];
            tempy = temp[1];
            tempx *= 800;
            tempy *= 800;
            g.drawRect((int)tempx,(int)tempy, 5, 5);
            System.out.println("Drawing point at (" + tempx + ", " + tempy + ")");
        }
    }
    
    public void forwards() {
        ArrayList<Point> temp = new ArrayList<Point>();
        for (Point p : points) {
            double[] vector = new double[]{p.getX(),p.getY(),p.getZ(),1};
            double[] returned = manipulate.translate(vector, 0, 0, -.5);
            double afterx = returned[0];
            double aftery = returned[1];
            double afterz = returned[2];
            temp.add(new Point(afterx,aftery,afterz));
        }
        points = temp;
        redraw();
    }
    
    public void backwards() {
        ArrayList<Point> temp = new ArrayList<Point>();
        for (Point p : points) {
            double[] vector = new double[]{p.getX(),p.getY(),p.getZ(),1};
            double[] returned = manipulate.translate(vector, 0, 0, .5);
            double afterx = returned[0];
            double aftery = returned[1];
            double afterz = returned[2];
            temp.add(new Point(afterx,aftery,afterz));
        }
        points = temp;
        redraw();        
    }
    
    public void left() {
        ArrayList<Point> temp = new ArrayList<Point>();
        for (Point p : points) {
            double[] vector = new double[]{p.getX(),p.getY(),p.getZ(),1};
            double[] returned = manipulate.translate(vector, -.5, 0, 0);
            double afterx = returned[0];
            double aftery = returned[1];
            double afterz = returned[2];
            temp.add(new Point(afterx,aftery,afterz));
        }
        points = temp;
        redraw();        
    }
    
    public void right() {
        ArrayList<Point> temp = new ArrayList<Point>();
        for (Point p : points) {
            double[] vector = new double[]{p.getX(),p.getY(),p.getZ(),1};
            double[] returned = manipulate.translate(vector, .5, 0, 0);
            double afterx = returned[0];
            double aftery = returned[1];
            double afterz = returned[2];
            temp.add(new Point(afterx,aftery,afterz));
        }
        points = temp;
        redraw();        
    }
    
    
    
    
}