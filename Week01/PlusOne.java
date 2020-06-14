/**
 * 暴力求解法
 * 思路：从最后一位开始加，发现值>=10则发生进位，利用额外的stack保存每次计算后的值
 */
class Solution {
    public int[] plusOne(int[] nums) {
        int count = nums.length;
        Stack<Integer> stack = new Stack<>();
        int incr = 1;
        for (int i = nums.length - 1; i > -1; i--) {

            int target = nums[i];
            int sum = target + incr;

            if (sum >= 10) {
                incr = 1;
                stack.push(sum % 10);
            } else {
                incr = 0;
                stack.push(sum);
            }
        }
        if (incr == 1) {
            stack.push(1);
            count++;
        }
        int[] result = new int[count];
        int index = 0;
        while (!stack.empty()) {
            result[index++] = stack.pop();
        }
        return result;
    }
}

/**
 * 来自leetcode上的解法分析
 */
class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for(int i = len - 1; i >= 0; i--) {
            //这里也巧妙，不管是+1还是进位+1，都只能是加1
            digits[i]++;
            digits[i] %= 10;
            if(digits[i]!=0)
                return digits;
        }
        //这一步很精妙，只有当数字全为9时，才可能发生全部进位，也就是进位后的值只能是100..00 这种
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }
}
