//Given two strings s and t, return true if t is an anagram of s, and false 
//otherwise. 
//
// An Anagram is a word or phrase formed by rearranging the letters of a 
//different word or phrase, typically using all the original letters exactly once. 
//
// 
// Example 1: 
// Input: s = "anagram", t = "nagaram"
//Output: true
// Example 2: 
// Input: s = "rat", t = "car"
//Output: false
// 
// 
// Constraints: 
//
// 
// 1 <= s.length, t.length <= 5 * 10⁴ 
// s and t consist of lowercase English letters. 
// 
//
// 
// Follow up: What if the inputs contain Unicode characters? How would you 
//adapt your solution to such a case? 
// Related Topics Hash Table String Sorting 👍 6333 👎 234


class Question242 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] ht = new int[255];
        for (int i=0; i< s.length(); i++) ht[s.charAt(i)]++;
        for (int i=0; i< t.length(); i++) ht[t.charAt(i)]--;
        for (int i=0; i<255; i++) if (ht[i] != 0) return false;
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}