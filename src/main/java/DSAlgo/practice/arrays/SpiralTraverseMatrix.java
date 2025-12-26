package DSAlgo.practice.arrays;

import java.util.ArrayList;
import java.util.List;

public class SpiralTraverseMatrix {

    //https://www.geeksforgeeks.org/problems/spirally-traversing-a-matrix-1587115621/1


    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        traverseSpiral(matrix, 0, 0, matrix.length, matrix[0].length, result);
        return result;
    }

    private static void traverseSpiral(int[][] matrix, int rowStart, int colStart, int rowEnd, int colEnd, List<Integer> result) {

        if (rowStart >= rowEnd || colStart >= colEnd) {
            return;
        }

        for (int i = colStart; i < colEnd; i++) result.add(matrix[rowStart][i]);

        for (int i = rowStart + 1; i < rowEnd; i++) result.add(matrix[i][colEnd - 1]);

        if (rowEnd - 1 > rowStart) {
            for (int i = colEnd - 2; i >= colStart; i--) result.add(matrix[rowEnd - 1][i]);
        }
        if (colEnd - 1 > colStart) {
            for (int i = rowEnd - 2; i > rowStart; i--) result.add(matrix[i][colStart]);
        }

        traverseSpiral(matrix, rowStart + 1, colStart + 1, rowEnd - 1, colEnd - 1, result);
    }

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
