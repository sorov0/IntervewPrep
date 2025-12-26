package multithreading.synchronization.solution;

public class TestSynchronised {


    public static void main(String[] args) {

        SynchronisedCounter synchronisedCounter = new SynchronisedCounter();

        // Both threads are running on common/shared resource, hence inconsistent Data
        MyThreadSynchronised t1 = new MyThreadSynchronised(synchronisedCounter);
        MyThreadSynchronised t2 = new MyThreadSynchronised(synchronisedCounter);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (Exception ex){

        }
        System.out.println(synchronisedCounter.getCount());
    }

}
