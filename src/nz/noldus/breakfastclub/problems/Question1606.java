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


import java.util.ArrayList;

class Question1606 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    static final boolean DEBUG = false;
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // we need to track 2 things:
        // 1) the timestamp each server will be available
        // 2) the number of requests each server has handled

        // My initial inclination is to use objects to represent servers,
        // and to connect them in a circular linked list

        class ServerNode {
            int id;             // This servers 'number'
            int avail = 1;      // The next timestamp this server can accept work
            ServerNode next;    // The next server in the list
            int numJobs = 0;    // The number of jobs this server has done
            int numServers = 0; // the number of servers in this list

            // Constructor recursivley creates the linked list
            // If root is null, then this is the initial call, and root should
            // be 'this'.
            // when id == numServer -1, this is the last node, set it's next to
            // root.
            public ServerNode(int id, ServerNode root, int numServers) {
                this.id = id;
                this.numServers = numServers;
                if (root == null) root = this;
                if (id == numServers -1) next = root;
                else next = new ServerNode(id+1, root, numServers);
            }

            public void process(int index, int time, int load, boolean started) {
                if (DEBUG) System.out.println("\tID:"+this.id+" Process("+index+","+time+","+load+","+started+")");
                if (!started && id != index % numServers) {
                    //We havent got to the initial server assignment yet
                    next.process(index, time, load, started);
                    return;
                } else if (!started && id == index % numServers ){
                    // This is the first server to try and assign the job to
                    started = true;
                } else if (started && id == index % numServers) {
                    // We couldnt find a server to handle this job
                    if (DEBUG) System.out.println("No available servers");
                    return;
                }

                // see if this server can start the job
                if (avail <= time) {
                    if (DEBUG) System.out.println("\t\t\tJob assigend to "+this.id);
                    //We can do it!
                    avail = time + load;
                    numJobs++;
                } else {
                    if (DEBUG) System.out.println("\t\t\tServer "+this.id+" unavailable");
                    //pass to the next server
                    next.process(index, time, load, started);
                }
            }

            public List<Integer> busiest() {
                ArrayList<Integer> result = new ArrayList<>();
                // find the max busyness
                int max = next.findMax(numJobs, this);
                if (DEBUG) System.out.println("Max is "+max);
                //add this server to the list if it is max
                if (numJobs == max) result.add(this.id);
                //Add all server id's to the list that have this max
                next.findAllBusy(max, this, result);
                return result;
            }

            private void findAllBusy(int max, ServerNode root, ArrayList<Integer> result) {
                if (this == root) return; //done
                if (this.numJobs == max) result.add(this.id);
                next.findAllBusy(max, root, result);
            }

            private int findMax(int curMax, ServerNode root) {
                if (this == root) return curMax;
                else return next.findMax(Math.max(curMax, numJobs), root);
            }
        }

        ServerNode root = new ServerNode(0,null, k);

        // For each arrival
        for(int i = 0; i< arrival.length; i++) {
            if (DEBUG) System.out.println("Job "+i);
            root.process(i, arrival[i], load[i], false);
        }

        return root.busiest();

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}