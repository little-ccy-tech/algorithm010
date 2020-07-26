package leetcode;

/**
 * 最大正方形
 * https://leetcode-cn.com/problems/maximal-square/
 *
 * @author chenchunyu
 * @date 2020/7/26
 */
public class MaximalSquare {

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        int i = maximalSquare(matrix);
        System.out.println(i);
    }


    /**
     * 解题思路
     * 构建一个二维的dp矩阵
     * 1、每个元素dp[i][j],表示原矩阵中以[i][j]位置为右下角所组成的最大正方形的边长
     * 2、递推公式：dp[i][j] = min(dp[i-1][j],dp[i-1][j-1],dp[i][j-1])+1, 也就是说等于它左边、上边和做上边3者最小值+1
     *
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        //新的dp方程
        int dp[][] = new int[matrix.length][matrix[0].length];
        int max = 0;
        int x = matrix.length,y = matrix[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (j==0){
                    dp[i][j] = Integer.valueOf(String.valueOf(matrix[i][j]));
                }else if (i==0){
                    dp[i][j] = Integer.valueOf(String.valueOf(matrix[i][j]));
                }else{
                    if (matrix[i][j] == '0') {
                        dp[i][j] = 0;
                    } else {
                        int min = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]);
                        dp[i][j] = min + 1;
                    }
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max * max;
    }
}
