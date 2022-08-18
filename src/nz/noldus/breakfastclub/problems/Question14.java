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
        // Lets sort by length first - as we only need to look
        // at the shortest string first
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });

        // The best possible result is the shortest string
        char[] best = strs[0].toCharArray();
        int len = best.length;

        //Iterate through the remainin strings
        for (int i=1; i<strs.length; i++) {
            // Compare our current best with this string
            for (int j = 0; j < len; j++) {
                if (best[j] != strs[i].charAt(j)) {
                    //Not a match
                    len = j;
                    break;
                }
            }
        }
        // Use the len chars from best[] to construct the answer
        return new String(best, 0, len);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}