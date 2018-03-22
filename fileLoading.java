import java.util.*;
import java.io.*;
public class fileLoading {
    public static ArrayList<ZObject> loadOBJ(String filename) {
        ArrayList<ZObject> objects = new ArrayList<ZObject>();
        ArrayList<OtherPoint> points = new ArrayList<OtherPoint>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 1) {
                    if (line.substring(0,2).equals("v ")) {
                        //System.out.println(line);
                        line = line.substring(line.indexOf(" ")+2);
                        //System.out.println(line);
                        double x = Double.parseDouble(line.substring(0,line.indexOf(" ")));
                        line = line.substring(line.indexOf(" ")+1);
                        double y = Double.parseDouble(line.substring(0,line.indexOf(" ")));
                        line = line.substring(line.indexOf(" ")+1);
                        double z = Double.parseDouble(line);
                        points.add(new OtherPoint(x,y,z));
                    }
                    if (line.substring(0,2).equals("f ")) {
                        //System.out.println(line);
                        line = line.substring(line.indexOf(" ")+1);
                        String s1 = line.substring(0,line.indexOf(" "));
                        line = line.substring(line.indexOf(" ")+1);
                        String s2 = line.substring(0,line.indexOf(" "));
                        line = line.substring(line.indexOf(" ")+1);
                        String s3 = line;
                        int l1;
                        int l2;
                        int l3;
                        if (s1.indexOf("/") != -1) {
                            l1 = Integer.parseInt(s1.substring(0,s1.indexOf("/")));
                        } else {
                            l1 = Integer.parseInt(s1);
                        }
                        if (s2.indexOf("/") != -1) {
                            l2 = Integer.parseInt(s2.substring(0,s2.indexOf("/")));
                        } else {
                            l2 = Integer.parseInt(s2);
                        }
                        if (s3.indexOf("/") != -1) {
                            l3 = Integer.parseInt(s3.substring(0,s3.indexOf("/")));
                        } else {
                            l3 = Integer.parseInt(s3);
                        }
                        //System.out.println(line);
                        //System.out.println(l1);
                        //System.out.println(l2);
                        //System.out.println(l3);
                        objects.add(new ZObject(points.get(l1-1),points.get(l2-1),points.get(l3-1)));
                    }
                }
            }
            /*for (ZObject z : objects) {
                System.out.println(z.getOne().getX());
                System.out.println(z.getTwo().getX());
                System.out.println(z.getThree().getX());
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("FILE LOADED " + objects.size() + " POLYGONS");
        return objects;
    }
}