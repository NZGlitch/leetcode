//Given an unsorted integer array nums, return the smallest missing positive 
//integer. 
//
// You must implement an algorithm that runs in O(n) time and uses constant 
//extra space. 
//
// 
// Example 1: 
// Input: nums = [1,2,0]
//Output: 3
// Example 2: 
// Input: nums = [3,4,-1,1]
//Output: 2
// Example 3: 
// Input: nums = [7,8,9,11,12]
//Output: 1
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 5 * 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
// Related Topics Array Hash Table 👍 10330 👎 1389


class Question41 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int firstMissingPositive(int[] nums) {
        boolean[] hash = new boolean[500001];
        for (int i=0; i< nums.length; i++) {
            if (nums[i] > hash.length) continue;
            if (nums[i] >= 0) hash[nums[i]] = true;
        }
        for (int i=1; i<hash.length; i++) {
            if (!hash[i]) return i;
        }
        return 500001;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}