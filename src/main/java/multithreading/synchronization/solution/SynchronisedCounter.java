package multithreading.synchronization.solution;

public class SynchronisedCounter {

    private int count = 0;

    // This is called critical section where multiple threads modify shared resources
    public synchronized void increment(){
        count++;
    }

    public void incrementBlockOnly(){
        synchronized (this){
            count++;
        }

    }


    public int getCount(){
        return count;
    }
}
