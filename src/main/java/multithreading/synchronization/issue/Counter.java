package multithreading.synchronization.issue;

public class Counter {

    private int count = 0;

    // This is race condition, to avoid it, we use synchronised
    public void increment(){
        count++;
    }

    public int getCount(){
        return count;
    }

}
