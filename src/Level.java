public class Level {
    int height;
    int width;
    int bases;
    int[][] arena = new int[height][width];

    public Level(int height, int width) {
        arena = new int [height][width];
    }

}
