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

    public void printQueues(LinkedList<Server> a, PriorityQueue<Server> b) {
        String aString = "--[";
        for(Server s : a) aString = aString+s.id+",";
        aString = aString+"]";

        String bString = "--[";
        for(Server s : b) bString = bString+s.id+",";
        bString = bString+"]";
        System.out.println(aString+"\n"+bString);
    }

    //Server object
    class Server implements Comparable<Server> {
        final int id;
        int at = -1;
        int jobs = 0;

        public Server(int id) { this.id = id; }

        public int compareTo(Server o) {
            return this.at - o.at;
        }
    }

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {


        // Sorted Linked list data strucutre, has special insert to align head pointer
        // with current i from main algorithm
        class ServerList extends LinkedList<Server> {

            //We will insert it to retain correct order - i is then next request id, the front of the queue
            //should satisfy the condition of being the lowest server id equal to or greater than i%k
            public void orderedInsert(Server s, int i) {
                if (DEBUG) System.out.println("----orderedInsert("+i+")");
                //simple case - list is empty
                if (this.isEmpty()) { this.add(s); return; }
                //More coimplicated case
                int pos = s.id - (i%k);
                if (pos < 0) pos+=k;
                if (DEBUG) System.out.println("----pos: "+pos);
                boolean inserted = false;
                for (int j = 0; j<this.size(); j++) {
                    int compPos = this.get(j).id-(i%k);
                    if (compPos < 0) compPos+=k;
                    if (DEBUG) System.out.println("----check idx "+j+" with pos "+compPos);
                    if (compPos > pos) {
                        if (DEBUG) System.out.println("----idx Found: "+j);
                        this.add(j, s);
                        return;
                    }
                }

                System.out.println("----No ID Found adding tot he end");

                // No target to insert before - add to end.
                this.offerLast(s);
            }
        }
        //We create a Linked List of servers ordered by id;
        final ServerList available = new ServerList();
        for (int i=0; i<k; i++) {
            available.offerLast(new Server(i));
        }
        //we create a priority queue for 'busy' servers orderd by next available time
        final PriorityQueue<Server> busy = new PriorityQueue<>();

        // The current number of jobs the busienst server has processed
        int maxJobs = 0;

        for (int i=0; i<arrival.length; i++) {
            if (DEBUG) System.out.println("Start Job "+i+" arrival "+arrival[i]);
            if (DEBUG) printQueues(available, busy);

            // need to reinsert this in the correct place in the list
            if (DEBUG) System.out.println("\n\n--Move busy to available");
            while (!busy.isEmpty() && busy.peek().at <= arrival[i]) available.orderedInsert(busy.poll(), i);
            if (DEBUG) printQueues(available, busy);

            // In theory the current front of the q is the best server
            // If the list is not empty we assign the job
            if (available.size() > 0) {

                Server next = available.poll();                // Take the server from the available list
                next.at = arrival[i] + load[i];             // Update it's available time
                next.jobs++;                              // Increment it's job count
                maxJobs = Math.max(maxJobs, next.jobs);   // Update maxJobs if needed
                busy.offer(next);
                if (DEBUG) System.out.println("--Assigning job to "+next.id+" at: "+next.at);
            } else {
                if (DEBUG) System.out.println("--No servers available, skipping");
            }
            if(DEBUG) System.out.println("--Job processed max="+maxJobs);
            if(DEBUG) printQueues(available, busy);
        }
        List<Integer> busiest = new ArrayList<>();
        final int max = maxJobs;
        //iterate through both queues and add any servers to the list that have jobs == maxJobs
        for(Server s : busy) if(s.jobs == max) busiest.add(s.id);
        for(Server s : available) if(s.jobs == max) busiest.add(s.id);
        return busiest;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}