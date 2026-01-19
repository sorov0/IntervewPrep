package JavaConcepts.Code.threadLearning.thread;

public class MyThread extends Thread{


    @Override
    public void run() {
        System.out.println("Running");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MyThread t1 = new MyThread(); // New
        System.out.println(t1.getName());
        System.out.println(t1.getState());
        t1.start(); // Runnable
        System.out.println(t1.getState());
        System.out.println(Thread.currentThread().getState()); //Main thread is running but the state is runnable

        Thread.sleep(100);
        System.out.println(t1.getState());
        t1.join(); // Main thread is waiting to thread t1 to get finished then the execution would proceed further
        System.out.println(t1.getState()); // terminated


    }
}
