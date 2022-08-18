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


class Question14 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        String best = strs[0];
        for (int i=0; i<strs.length; i++) {
            String newBest = "";
            for(int j=0; j<best.length() && j< strs[i].length(); j++) {
                if (best.charAt(j) == strs[i].charAt(j)) newBest+=best.charAt(j);
                else break;
            }
            best = newBest;
        }
        return best;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}