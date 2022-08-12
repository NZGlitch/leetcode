//Given an array of intervals where intervals[i] = [starti, endi], merge all 
//overlapping intervals, and return an array of the non-overlapping intervals that 
//cover all the intervals in the input. 
//
// 
// Example 1: 
//
// 
//Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
// 
//
// Example 2: 
//
// 
//Input: intervals = [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping.
// 
//
// 
// Constraints: 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
// Related Topics Array Sorting 👍 15390 👎 555


import java.util.ArrayList;

class Question56 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        SortedSet<Integer> periods = new TreeSet();
        for (int i=0; i<intervals.length; i++) {
            for(int j=intervals[i][0]*2; j<=intervals[i][1]*2; j++) {
                periods.add(j);
            }
        }
        ArrayList<int[]> merged = new ArrayList<>();
        int s = periods.first();
        int e = periods.first();
        for(int p : periods) {
            //Check contiguous
            if (p <= e + 1) e = p;
            else {
                merged.add(new int[] {s/2,e/2});
                s = p;
                e = p;
            }
        }
        //Add the last one
        merged.add(new int[] {s/2,e/2});

        int[][] res = new int[merged.size()][2];
        for (int i=0; i< merged.size(); i++) res[i] = merged.get(i);
        return res;
    }
}

[1,4][5,8],[6,10]
  2,3,4,5,6,7,8|10,11,12,13,14,15,16|

//leetcode submit region end(Prohibit modification and deletion)

}