package JavaConcepts.Code.threadLearning.thread;

public class Test {

    public static void main(String[] args) {

        //Thread Class
        WorldThreadUsingClass world = new WorldThreadUsingClass();
        world.start();

        //Runnable interface
        WorldThreadUsingRunnable worldThreadUsingRunnable = new WorldThreadUsingRunnable();
        Thread t1 = new Thread(worldThreadUsingRunnable);
        t1.start();


        for(int i = 0 ; i<1000 ; i++){
            System.out.println(Thread.currentThread().getState());
        }
    }
}
