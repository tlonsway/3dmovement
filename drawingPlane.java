import java.util.*;
import javax.swing.*;
import java.awt.*;
public class drawingPlane extends JComponent{
    //ArrayList<OtherPoint> points = new ArrayList<OtherPoint>(); // ArrayList of currently existed points, unprojected
    //ArrayList<Vector> vectors = new ArrayList<Vector>(); // ArrayList of currently existing vectors between points
    //ArrayList<Polygon> polygons = new ArrayList<Polygon>();
    //ArrayList<Cube> cubes = new ArrayList<Cube>();
    ArrayList<ZObject> pzobjects = new ArrayList<ZObject>();
    int[][][] map;
    int[][] depth;
    int WINDOW_WIDTH;
    int WINDOW_HEIGHT;
    int FOV;
    int ASPECT;
    public drawingPlane(int[][][] m, int[][] de, int width, int height, int fo) {
        super();
        WINDOW_WIDTH = width;
        WINDOW_HEIGHT = height;
        FOV = fo;
        //ASPECT = WINDOW_WIDTH/WINDOW_HEIGHT;
        //ASPECT = WINDOW_HEIGHT/WINDOW_WIDTH;
        ASPECT = 1;
        map = m;
        depth = de;
        boolean dcheck;
        //long mapStartTime = System.nanoTime();
        for (int d=0;d<map.length;d++) {
            for (int w=0;w<map[0].length;w++) {
                for (int l=0;l<map[0][0].length;l++) {
                    if (map[d][w][l] == 1) {
                        Cube c = Shapes.genCube(w,d,l);
                        //cubes.add(c);
                        //ArrayList<OtherPoint> po = c.getPoints();
                        for (ZObject zo : c.getZObjects()) {
                            //System.out.println("Processing ZObject");
                            pzobjects.add(zo);
                        }
                        
                        //pzobjects.addAll(c.getZObjects());
                        //ArrayList<Vector> ve = c.getVectors();
                        //ArrayList<Polygon> pol = c.getPolygons();
                        //for (OtherPoint p : po) {
                        //    points.add(p);
                        //}
                        //for (Vector v : ve) {
                        //    vectors.add(v);
                        //}
                        //for (Polygon p : pol) {
                        //    polygons.add(p);
                        //}
                    }
                }
            }
        }        
        //System.out.println("MAP GEN TOOK: " + (System.nanoTime()-mapStartTime)/1000000000.0 + " seconds");
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

        
        //ZBUFFER
        //SORT EDGES AND POLYGONS BY Z
        //long zsortstartTime = System.nanoTime();
        ArrayList<ZObject> zobjects = ZBuffer.sortZ(pzobjects);
        //System.out.println("ZBUFFER SORT TOOK: " + (System.nanoTime()-zsortstartTime)/1000000000.0 + " seconds");        
        //System.out.println("Amount of rendered objects in plane: " + zobjects.size());
        //System.out.println("Amount of unrendered objects in plane: " + pzobjects.size());
        //long drawstartTime = System.nanoTime();
        for (ZObject z : zobjects) {
            if (z.getType().equals("Vector")) {
                g.setColor(Color.BLACK);
                Vector v = z.getVector();
                if (v.getOne().getZ() > 0 && v.getTwo().getZ() > 0) {
                    OtherPoint pone = v.getOne();
                    double onex = pone.getX();
                    double oney = pone.getY();
                    double onez = pone.getZ();
                    double[] oneproj = project.project2D(new double[]{onex,oney,onez,1},FOV,ASPECT,5.0,100.0);
                    double oneax = oneproj[0];
                    double oneay = oneproj[1];
                    OtherPoint ptwo = v.getTwo();
                    double twox = ptwo.getX();
                    double twoy = ptwo.getY();
                    double twoz = ptwo.getZ();
                    double[] twoproj = project.project2D(new double[]{twox,twoy,twoz,1},FOV,ASPECT,5.0,100.0);            
                    double twoax = twoproj[0];
                    double twoay = twoproj[1];
                    if (oneax > -50 && oneax < 800 && oneay > 0 && oneay < 800) {
                        g.drawLine((int)(WINDOW_WIDTH*oneax), (int)(WINDOW_HEIGHT*oneay), (int)(WINDOW_WIDTH*twoax), (int)(WINDOW_HEIGHT*twoay));
                    }
                }                
            }
            if (z.getType().equals("Polygon")) {
                Polygon p = z.getPolygon();
                if (p.getOne().getZ() > 0 && p.getTwo().getZ() > 0 && p.getThree().getZ() > 0) {
                    OtherPoint pone = p.getOne();
                    double onex = pone.getX();
                    double oney = pone.getY();
                    double onez = pone.getZ();
                    double[] oneproj = project.project2D(new double[]{onex,oney,onez,1},FOV,ASPECT,5.0,100.0);
                    double oneax = oneproj[0];
                    double oneay = oneproj[1];
                    OtherPoint ptwo = p.getTwo();
                    double twox = ptwo.getX();
                    double twoy = ptwo.getY();
                    double twoz = ptwo.getZ();
                    double[] twoproj = project.project2D(new double[]{twox,twoy,twoz,1},FOV,ASPECT,5.0,100.0);           
                    double twoax = twoproj[0];
                    double twoay = twoproj[1];
                    OtherPoint pthree = p.getThree();
                    double threex = pthree.getX();
                    double threey = pthree.getY();
                    double threez = pthree.getZ();
                    double[] threeproj = project.project2D(new double[]{threex,threey,threez,1},FOV,ASPECT,5.0,100.0);
                    double threeax = threeproj[0];
                    double threeay = threeproj[1];
                    int[] xp = new int[]{(int)(WINDOW_WIDTH*oneax),(int)(WINDOW_WIDTH*twoax),(int)(WINDOW_WIDTH*threeax)};
                    int[] yp = new int[]{(int)(WINDOW_HEIGHT*oneay),(int)(WINDOW_HEIGHT*twoay),(int)(WINDOW_HEIGHT*threeay)};
                    int avy = (int)((oney+twoy+threey)/3);                  
                    g.setColor(p.getColor());
                    if (oneax > -50 && oneax < 800 && oneay > 0 && oneay < 800) {
                        //g.drawPolygon(xp,yp,3);
                        g.fillPolygon(xp,yp,3);                    
                    }                                
                }                   
            }
        }
        //System.out.println("Frame generation took " + (System.nanoTime()-startTime));
        //System.out.println("FRAME DRAW TOOK: " + (System.nanoTime()-drawstartTime)/1000000000.0 + " seconds");             
    }
    
    
    public void move(char dir, double dis) {
        //long moveStartTime = System.nanoTime();
        ArrayList<ZObject> tempzobj = new ArrayList<ZObject>();
        double xdist = 0;
        double ydist = 0;
        double zdist = 0;
        if (dir == 'x') {
            xdist = dis;
        }
        if (dir == 'y') {
            ydist = dis;
        }
        if (dir == 'z') {
            zdist = dis;
        }
        for (ZObject zo : pzobjects) {
            if (zo.getType().equals("Vector")) {
                OtherPoint pone = zo.getOne();
                double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};
                double[] oreturned = manipulate.translate(ovector, xdist, ydist, zdist);
                OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
                OtherPoint ptwo = zo.getTwo();
                double[] tvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};
                double[] treturned = manipulate.translate(tvector, xdist, ydist, zdist);
                OtherPoint dptwo = new OtherPoint(treturned[0],treturned[1],treturned[2]);            
                tempzobj.add(new ZObject(dpone,dptwo));               
            }
            if (zo.getType().equals("Polygon")) {
                OtherPoint pone = zo.getOne();
                OtherPoint ptwo = zo.getTwo();
                OtherPoint pthree = zo.getThree();
                double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};            
                double[] twvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};            
                double[] trvector = new double[]{pthree.getX(),pthree.getY(),pthree.getZ(),1};
                double[] oreturned = manipulate.translate(ovector, xdist, ydist, zdist);            
                double[] twreturned = manipulate.translate(twvector, xdist, ydist, zdist);
                double[] trreturned = manipulate.translate(trvector, xdist, ydist, zdist);
                OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
                OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
                OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
                tempzobj.add(new ZObject(dpone,dptwo,dpthree,zo.getColor()));                
            }
        }
        pzobjects = tempzobj;
        //System.out.println("TRANSLATION MOVEMENT TOOK: " + (System.nanoTime()-moveStartTime)/1000000000.0 + " seconds");     
        redraw();
    }
    public void look(char ax, double angle) {
        //long lookStartTime = System.nanoTime();
        ArrayList<ZObject> tempzobj = new ArrayList<ZObject>();
        for (ZObject zo : pzobjects) {
            if (zo.getType().equals("Vector")) {
                OtherPoint pone = zo.getOne();
                double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};
                double[] oreturned = manipulate.rotate(ovector, ax, angle);
                OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
                OtherPoint ptwo = zo.getTwo();
                double[] tvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};
                double[] treturned = manipulate.rotate(tvector, ax, angle);
                OtherPoint dptwo = new OtherPoint(treturned[0],treturned[1],treturned[2]);            
                tempzobj.add(new ZObject(dpone,dptwo));               
            }
            if (zo.getType().equals("Polygon")) {
                OtherPoint pone = zo.getOne();
                OtherPoint ptwo = zo.getTwo();
                OtherPoint pthree = zo.getThree();
                double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};            
                double[] twvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};            
                double[] trvector = new double[]{pthree.getX(),pthree.getY(),pthree.getZ(),1};
                double[] oreturned = manipulate.rotate(ovector, ax, angle);          
                double[] twreturned = manipulate.rotate(twvector, ax, angle);
                double[] trreturned = manipulate.rotate(trvector, ax, angle);
                OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
                OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
                OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
                tempzobj.add(new ZObject(dpone,dptwo,dpthree,zo.getColor()));                
            }
        }
        pzobjects = tempzobj;
        //System.out.println("TRANSFORMED OBJECT COUNT: " + tempzobj.size());
        //System.out.println("ROTATION MOVEMENT TOOK: " + (System.nanoTime()-lookStartTime)/1000000000.0 + " seconds");          
        redraw();
    }
    //double[] trreturned = manipulate.rotate(trvector, axis, angle);
}