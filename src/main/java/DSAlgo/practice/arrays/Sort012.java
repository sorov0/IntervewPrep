package DSAlgo.practice.arrays;

public class Sort012 {

    //https://www.geeksforgeeks.org/problems/sort-an-array-of-0s-1s-and-2s4231/1
    public static void sort012(int a[], int n)
    {
        // code here
        int l = 0;
        int h = n-1;
        int mid = 0;


        while(mid<=h){
            if(a[mid]==0){
                a[mid] = a[l];
                a[l] = 0;
                l++;
                mid++;
                continue;
            }
            if(a[mid]==1){
                mid++;
                continue;
            }
            if(a[mid]==2){
                a[mid] = a[h];
                a[h] = 2;
                h--;
                continue;
            }
        }


    }

    public static void main(String[] args){
        int arr[] = new int[]{0 ,2 ,1, 2 ,0};
        Sort012.sort012(arr , arr.length);
        System.out.println(arr);

    }
}
