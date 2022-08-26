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
        Set<Integer> lookup = new HashSet();
        for (int i : nums2) lookup.add(i);
        int window = 1;
        int ptr = 0;
        BigInteger hash = BigInteger.ZERO;
        int hashsize = 0;
        int best = 0;

        //While our n1 pointer is within range
        while(true) {
            //If our hashsize is less than our window size, 'grow' our hash by one
            //System.out.println("w:"+window+" p:"+ptr+" hs:"+hashsize+" b:"+best+" h"+hash);
            if (hashsize < window && ptr < nums1.length) {
               // System.out.println("    grow");
                hash = hash.add(mulpow(nums1[ptr++], 100, hashsize++));
                continue; //restart loop
            } else if (hashsize < window) {
                //We cant grow our window, so we are done
                //System.out.println("     No space");
                return best;
            }

            //search second array for matching hash. If found increase window size
            if (search(nums2, window, hash)) {
                //System.out.println("    search");
                best = window;
                window++;       //this will automatically cause ptr to be increased
            } else if (ptr < nums1.length-1) {
                //System.out.print("    slide");
                //Not found, move our window
                // Two cases -
                    // the next value is in the taget arrey, window >> 1
                    // the next value is Not in thetarget array ptr>>next found value, new window
                if (lookup.contains(nums1[ptr])) {
                    //System.out.println(" single");
                    //1 subtract leading number
                    hash = hash.subtract(BigInteger.valueOf(nums1[ptr - window]).multiply(BigInteger.ONE));
                    //2 divide by exp
                    hash = hash.divide(BigInteger.valueOf(100));
                    //3 Add next number
                    hash = hash.add(mulpow(nums1[ptr++], 100, window - 1));

                } else {
                    //System.out.println(" multi");
                    // Move the pointer to the next number that appears in the target set as well
                    while((ptr+window) < nums1.length && !lookup.contains(nums1[ptr])) ptr++;
                    //reset hashsize
                    hashsize = 0;
                    hash = BigInteger.ZERO;
                    ptr--;
                }
            } else {
                //System.out.println("    done");
                //We are at the end, our best answer is window size less 1
                return best;
            }
        }
     }
     public boolean search(int[] haystack, int window, BigInteger h1) {
        //System.out.println("SEARCH w:"+window+" h1:"+h1);
        //create hash of size window
         BigInteger h2 = BigInteger.ZERO;
         int ptr=window;
         for (int i = 0; i<window; i++) {
             h2 = h2.add(mulpow(haystack[i], 100, i));
         }
         //System.out.println("        init h2:"+h2);
         //iterate through list, sliding window until there is a match
         while ((!h1.equals(h2)) && (ptr < haystack.length)) {
             //System.out.println("        slide h2:"+h2);
             //slide right
             h2 = h2.subtract(BigInteger.valueOf(haystack[ptr-window]));
             //System.out.println("        slide a h2:"+h2);
             h2 = h2.divide(BigInteger.valueOf(100));
             //System.out.println("        slide b h2:"+h2);
             h2 = h2.add(mulpow(haystack[ptr++],100,window-1));
             //System.out.println("        slide c h2:"+h2);
         }
         return h1.equals(h2);
     }

     public BigInteger mulpow(int v, int base, int pow) {
        return BigInteger.valueOf(v).multiply(BigInteger.valueOf(base).pow(pow));
     }
}
//leetcode submit region end(Prohibit modification and deletion)

}