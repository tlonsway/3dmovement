import java.util.*;
public class Cube {
    ArrayList<OtherPoint> points;
    ArrayList<Vector> vectors;
    ArrayList<Polygon> polygons;
    public Cube(ArrayList<OtherPoint> p, ArrayList<Vector> v, ArrayList<Polygon> po) {
        points = p;
        vectors = v;
        polygons = po;
    }
    public void setPoints(ArrayList<OtherPoint> p) {
        points = p;
    }
    public void setVectors(ArrayList<Vector> v) {
        vectors = v;
    }
    public void setPolygons(ArrayList<Polygon> p) {
        polygons = p;
    }
    public ArrayList<OtherPoint> getPoints() {
        return points;
    }
    public ArrayList<Vector> getVectors() {
        return vectors;
    }
    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }
}