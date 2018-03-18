public class Polygon {
    OtherPoint p1;
    OtherPoint p2;
    OtherPoint p3;
    public Polygon(OtherPoint pone, OtherPoint ptwo, OtherPoint pthree) {
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
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
}