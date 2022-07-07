//Given a string s containing just the characters '(', ')', '{', '}', '[' and ']
//', determine if the input string is valid. 
//
// An input string is valid if: 
//
// 
// Open brackets must be closed by the same type of brackets. 
// Open brackets must be closed in the correct order. 
// 
//
// 
// Example 1: 
//
// 
//Input: s = "()"
//Output: true
// 
//
// Example 2: 
//
// 
//Input: s = "()[]{}"
//Output: true
// 
//
// Example 3: 
//
// 
//Input: s = "(]"
//Output: false
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10⁴ 
// s consists of parentheses only '()[]{}'. 
// 
// Related Topics String Stack 👍 14018 👎 652


class Question20 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        Stack<Character> parens = new Stack();
        Map<Character,Character> matching = new HashMap();
        matching.put('(',')');
        matching.put('[',']');
        matching.put('{','}');

        for (int i = 0; i<s.length(); i++) {
            char next = s.charAt(i);
            //Opening bracket
            if (matching.containsKey(next)) {
                parens.push(next);
            } else {
                if (parens.isEmpty()) return false;
                char close = parens.pop();
                if (matching.get(close) != next) return false;
            }
        }
        return parens.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}