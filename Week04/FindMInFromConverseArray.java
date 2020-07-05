package leetcode;

/**
 * 从一个被旋转过的升序数组中寻找最小的值
 *
 * @author chenchunyu
 * @date 2020/7/6
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class FindMInFromConverseArray {

    public static void main(String[] args) {
        int min = findMin(new int[]{3, 4, 5, 1, 2});
        System.out.println(min);

    }


    /**
     * 解题思路：找到变化点，最小值就是变化点右侧的值
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0, right = nums.length - 1;

        //当前数组是一个有序的数组
        if (nums[right] > nums[left]) {
            return nums[0];
        }

        while (right >= left) {
            //找到中间值
            int mid = left + (right - left) / 2;

            //判断是否找到了变化点
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            //没有找到时，判断变化点是在左侧还是在右侧
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
