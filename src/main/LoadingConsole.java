package main;

public class LoadingConsole {

    public static String animation1 =
                "  '########::'#######::'##:::::'##:'########:'########:::::'########::'########:'########:'########:'##::: ##::'######::'########:\n" +
                "  ... ##..::'##.... ##: ##:'##: ##: ##.....:: ##.... ##:::: ##.... ##: ##.....:: ##.....:: ##.....:: ###:: ##:'##... ##: ##.....::\n" +
                "  ::: ##:::: ##:::: ##: ##: ##: ##: ##::::::: ##:::: ##:::: ##:::: ##: ##::::::: ##::::::: ##::::::: ####: ##: ##:::..:: ##:::::::\n" +
                "  ::: ##:::: ##:::: ##: ##: ##: ##: ######::: ########::::: ##:::: ##: ######::: ######::: ######::: ## ## ##: ##::::::: ######:::\n" +
                "  ::: ##:::: ##:::: ##: ##: ##: ##: ##...:::: ##.. ##:::::: ##:::: ##: ##...:::: ##...:::: ##...:::: ##. ####: ##::::::: ##...::::\n" +
                "  ::: ##:::: ##:::: ##: ##: ##: ##: ##::::::: ##::. ##::::: ##:::: ##: ##::::::: ##::::::: ##::::::: ##:. ###: ##::: ##: ##:::::::\n" +
                "  ::: ##::::. #######::. ###. ###:: ########: ##:::. ##:::: ########:: ########: ##::::::: ########: ##::. ##:. ######:: ########:\n" +
                "  :::..::::::.......::::...::...:::........::..:::::..:::::........:::........::..::::::::........::..::::..:::......:::........::";

    public static String animation2 = animation1.replace('#',' ');
    public static String animation3 = animation1.replace('.',' ').replace(':',' ');
    public static String[] frames = animation2.split("\n");

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void sleepFact(long ms){
        try {
                    Thread.sleep(ms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    }

    public static void printDelayed(String s){
            for (int i=0; i < s.length();i++) {
                System.out.print(s.charAt(i));
                sleepFact(1);
            }

        }

        public static void Loading(){
            // loop to create the animation
        clearScreen();
            for (String frame : frames) {
                printDelayed(frame);
                System.out.println();
            }
            for(int i=0 ; i<= 3; i++){
                clearScreen();
                System.out.println(animation3);
                sleepFact(100*i);
            }            
            clearScreen();
            System.out.println(animation2);
            clearScreen();
            System.out.println(animation1);
        }

    public static void main(String[] args) {
    }
}
