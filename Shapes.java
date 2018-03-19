import java.util.*;
import java.awt.*;
public class Shapes {
    public static Cube genCube(double x, double y, double z) {
        //Cubes are 1x1x1
        ArrayList<OtherPoint> points = new ArrayList<OtherPoint>();
        points.add(new OtherPoint(x,y,z));
        points.add(new OtherPoint(x+1,y,z));
        points.add(new OtherPoint(x,y+1,z));
        points.add(new OtherPoint(x+1,y+1,z));
        points.add(new OtherPoint(x,y,z+1));
        points.add(new OtherPoint(x+1,y,z+1));
        points.add(new OtherPoint(x,y+1,z+1));
        points.add(new OtherPoint(x+1,y+1,z+1));
        //GENERATE VECTORS 
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
        vectors.add(new Vector(points.get(6),points.get(7)));
        //GENERATE POLYGONS
        ArrayList<Polygon> polygons = new ArrayList<Polygon>();
        Color co = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        polygons.add(new Polygon(points.get(0),points.get(1),points.get(4),co));
        polygons.add(new Polygon(points.get(1),points.get(3),points.get(5),co));
        polygons.add(new Polygon(points.get(4),points.get(5),points.get(6),co));
        polygons.add(new Polygon(points.get(1),points.get(4),points.get(5),co));
        polygons.add(new Polygon(points.get(3),points.get(5),points.get(7),co));
        polygons.add(new Polygon(points.get(5),points.get(6),points.get(7),co));
        polygons.add(new Polygon(points.get(0),points.get(2),points.get(6),co));
        polygons.add(new Polygon(points.get(0),points.get(4),points.get(6),co));
        polygons.add(new Polygon(points.get(0),points.get(1),points.get(2),co));
        polygons.add(new Polygon(points.get(2),points.get(3),points.get(6),co));
        polygons.add(new Polygon(points.get(3),points.get(6),points.get(7),co));
        polygons.add(new Polygon(points.get(1),points.get(2),points.get(3),co));
        return new Cube(points, vectors, polygons);
    }
}