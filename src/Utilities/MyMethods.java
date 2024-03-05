package Utilities;

public class MyMethods {
    public static void myWait(int time){

        try {
            Thread.sleep(time* 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
