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


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

class Question1906 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] minDifference(int[] nums, int[][] queries) {
        int[] res = new int[queries.length];                        //Result array
        Map<Integer, Integer> lookup  = new HashMap<>();            //Lookup table of previous results
        for (int i=0; i<queries.length; i++) {
            res[i] = minDiffSub(lookup, nums, queries[i][0], queries[i][1]);
        }
        return res;
    }

    public int hashKey(int s, int e) {
        return (100000*s) + e;
    }

    public int minDiffSub(Map<Integer,Integer> lookup, int[] nums, int s, int e) {
        // Base Case: s == e
        if (s == e) return -1;

        // Base Case: See if the answer is in lookup, if so, just return it
        if (lookup.containsKey(hashKey(s, e))) return lookup.get(hashKey(s, e));

        int sn = s;
        int en = e;
        //move left index past identical values
        while (sn + 1 <= en && nums[sn] == nums[sn + 1]) sn++;

        // move right index past identical values
        while (en - 1 >= sn && nums[en] == nums[en - 1]) en--;

        //If our indexes equal, all numbers are the same
        if (sn == en) {
            lookup.put(hashKey(s, e), -1);
            return -1;
        }

        // Base case: See if our 'gap' is one - in that case, we just need to compare the two numbers
        // We know from the previous step they can not be equal
        if (en == sn + 1) {
            int val = Math.abs(nums[sn] - nums[en]);
            lookup.put(hashKey(s, e), val);
            return val;
        }

        // Working case - split the problem into 3:
        // s+1...e, s...e-1, (s,e)
        // Ends first
        int best = (nums[sn] == nums[en]) ? -1 : Math.abs(nums[sn] - nums[en]);
        if (best == 1) {        //Can't beat this
            lookup.put(hashKey(s, e), 1);
            return 1;
        }

        // Left side
        int left = minDiffSub(lookup, nums, sn + 1, en);        //Left side
        if (left == 1) {
            lookup.put(hashKey(s, e), 1);
            return 1;
        }

        if (best < 1) best = left;
        else if (left > 0) best = Math.min(best, left);

        //Right side
        int right = minDiffSub(lookup, nums, sn, en - 1);       //Right side
        if (right == 1) {
            lookup.put(hashKey(s, e), 1);
            return 1;
        }

        if (best < 1) best = right;
        else if (right > 0) best = Math.min(best, right);

        lookup.put(hashKey(s, e), best);

        return best;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}