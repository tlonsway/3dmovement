import java.util.*;
import java.awt.*;
public class ZObject implements Comparable<ZObject> {
    String type;
    OtherPoint p1;
    OtherPoint p2;
    OtherPoint p3;
    Color c;
    public ZObject() {
    }    
    public ZObject(OtherPoint pone, OtherPoint ptwo) {
        type = "Vector";
        p1 = pone;
        p2 = ptwo;
    }
    public ZObject(OtherPoint pone, OtherPoint ptwo, OtherPoint pthree, Color co) {
        type = "Polygon";
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
        c = co;
    }
    public ZObject(OtherPoint pone, OtherPoint ptwo, OtherPoint pthree) {
        type = "Polygon";
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
        //c = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        c = new Color(0,(int)(Math.random()*256),0);
    }    
    public double getZ() {
        //FIND POINT WITH CLOSEST Z
        /*OtherPoint closestpoint = p1;
        if (p2.getZ()<closestpoint.getZ()) {
            closestpoint=p2;
        }
        if (type.equals("Polygon") && p3.getZ()>closestpoint.getZ()) {
            closestpoint=p3;
        }*/
        //return closestpoint.getZ();
        if (type.equals("Polygon")) {
            return (p1.getZ()+p2.getZ()+p3.getZ())/3;
        } else {
            return (p1.getZ()+p2.getZ())/2;
        }
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
        return new Polygon(p1,p2,p3,c);
    }
    public Vector getVector() {
        return new Vector(p1,p2);
    }
    public Color getColor() {
        return c;
    }
    public int compareTo(ZObject other) {
        double otherZ = other.getZ();
        double thisZ = this.getZ();
        int ret;
        if (thisZ > otherZ)
            return -1;
        else if (thisZ < otherZ)
            return 1;
        else
            return 0;
    }
    public double[] getOneList() {
        return new double[]{p1.getX(),p1.getY(),p1.getZ(),1};
    }
    public double[] getTwoList() {
        return new double[]{p2.getX(),p2.getY(),p2.getZ(),1};
    }
    public double[] getThreeList() {
        return new double[]{p3.getX(),p3.getY(),p3.getZ(),1};
    }    
}