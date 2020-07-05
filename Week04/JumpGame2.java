package leetcode;

/**
 * 跳跃游戏2
 *
 * @author chenchunyu
 * @date 2020/7/6
 * https://leetcode-cn.com/problems/jump-game-ii/
 */
public class JumpGame2 {

    public static void main(String[] args) {

        int jump = jump(new int[]{2,3,1,1,4});
        System.out.println(jump);
    }

    public static int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            //找能跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) { //遇到边界，就更新边界，并且步数加一
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
