//A frog is crossing a river. The river is divided into some number of units, 
//and at each unit, there may or may not exist a stone. The frog can jump on a 
//stone, but it must not jump into the water. 
//
// Given a list of stones' positions (in units) in sorted ascending order, 
//determine if the frog can cross the river by landing on the last stone. Initially, 
//the frog is on the first stone and assumes the first jump must be 1 unit. 
//
// If the frog's last jump was k units, its next jump must be either k - 1, k, 
//or k + 1 units. The frog can only jump in the forward direction. 
//
// 
// Example 1: 
//
// 
//Input: stones = [0,1,3,5,6,8,12,17]
//Output: true
//Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd 
//stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 
//units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
// 
//
// Example 2: 
//
// 
//Input: stones = [0,1,2,3,4,8,9,11]
//Output: false
//Explanation: There is no way to jump to the last stone as the gap between the 
//5th and 6th stone is too large.
// 
//
// 
// Constraints: 
//
// 
// 2 <= stones.length <= 2000 
// 0 <= stones[i] <= 2³¹ - 1 
// stones[0] == 0 
// stones is sorted in a strictly increasing order. 
// 
// Related Topics Array Dynamic Programming 👍 3066 👎 178


import java.util.HashMap;

class Question403 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canCross(int[] stones) {
        /**
         *          0   1   3   5   6   8   12  17
         *      0   X   1   X   X   X   X   X   X   (k=0 -> -1,0,1)
         *      1   X   0   2   X   X   X   X   X   (k=1 [0,1] -> 0,1,2)
         *      3   X   X   X   2   3   X   X   X   (k=2 [1,3] -> 1,2,3)
         *      5   X   X   X   X   1   3   X   X   (k=2 [3,5] -> 1,2,3)
         *      6   X   X   X   X   0   2   X   X   (k=1,3 [3,6][5,1] -> 0,1,2,3,4)
         *      8   X   X   X   X   X   X   4   X   (k=2,3 [5,8][6,1] -> 1,2,3,4)
         *      12  X   X   X   X   X   X   X   5   (k=5 [8,12] -> 3,4,5
         */

        // If element 1 is not 1, there can not be a path
        if (stones[1] != 1) return false;

        // Dynamix table of partial solutions
        int dp[][] = new int[stones.length][stones.length];

        // Initialise first row which is always just a 1 at idx 1
        dp[0] = new int[stones.length];
        for (int i=0; i<stones.length; i++) dp[0][i] = Integer.MIN_VALUE;
        dp[0][1] = 1;

        // For each row in the table
        for (int y=1; y<stones.length; y++) {
            //Calculate valid steps for this row
            List<Integer> steps = new ArrayList<Integer>();
            //Check each value in column index Y, from row 0 to y-1
            for (int y2=0; y2<y; y2++) {
                if (dp[y2][y] != Integer.MIN_VALUE) {
                    steps.add(dp[y2][y]-1);
                    steps.add(dp[y2][y]);
                    steps.add(dp[y2][y]+1);
                }
            }
            //Our value
            int us = stones[y];
            //For each column in row Y
            for (int x=0; x<stones.length; x++) {
                //The target is the xth stone
                int target = stones[x];
                //Is there a step that gets us to target?
                if (steps.contains(target-us)){
                    //Yes, if the target is the last stone, we are done
                    if (target == stones[stones.length-1]) return true;
                    //Otherwise record this step
                    dp[y][x] = (target-us);
                }
                //No, set this to min value (i.e. dont care)
                else dp[y][x] = Integer.MIN_VALUE;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}