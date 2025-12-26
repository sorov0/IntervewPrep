package multithreading.synchronization.issue;

public class Test {


    public static void main(String[] args) {

        Counter counter = new Counter();

        // Both threads are running on common/shared resource, hence inconsistent Data
        MyThread t1 = new MyThread(counter);
        MyThread t2 = new MyThread(counter);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (Exception ex){

        }
        System.out.println(counter.getCount());
    }
}
