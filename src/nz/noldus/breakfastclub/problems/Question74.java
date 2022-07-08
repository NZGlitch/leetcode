//Write an efficient algorithm that searches for a value target in an m x n 
//integer matrix matrix. This matrix has the following properties: 
//
// 
// Integers in each row are sorted from left to right. 
// The first integer of each row is greater than the last integer of the 
//previous row. 
// 
//
// 
// Example 1: 
//
// 
//Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//Output: true
// 
//
// Example 2: 
//
// 
//Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//Output: false
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -10⁴ <= matrix[i][j], target <= 10⁴ 
// 
// Related Topics Array Binary Search Matrix 👍 8353 👎 285


class Question74 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        // We can use binary search to find the row the answer must reside in
        int row = findRow(matrix, target, 0, matrix.length-1);

        // We can use binary search to find the target in optimal time
        int index = Arrays.binarySearch(matrix[row], target);

        // If index is < 0 then we couldnt find the target
        return index >= 0;
    }

    public int findRow(int[][] matrix, int target, int low, int high) {
        // Build in binary search is not optimal for finding the row, we would need to
        // create a new array of indexes, so instead we implement binary search ourselves

        if (low >= high) return low;        //Base case = only 1 row to look at, must be the one

        // Get the mid point
        int mid = (low+high) / 2;

        // Is the answer in the current mid row?
        if (target >= matrix[mid][0] && target <= matrix[mid][matrix[mid].length-1]) return mid;

        //check if target < r[mid][0] , if so, search r[low]->r[mid-1]
        else if (target < matrix[mid][0]) return findRow(matrix, target, low, mid-1);

        //check if target > r[mid][n], if so search r[mid+1][high]
        else return findRow(matrix, target, mid+1, high);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}