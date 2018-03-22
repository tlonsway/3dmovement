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
    public double[] getOneList() {
        return new double[]{p1.getX(),p1.getY(),p1.getZ(),1};
    }
    public double[] getTwoList() {
        return new double[]{p2.getX(),p2.getY(),p2.getZ(),1};
    }
}