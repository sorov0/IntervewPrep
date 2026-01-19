package JavaConcepts.Code.threadLearning.thread;

public class WorldThreadUsingClass extends Thread{

    @Override
    public void run() {
        for(int i = 0 ; i<1000 ; i++){
            System.out.println(Thread.currentThread().getName());
        }
    }
}
