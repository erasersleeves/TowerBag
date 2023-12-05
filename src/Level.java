public class Level {
    int height;
    int width;
    int bases;
    int[][] arena = new int[height][width];
    Enemy[] enemies;
    Tower[] towers;
    Player player;

    public Level(int height, int width) {
        arena = new int [height][width];
    }


    public static Object getInstance() {
        return null;
    }

}
