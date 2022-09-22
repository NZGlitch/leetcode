//Given an array of strings strs, group the anagrams together. You can return 
//the answer in any order. 
//
// An Anagram is a word or phrase formed by rearranging the letters of a 
//different word or phrase, typically using all the original letters exactly once. 
//
// 
// Example 1: 
// Input: strs = ["eat","tea","tan","ate","nat","bat"]
//Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
// Example 2: 
// Input: strs = [""]
//Output: [[""]]
// Example 3: 
// Input: strs = ["a"]
//Output: [["a"]]
// 
// 
// Constraints: 
//
// 
// 1 <= strs.length <= 10⁴ 
// 0 <= strs[i].length <= 100 
// strs[i] consists of lowercase English letters. 
// 
// Related Topics Array Hash Table String Sorting 👍 11750 👎 369


import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

class Question49 {
    //leetcode submit region begin(Prohibit modification and deletion)
    import java.math.BigInteger;
class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer,List<String>> anagrams = new HashMap<>();
        for (String s: strs) {
            char[] chrs = s.toCharArray();
            Arrays.sort(chrs);
            int hash = new String(chrs).hashCode();
            if (!anagrams.containsKey(hash)) anagrams.put(hash, new ArrayList());
            anagrams.get(hash).add(s);
        }

        return new ArrayList(anagrams.values());
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}