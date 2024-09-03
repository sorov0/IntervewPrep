package DSAlgo.arrays;

import java.util.Arrays;
import java.util.Collections;

public class RotateImageMatrix {

    //https://leetcode.com/problems/rotate-image/description/

    public void rotate(int[][] matrix) {
        // Step 1: Convert to Transpose
        // Step 2: Reverse each Row of the Matrix
        int n = matrix.length;
        for(int i = 0 ; i<n ; i++){
            for(int j = i+1 ; j<n ; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        for(int i = 0 ; i<n ; i++){
            int l = 0 , h = n-1;
            while(l<h){
                int tmp = matrix[i][l];
                matrix[i][l] = matrix[i][h];
                matrix[i][h] = tmp;
                l++;h--;
            }
        }
    }
}
