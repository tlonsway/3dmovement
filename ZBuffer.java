import java.util.*;
public class ZBuffer {
    public static ArrayList<ZObject> sortZ(ArrayList<ZObject> p2objects) {
        //double previous = -100000000;
        //double greatestz;
        /*ZObject largest = new ZObject();
        int besti = 0;
        objects.add(pobjects.get(0));
        pobjects.remove(0);
        for (ZObject p : pobjects) {
            for (int i=0; i<objects.size(); i++) {
                if (i==objects.size()-1) {
                    objects.add(i,p);
                }
                if (p.getZ()>=objects.get(i).getZ()) {
                    objects.add(i,p);
                    break;
                }
            }
        }*/
        
        
        Collections.sort(p2objects);
        //Collections.reverse(p2objects);
        return p2objects;
    } 
}