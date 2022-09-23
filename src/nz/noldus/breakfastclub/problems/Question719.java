//The distance of a pair of integers a and b is defined as the absolute 
//difference between a and b. 
//
// Given an integer array nums and an integer k, return the kᵗʰ smallest 
//distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,3,1], k = 1
//Output: 0
//Explanation: Here are all the pairs:
//(1,3) -> 2
//(1,1) -> 0
//(3,1) -> 2
//Then the 1ˢᵗ smallest distance pair is (1,1), and its distance is 0.
// 
//
// Example 2: 
//
// 
//Input: nums = [1,1,1], k = 2
//Output: 0
// 
//
// Example 3: 
//
// 
//Input: nums = [1,6,1], k = 3
//Output: 5
// 
//
// 
// Constraints: 
//
// 
// n == nums.length 
// 2 <= n <= 10⁴ 
// 0 <= nums[i] <= 10⁶ 
// 1 <= k <= n * (n - 1) / 2 
// 
// Related Topics Array Two Pointers Binary Search Sorting 👍 2296 👎 70


import java.util.*;

class Question719 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    class KNode implements Comparable<KNode> {
        final int root;
        final int next;
        final int dist;

        public KNode(int root, int next, int dist) {
            this.root = root;
            this.next = next;
            this.dist = dist;
        }

        public int compareTo(KNode node) {
            return this.dist - node.dist;
        }
    }

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<KNode> queue = new PriorityQueue<>();

        for (int i=0; i<nums.length-1; i++)
            queue.offer(new KNode(i,i+1, Math.abs(nums[i]-nums[i+1])));

        while (k>1) {
            KNode node = queue.poll();
            if (node.next < nums.length-1)
                queue.offer(new KNode(node.root, node.next+1,Math.abs(nums[node.root] - nums[node.next+1])));
            k--;
        }

        KNode winner = queue.poll();
        return winner.dist;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}