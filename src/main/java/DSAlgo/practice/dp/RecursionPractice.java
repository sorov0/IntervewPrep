package DSAlgo.practice.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RecursionPractice {


    //print 1 to n using f(n) = f(n+1)
    static  void print1(int i , int n){
        if(i>n) return;
        System.out.print(i + " ");
        print1(i+1 , n);
    }

    //print 1 to n using f(n) = f(n-1)
    static void print2(int i , int n){
        if(i<1) return;
        print2(i-1 , n);
        System.out.print(i + " ");
    }

    static void print3(int i , int n){
        if(i<1) return;
        System.out.print(i + " ");
        print3(i-1 , n);
    }

    static void print4(int i , int n){
        if(i>10) return;
        print4(i+1 , n);
        System.out.print(i + " ");
    }

    static int sum(int i , int s){
        if(i<1) return s;
        return sum(i-1 , s + i);
    }

    static int sumOfNaturalNumber(int n){
        if(n==1) return 1;
        else return sumOfNaturalNumber(n-1) + n;
    }

    static void reverseArray(ArrayList<Integer> arr , int l , int r){
        if(l+1==r){
            Collections.swap(arr , l , r);
            return;
        }
        if(l==r) return;
        Collections.swap(arr , l , r);
        reverseArray(arr , l+1 , r-1);
    }

    static void reverseArray2(ArrayList<Integer> arr , int i , int n){
        if(i>n/2) return;
        Collections.swap(arr , i , n-i-1);
        reverseArray2(arr , i+1 , n);
    }

    static boolean checkIfPalindrome(String str ,int i , int size){
        if(i>size/2) return false;
        if(str.charAt(i)!=str.charAt(size-i-1)) return false;
        return checkIfPalindrome(str , i+1 , size);
    }

    static int fib(int n){
        if(n<=1) return n;
        return fib(n-1) + fib(n-2);
    }

    static void printSubSeq(ArrayList<Integer> arr , int ind , ArrayList<Integer> subSeq){

        if(ind == arr.size()){
            System.out.println(subSeq);
            return;
        }else{
            subSeq.add(arr.get(ind));
            printSubSeq(arr , ind+1 , subSeq);
            subSeq.remove(subSeq.size()-1);
            printSubSeq(arr , ind+1 , subSeq);
        }
    }

    static void printSubSeq2(ArrayList<Integer> arr , int ind , ArrayList<Integer> subSeq){

        if(ind == arr.size()){
            System.out.println(subSeq);
            return;
        }else{
            printSubSeq2(arr , ind+1 , subSeq);
            subSeq.add(arr.get(ind));
            printSubSeq2(arr , ind+1 , subSeq);
            subSeq.remove(subSeq.size()-1);
        }
    }

    static int cntSubSeq(ArrayList<Integer> arr , int ind){
        if(ind == arr.size()) return 0;
        return 1+cntSubSeq(arr , ind+1) + cntSubSeq(arr , ind+1);
    }

    //it prints all subsequence with given sum
    static void sumSubSeq(ArrayList<Integer> arr , ArrayList<Integer> sub , int ind, int gsum , int sum){
        if(ind==arr.size()){
            if(gsum==sum){
                System.out.println(sub);
            }
            return;
        }
        sub.add(arr.get(ind));
        sum+=arr.get(ind);
        sumSubSeq(arr , sub , ind+1 , gsum , sum);
        sum-=arr.get(ind);
        sub.remove(sub.size()-1);
        sumSubSeq(arr , sub , ind+1 , gsum , sum);
    }

    //it prints only one subsequence with given sum
    static boolean sumSubSeq2(ArrayList<Integer> arr , ArrayList<Integer> sub , int ind, int gsum , int sum){
        if(ind==arr.size()){
            if(gsum==sum){
                System.out.println(sub);
                return true;
            }
            return false;
        }
        sub.add(arr.get(ind));
        sum+=arr.get(ind);
        if(sumSubSeq2(arr , sub , ind+1 , gsum , sum)) return true;
        sum-=arr.get(ind);
        sub.remove(sub.size()-1);
        if(sumSubSeq2(arr , sub , ind+1 , gsum , sum)) return true;
        return false;
    }

    //it prints only one subsequence with given sum
    static int sumSubSeq3(ArrayList<Integer> arr , ArrayList<Integer> sub , int ind, int gsum , int sum){
        if(ind==arr.size()){
            if(gsum==sum){
                return 1;
            }
            return 0;
        }
        sub.add(arr.get(ind));
        sum+=arr.get(ind);
        int l = sumSubSeq3(arr , sub , ind+1 , gsum , sum);
        sum-=arr.get(ind);
        sub.remove(sub.size()-1);
        int r = sumSubSeq3(arr , sub , ind+1 , gsum , sum);
        return l+r;
    }

    public static void main(String[] args){

        print1(1 , 10);
        System.out.println();
        print2(10 , 10);
        System.out.println();
        print3(10 , 10);
        System.out.println();
        print4(1 , 10);
        System.out.println();
        int s = sum(4 , 0);
        System.out.println(s);
        System.out.println(sumOfNaturalNumber(4));

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        reverseArray(arr , 0 , arr.size()-1);
        System.out.println(arr);
        reverseArray2(arr , 0 , arr.size());
        System.out.println(arr);

        System.out.println("Printing subsequence1!!!");
        ArrayList<Integer> sub = new ArrayList<>();
        printSubSeq(arr , 0 , sub);
        System.out.println("Printing subsequence2!!!");
        printSubSeq2(arr , 0 , sub);
        //System.out.println(cntSubSeq(arr , 0));

        //sumSubSeq2(arr , sub , 0 , 6 , 0);
        System.out.println(sumSubSeq3(arr , sub , 0 , 6 , 0));

        int dp[][] = new int[7][5];
        for (int i = 0 ; i<7 ; i++){
            Arrays.fill(dp[i], -1);
            //System.out.println(dp[i]);
        }
        for(int i = 0 ; i<7 ; i++){
            for(int j = 0 ; j<5 ; j++){
                System.out.print(dp[i][j]);
            }
            System.out.println();
        }

        System.out.println(Integer.MAX_VALUE);
        int num = (int) Math.pow(10, 9);
        System.out.println(num);

    }
}
