/* *****************************************************************************
 *  Name:    Hasit Nanda
 *  NetID:   hasitnanda
 *  Precept: P00
 *
 *  Description: Program to implement Randomized Queue in which dequueued items
 * are chosen uniformly at random among items in the data structure.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // initial capacity
    private static final int INIT_CAPACITY = 8;
    // array of items
    private Item[] queue;
    // number of items
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot Add Null Item");
        }
        if (n == queue.length) {
            resize(2 * queue.length);    // double size of array if necessary
        }
        queue[n] = item;
        n += 1;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        int position = StdRandom.uniform(n);

        // swaps item at n-1 with item at random position
        // so that null space is created at end of array
        Item item = queue[position];
        queue[position] = queue[n - 1];
        queue[n - 1] = null;                              // to avoid loitering
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == queue.length / 4) resize(queue.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        int position = StdRandom.uniform(n);

        return queue[position];
    }

    // resize array if nnumber of elements changes
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // iterator
    private class ReverseArrayIterator implements Iterator<Item> {
        // current item pointer
        private int i;
        // first n items in queue
        private Item[] copy;

        // constructor
        public ReverseArrayIterator() {
            i = 0;
            copy = (Item[]) new Object[n];
            for (int x = 0; x < n; x++) {
                copy[x] = queue[x];
            }
            StdRandom.shuffle(copy);
        }

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy[i++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
        for (int a : queue) {
            for (int b : queue) {
                StdOut.print(a + "-" + b + " ");
            }
            StdOut.println();
        }

        StdOut.println("");

        RandomizedQueue<Integer> queue2 = new RandomizedQueue<Integer>();
        for (int i = 1; i <= 10; i++) {
            queue2.enqueue(i);
            StdOut.println("(" + queue2.size() + " items in queue)");
        }
        StdOut.println("");

        Iterator<Integer> iterator2 = queue2.iterator();
        // Should print numbers 1-10 in random order
        while (iterator2.hasNext()) {
            int i = iterator2.next();
            StdOut.println(i);

        }
        StdOut.println("");

        for (int i = 0; i < 5; i++) {
            StdOut.println(queue2.sample());
        }

        // Should dequeue numbers 1-10 in random order
        StdOut.println("");
        while (!queue2.isEmpty()) {
            StdOut.println(queue2.dequeue());
            StdOut.println("(" + queue2.size() + " items in queue)");
        }
    }

}
