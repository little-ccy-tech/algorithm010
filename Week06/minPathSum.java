package leetcode;

/**
 * m*n的二维网格中从0到[m][n]的所有路径中和最小的值
 * 每次只能向右或下移动一步
 *
 * @author chenchunyu
 * @date 2020/7/26
 */
public class minPathSum {

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int i = minPathSum(grid);
        System.out.println(i);
    }


    /**
     * 解题思路
     * //dp[m][n] 表示从[0][0]到[m][n] 的所有路径中和最小的值
     * //dp[m][n] = min(dp[m][n-1],dp[m-1][n])+grid[m][n];
     * <p>
     * //dp[0][0] ~ dp[0][n] = grid[0][0] ~ grid[0][n]
     * //dp[0][0] ~ dp[m][0] = grid[0][0] ~ grid[m][0]
     *
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        if (null == grid || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i==0 && j!=0){
                    grid[i][j] = grid[i][j-1]+grid[i][j];
                }else if (i!=0 && j==0){
                    grid[i][j] = grid[i-1][j]+grid[i][j];
                }else if(i ==0 && j ==0){
                    grid[i][j] = grid[i][j];
                }else{
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[m-1][n-1];
    }
}
