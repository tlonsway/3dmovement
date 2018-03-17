import java.util.*;
import javax.swing.*;
import java.awt.*;
public class drawingPlane extends JComponent{
    ArrayList<Point> points = new ArrayList<Point>(); // ArrayList of currently existed points, unprojected
    ArrayList<Vector> vectors = new ArrayList<Vector>();
    
    public drawingPlane(ArrayList<Point> alp, ArrayList<Vector> alv) {
        super();
        points = alp;
        vectors = alv;
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
        /*for (Point p : points) {
            temp = project.project2D(new double[]{p.getX(),p.getY(),p.getZ(),1},75.0,1.0,5.0,100.0);
            tempx = temp[0];
            tempy = temp[1];
            tempx *= 800;
            tempy *= 800;
            g.drawRect((int)tempx,(int)tempy, 5, 5);
            System.out.println("Drawing point at (" + tempx + ", " + tempy + ")");
        }*/
        for (Vector v : vectors) {
            Point pone = v.getOne();
            double onex = pone.getX();
            double oney = pone.getY();
            double onez = pone.getZ();
            double[] oneproj = project.project2D(new double[]{onex,oney,onez,1},75.0,1.0,5.0,100.0);
            double oneax = oneproj[0];
            double oneay = oneproj[1];
            Point ptwo = v.getTwo();
            double twox = ptwo.getX();
            double twoy = ptwo.getY();
            double twoz = ptwo.getZ();
            double[] twoproj = project.project2D(new double[]{twox,twoy,twoz,1},75.0,1.0,5.0,100.0);            
            double twoax = twoproj[0];
            double twoay = twoproj[1];
            g.drawLine((int)(800*oneax), (int)(800*oneay), (int)(800*twoax), (int)(800*twoay));
            
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
        ArrayList<Vector> tempv = new ArrayList<Vector>();
        for (Vector v : vectors) {
            Point pone = v.getOne();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, 0, 0, -.5);
            Point dpone = new Point(oreturned[0],oreturned[1],oreturned[2]);
            Point ptwo = v.getTwo();
            double[] tvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};
            double[] treturned = manipulate.translate(tvector, 0, 0, -.5);
            Point dptwo = new Point(treturned[0],treturned[1],treturned[2]);            
            tempv.add(new Vector(dpone,dptwo));
        }
        vectors = tempv;
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
        ArrayList<Vector> tempv = new ArrayList<Vector>();
        for (Vector v : vectors) {
            Point pone = v.getOne();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, 0, 0, .5);
            Point dpone = new Point(oreturned[0],oreturned[1],oreturned[2]);
            Point ptwo = v.getTwo();
            double[] tvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};
            double[] treturned = manipulate.translate(tvector, 0, 0, .5);
            Point dptwo = new Point(treturned[0],treturned[1],treturned[2]);            
            tempv.add(new Vector(dpone,dptwo));
        }
        vectors = tempv;        
        points = temp;
        redraw();        
    }
    
    public void left() {
        ArrayList<Point> temp = new ArrayList<Point>();
        for (Point p : points) {
            double[] vector = new double[]{p.getX(),p.getY(),p.getZ(),1};
            double[] returned = manipulate.translate(vector, .5, 0, 0);
            double afterx = returned[0];
            double aftery = returned[1];
            double afterz = returned[2];
            temp.add(new Point(afterx,aftery,afterz));
        }
        ArrayList<Vector> tempv = new ArrayList<Vector>();
        for (Vector v : vectors) {
            Point pone = v.getOne();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, .5, 0, 0);
            Point dpone = new Point(oreturned[0],oreturned[1],oreturned[2]);
            Point ptwo = v.getTwo();
            double[] tvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};
            double[] treturned = manipulate.translate(tvector, .5, 0, 0);
            Point dptwo = new Point(treturned[0],treturned[1],treturned[2]);            
            tempv.add(new Vector(dpone,dptwo));
        }
        vectors = tempv;        
        points = temp;
        redraw();        
    }
    
    public void right() {
        ArrayList<Point> temp = new ArrayList<Point>();
        for (Point p : points) {
            double[] vector = new double[]{p.getX(),p.getY(),p.getZ(),1};
            double[] returned = manipulate.translate(vector, -.5, 0, 0);
            double afterx = returned[0];
            double aftery = returned[1];
            double afterz = returned[2];
            temp.add(new Point(afterx,aftery,afterz));
        }
        ArrayList<Vector> tempv = new ArrayList<Vector>();
        for (Vector v : vectors) {
            Point pone = v.getOne();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, -.5, 0, 0);
            Point dpone = new Point(oreturned[0],oreturned[1],oreturned[2]);
            Point ptwo = v.getTwo();
            double[] tvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};
            double[] treturned = manipulate.translate(tvector, -.5, 0, 0);
            Point dptwo = new Point(treturned[0],treturned[1],treturned[2]);            
            tempv.add(new Vector(dpone,dptwo));
        }
        vectors = tempv;         
        points = temp;
        redraw();        
    }
    
    
    
    
}