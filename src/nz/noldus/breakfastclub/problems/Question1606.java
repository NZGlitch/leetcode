//You have k servers numbered from 0 to k-1 that are being used to handle 
//multiple requests simultaneously. Each server has infinite computational capacity but 
//cannot handle more than one request at a time. The requests are assigned to 
//servers according to a specific algorithm: 
//
// 
// The iᵗʰ (0-indexed) request arrives. 
// If all servers are busy, the request is dropped (not handled at all). 
// If the (i % k)ᵗʰ server is available, assign the request to that server. 
// Otherwise, assign the request to the next available server (wrapping around 
//the list of servers and starting from 0 if necessary). For example, if the iᵗʰ 
//server is busy, try to assign the request to the (i+1)ᵗʰ server, then the (i+2)ᵗʰ 
//server, and so on. 
// 
//
// You are given a strictly increasing array arrival of positive integers, 
//where arrival[i] represents the arrival time of the iᵗʰ request, and another array 
//load, where load[i] represents the load of the iᵗʰ request (the time it takes to 
//complete). Your goal is to find the busiest server(s). A server is considered 
//busiest if it handled the most number of requests successfully among all the 
//servers. 
//
// Return a list containing the IDs (0-indexed) of the busiest server(s). You 
//may return the IDs in any order. 
//
// 
// Example 1: 
//
// 
//Input: k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3] 
//Output: [1] 
//Explanation: 
//All of the servers start out available.
//The first 3 requests are handled by the first 3 servers in order.
//Request 3 comes in. Server 0 is busy, so it's assigned to the next available 
//server, which is 1.
//Request 4 comes in. It cannot be handled since all servers are busy, so it is 
//dropped.
//Servers 0 and 2 handled one request each, while server 1 handled two requests.
// Hence server 1 is the busiest server.
// 
//
// Example 2: 
//
// 
//Input: k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
//Output: [0]
//Explanation: 
//The first 3 requests are handled by first 3 servers.
//Request 3 comes in. It is handled by server 0 since the server is available.
//Server 0 handled two requests, while servers 1 and 2 handled one request each.
// Hence server 0 is the busiest server.
// 
//
// Example 3: 
//
// 
//Input: k = 3, arrival = [1,2,3], load = [10,12,11]
//Output: [0,1,2]
//Explanation: Each server handles a single request, so they are all considered 
//the busiest.
// 
//
// 
// Constraints: 
//
// 
// 1 <= k <= 10⁵ 
// 1 <= arrival.length, load.length <= 10⁵ 
// arrival.length == load.length 
// 1 <= arrival[i], load[i] <= 10⁹ 
// arrival is strictly increasing. 
// 
// Related Topics Array Greedy Heap (Priority Queue) Ordered Set 👍 374 👎 18


import java.util.*;

class Question1606 {
    //leetcode submit region begin(Prohibit modification and deletion)
import java.util.*;
class Solution {

    static final boolean DEBUG = false;

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        //Server object
        class Server {
            int id;
            int at;
            int jobs;
        }

        // Sorted Linked list data strucutre, has special functions to align head pointer
        // with current i from main algorithm
        class ServerList extends LinkedList<Server> {

            //We will insert it to retain correct order
            public orderedInsert(Server s, int i) {
                //simple case - list is empty
                if (this.isEmpty()) { this.add(s); }

                /**
                 * cases
                 * x                head =
                 *
                 * xxxx|xxxx        head > tail
                 *  xxixx|xxxx          i > head
                 *  ixxxx|xxxx          i = head
                 *  xxxx|xxixx          i < head
                 *
                 * xxxxxxxx |      head < tail || head = tail
                 *  |ixxxxxx       while next !=null && i < next then >>1
                 */


//                if (this.peek().id > this.peekLast().id) { // first case
//                    int index = 0;
//                    if (i > this.peek().id) while (i<this.peek().id)
//                }
            }
        }
        //We create a Linked List of servers ordered by id;
        //we create a priority queue for 'busy' servers orderd by next available time

        //for each i 0....k
            //while (busy.peek().at < arrival[i])
            //      need to reinsert this in the correct place in the list (non-trivial)
            //
            //         list.orderedInsert(busy.poll())
            //
            //  after that it is possible list.head < i and there exists a server > i
            //  if (list.max > i && list.head < i) while (list.head < i) { list.rotate }
            //
            //if (list.isEmpty()) continue //no servers to give the job to
            //server = list.remove()                //the current head is the best server
            //server.at = arrival[i] + load[i]      //set it's avaialable time
            //server.jobs++;                        //inc its job count
            // m = max(m, server.jobs)              //check if max has changed
            // busy.push(server)                    //put server into busy queue

    }

}
//leetcode submit region end(Prohibit modification and deletion)

}