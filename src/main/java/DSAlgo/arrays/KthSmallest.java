package DSAlgo.arrays;



import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallest {

    //https://www.geeksforgeeks.org/problems/kth-smallest-element5635/1

    public static int kthSmallest(int[] arr, int l, int r, int k)
    {
        //Your code here
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = l ; i<=r ; i++){
            if(pq.size()==k){
                if(arr[i]<pq.peek()){
                    pq.poll();
                    pq.add(arr[i]);
                }
            }else{
                pq.add(arr[i]);
            }


        }
        return pq.peek();

    }

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(3);
        pq.add(1);
        pq.add(4);
        pq.add(2);
        while(!pq.isEmpty()){
            System.out.println(pq.peek());
            pq.poll();
        }
    }
}
