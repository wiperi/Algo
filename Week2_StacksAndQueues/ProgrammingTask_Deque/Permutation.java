package Week2_StacksAndQueues.ProgrammingTask_Deque;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {

        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        int n = Integer.parseInt(args[0]);
        for (int i = 0; i < n; i++) {
            StdOut.print(rq.dequeue() + " ");
        }
    }
}
