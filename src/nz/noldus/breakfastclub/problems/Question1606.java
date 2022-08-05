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
import java.util.Comparator;
import java.util.PriorityQueue;

class Question1606 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    static final boolean DEBUG = false;

    //Data structure for tracking server availability
    class Server implements Comparable<Server> {
        final int id;
        int nextAvail = 0;
        int jobs = 0;
        public Server(int id) { this.id = id; }

        public int compareTo(Server b) {
            int availDif = this.nextAvail - b.nextAvail;
            if (availDif != 0) return availDif;
            return this.id - b.id;
        }

        public boolean equals(Object o) {
            if (! (o instanceof Server)) return false;
            return this.id == ((Server)o).id;
        }

    }
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        SortedSet<Server> servers = new TreeSet();

        for (int i=0; i<k; i++) servers.add(new Server(i));

        int max = -1; //keep track of the server that has done the most jobs;

        for (int i=0; i<arrival.length; i++) {
            int t = arrival[i];
            int l = load[i];

            if (DEBUG) System.out.println("Checking Job "+i+" t:"+t+" l:"+l);
            if(DEBUG)printQueue(servers);
            Server bestServer = null;
            int bestN = k;
            //iterate through queue looking for available servers
            //We need to make this more efficient!
            for(Server s : servers) {
                if (DEBUG) System.out.println("--Testing server "+s.id);
                if (s.nextAvail <= t) {
                    int n = s.id < (i%k) ? s.id+k : s.id;
                    if (DEBUG) System.out.println("----Available, n: "+n);
                    if (bestServer == null || n < bestN) {
                        if (DEBUG) System.out.println("----Best so far");
                        bestN = n;
                        bestServer = s;
                        //if n is 0, we cant do any better
                        if (bestN == 0) break;
                    }
                } else {
                    //No more to check
                    break;
                }
            }
            //Update the servers details
            //Increment job count, set next avail time, update max if needed
            if (bestServer != null) {
                if (DEBUG) System.out.println("--Best server is "+bestServer.id);
                servers.remove(bestServer);
                bestServer.jobs++;
                max = Math.max(max, bestServer.jobs);
                bestServer.nextAvail = t+l;
                servers.add(bestServer);
                if (DEBUG) System.out.println("--Max is now "+max);
            } else {
                if (DEBUG) System.out.println("--No server found.");
            }
            if (DEBUG) System.out.println("\n\n");
        }

        //Iterate through all servers and add those with 'max' jobs to result list
        List<Integer> result = new ArrayList<>();
        for (Server s : servers) if (s.jobs == max) result.add(s.id);
        return result;

    }

    public void printQueue(SortedSet<Server> q) {
        String str = "";
        for (Server s : q) {
            str+="("+s.id+","+s.nextAvail+","+s.jobs+")";
        }
        System.out.println(str);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}