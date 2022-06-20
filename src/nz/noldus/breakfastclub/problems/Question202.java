//Write an algorithm to determine if a number n is happy. 
//
// A happy number is a number defined by the following process: 
//
// 
// Starting with any positive integer, replace the number by the sum of the 
//squares of its digits. 
// Repeat the process until the number equals 1 (where it will stay), or it 
//loops endlessly in a cycle which does not include 1. 
// Those numbers for which this process ends in 1 are happy. 
// 
//
// Return true if n is a happy number, and false if not. 
//
// 
// Example 1: 
//
// 
//Input: n = 19
//Output: true
//Explanation:
//1² + 9² = 82
//8² + 2² = 68
//6² + 8² = 100
//1² + 0² + 0² = 1
// 
//
// Example 2: 
//
// 
//Input: n = 2
//Output: false
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 2³¹ - 1 
// 
// Related Topics Hash Table Math Two Pointers 👍 5651 👎 729


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Question202 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    public boolean isHappy(int n) {
        Set<Integer> ht = new HashSet(); //Set of vistied numbers
        //Map<Integer,Integer> sst = new HashMap(); //Table of known sumsquares
        int[] sq = new int[] {0,1,4,9,16,25,36,49,64,81}; //Squares

        while (n != 1) {
            if (ht.contains(n)) return false; // we've been here before
            else ht.add(n);                   // record this visit;
            int sum = ss(n,sq/*,sst*/); //new number, lets work out its sumSquared
            n=sum;
        }
        return true;
    }

    public int ss(int n, int[] sq /*, Map<Integer,Integer> sst */) {
        if (n <=9 ) return sq[n];
        //if (sst.containsKey(n)) return sst.get(n);

        //seperate last digit from rest
        int last = n%10;
        int rest = n/10;

        //Answer is square of last digit plus ss(rest)
        int res =  sq[last] + ss(rest,sq/*,sst*/);

        //Save in case we need to do this number again
        //sst.put(n,res);

        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}