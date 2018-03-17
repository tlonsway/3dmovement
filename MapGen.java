public class MapGen {
 
    public static int[][][] generate(int size) {
        int[][][] map = new int[10][size][size];
        for (int w=0; w<map[0].length; w++) {
            for (int l=0; l<map[0][0].length; l++) {
                for (int d=0; d<map.length; d++) {
                    map[d][w][l] = 1;
                }
            }
        }
        return map;
    }
    
    
    
}