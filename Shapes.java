import java.util.*;
public class Shapes {
    public static Cube genCube(double x, double y, double z) {
        //Cubes are 4x4x4
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(x,y,z));
        points.add(new Point(x+4,y,z));
        points.add(new Point(x,y+4,z));
        points.add(new Point(x+4,y+4,z));
        points.add(new Point(x,y,z+4));
        points.add(new Point(x+4,y,z+4));
        points.add(new Point(x,y+4,z+4));
        points.add(new Point(x+4,y+4,z+4));
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
        return new Cube(points, vectors);
    }
}