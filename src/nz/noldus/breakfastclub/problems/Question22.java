//Given n pairs of parentheses, write a function to generate all combinations 
//of well-formed parentheses. 
//
// 
// Example 1: 
// Input: n = 3
//Output: ["((()))","(()())","(())()","()(())","()()()"]
// Example 2: 
// Input: n = 1
//Output: ["()"]
// 
// 
// Constraints: 
//
// 
// 1 <= n <= 8 
// 
// Related Topics String Dynamic Programming Backtracking 👍 14194 👎 532


import java.util.ArrayList;

class Question22 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class Node {
        final char sym;
        final int open;
        final int pairs;
        Node nextOpen;
        Node nextClosed;

        public Node() {
            this.sym = '\0';
            this.open = 0;
            this.pairs = 0;
        }
        public Node(char sym, Node prev) {
            this.sym = sym;
            if (sym == '(') {
                this.open = prev.open + 1;
                this.pairs = prev.pairs;

            }
            else {
                this.open = prev.open -1;
                this.pairs = prev.pairs + 1;
            }
        }

        public boolean valid(int n) {
            if (this.sym == '\0') return true;
            if (this.open < 0) return false;
            if (this.sym == '(') return this.pairs < n;
            return this.pairs <= n;
        }

        public void genNodes(int o, int c, int n) {
            //System.out.println("genNodes("+o+","+c+","+n+")");
            if (o > 0) {
                Node open = new Node('(', this);
                if (open.valid(n)) {
                    //System.out.println("\tAdd valid open");
                    this.nextOpen = open;
                    this.nextOpen.genNodes(o - 1, c, n);
                }
            }
            if (c > 0) {
                Node closed = new Node(')', this);
                if (closed.valid(n)) {
                    //System.out.println("\tAdd valid close");
                    nextClosed = closed;
                    nextClosed.genNodes(o, c-1, n);
                }
            }
        }

        public List<String> genStrings() {
            List<String> merged = new ArrayList<>();
            if (nextOpen != null) {
                if (this.sym == '\0') merged.addAll(nextOpen.genStrings());
                else for (String o : nextOpen.genStrings()) merged.add(this.sym + o);
            }

            if (nextClosed != null) {
                if (this.sym == '\0') merged.addAll(nextClosed.genStrings());
                else for (String c : nextClosed.genStrings()) merged.add(this.sym + c);
            }

            if (nextOpen == null && nextClosed == null && this.sym != '\0') merged.add(""+this.sym);

            return merged;
        }
    }
    public List<String> generateParenthesis(int n) {
        Node root = new Node();
        root.genNodes(n,n,n);
        return root.genStrings();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}