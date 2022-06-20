//Given an integer array nums, handle multiple queries of the following type: 
//
// 
// Calculate the sum of the elements of nums between indices left and right 
//inclusive where left <= right. 
// 
//
// Implement the NumArray class: 
//
// 
// NumArray(int[] nums) Initializes the object with the integer array nums. 
// int sumRange(int left, int right) Returns the sum of the elements of nums 
//between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + 
//nums[right]). 
// 
//
// 
// Example 1: 
//
// 
//Input
//["NumArray", "sumRange", "sumRange", "sumRange"]
//[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
//Output
//[null, 1, -1, -3]
//
//Explanation
//NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
//numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
//numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
//numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁵ <= nums[i] <= 10⁵ 
// 0 <= left <= right < nums.length 
// At most 10⁴ calls will be made to sumRange. 
// 
// Related Topics Array Design Prefix Sum 👍 2102 👎 1674


class Question303 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class NumArray {
        final int[] a;
        public NumArray(int[] n) {
            this.a = n;
            for (int i=1;i<a.length;i++){
                this.a[i] = this.a[i-1] + a[i];
            }
        }
        public int sumRange(int l, int r) {
            return a[r] - (l == 0 ? 0 : a[l-1]);
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)

}