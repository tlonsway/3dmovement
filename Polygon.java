public class Polygon {
    Point p1;
    Point p2;
    Point p3;
    public Polygon(Point pone, Point ptwo, Point pthree) {
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
    }
    public Point getOne() {
        return p1;
    }
    public Point getTwo() {
        return p2;
    }
    public Point getThree() {
        return p3;
    }
}