import java.util.*;
import javax.swing.*;
import java.awt.*;
public class drawingPlane extends JComponent{
    ArrayList<OtherPoint> points = new ArrayList<OtherPoint>(); // ArrayList of currently existed points, unprojected
    ArrayList<Vector> vectors = new ArrayList<Vector>(); // ArrayList of currently existing vectors between points
    ArrayList<Polygon> polygons = new ArrayList<Polygon>();
    ArrayList<Cube> cubes = new ArrayList<Cube>();
    //ARRAY FOR ZOBJECTS 
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
        ASPECT = WINDOW_WIDTH/WINDOW_HEIGHT;
        map = m;
        depth = de;
        boolean dcheck;
        for (int d=0;d<map.length;d++) {
            for (int w=0;w<map[0].length;w++) {
                for (int l=0;l<map[0][0].length;l++) {
                    if (map[d][w][l] == 1) {
                        Cube c = Shapes.genCube(w,d,l);
                        cubes.add(c);
                        ArrayList<OtherPoint> po = c.getPoints();
                        ArrayList<Vector> ve = c.getVectors();
                        ArrayList<Polygon> pol = c.getPolygons();
                        for (OtherPoint p : po) {
                            points.add(p);
                        }
                        for (Vector v : ve) {
                            vectors.add(v);
                        }
                        for (Polygon p : pol) {
                            polygons.add(p);
                        }
                    }
                }
            }
        }        
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
        //long startTime = System.nanoTime();
        ArrayList<ZObject> zobjects = ZBuffer.sortZ(vectors, polygons);
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
                        g.drawPolygon(xp,yp,3);
                        g.fillPolygon(xp,yp,3);                    
                    }                                
                }                   
            }
        }
        //System.out.println("Frame generation took " + (System.nanoTime()-startTime));
        
        
        
        
        //System.out.println("Points in plane: " + points.size());
        //System.out.println("Vectors in plane: " + vectors.size());        
        //System.out.println("Polygons in plane: " + polygons.size());        
        
        
        
        
        /*
        for (Polygon p : polygons) {
            if (p.getOne().getZ() > 0 && p.getTwo().getZ() > 0 && p.getThree().getZ() > 0) {
                OtherPoint pone = p.getOne();
                double onex = pone.getX();
                double oney = pone.getY();
                double onez = pone.getZ();
                double[] oneproj = project.project2D(new double[]{onex,oney,onez,1},75.0,1.0,5.0,100.0);
                double oneax = oneproj[0];
                double oneay = oneproj[1];
                OtherPoint ptwo = p.getTwo();
                double twox = ptwo.getX();
                double twoy = ptwo.getY();
                double twoz = ptwo.getZ();
                double[] twoproj = project.project2D(new double[]{twox,twoy,twoz,1},75.0,1.0,5.0,100.0);            
                double twoax = twoproj[0];
                double twoay = twoproj[1];
                OtherPoint pthree = p.getThree();
                double threex = pthree.getX();
                double threey = pthree.getY();
                double threez = pthree.getZ();
                double[] threeproj = project.project2D(new double[]{threex,threey,threez,1},75.0,1.0,5.0,100.0);
                double threeax = threeproj[0];
                double threeay = threeproj[1];
                int[] xp = new int[]{(int)(800*oneax),(int)(800*twoax),(int)(800*threeax)};
                int[] yp = new int[]{(int)(800*oneay),(int)(800*twoay),(int)(800*threeay)};
                g.setColor(Color.GREEN);
                if (oneax > -50 && oneax < 800 && oneay > 0 && oneay < 800) {
                    g.drawPolygon(xp,yp,3);
                    g.fillPolygon(xp,yp,3);                    
                }                                
            }    
        }
        g.setColor(Color.RED);
        for (Vector v : vectors) {
            if (v.getOne().getZ() > 0 && v.getTwo().getZ() > 0) {
                OtherPoint pone = v.getOne();
                double onex = pone.getX();
                double oney = pone.getY();
                double onez = pone.getZ();
                double[] oneproj = project.project2D(new double[]{onex,oney,onez,1},75.0,1.0,5.0,100.0);
                double oneax = oneproj[0];
                double oneay = oneproj[1];
                OtherPoint ptwo = v.getTwo();
                double twox = ptwo.getX();
                double twoy = ptwo.getY();
                double twoz = ptwo.getZ();
                double[] twoproj = project.project2D(new double[]{twox,twoy,twoz,1},75.0,1.0,5.0,100.0);            
                double twoax = twoproj[0];
                double twoay = twoproj[1];
                if (oneax > -50 && oneax < 800 && oneay > 0 && oneay < 800) {
                    g.drawLine((int)(800*oneax), (int)(800*oneay), (int)(800*twoax), (int)(800*twoay));
                }
            }
        }
        */
    }
    public void forwards() {
        ArrayList<OtherPoint> p2 = points;
        ArrayList<Vector> v2 = vectors;
        ArrayList<Polygon> pol2 = polygons;           
        ArrayList<OtherPoint> temp = new ArrayList<OtherPoint>();
        for (OtherPoint p : p2) {
            double[] vector = new double[]{p.getX(),p.getY(),p.getZ(),1};
            double[] returned = manipulate.translate(vector, 0, 0, -.1);
            double afterx = returned[0];
            double aftery = returned[1];
            double afterz = returned[2];
            temp.add(new OtherPoint(afterx,aftery,afterz));
        }
        ArrayList<Vector> tempv = new ArrayList<Vector>();
        for (Vector v : v2) {
            OtherPoint pone = v.getOne();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, 0, 0, -.1);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint ptwo = v.getTwo();
            double[] tvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};
            double[] treturned = manipulate.translate(tvector, 0, 0, -.1);
            OtherPoint dptwo = new OtherPoint(treturned[0],treturned[1],treturned[2]);            
            tempv.add(new Vector(dpone,dptwo));
        }
        ArrayList<Polygon> tempo = new ArrayList<Polygon>();
        for (Polygon p : pol2) {
            OtherPoint pone = p.getOne();
            OtherPoint ptwo = p.getTwo();
            OtherPoint pthree = p.getThree();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};            
            double[] twvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};            
            double[] trvector = new double[]{pthree.getX(),pthree.getY(),pthree.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, 0, 0, -.1);            
            double[] twreturned = manipulate.translate(twvector, 0, 0, -.1);
            double[] trreturned = manipulate.translate(trvector, 0, 0, -.1);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
            OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
            Polygon poly = new Polygon(dpone,dptwo,dpthree,p.getColor());
            tempo.add(poly);
        }
        polygons = tempo;
        vectors = tempv;
        points = temp;
        redraw();
    }
    public void backwards() {
        ArrayList<OtherPoint> p2 = points;
        ArrayList<Vector> v2 = vectors;
        ArrayList<Polygon> pol2 = polygons;           
        ArrayList<OtherPoint> temp = new ArrayList<OtherPoint>();
        for (OtherPoint p : p2) {
            double[] vector = new double[]{p.getX(),p.getY(),p.getZ(),1};
            double[] returned = manipulate.translate(vector, 0, 0, .1);
            double afterx = returned[0];
            double aftery = returned[1];
            double afterz = returned[2];
            temp.add(new OtherPoint(afterx,aftery,afterz));
        }
        ArrayList<Vector> tempv = new ArrayList<Vector>();
        for (Vector v : v2) {
            OtherPoint pone = v.getOne();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, 0, 0, .1);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint ptwo = v.getTwo();
            double[] tvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};
            double[] treturned = manipulate.translate(tvector, 0, 0, .1);
            OtherPoint dptwo = new OtherPoint(treturned[0],treturned[1],treturned[2]);            
            tempv.add(new Vector(dpone,dptwo));
        }
        ArrayList<Polygon> tempo = new ArrayList<Polygon>();
        for (Polygon p : pol2) {
            OtherPoint pone = p.getOne();
            OtherPoint ptwo = p.getTwo();
            OtherPoint pthree = p.getThree();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};            
            double[] twvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};            
            double[] trvector = new double[]{pthree.getX(),pthree.getY(),pthree.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, 0, 0, .1);            
            double[] twreturned = manipulate.translate(twvector, 0, 0, .1);
            double[] trreturned = manipulate.translate(trvector, 0, 0, .1);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
            OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
            Polygon poly = new Polygon(dpone,dptwo,dpthree,p.getColor());
            tempo.add(poly);
        }
        polygons = tempo;        
        vectors = tempv;        
        points = temp;
        redraw();        
    }
    public void left() {
        ArrayList<OtherPoint> p2 = points;
        ArrayList<Vector> v2 = vectors;
        ArrayList<Polygon> pol2 = polygons;           
        ArrayList<OtherPoint> temp = new ArrayList<OtherPoint>();
        for (OtherPoint p : p2) {
            double[] vector = new double[]{p.getX(),p.getY(),p.getZ(),1};
            double[] returned = manipulate.translate(vector, .1, 0, 0);
            double afterx = returned[0];
            double aftery = returned[1];
            double afterz = returned[2];
            temp.add(new OtherPoint(afterx,aftery,afterz));
        }
        ArrayList<Vector> tempv = new ArrayList<Vector>();
        for (Vector v : v2) {
            OtherPoint pone = v.getOne();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, .1, 0, 0);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint ptwo = v.getTwo();
            double[] tvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};
            double[] treturned = manipulate.translate(tvector, .1, 0, 0);
            OtherPoint dptwo = new OtherPoint(treturned[0],treturned[1],treturned[2]);            
            tempv.add(new Vector(dpone,dptwo));
        }
        ArrayList<Polygon> tempo = new ArrayList<Polygon>();
        for (Polygon p : pol2) {
            OtherPoint pone = p.getOne();
            OtherPoint ptwo = p.getTwo();
            OtherPoint pthree = p.getThree();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};            
            double[] twvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};            
            double[] trvector = new double[]{pthree.getX(),pthree.getY(),pthree.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, .1, 0, 0);            
            double[] twreturned = manipulate.translate(twvector, .1, 0, 0);
            double[] trreturned = manipulate.translate(trvector, .1, 0, 0);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
            OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
            Polygon poly = new Polygon(dpone,dptwo,dpthree,p.getColor());
            tempo.add(poly);
        }
        polygons = tempo;        
        vectors = tempv;        
        points = temp;
        redraw();        
    }
    public void right() {
        ArrayList<OtherPoint> p2 = points;
        ArrayList<Vector> v2 = vectors;
        ArrayList<Polygon> pol2 = polygons;           
        ArrayList<OtherPoint> temp = new ArrayList<OtherPoint>();
        for (OtherPoint p : p2) {
            double[] vector = new double[]{p.getX(),p.getY(),p.getZ(),1};
            double[] returned = manipulate.translate(vector, -.1, 0, 0);
            double afterx = returned[0];
            double aftery = returned[1];
            double afterz = returned[2];
            temp.add(new OtherPoint(afterx,aftery,afterz));
        }
        ArrayList<Vector> tempv = new ArrayList<Vector>();
        for (Vector v : v2) {
            OtherPoint pone = v.getOne();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, -.1, 0, 0);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint ptwo = v.getTwo();
            double[] tvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};
            double[] treturned = manipulate.translate(tvector, -.1, 0, 0);
            OtherPoint dptwo = new OtherPoint(treturned[0],treturned[1],treturned[2]);            
            tempv.add(new Vector(dpone,dptwo));
        }
        ArrayList<Polygon> tempo = new ArrayList<Polygon>();
        for (Polygon p : pol2) {
            OtherPoint pone = p.getOne();
            OtherPoint ptwo = p.getTwo();
            OtherPoint pthree = p.getThree();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};            
            double[] twvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};            
            double[] trvector = new double[]{pthree.getX(),pthree.getY(),pthree.getZ(),1};
            double[] oreturned = manipulate.translate(ovector,-.1, 0, 0);            
            double[] twreturned = manipulate.translate(twvector, -.1, 0, 0);
            double[] trreturned = manipulate.translate(trvector, -.1, 0, 0);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
            OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
            Polygon poly = new Polygon(dpone,dptwo,dpthree,p.getColor());
            tempo.add(poly);
        }
        polygons = tempo;        
        vectors = tempv;         
        points = temp;
        redraw();        
    }
    public void up() {
        ArrayList<OtherPoint> p2 = points;
        ArrayList<Vector> v2 = vectors;
        ArrayList<Polygon> pol2 = polygons;          
        ArrayList<OtherPoint> temp = new ArrayList<OtherPoint>();
        for (OtherPoint p : p2) {
            double[] vector = new double[]{p.getX(),p.getY(),p.getZ(),1};
            double[] returned = manipulate.translate(vector, 0, .1, 0);
            double afterx = returned[0];
            double aftery = returned[1];
            double afterz = returned[2];
            temp.add(new OtherPoint(afterx,aftery,afterz));
        }
        ArrayList<Vector> tempv = new ArrayList<Vector>();
        for (Vector v : v2) {
            OtherPoint pone = v.getOne();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, 0, .1, 0);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint ptwo = v.getTwo();
            double[] tvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};
            double[] treturned = manipulate.translate(tvector, 0, .1, 0);
            OtherPoint dptwo = new OtherPoint(treturned[0],treturned[1],treturned[2]);            
            tempv.add(new Vector(dpone,dptwo));
        }
        ArrayList<Polygon> tempo = new ArrayList<Polygon>();
        for (Polygon p : pol2) {
            OtherPoint pone = p.getOne();
            OtherPoint ptwo = p.getTwo();
            OtherPoint pthree = p.getThree();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};            
            double[] twvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};            
            double[] trvector = new double[]{pthree.getX(),pthree.getY(),pthree.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, 0, .1, 0);            
            double[] twreturned = manipulate.translate(twvector, 0, .1, 0);
            double[] trreturned = manipulate.translate(trvector, 0, .1, 0);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
            OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
            Polygon poly = new Polygon(dpone,dptwo,dpthree,p.getColor());
            tempo.add(poly);
        }
        polygons = tempo;        
        vectors = tempv;         
        points = temp;
        redraw();        
    }    
    public void down() {
        ArrayList<OtherPoint> p2 = points;
        ArrayList<Vector> v2 = vectors;
        ArrayList<Polygon> pol2 = polygons;        
        ArrayList<OtherPoint> temp = new ArrayList<OtherPoint>();
        for (OtherPoint p : p2) {
            double[] vector = new double[]{p.getX(),p.getY(),p.getZ(),1};
            double[] returned = manipulate.translate(vector, 0, -.1, 0);
            double afterx = returned[0];
            double aftery = returned[1];
            double afterz = returned[2];
            temp.add(new OtherPoint(afterx,aftery,afterz));
        }
        ArrayList<Vector> tempv = new ArrayList<Vector>();
        for (Vector v : v2) {
            OtherPoint pone = v.getOne();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, 0, -.1, 0);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint ptwo = v.getTwo();
            double[] tvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};
            double[] treturned = manipulate.translate(tvector, 0, -.1, 0);
            OtherPoint dptwo = new OtherPoint(treturned[0],treturned[1],treturned[2]);            
            tempv.add(new Vector(dpone,dptwo));
        }
        ArrayList<Polygon> tempo = new ArrayList<Polygon>();
        for (Polygon p : pol2) {
            OtherPoint pone = p.getOne();
            OtherPoint ptwo = p.getTwo();
            OtherPoint pthree = p.getThree();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};            
            double[] twvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};            
            double[] trvector = new double[]{pthree.getX(),pthree.getY(),pthree.getZ(),1};
            double[] oreturned = manipulate.translate(ovector, 0, -.1, 0);            
            double[] twreturned = manipulate.translate(twvector, 0, -.1, 0);
            double[] trreturned = manipulate.translate(trvector, 0, -.1, 0);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
            OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
            Polygon poly = new Polygon(dpone,dptwo,dpthree,p.getColor());
            tempo.add(poly);
        }
        polygons = tempo;        
        vectors = tempv;         
        points = temp;
        redraw();        
    }
    public void look(char ax, double angle) {
        ArrayList<OtherPoint> p2 = points;
        ArrayList<Vector> v2 = vectors;
        ArrayList<Polygon> pol2 = polygons;
        char axis = ax;
        ArrayList<OtherPoint> temp = new ArrayList<OtherPoint>();
        for (OtherPoint p : p2) {
            double[] vector = new double[]{p.getX(),p.getY(),p.getZ(),1};
            double[] returned = manipulate.rotate(vector, axis, angle);
            double afterx = returned[0];
            double aftery = returned[1];
            double afterz = returned[2];
            temp.add(new OtherPoint(afterx,aftery,afterz));
        }
        ArrayList<Vector> tempv = new ArrayList<Vector>();
        for (Vector v : v2) {
            OtherPoint pone = v.getOne();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};
            double[] oreturned = manipulate.rotate(ovector, axis, angle);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint ptwo = v.getTwo();
            double[] tvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};
            double[] treturned = manipulate.rotate(tvector, axis, angle);
            OtherPoint dptwo = new OtherPoint(treturned[0],treturned[1],treturned[2]);            
            tempv.add(new Vector(dpone,dptwo));
        }
        ArrayList<Polygon> tempo = new ArrayList<Polygon>();
        for (Polygon p : pol2) {
            OtherPoint pone = p.getOne();
            OtherPoint ptwo = p.getTwo();
            OtherPoint pthree = p.getThree();
            double[] ovector = new double[]{pone.getX(),pone.getY(),pone.getZ(),1};            
            double[] twvector = new double[]{ptwo.getX(),ptwo.getY(),ptwo.getZ(),1};            
            double[] trvector = new double[]{pthree.getX(),pthree.getY(),pthree.getZ(),1};
            double[] oreturned = manipulate.rotate(ovector, axis, angle);            
            double[] twreturned = manipulate.rotate(twvector, axis, angle);
            double[] trreturned = manipulate.rotate(trvector, axis, angle);
            OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
            OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
            OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
            Polygon poly = new Polygon(dpone,dptwo,dpthree,p.getColor());
            tempo.add(poly);
        }
        polygons = tempo;        
        vectors = tempv;        
        points = temp;
        redraw();     
    }
}