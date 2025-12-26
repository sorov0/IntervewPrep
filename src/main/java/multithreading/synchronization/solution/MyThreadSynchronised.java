package multithreading.synchronization.solution;

public class MyThreadSynchronised extends Thread {

    private SynchronisedCounter synchronisedCounter;

    public MyThreadSynchronised(SynchronisedCounter counter) {
        this.synchronisedCounter = counter;
    }

    @Override
    public void run() {
        for(int i = 0 ; i<1000 ; i++){
            synchronisedCounter.increment();
        }
    }


}
