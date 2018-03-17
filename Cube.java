import java.util.*;
public class Cube {
    ArrayList<Point> points;
    ArrayList<Vector> vectors;
    public Cube(ArrayList<Point> p, ArrayList<Vector> v) {
        points = p;
        vectors = v;
    }
    public void setPoints(ArrayList<Point> p) {
        points = p;
    }
    public void setVectors(ArrayList<Vector> v) {
        vectors = v;
    }
    public ArrayList<Point> getPoints() {
        return points;
    }
    public ArrayList<Vector> getVectors() {
        return vectors;
    }
}