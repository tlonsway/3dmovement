import java.util.*;
public class ZBuffer {
    public static ArrayList<ZObject> sortZ(ArrayList<Vector> vectors, ArrayList<Polygon> polygons) {
        ArrayList<ZObject> pobjects = new ArrayList<ZObject>();
        //System.out.println("vectors: " + vectors.size());
        //System.out.println("polygons: " + polygons.size());
        
        for (Vector v : vectors) {
            pobjects.add(new ZObject(v.getOne(), v.getTwo()));
        }
        for (Polygon p : polygons) {
            pobjects.add(new ZObject(p.getOne(),p.getTwo(),p.getThree(),p.getColor()));
        }
        ArrayList<ZObject> objects = new ArrayList<ZObject>();
        double previous = -100000000;
        //double greatestz;
        ZObject largest = new ZObject();
        int besti = 0;
        while (pobjects.size()>0) {
            previous = -10000000;
            besti = 0;
            for (int i=0;i<pobjects.size();i++) {
                if (pobjects.get(i).getZ() > previous) {
                    previous = pobjects.get(i).getZ();
                    largest = pobjects.get(i);
                    besti = i;
                }
            }
            //System.out.println("i: " + besti);
            //System.out.println("pobjects length: " + pobjects.size());
            //System.out.println("previous: " + previous);
            pobjects.remove(besti);
            objects.add(largest);
            //System.out.println("SORT PASS COMPLETE");
        }
        return objects;
    } 
}