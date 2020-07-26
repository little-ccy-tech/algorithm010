package leetcode;

/**
 * 解码方法
 * https://leetcode-cn.com/problems/decode-ways/
 *
 * @author chenchunyu
 * @date 2020/7/26
 */
public class NumDecoding {


    public static void main(String[] args) {
        int i = numDecodings("100");
        System.out.println(i);
    }


    /**
     * 动态规划
     * dp方程 f(n) 表示n个数字时最多的解法
     * f(n) = f(n-1) + 1或2，当n-1和n位组合成的数字 <=26 时则加2，否则加1
     * f(1) = 1;
     *
     * @param s
     * @return
     */
    public static int numDecodings(String s) {

        if ("".equals(s) || '0'== s.charAt(0)) {
            return 0;
        }
        char[] chars = s.toCharArray();

        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < chars.length; i++) {

            //i单独解码的方法数量
            if (chars[i] != '0') {
                dp[i] = dp[i - 1];
            }
            //与i-1组合解码
            String num1 = String.valueOf(new char[]{chars[i - 1], chars[i]});
            Integer value = Integer.valueOf(num1);
            if (value >=10 && value <= 26) {
                if (i == 1) {
                    dp[i] = dp[i] + 1;
                }else{
                    dp[i] = dp[i] + dp[i - 2];
                }
            }
        }
        return dp[chars.length - 1];
    }
}
