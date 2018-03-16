import java.lang.Math.*;
public class project {
    public static double[] project2D(double[] vector, double fov, double aspect, double near, double far) {
        //sides calculation
        double top = near*(Math.tan((Math.PI/180)*(fov/2)));
        double bottom = -1*top;
        double right = top*aspect;
        double left = -1*right;
        //Projection matrix calculation
        double[][] matrix = new double[][]{   {Math.atan((fov/2)),0,0,0},
                                              {0,Math.atan((fov)/2),0,0},
                                              {0,0,-((far+near)/(far-near)),-((2*(far*near))/(far-near))},
                                              {0,0,-1,0}};        
        double tempadd = 0;
        double[] result = new double[4];
        //multiple the vector with the array
        for (int r1=0; r1<vector.length; r1++) {
            for (int c1=0; c1<vector.length; c1++) {
                tempadd += matrix[r1][c1]*vector[c1];
            }
            result[r1]=tempadd;
            tempadd = 0;
        }
        //negating the results for some reason?
        double x = -1*(result[0]/result[3]);
        double y = -1*(result[1]/result[3]);
        //transform values into 0-1 range
        x = (x+1)/2;
        y = (y+1)/2;
        System.out.println("(" + x + "," + y + ")");
        return(new double[]{x,y});
    }
}