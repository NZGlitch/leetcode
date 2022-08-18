//The n-queens puzzle is the problem of placing n queens on an n x n chessboard 
//such that no two queens attack each other. 
//
// Given an integer n, return all distinct solutions to the n-queens puzzle. 
//You may return the answer in any order. 
//
// Each solution contains a distinct board configuration of the n-queens' 
//placement, where 'Q' and '.' both indicate a queen and an empty space, respectively. 
//
// 
// Example 1: 
//
// 
//Input: n = 4
//Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//Explanation: There exist two distinct solutions to the 4-queens puzzle as 
//shown above
// 
//
// Example 2: 
//
// 
//Input: n = 1
//Output: [["Q"]]
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 9 
// 
// Related Topics Array Backtracking 👍 7898 👎 182


import java.util.ArrayList;

class Question51 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> solveNQueens(int n) {
        // Lets keep a record of rows/columns used
        // ' ' means available, 'Q' means queen, '.' means blocked
        String board = new String(new char[n*n]).replace('\u0000', ' ');
        Stack<String> stack = new Stack();
        stack.push(board);

        List<List<String>> answer = new ArrayList<>();


        // for a naive solution, we will ignore rotations and other optimisations
        while (!stack.isEmpty()) {
            String current = stack.pop();

            if (current.charAt(current.length()-1) != ' ') {
                //This is a complete answer
                List<String> ans = new ArrayList<>();
                for (int i=0; i<n; i++) {
                    ans.add(current.substring(i*n, i*n+4));
                }
                answer.add(ans);
            }
            // find the first available space
            for (int i=0; i<current.length(); i++) {
                if (current.charAt(i) == ' ') {
                    //Create a board with the queen added at position i
                    stack.push(addQueen(current, i,n));
                    //adding 'dot' board
                    stack.push(current.substring(0,i)+'.'+current.substring(i+1, current.length()));
                    break;
                }
            }
        }
        return answer;
    }

    private String addQueen(String src, int pos, int n) {
        char[] string = src.toCharArray();
        // Need to dot out all the rows, columns and diaganals
        // y coord = pos/n  -> 2
        int y = pos/n;
        int x = pos%n;
        for (int i=0; i<n; i++) {
            string[y*n+i] = '.';
            string[i*n+x] = '.';
        }

        int y1 = y; int x1=x;
        while (y1>0 && x1>0) {
            y1--; x1--;
        }
        while (y1<n && x1<n) {
            string[y1*n+x1] = '.';
        }
        string[y*n+x] = 'Q';
        return new String(string);
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}