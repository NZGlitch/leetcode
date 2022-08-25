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
        // Create a binary search tree for nums 2
        PriorityQueue<Integer> lookup = new PriorityQueue<>();
        for(int i : nums2) lookup.add(i);

        //Need to track indexes for dupes
        int[] idxs = new int[1000];

        //Initialise some vars
        int best = 0; //Best sub array found so far

        //Iterate through each element in nums, looking for a match
        //in out binary search tree
        for (int i = 0; i<nums1.length; i++) {
            if (lookup.contains(nums1[i])) {
                //Find the best subarray for this value and remove it
                best = findBest(nums1,nums2,i,idxs,best);
                lookup.remove(nums1[i]);
            }
        }
        return best;
    }
     public int findBest(int[] nums1, int[] nums2, int idx, int[] idxs, int best) {
         BigInteger base = BigInteger.valueOf(100); // used for calculations
         // We have found an element that exists in both arrays, lets find the first index of the
         // element in the second array
         int i = idx;                //nums1 index
         int j = idxs[nums1[i]];    // Start from the index after the last one we started from for this val
         while(nums2[j] != nums1[i]) j++;
         // j is now the index of the first instances of nums1[i]
         idxs[nums1[i]] = j+1; // update index list

         // We now have a pointer to each array - keep a rolling hash until the end
         BigInteger rollingHashA = BigInteger.ZERO;
         BigInteger rollingHashB = BigInteger.ZERO;
         int windowSize = 0;
         int bestCount = 0;
         while(i<nums1.length && j < nums2.length) {
             BigInteger exp = base.pow(windowSize); //exponent for this iteration

             // add the current element to each hash
             rollingHashA = rollingHashA.add(BigInteger.valueOf(nums1[i]).multiply(exp));
             rollingHashB = rollingHashB.add(BigInteger.valueOf(nums2[j]).multiply(exp));

             //Update best if match
             if (rollingHashA.equals(rollingHashB)) bestCount = windowSize+1;

             //increment vars;
             windowSize++; i++; j++;
         }

         return Math.max(best,bestCount);
     }
}
//leetcode submit region end(Prohibit modification and deletion)

}