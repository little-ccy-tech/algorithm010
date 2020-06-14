/**
 * 计算两数之和
 * 思路：
 * 1、首先是暴力求解，使用双层循环
 * 2、其次使用空间换时间，利用额外的hash表来存储数组中的元素。因为hash表的访问是O(1)的
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int needCompare = target-nums[i];
            if (map.containsKey(needCompare)){
                return new int[]{i,map.get(needCompare)};
            }else{
                map.put(nums[i],i);
            }
        }
        throw new RuntimeException("not found!");
    }
}