public class manipulate {
    public static double[] translate(double[] vector, double x, double y, double z) {
        //double[] vector = new double[]{v.getWX(), v.getWY(), v.getWZ(), 1};
        double[][] matrix = new double[][]{{1,0,0,x},
                                           {0,1,0,y},
                                           {0,0,1,z},
                                           {0,0,0,1}};
        double tempadd = 0;
        double[] result = new double[4];
        for (int r1=0; r1<vector.length; r1++) {
            for (int c1=0; c1<vector.length; c1++) {
                tempadd += matrix[r1][c1]*vector[c1];
            }
            result[r1]=tempadd;
            tempadd = 0;
        }
        return result;   
    }
    public static double[] scale(double[] vector, double x, double y, double z) {
        double[][] matrix = new double[][]{{x,0,0,0},
                                           {0,y,0,0},
                                           {0,0,z,0},
                                           {0,0,0,1}};
        double tempadd = 0;
        double[] result = new double[4];
        for (int r1=0; r1<vector.length; r1++) {
            for (int c1=0; c1<vector.length; c1++) {
                tempadd += matrix[r1][c1]*vector[c1];
            }
            result[r1]=tempadd;
            tempadd = 0;
        }
        return result;

    }
    public static double[] rotate(double[] vector, char c, double angle) {
        double[][] matrix = {};
        //vector = new double[]{vector[0],vector[1],vector[2]};
        if (c=='x') {
            matrix = new double[][]{{1,0,0,0},
                                   {0,Math.cos(angle),-(Math.sin(angle)),0},
                                   {0,Math.sin(angle),Math.cos(angle),0},
                                   {0,0,0,0}};           
        }
        if (c=='y') {
            matrix = new double[][]{{Math.cos(angle),0,Math.sin(angle),0},
                                   {0,1,0,0},
                                   {-(Math.sin(angle)),0,Math.cos(angle),0},
                                   {0,0,0,0}};              
            
        }
        if (c=='z') {
            matrix = new double[][]{{Math.cos(angle),-(Math.sin(angle)),0,0},
                                   {Math.sin(angle),Math.cos(angle),0,0},
                                   {0,0,1,0},
                                   {0,0,0,0}};              
            
        }
        double tempadd = 0;
        double[] result = new double[4];
        for (int r1=0; r1<vector.length; r1++) {
            for (int c1=0; c1<vector.length; c1++) {
                tempadd += matrix[r1][c1]*vector[c1];
            }
            result[r1]=tempadd;
            tempadd = 0;
        }        
        return result;
    }  
}