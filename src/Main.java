public class Main {
    public static void main(String[] args) {
        Level demo = new Level(5,10);
        //first row of arena is all 1
        for (int i = 0; i < 10; i++) {
            demo.arena[0][i] = 1;
        }
        //last row of arena is all 1
        for (int i = 0; i < 10; i++) {
            demo.arena[4][i] = 1;
        }
        demo.arena[0][0] = 2;
        demo.arena[4][0] = 2;
        demo.arena[0][9] = 3;
        demo.arena[4][9] = 3;

        Bat bat1 = new Bat(0,0);


        //print out the arena
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                //switch case to print out the arena
                switch (demo.arena[i][j]) {
                    case 0:
                        System.out.print("  ");
                        break;
                    case 1:
                        System.out.print("- ");
                        break;
                    case 2:
                        System.out.print("> ");
                        break;
                    case 3:
                        System.out.print("| ");
                        break;
                }
            }
            System.out.println();
        }
    }
}
