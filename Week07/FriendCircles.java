package leetcode;

/**
 * 朋友圈问题
 * https://leetcode-cn.com/problems/friend-circles/
 *
 * @author chenchunyu
 * @date 2020/7/27
 */
public class FriendCircles {


    public static void main(String[] args) {
        int[][] M = new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        int circleNum = findCircleNum(M);
        System.out.println(circleNum);
    }


    /**
     * 解题思路1使用并查集
     * @param M
     * @return
     */
    public static int findCircleNum(int[][] M) {

        //创建一个并查集合
        UnionFind unionFind = new UnionFind(M.length);

        //根据M来合并
        for (int i=0;i<M.length-1;i++){
            for (int j=i+1;j<M.length;j++){
                if (M[i][j] == 1){
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.getCount();
    }
}
