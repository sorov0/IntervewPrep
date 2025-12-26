package multithreading.threadAndRunnable;

public class Hello implements Runnable{

    @Override
    public void run() {
        System.out.println("Hello");
    }
}
