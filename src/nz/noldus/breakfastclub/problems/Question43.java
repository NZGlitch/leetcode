//Given two non-negative integers num1 and num2 represented as strings, return
//the product of num1 and num2, also represented as a string. 
//
// Note: You must not use any built-in BigInteger library or convert the inputs 
//to integer directly. 
//
// 
// Example 1: 
// Input: num1 = "2", num2 = "3"
//Output: "6"
// Example 2: 
// Input: num1 = "123", num2 = "456"
//Output: "56088"
// 
// 
// Constraints: 
//
// 
// 1 <= num1.length, num2.length <= 200 
// num1 and num2 consist of digits only. 
// Both num1 and num2 do not contain any leading zero, except the number 0 
//itself. 
// 
// Related Topics Math String Simulation 👍 4698 👎 1896


class Question43 {
    //leetcode submit region begin(Prohibit modification and deletion)
    import java.nio.charset.Charset;
    import java.nio.charset.StandardCharsets;
    import java.util.Collections;


    public  class Solution {
    public String multiply(String n1, String n2) {
        // A string is just a character array, so lets use that
        // We want t be 'little endian' so we need to reverse the strings first
        char[] a = stringToReversedIntCharArray(n1);
        char[] b = stringToReversedIntCharArray(n2);

        // We want to have the shorter number in the outside llop, both for efficiency, and to prevent
        // index out of bounds issues - so swap them if they are the wrong way around
        if (a.length > b.length) {
            char[] c = a;
            a=b;
            b=c;
        }

        // A place to store the overall result. Max length is a.len + b.len
        char[] product = new char[a.length+b.length];

        // Iterate through each digit in the shorter number
        for (int i = 0; i<a.length; i++) {

            // Multiply each digit in a by each digit in b
            for (int j=0; j<b.length; j++) {
                // Add the sum of these values to the current product array
                // The offset is how many 'places' we need to shift the result of the multiplication
                // to have it in the right place
                sum(product, a[i]* b[j], i+j);
            }
        }

        //TODO use streams here if possible


        // Convert product array back to chars
        int indexLastNonZero = 0;       //need this to remove leading zeros later
        String result = "";
        for (int i = 0; i < product.length; i++) {
            int v = product[i];
            if (v != 0) indexLastNonZero = i;
            result = (char)(v+48) + result;
        }

        //remove leading zeros
        if (indexLastNonZero != result.length()-1) result = result.substring(result.length()-1-indexLastNonZero);
        return result;
    }

        /**
         * Converst the string to something more usable - in this case a char[] with
         * the value being the actual int value of a digit in the string, and the digits
         * reveresed so we have the right 'endyness'
         */

        char [] stringToReversedIntCharArray(String s) {
            char[] res = new char[s.length()];
            for (int i =0; i<s.length(); i++) res[s.length()-1-i] = (char)(s.charAt(i)-48);
            return res;
        }

        /**
         * Perform accum = accum+val
         * accum: an array of digits
         * val: an array of digits MUST BE exaclty 2 values
         * offset: how many places to shift val
          */
        void sum(char[] accum, int c, int offset) {
            // Basically doing pen+paper addition one digit at a time until there are
            // no more carry overs.

            int a=0,r=0;
            do {
                a = accum[offset];
                r = (a+c)%10;
                c = (a+c)/10;
                accum[offset++] = (char)r;

            } while (c != 0);
        }
    /**
    // Helper method for converting a char array to a string
    String pca(char[] arr) {
        String s = "";
        for (int i = 0; i< arr.length; i++) s+=""+(int)arr[i];
        return s;
    }
    */
}

//leetcode submit region end(Prohibit modification and deletion)

}