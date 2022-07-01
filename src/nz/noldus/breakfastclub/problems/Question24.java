//Given a linked list, swap every two adjacent nodes and return its head. You 
//must solve the problem without modifying the values in the list's nodes (i.e., 
//only nodes themselves may be changed.) 
//
// 
// Example 1: 
//
// 
//Input: head = [1,2,3,4]
//Output: [2,1,4,3]
// 
//
// Example 2: 
//
// 
//Input: head = []
//Output: []
// 
//
// Example 3: 
//
// 
//Input: head = [1]
//Output: [1]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is in the range [0, 100]. 
// 0 <= Node.val <= 100 
// 
// Related Topics Linked List Recursion 👍 7319 👎 304


class Question24 {
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        //Base cases - no more nodes to process:
        if (head == null) return head;
        if (head.next == null) return head;

        ListNode newHead = head.next;      // The new head is the next node (1->[2]->Rest)
        ListNode rest = newHead.next;      // The rest is the next nodes next node (2->[Rest])
        newHead.next = head;               // Point new nodes head to the current head (2[->]1->2->1...)
        head.next = swapPairs(rest);       // Point the oringinal heads next node to the rest (2->1[->][Rest])
        return newHead;                    // Return the new head ([2]->1->Rest)
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}