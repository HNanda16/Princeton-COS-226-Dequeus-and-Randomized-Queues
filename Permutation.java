/* *****************************************************************************
 *  Name:    Hasit Nanda
 *  NetID:   hasitnanda
 *  Precept: P00
 *
 *  Description:  This is a client which takes an input of strings and in
 * integer k and outputs k of the strings uniformly at random.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
