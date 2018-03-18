public class MapGen {
    public static Map generate(int size) {
        int[][][] map = new int[5][size][size];
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
        for (int w=1; w<map[0].length-1; w++) {
            for (int l=1; l<map[0][0].length-1; l++) {
                double seed = Math.random(); // random seed
                double weight = (depth[w+1][l+1]+depth[w+1][l]+depth[w][l+1]+depth[w-1][l]+depth[w-1][l+1]+depth[w-1][l-1]+depth[w][l-1]+depth[w+1][l-1])/8; // surrounding blocks
                value = (int)((seed*weight)+.5);
                map[4-value][w][l] = 1;
                depth[w][l] = 4-value;
            }
        }
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
        return new Map(map, depth);
    }
}