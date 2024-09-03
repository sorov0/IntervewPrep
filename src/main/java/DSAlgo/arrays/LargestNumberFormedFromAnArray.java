package DSAlgo.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class LargestNumberFormedFromAnArray {

    //https://www.geeksforgeeks.org/problems/largest-number-formed-from-an-array1117/1

    static String printLargest(int n, String[] arr) {
        // code here

        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String x, String y) {
                return (y+x).compareTo(x+y);
            }
        });

        String res = String.join("", arr);

        System.out.print(res);
        return  res;

    }

    public static void main(String[] args){
        ArrayList newArr = new ArrayList(Arrays.asList("3", "30", "34", "5", "9"));
        String[] strArray = new String[]{"3", "30", "34", "5", "9"};

        printLargest(2 , strArray);
    }

}
