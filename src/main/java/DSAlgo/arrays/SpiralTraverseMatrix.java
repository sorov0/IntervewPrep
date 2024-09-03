package DSAlgo.arrays;

import java.util.ArrayList;

public class SpiralTraverseMatrix {

    //https://www.geeksforgeeks.org/problems/spirally-traversing-a-matrix-1587115621/1

    static ArrayList<Integer> spirallyTraverse(int matrix[][], int r, int c)
    {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();

        int srow = 0;
        int scol = 0;
        int erow = r-1;
        int ecol = c-1;

        while(srow<=erow && scol<=ecol){

            for(int col = scol ; col<=ecol ; col++){
                ans.add(matrix[srow][col]);
            }
            srow++;
            for(int row = srow ; row<=erow ; row++){
                ans.add(matrix[row][ecol]);
            }
            ecol--;

            if(srow<=erow){
                for(int col = ecol ; col>=scol ; col--){
                    ans.add(matrix[erow][col]);
                }
                erow--;
            }

            if(scol<=ecol){
                for(int row = erow ; row>=srow ; row--){
                    ans.add(matrix[row][scol]);
                }
                scol++;
            }
        }
        return ans;
    }
}
