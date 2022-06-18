package nz.noldus.breakfastclub.problems;
//You are given a 0-indexed array nums that consists of n distinct positive
//integers. Apply m operations to this array, where in the iᵗʰ operation you replace 
//the number operations[i][0] with operations[i][1]. 
//
// It is guaranteed that in the iᵗʰ operation: 
//
// 
// operations[i][0] exists in nums. 
// operations[i][1] does not exist in nums. 
// 
//
// Return the array obtained after applying all the operations. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,4,6], operations = [[1,3],[4,7],[6,1]]
//Output: [3,2,7,1]
//Explanation: We perform the following operations on nums:
//- Replace the number 1 with 3. nums becomes [3,2,4,6].
//- Replace the number 4 with 7. nums becomes [3,2,7,6].
//- Replace the number 6 with 1. nums becomes [3,2,7,1].
//We return the final array [3,2,7,1].
// 
//
// Example 2: 
//
// 
//Input: nums = [1,2], operations = [[1,3],[2,1],[3,2]]
//Output: [2,1]
//Explanation: We perform the following operations to nums:
//- Replace the number 1 with 3. nums becomes [3,2].
//- Replace the number 2 with 1. nums becomes [3,1].
//- Replace the number 3 with 2. nums becomes [2,1].
//We return the array [2,1].
// 
//
// 
// Constraints: 
//
// 
// n == nums.length 
// m == operations.length 
// 1 <= n, m <= 10⁵ 
// All the values of nums are distinct. 
// operations[i].length == 2 
// 1 <= nums[i], operations[i][0], operations[i][1] <= 10⁶ 
// operations[i][0] will exist in nums when applying the iᵗʰ operation. 
// operations[i][1] will not exist in nums when applying the iᵗʰ operation. 
// 
// Related Topics Array Hash Table Simulation 👍 282 👎 13

import java.util.*;

class Question2295 {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] arrayChange(int[] n, int[][] o) {

            //First construct a map of possible operations
            Map<Integer, OpList> ops = new HashMap<>();
            for (int i = 0; i< o.length; i++) {
                if (!ops.containsKey(o[i][0])) ops.put(o[i][0], new OpList());
                ops.get(o[i][0]).add(new Op(o[i][1], i));
            }
            
            //Iterate through nums applying all appropriate ops to each element
            for (int i=0; i<n.length; i++) {
                int idx = -1;                       // Current op index
                while (ops.containsKey(n[i])) {     // Is there an op for this value?
                    Op op = ops.get(n[i]).getFirstAfter(idx); // get the first available op after the current idx
                    if (op == null) break;          //We can't apply this op, so no more to do
                    idx = op.i;                     // update op index
                    n[i] = op.r;                    // apply change
                }
            }
            return n;
        }
    }


    class OpList {
        ArrayList<Op> ops;

        public OpList() {ops = new ArrayList();}
        public void add(Op o) {ops.add(o);}
        public Op getFirstAfter(int idx) {
            for (int i = 0; i< ops.size(); i++) {
                if (ops.get(i).i > idx) return ops.get(i);
            }
            return null;
        }
    }

    class Op {
        final int r; // replacement
        final int i; // index

        public Op(int r, int i) {
            this.r=r; this.i=i;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}