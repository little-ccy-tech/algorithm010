/**
 * 最开始的暴力求解法
 * 思路：每次遍历数组中的全部元素，如果发现值为0的元素，则与其后一个元素进行交换
 * 做多次循环直到不再发生交换表示移动完成
 */
class Solution {
    public void moveZeroes(int[] nums) {
        boolean finished = true;
        do {
            boolean switched = false;
            for (int i = 0; i < nums.length; i++) {
                //当前遍历的元素值为0；当前不是最后一个元素；后一个元素值不为0
                if (nums[i] == 0 && i != nums.length - 1 && nums[i + 1] != 0) {
                    int temp = nums[i + 1];
                    nums[i + 1] = nums[i];
                    nums[i] = temp;
                    switched = true;
                }
            }
            finished = !switched;
        } while (!finished);

    }
}

/**
 * 1、发现非0元素值，则从数组的0号位置开始赋值，并记录最后一个赋值位置的下标
 * 2、记录的下标到数组尾的元素全部赋值为0
 */
class Solution {
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }

        for (int j = index; j < nums.length; j++) {
            nums[j] = 0;
        }

    }
}