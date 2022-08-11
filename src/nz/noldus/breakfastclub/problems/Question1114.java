//Suppose we have a class: 
//
// 
//public class Foo {
//  public void first() { print("first"); }
//  public void second() { print("second"); }
//  public void third() { print("third"); }
//}
// 
//
// The same instance of Foo will be passed to three different threads. Thread A 
//will call first(), thread B will call second(), and thread C will call third(). 
//Design a mechanism and modify the program to ensure that second() is executed 
//after first(), and third() is executed after second(). 
//
// Note: 
//
// We do not know how the threads will be scheduled in the operating system, 
//even though the numbers in the input seem to imply the ordering. The input format 
//you see is mainly to ensure our tests' comprehensiveness. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3]
//Output: "firstsecondthird"
//Explanation: There are three threads being fired asynchronously. The input [1,
//2,3] means thread A calls first(), thread B calls second(), and thread C calls 
//third(). "firstsecondthird" is the correct output.
// 
//
// Example 2: 
//
// 
//Input: nums = [1,3,2]
//Output: "firstsecondthird"
//Explanation: The input [1,3,2] means thread A calls first(), thread B calls 
//third(), and thread C calls second(). "firstsecondthird" is the correct output.
// 
//
// 
// Constraints: 
//
// 
// nums is a permutation of [1, 2, 3]. 
// 
// Related Topics Concurrency 👍 1053 👎 177


class Question1114 {
    //leetcode submit region begin(Prohibit modification and deletion)
class Foo {

    static final Semaphore sem = new Semaphore(1);
    volatile int curNum = 1;

    public Foo() { }

    public void first(Runnable printFirst) throws InterruptedException {
        printNum(1, printFirst);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        printNum(2, printSecond);
    }

    public void third(Runnable printThird) throws InterruptedException {
        printNum(3, printThird);
    }

    public void printNum ( int num, Runnable printer) throws InterruptedException {
        while (curNum < num + 1) {
            if (curNum == num) {
                sem.acquire();
                printer.run();
                sem.release();
                curNum++;
            } else {
                Thread.yield();
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}