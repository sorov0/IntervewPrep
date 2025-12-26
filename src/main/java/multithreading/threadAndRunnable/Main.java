package multithreading.threadAndRunnable;

public class Main {

    public static void main(String[] args) {

        World world = new World();
        world.start();

        Thread t1 = new Thread(new Hello());
        t1.start();

    }
}
