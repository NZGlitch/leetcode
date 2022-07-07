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
        /**
         * The key to this question is the requirement that the algorihm must work in constant space and O(n) time
         * This gives us a couple of really useful hints:
         *   1) We can not have any nested loops
         *   2) We can only use fixed size data storage (arrays)
         *
         * The strategy is pretty straight forward, from the problem description we know that the nums array can be at
         * most 500,000 numbers, therefore the highest possible answer (where the array we are given has all the
         * positive intergers from 1 -> 500,000 ) is 500,001. So we just create 500,000 boolean buckets, all set to
         * false. When we see anumber we change that bucket to true. We then look for the first bucket that has a false
         * and it's index will be the number we are looking for.
         *
         * Because 0 is irrelevant, we can save one bucket of space by having bucket[i] for the number i+1.
         *
         * Optimisations: We can keep an incremental count of numbers seen 'in order' to reduce the size of the
         * check loop
         *
         * We only need at most n buckets where n is the length of nums
         */

        boolean[] buckets = new boolean[Math.min(nums.length,500000)];  // Hash to record numbers we've seen
        int minSeen = 0;                                                //Optimisation, allows us to skip some numbers when checking
        for (int i=0; i< nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > buckets.length) continue;     // Watch out for numbers outside the bucket range
            buckets[nums[i]-1] = true;                                  // Note that we have seen this number
            if (nums[i] == minSeen+1) minSeen++;
        }

        // Look for the first 'false' bucket
        for (int i=minSeen; i<buckets.length; i++) {
            if (!buckets[i]) return i+1;           // We havent seen this number, so it is the answer
        }

        // This is the case where every bucket is true, the only possible answer is n+1
        return buckets.length+1;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}