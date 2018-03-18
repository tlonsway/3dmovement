import java.util.*;
public class ZObject {
    String type;
    OtherPoint p1;
    OtherPoint p2;
    OtherPoint p3;
    public ZObject() {
    }    
    public ZObject(OtherPoint pone, OtherPoint ptwo) {
        type = "Vector";
        p1 = pone;
        p2 = ptwo;
    }
    public ZObject(OtherPoint pone, OtherPoint ptwo, OtherPoint pthree) {
        type = "Polygon";
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
    }
    public double getZ() {
        //FIND POINT WITH FARTHEST Z
        OtherPoint farthestpoint = p1;
        if (p2.getZ()>farthestpoint.getZ()) {
            farthestpoint=p2;
        }
        if (type.equals("Polygon") && p3.getZ()>farthestpoint.getZ()) {
            farthestpoint=p3;
        }
        return farthestpoint.getZ();
    }
    public String getType() {
        return type;
    }
    public OtherPoint getOne() {
        return p1;
    }
    public OtherPoint getTwo() {
        return p2;
    }
    public OtherPoint getThree() {
        return p3;
    }
    public Polygon getPolygon() {
        return new Polygon(p1,p2,p3);
    }
    public Vector getVector() {
        return new Vector(p1,p2);
    }
}