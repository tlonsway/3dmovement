public class Vector {
    private Point p1;
    private Point p2;    
    public Vector(Point pone, Point ptwo) {
        p1 = pone;
        p2 = ptwo;
    }
    public void setOne(Point p) {
        p1 = p;
    }    
    public void setTwo(Point p) {
        p2 = p;
    }    
    public Point getOne() {
        return p1;
    }
    public Point getTwo() {
        return p2;
    }
}