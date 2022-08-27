//Given two integer arrays nums1 and nums2, return the maximum length of a 
//subarray that appears in both arrays. 
//
// 
// Example 1: 
//
// 
//Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
//Output: 3
//Explanation: The repeated subarray with maximum length is [3,2,1].
// 
//
// Example 2: 
//
// 
//Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
//Output: 5
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums1.length, nums2.length <= 1000 
// 0 <= nums1[i], nums2[i] <= 100 
// 
// Related Topics Array Binary Search Dynamic Programming Sliding Window 
//Rolling Hash Hash Function 👍 4245 👎 102


import java.math.BigInteger;
import java.util.PriorityQueue;

class Question718 {
    //leetcode submit region begin(Prohibit modification and deletion)
import java.math.BigInteger;

    class Solution {

    public int findLength(int[] nums1, int[] nums2) {
        //Dynamic programming  - create a table of nums1.len+1 x nums2.len+1
        int s1 = nums1.length, s2 = nums2.length;
        int[][] dp = new int[s1 + 1][s2 + 1];
        int best = 0; //Current best balue

        //Iterate through every possible pair
        for (int i = 0; i < s1; i++) {
            for (int j = 0; j < s2; j++) {
                // If this pari matches, calculate score
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;        //score is 1 + the score of the previous pair
                    best = Math.max(best, dp[i + 1][j + 1]);    //update best
                }
            }
        }
        return best;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}