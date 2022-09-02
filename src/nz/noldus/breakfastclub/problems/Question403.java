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
import java.util.SortedSet;

class Question403 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canCross(int[] stones) {
        /**
         *          0   1   3   5   6   8   12  17
         *      0   X   T   X   X   X   X   X   X   (k=0 -> -1,0,1)
         *      1   X   T   T   X   X   X   X   X   (k=1 [0,1] -> 0,1,2)
         *      3   X   X   X   T   T   X   X   X   (k=2 [1,3] -> 1,2,3)
         *      5   X   X   X   X   T   T   X   X   (k=2 [3,5] -> 1,2,3)
         *      6   X   X   X   X   T   T   X   X   (k=1,3 [3,6][5,1] -> 0,1,2,3,4)
         *      8   X   X   X   X   X   X   T   X   (k=2,3 [5,8][6,1] -> 1,2,3,4)
         *      12  X   X   X   X   X   X   X   T   (k=5 [8,12] -> 3,4,5
         *
         */

        // If element 1 is not 1, there can not be a path
        if (stones[1] != 1) return false;
        // If there re only 2 stones, then there must be a path
        if (stones.length == 2) return true;

        //For performance, we want to keep the stones in a sorrted structure
        SortedSet<Integer> stoneSet = new TreeSet<>();
        for (int v : stones) stoneSet.add(v);

        //It will be useful to lookup a values index
        Map<Integer, Integer> idxs = new HashMap<>();
        for(int i=0; i<stones.length; i++) idxs.put(stones[i], i);

        // Table to keep a track of ways we can get from a->b
        // e.g. if dp[1][3] == True, then there is a 2-step path from stones[1] to stones[3]
        // Which must of ourse be size 2
        boolean dp[][] = new boolean[stones.length][stones.length];

        // There is always a bath from 0 to 1
        dp[0][1] = true;

        // For each row in the table
        for (int y=1; y<stones.length; y++) {
            //Our value
            int us = stones[y];
            //For each column in this row
            for (int x=y+1; x<stones.length; x++) {
                //The target is the xth stone
                int target = stones[x];

                //The step size to get to this target
                int step = target-us;

                //the minimum stone value we must come from is
                int nMin = (us-step)-1;

                //the maximum stone value we could have come from is
                int nMax = (us-step)+1;

                //if nmax < 0, there is no possibility of a result
                if (nMax < 0) continue;

                //Is there a stone between nMin and nMax inclusive that has a path to us as true?
                for (int v : stoneSet.tailSet(nMin)) {
                    // If v is too big, quit - no more to look ut
                    if (v > nMax) break;
                    if (dp[idxs.get(v)][y]) {
                        //There is a path from <v> to stones[y]
                        //and it's step size is in the range that allows us to go to stones[x]
                        dp[y][x] = true;
                        // Is this the end goal? If so, we are done
                        if (x == stones.length - 1) return true; //if yes we are done
                    }
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}