public class Vector {
    private OtherPoint p1;
    private OtherPoint p2;    
    public Vector(OtherPoint pone, OtherPoint ptwo) {
        p1 = pone;
        p2 = ptwo;
    }
    public void setOne(OtherPoint p) {
        p1 = p;
    }    
    public void setTwo(OtherPoint p) {
        p2 = p;
    }    
    public OtherPoint getOne() {
        return p1;
    }
    public OtherPoint getTwo() {
        return p2;
    }
}