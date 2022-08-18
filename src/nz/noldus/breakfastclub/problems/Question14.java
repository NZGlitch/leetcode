//Write a function to find the longest common prefix string amongst an array of 
//strings. 
//
// If there is no common prefix, return an empty string "". 
//
// 
// Example 1: 
//
// 
//Input: strs = ["flower","flow","flight"]
//Output: "fl"
// 
//
// Example 2: 
//
// 
//Input: strs = ["dog","racecar","car"]
//Output: ""
//Explanation: There is no common prefix among the input strings.
// 
//
// 
// Constraints: 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] consists of only lowercase English letters. 
// 
// Related Topics String 👍 9692 👎 3293


import java.util.Arrays;
import java.util.Comparator;

class Question14 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        // Find the shortest string first, that will be our base
        String shortest = strs[0];
        for (int i=0; i<strs.length; i++)
            if (strs[i].length() < shortest.length()) shortest = strs[i];

        // The best possible result can not be longer than the shortest
        // String
        char[] best = shortest.toCharArray();
        int bestLen = best.length;

        //Iterate through the remainin strings
        for (int i=0; i<strs.length; i++) {
            // Compare our current best with this string
            for (int j = 0; j < bestLen; j++) {
                if (best[j] != strs[i].charAt(j)) {
                    //Not a match
                    bestLen = j;
                    break;
                }
            }
        }
        // Use the len chars from best[] to construct the answer
        return new String(best, 0, bestLen);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}