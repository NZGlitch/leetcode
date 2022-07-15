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
        char[] shrt; char[] lng;
        if (n1.length() > n2.length()) {
            lng = new StringBuilder(n2).reverse().toString().toCharArray();
            shrt = new StringBuilder(n1).reverse().toString().toCharArray();
        } else {
            shrt = new StringBuilder(n2).reverse().toString().toCharArray();
            lng = new StringBuilder(n1).reverse().toString().toCharArray();
        }

        // A place to store the overall result
        char[] product = new char[shrt.length+lng.length+1];

        // Convert our byte arrays to numbers (in the ascii table, subtrcing 48 from a
        // char gives you its integer value
        for (int i =0; i<shrt.length; i++) shrt[i] = (char)(shrt[i]-48);
        for (int i =0; i<lng.length; i++) lng[i] = (char)(lng[i]-48);

        // Iterate through each number in 'short'
        for (int i = 0; i<shrt.length; i++) {
            // Multiply each character in short by each character in long
            for (int j=0; j<lng.length; j++) {
                char[] prd = mul(shrt[i], lng[j]);
                // Add this to our running total
                sum(product, prd, i+j);
            }
        }

        // Convert product array back to chars
        for (int i =0; i<product.length; i++) product[i] = (char)(product[i]+48);

        // Convert product back to a string (dont forget to reverse)
        String res = new StringBuilder(new String(product))
                .reverse()
                .toString();

        // Finally, remove leading zeros
        return res.replaceFirst("^0+(?!$)", "");


    }

    // Given two char 'numbers' return a char array of thier product
    char[] mul(char a, char b) {
        int p = a*b;
        return new char[] {
                (char)(p%10),
                (char)(p/10)
        };
    }


    /**
     * Perform accum = accum+val
     * accum: an array of digits
     * val: an array of digits MUST BE exaclty 2 values
     * offset: how many places to shift val
      */
    void sum(char[] accum, char[] val, int offset) {
        int s1,s2,s3 = 0;
        s1 = val[0] + accum[offset];
        s2 = s1/10;
        s1 = s1%10;
        s2+= val[1] + accum[offset+1];
        s3 = s2/10;
        s2 = s2%10;
        accum[offset] = (char)s1;
        accum[offset+1] = (char)s2;
        accum[offset+2]+= (char)s3;
    }

    // Helper method for converting a char array to a string
    String pca(char[] arr) {
        String s = "";
        for (int i = 0; i< arr.length; i++) s+=""+(int)arr[i];
        return s;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

}