import java.awt.*;
public class Polygon {
    OtherPoint p1;
    OtherPoint p2;
    OtherPoint p3;
    Color c;
    public Polygon(OtherPoint pone, OtherPoint ptwo, OtherPoint pthree) {
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
        c = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
    }
    public Polygon(OtherPoint pone, OtherPoint ptwo, OtherPoint pthree, Color co) {
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
        c = co;
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
    public Color getColor() {
        return c;
    }
}