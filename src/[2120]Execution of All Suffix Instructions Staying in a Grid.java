//There is an n x n grid, with the top-left cell at (0, 0) and the bottom-right 
//cell at (n - 1, n - 1). You are given the integer n and an integer array 
//startPos where startPos = [startrow, startcol] indicates that a robot is initially at 
//cell (startrow, startcol). 
//
// You are also given a 0-indexed string s of length m where s[i] is the iᵗʰ 
//instruction for the robot: 'L' (move left), 'R' (move right), 'U' (move up), and 
//'D' (move down). 
//
// The robot can begin executing from any iᵗʰ instruction in s. It executes the 
//instructions one by one towards the end of s but it stops if either of these 
//conditions is met: 
//
// 
// The next instruction will move the robot off the grid. 
// There are no more instructions left to execute. 
// 
//
// Return an array answer of length m where answer[i] is the number of 
//instructions the robot can execute if the robot begins executing from the iᵗʰ 
//instruction in s. 
//
// 
// Example 1: 
//
// 
//Input: n = 3, startPos = [0,1], s = "RRDDLU"
//Output: [1,5,4,3,1,0]
//Explanation: Starting from startPos and beginning execution from the iᵗʰ 
//instruction:
//- 0ᵗʰ: "RRDDLU". Only one instruction "R" can be executed before it moves off 
//the grid.
//- 1ˢᵗ:  "RDDLU". All five instructions can be executed while it stays in the 
//grid and ends at (1, 1).
//- 2ⁿᵈ:   "DDLU". All four instructions can be executed while it stays in the 
//grid and ends at (1, 0).
//- 3ʳᵈ:    "DLU". All three instructions can be executed while it stays in the 
//grid and ends at (0, 0).
//- 4ᵗʰ:     "LU". Only one instruction "L" can be executed before it moves off 
//the grid.
//- 5ᵗʰ:      "U". If moving up, it would move off the grid.
// 
//
// Example 2: 
//
// 
//Input: n = 2, startPos = [1,1], s = "LURD"
//Output: [4,1,0,0]
//Explanation:
//- 0ᵗʰ: "LURD".
//- 1ˢᵗ:  "URD".
//- 2ⁿᵈ:   "RD".
//- 3ʳᵈ:    "D".
// 
//
// Example 3: 
//
// 
//Input: n = 1, startPos = [0,0], s = "LRUD"
//Output: [0,0,0,0]
//Explanation: No matter which instruction the robot begins execution from, it 
//would move off the grid.
// 
//
// 
// Constraints: 
//
// 
// m == s.length 
// 1 <= n, m <= 500 
// startPos.length == 2 
// 0 <= startrow, startcol < n 
// s consists of 'L', 'R', 'U', and 'D'. 
// 
// Related Topics String Simulation 👍 274 👎 26


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] executeInstructions(int n, int[] startPos, String s) {
        Pos root = new Pos(startPos, null);
        int res[] = new int[s.length()];
        for (int i = s.length()-1; i>=0; i--) {
            root.applyMove(s.charAt(i));
            root = new Pos(startPos, root);
            res[i] = root.next.validMoves(n);
        }
        return res;
    }

    class Pos {
        int x;
        int y;
        final Pos next;

        Pos(int[] startPos, Pos next) {
            this.y = startPos[0];
            this.x = startPos[1];
            this.next = next;
        }

        void applyMove(char m) {
            switch (m) {
                case 'L': this.x--; break;
                case 'R': this.x++; break;
                case 'U': this.y--; break;
                case 'D': this.y++; break;
            }
            if (next != null) next.applyMove(m);
        }

        int validMoves(int n) {
            if (!(this.x >= 0 && this.y >= 0 && this.x < n && this.y < n))
                return 0;
            return (next == null) ? 1 : 1+next.validMoves(n);
        }

//        public String toString() {
//            String s =  "-("+x+","+y+")-";
//            if (next == null) return s;
//            else return s+next.toString();
//        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
