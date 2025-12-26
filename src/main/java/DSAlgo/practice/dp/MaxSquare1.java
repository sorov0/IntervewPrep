package DSAlgo.practice.dp;

public class MaxSquare1 {

    public int maximalSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int maxSide = 0;

        // Fill DP table
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;  // First row or first column
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide; // Return area
    }
}
