package JavaConcepts.Code.threadLearning.thread;

public class ThreadMethod extends Thread {

    // Thread Method: run, start, sleep, join, setPriority, interrupt, yield, setDemon
    // Demon Thread are running in the background and JVM does not wait for demon thread to stop.
    // It keeps running in the background. eg: Garbage Collector

    public ThreadMethod(String name) {
        super(name);
    }

    // setPriority (It hints JVM which thread to give more priority)
    /*
    @Override
    public void run() {
        System.out.println("thread running with priority: " + Thread.currentThread().getName());
    }
    */

    // interrupt(It hints JVM and tell main thread to stop the execution of thread getting interrupted)
    /*
    @Override
    public void run() {
        for(int i = 0 ; i<10 ; i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
     */

    // yield (It hints JVM that give chance to another thread to get executed after one iteration)
    @Override
    public void run() {
        for(int i = 0 ; i<10 ; i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
            Thread.yield();
        }
    }

    public static void main(String[] args) {

        //setPriority
        /*
        ThreadMethod low = new ThreadMethod("low");
        low.setPriority(Thread.MIN_PRIORITY);
        ThreadMethod medium = new ThreadMethod("medium");
        medium.setPriority(Thread.NORM_PRIORITY);
        ThreadMethod high = new ThreadMethod("high");
        high.setPriority(Thread.MAX_PRIORITY);
        low.start();
        medium.start();
        high.start();
        System.out.println(low.getName());
        System.out.println(medium.getName());
        System.out.println(high.getName());
        */

        // Interrupt
        /*
        ThreadMethod t1 = new ThreadMethod("InterruptLearning");
        t1.start();
        t1.interrupt(); // Main Thread interrupted the t1 thread(if runnable, gets stopped)
        System.out.println("Execution completed");
         */

        // Yield
        ThreadMethod t1 = new ThreadMethod("t1");

        ThreadMethod t2 = new ThreadMethod("t2");

        t1.start();
        t2.start();

    }
}
