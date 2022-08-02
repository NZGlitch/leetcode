//Given n pairs of parentheses, write a function to generate all combinations 
//of well-formed parentheses. 
//
// 
// Example 1: 
// Input: n = 3
//Output: ["((()))","(()())","(())()","()(())","()()()"]
// Example 2: 
// Input: n = 1
//Output: ["()"]
// 
// 
// Constraints: 
//
// 
// 1 <= n <= 8 
// 
// Related Topics String Dynamic Programming Backtracking 👍 14194 👎 532


import java.util.ArrayList;

class Question22 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public List<String> generateParenthesis(int pairsToFind) {
        List<String> validSolutions = new ArrayList<>();
        findSolutions(pairsToFind, 0, 0, 0, new char[pairsToFind*2], validSolutions);
        return validSolutions;
    }

    public void findSolutions(int pairsToFind, int pairCount, int openCount, int curIdx, char[] curString, List<String> solutions) {
        //is the current string a solution?
        if (pairsToFind == pairCount && openCount == 0) {
            solutions.add(new String(curString));
            return;
        }

        //if there is space for another open brace
        if (pairCount + openCount < pairsToFind) {
            curString[curIdx] = '(';
            findSolutions(pairsToFind, pairCount, openCount+1, curIdx + 1, curString, solutions);
        }

        //If we can add a close brace
        if (pairCount < pairsToFind && openCount > 0) {
            curString[curIdx] = ')';
            findSolutions(pairsToFind, pairCount+1, openCount-1, curIdx +1, curString, solutions);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}