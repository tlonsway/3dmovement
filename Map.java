public class Map {
    private int[][][] locations;
    private int[][] depth;
    public Map(int[][][] l, int[][] d) {
        locations = l;
        depth = d;
    }
    public int[][][] getMap() {
        return locations;
    }
    public int[][] getDepth() {
        return depth;
    }
}