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

    public class Hash {
        final int a;
        BigInteger hash;
        int window;
        int ptr;

        public Hash(int a, long hash, int window, int ptr) {
            this.a = a; this.hash = BigInteger.ZERO; this.window = window; this.ptr = ptr;
        }

        // Special helper method for doing a regular op with bigints
        public void mulpow(int val) {
            hash = hash.add(BigInteger.valueOf(val).multiply(BigInteger.valueOf(a).pow(window-1)));
        }

        //subtract a value from the current hash
        public void subtract(int val) {
            hash = hash.subtract(BigInteger.valueOf(val));
        }

        //divide the hash by a value
        public void divide(int val) {
            hash = hash.divide(BigInteger.valueOf(val));
        }

        public String toString() {
            return "a: "+a+" h:"+hash+" w:"+window+" p:"+ptr;
        }
    }

    public int findLength(int[] nums1, int[] nums2) {
        /**
         * Simple implementation:
         * Let us assume that (a) = 10, and long is sufficient for hashing
         */

        // Make sure nums2 is the shorter array
        if (nums2.length > nums1.length) {
            int[] t = nums1;
            nums1 = nums2;
            nums2 = t;
        }

        //Create a constant time lookup table for nums2 for short-circuiting
        Set<Integer> lookup = new HashSet();     //TODo is this the best struct?
        for (int i : nums2) lookup.add(i);

        // Struct for holding useful values
        Hash hash = new Hash(100,0,0,0);
        int best = 0;   // best result so far

        //While we have a valid window to look at in nums1
        while (hash.ptr + hash.window <= nums1.length) {
            //System.out.println("Current: "+hash.toString());
            //Search for the current hash
            if (search(nums2, hash)) {
                best = hash.window;             //Save our best match so far
                if (!grow(nums1, hash)) break;  //if there is a match increase window, if we cant grow - done!
            } else {
                if(!slide(nums1, hash)) break;  //Otherwise move pointer to right - if we cant - done!
            }
        }
        return best;
    }

    /**
     * Search target array for window of values with givent hash
     * Note, a window size of 0 always returns true (all sets contain the empty set)
     */
    private boolean search(int[] target, Hash hash1) {
        //if (hash1.window == 4) System.out.println("     search h1:"+hash1.hash);
        if (hash1.window == 0) return true;
        // If target size is less than window size, then result is not possible
        if (target.length < hash1.window) return false;

        // Initialise target hash
        Hash hash2 = new Hash(hash1.a, 0, 0, 0);
        while(hash2.window < hash1.window) {
            //if (hash1.window == 4) System.out.println("h2 grow");
            grow(target, hash2);
        }

        //if (hash1.window == 4) System.out.println("h2 init: "+hash2);

        // Slide until match, or end
        while(!hash2.hash.equals(hash1.hash)) {
            //if (hash1.window == 4) System.out.println("h2:"+hash2.hash);
            if (!slide(target, hash2)) break;
        }
        return hash2.hash.equals(hash1.hash);
    }

    /**
     * Grow window by one, return false if this is not possible
     */
    private boolean grow(int[] data, Hash hash) {
        //System.out.println("     grow");
        if (hash.ptr + hash.window >= data.length) return false;
        // Simply add v*a^w to the hash
        hash.window++;
        hash.mulpow(data[hash.ptr+hash.window-1]);
        return true;
    }

    /**
     * Slide window right one, return false if this is not possible
     */
    private boolean slide(int[] data, Hash hash) {
        //if (hash.window > 3) System.out.println("     slide. hash: "+hash);
        if (hash.ptr + hash.window >= data.length) return false;
        // 1. Sub current first value
        hash.subtract(data[hash.ptr++]);
        //if (hash.window > 3) System.out.println("     slide1 hash: "+hash);
        // 2. divide by a (i.e. shiifting 1 space)
        hash.divide(hash.a);
        //if (hash.window > 3) System.out.println("     slide2 hash: "+hash);
        // 3. add next value (v*a^w)
        hash.mulpow(data[hash.ptr+hash.window-1]);
        //if (hash.window > 3) System.out.println("     slide3 hash: "+hash);
        return true;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}