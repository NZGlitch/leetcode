//The minimum absolute difference of an array a is defined as the minimum value 
//of |a[i] - a[j]|, where 0 <= i < j < a.length and a[i] != a[j]. If all elements 
//of a are the same, the minimum absolute difference is -1. 
//
// 
// For example, the minimum absolute difference of the array [5,2,3,7,2] is |2 -
// 3| = 1. Note that it is not 0 because a[i] and a[j] must be different. 
// 
//
// You are given an integer array nums and the array queries where queries[i] = 
//[li, ri]. For each query i, compute the minimum absolute difference of the 
//subarray nums[li...ri] containing the elements of nums between the 0-based indices 
//li and ri (inclusive). 
//
// Return an array ans where ans[i] is the answer to the iᵗʰ query. 
//
// A subarray is a contiguous sequence of elements in an array. 
//
// The value of |x| is defined as: 
//
// 
// x if x >= 0. 
// -x if x < 0. 
// 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,3,4,8], queries = [[0,1],[1,2],[2,3],[0,3]]
//Output: [2,1,4,1]
//Explanation: The queries are processed as follows:
//- queries[0] = [0,1]: The subarray is [1,3] and the minimum absolute 
//difference is |1-3| = 2.
//- queries[1] = [1,2]: The subarray is [3,4] and the minimum absolute 
//difference is |3-4| = 1.
//- queries[2] = [2,3]: The subarray is [4,8] and the minimum absolute 
//difference is |4-8| = 4.
//- queries[3] = [0,3]: The subarray is [1,3,4,8] and the minimum absolute 
//difference is |3-4| = 1.
// 
//
// Example 2: 
//
// 
//Input: nums = [4,5,2,2,7,10], queries = [[2,3],[0,2],[0,5],[3,5]]
//Output: [-1,1,1,3]
//Explanation: The queries are processed as follows:
//- queries[0] = [2,3]: The subarray is [2,2] and the minimum absolute 
//difference is -1 because all the
//  elements are the same.
//- queries[1] = [0,2]: The subarray is [4,5,2] and the minimum absolute 
//difference is |4-5| = 1.
//- queries[2] = [0,5]: The subarray is [4,5,2,2,7,10] and the minimum absolute 
//difference is |4-5| = 1.
//- queries[3] = [3,5]: The subarray is [2,7,10] and the minimum absolute 
//difference is |7-10| = 3.
// 
//
// 
// Constraints: 
//
// 
// 2 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 100 
// 1 <= queries.length <= 2 * 10⁴ 
// 0 <= li < ri < nums.length 
// 
// Related Topics Array Hash Table 👍 420 👎 29


class Question1906 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] minDifference(int[] nums, int[][] queries) {
        //Naive approach
        int[] res = new int[queries.length];
        int[][] lookup = new int[nums.length][nums.length];
        for (int i=0; i<queries.length; i++) {
            res[i] = minDiffSub(lookup, nums, queries[i][0], queries[i][1]);
        }
        return res;

    }

    public int minDiffSub(int[][] lookup, int[] nums, int s, int e) {
        //See if the answer is in lookup 0 if so, just return it
        if (lookup[s][e] != 0) return lookup[s][e];

        //See if our 'gap' is one - in that case, we just need to compare the two numbers
        if (e == s+1) {
            if (nums[s] == nums[e]) { // Both numbers are the same
                lookup[s][e] = -1;
                return lookup[s][e];
            } else {                //Number differ - calculate diff
                lookup[s][e] = Math.abs(nums[s]-nums[e]);
                return lookup[s][e];
            }
        }
        //We have a gap greater than one - first, we recursivley find the answer for (s,e-1)
        int best = minDiffSub(lookup, nums, s, e-1);

        //now find best for (s,e),(s+1,e)....(e-1,e)
        for (int i=s; i<e; i++) {
            if (nums[i] == nums[e]) continue;           //ignore identical numbers
            int amd = Math.abs(nums[i]-nums[e]);        //Calc min diff for this pair
            if (best == -1 || amd < best) best = amd;   //Sve it as best if its better than previous
        }

        lookup[s][e] = best;
        return lookup[s][e];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}