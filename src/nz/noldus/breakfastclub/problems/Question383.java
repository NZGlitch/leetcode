//Given two strings ransomNote and magazine, return true if ransomNote can be 
//constructed by using the letters from magazine and false otherwise. 
//
// Each letter in magazine can only be used once in ransomNote. 
//
// 
// Example 1: 
// Input: ransomNote = "a", magazine = "b"
//Output: false
// Example 2: 
// Input: ransomNote = "aa", magazine = "ab"
//Output: false
// Example 3: 
// Input: ransomNote = "aa", magazine = "aab"
//Output: true
// 
// 
// Constraints: 
//
// 
// 1 <= ransomNote.length, magazine.length <= 10⁵ 
// ransomNote and magazine consist of lowercase English letters. 
// 
// Related Topics Hash Table String Counting 👍 2927 👎 357


class Question383 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] table = new int[255];
        for (int i=0; i< ransomNote.length(); i++)
            table[ransomNote.charAt(i)]++;
        int count = ransomNote.length();
        for(int i=0; i<magazine.length() && count >0; i++) {
            if (table[magazine.charAt(i)] > 0) {
                table[magazine.charAt(i)]--;
                count--;
            }
        }
        return count == 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}