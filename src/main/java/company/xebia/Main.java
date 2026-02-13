package company.xebia;

import java.util.HashSet;

public class Main {


    /*

["eat","tea","tan","ate","nat","bat"]
0/p-
[
  ["eat","tea","ate"],
  ["tan","nat"],
  ["bat"]
]

     */

    // i/p: [2, 3, -8, 7, -1, 2, 3]
    // o/p: 11

    /*

        str = "abcabc abcabc"
        // o/p: abcabc, abc

     */


    static HashSet<String> res = new HashSet<>();
    static void getRepeatedString(String str){

        if(str.length() <= 1){
            return;
        }
        String firstHalf = str.substring(0, str.length()/2);
        //System.out.println();
        String secondHalf = str.substring(str.length()/2, str.length());
        if(firstHalf.equals(secondHalf)){
            res.add(firstHalf);
        }
        getRepeatedString(firstHalf);
        getRepeatedString(secondHalf);

    }

    static void getSubArray(int arr[]){

        int curSum = arr[0];
        int totalSum = arr[0];

        int leftInd = 0;;
        int rightInd = 0;

        for(int right = 1 ; right<arr.length ; right++){

            if(arr[right] > curSum + arr[right]){
                leftInd = right;
                curSum = arr[right];
            }else{
                curSum = curSum + arr[right];
            }

            if(curSum > totalSum){
                rightInd = right;
                totalSum = curSum;
            }
        }
        for(int i = leftInd ; i<=rightInd ; i++){
            System.out.print(arr[i] + " ");
        }
    }

}
