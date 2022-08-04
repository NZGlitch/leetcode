//Given an m x n matrix, return all elements of the matrix in spiral order. 
//
// 
// Example 1: 
//
// 
//Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [1,2,3,6,9,8,7,4,5]
// 
//
// Example 2: 
//
// 
//Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//Output: [1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics Array Matrix Simulation 👍 8236 👎 886


import java.util.ArrayList;

class Question54 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

        // These variables set the current 'bounds' of the matrix;
        short x1=0,y1=0;                                  // Top left
        int x2=matrix[0].length-1,y2=matrix.length-1;   // Bottom right

        // Result array we are going to build
        ArrayList<Integer> result = new ArrayList<>();

        // Current index of result array
        short idx = 0;

        // Current coordinate
        short x = 0, y=0;

        // Current direction
        short dir = 0;

        // Continue until there are no more 'spaces' to check
        while (x2 >= x1 && y2 >= y1) {
            // Add the current element to the list
            result.add(matrix[y][x]);
            switch(dir) {
                case 0:     // Moving right
                    if (x == x2) { dir = 1; y1++; y++; }
                    else x++;
                    break;
                case 1:     // Moving down
                    if (y == y2) { dir = 2; x2--; x--; }
                    else y++;
                    break;
                case 2:     // Moving left
                    if (x == x1) { dir = 3; y2--; y--; }
                    else x--;
                    break;
                case 3:     // Moving up
                    if (y == y1) { dir = 0; x1++; x++; }
                    else y--;
                    break;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}