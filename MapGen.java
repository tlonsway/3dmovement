public class MapGen {
    public static Map generate(int size) {
        int[][][] map = new int[1000][size][size];
        int[][] depth = new int[size][size];
        int previous=1;
        int value=1;
        //Fill map with base height 1
        for (int w=0; w<map[0].length; w++) {
            for (int l=0; l<map[0][0].length; l++) {
                map[0][w][l] = 1;
                depth[w][l] = 1;
            }
        }
        //Algorithm for building hills
        /*for (int w=1; w<map[0].length-1; w++) {
            for (int l=1; l<map[0][0].length-1; l++) {
                double seed = Math.random(); // random seed
                double weight = (depth[w+1][l+1]+depth[w+1][l]+depth[w][l+1]+depth[w-1][l]+depth[w-1][l+1]+depth[w-1][l-1]+depth[w][l-1]+depth[w+1][l-1])/8; // surrounding blocks
                value = (int)((seed*weight)+.5);
                map[4-value][w][l] = 1;
                depth[w][l] = 4-value;
            }
        }*/
        for (int w=0; w<map[0].length; w++) {
            for (int l=0; l<map[0][0].length; l++) {
                map[0][w][l] = 0;
            }
        }        
        
        /*for (int r=0;r<depth.length;r++) {
            for (int c=0;c<depth[0].length;c++) {
                System.out.print(depth[r][c] + "\t");
            }
            System.out.println();
        }*/
        
        //DRAWS CURVED DISTANCED STAIRCASE
        //for (int w=0; w<map[0].length; w++) {
        //    for (int l=0; l<map[0][0].length; l++) {
        //        map[w*l][w][l] = 1;
        //    }
        //}     
        
        //OFFSET VERTICAL STAIRCASE
        /*for (int w=0; w<map[0].length; w++) {
            for (int l=0; l<map[0][0].length; l++) {
                map[Math.abs((int)(5*l-w/3))][w][l] = 1;
            }
        }*/
        
        //ONSET VERTICAL STAIRCASE
        /*for (int w=0; w<map[0].length; w++) {
            for (int l=0; l<map[0][0].length; l++) {
                map[Math.abs((int)(l+Math.pow(w,2)))][w][l] = 1;
            }
        }*/
        
        //QUARTER CONE
        /*for (int w=0; w<map[0].length; w++) {
            for (int l=0; l<map[0][0].length; l++) {
                map[(int)(Math.pow(Math.pow(l,2)+Math.pow(w,2),.5))][w][l] = 1;
            }
        }*/        
        
        //RIPPLE EQUATION
        for (int w=0; w<map[0].length; w++) {
            for (int l=0; l<map[0][0].length; l++) {
                map[Math.abs((int)(3*(Math.sin(10*(Math.pow(l,2)+Math.pow(w,2))))+.5))][w][l] = 1;
                //System.out.println(Math.sin(10*(Math.pow(l,2)+Math.pow(w,2))));
            }
        }      
        
        return new Map(map, depth);
    }
}