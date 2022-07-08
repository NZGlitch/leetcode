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

        // Stacks are ideally suited to this sort of problem, as they will keep a track of the last
        // opening bracket automatically for us
        Stack<Character> parens = new Stack();

        //We want a mapping of opening brackets to closing brackets - the most optimal way to do this
        //in java is to use ascii code as an index on a char array
        char[] mapParens = new char[126];
        mapParens['('] = ')'; mapParens['['] = ']'; mapParens['{'] = '}';
        int idx = -1;

        while (++idx < s.length()) {

            //Get the next character in the string
            char next = s.charAt(idx);

            // If the next char is in our map (i.e. != \0), it is an opening bracket
            if (mapParens[next] != '\u0000') parens.push(next);
            else if ( !parens.isEmpty() ){
                // Otherwise the character's closing brace MUST be mapped to the current top of the stack
                if (mapParens[parens.pop()] != next) return false;
            } else return false;  //Nothing on stack to match

        }
        //End of string without errors, result is true if stack is empty
        return parens.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}