/**
 * 利用java的hashmap结构，一次遍历
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


