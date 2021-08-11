/* *****************************************************************************
 *  Name:    Hasit Nanda
 *  NetID:   hasitnanda
 *  Precept: P00
 *
 *  Description:  This Program implements a deque which is a
 * generalization of a stack and a queue that supports adding and removing
 * items from either the front or the back of the data structure.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // size of deque
    private int n;
    // first node pointer
    private Node first;
    // last point pointer
    private Node last;

    private class Node {
        // Node item
        private Item item;
        // next Node pointer
        private Node next;
        // previous Node pointer
        private Node previous;
    }

    // construct an empty deque
    public Deque() {
        n = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot Add Null Item");
        }
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) {
            last = newNode;
        }
        else {
            first.previous = newNode;
        }
        newNode.next = first;
        first = newNode;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot Add Null Item");
        }
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) {
            first = newNode;
        }
        else {
            last.next = newNode;
        }
        newNode.previous = last;
        last = newNode;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        n--;
        return item;
    }


    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = last.item;        // save item to return
        last = last.previous;         // delete last node
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    // iterator
    private class LinkedIterator implements Iterator<Item> {
        // current node
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Deque is empty");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        StdOut.println("Queue 1:");
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 1; i <= 10; i++) {
            deque.addFirst(i);
            StdOut.println("(" + deque.size() + " items in queue)");
        }
        StdOut.println("");

        Iterator<Integer> iterator1 = deque.iterator();
        // Should print numbers 1-10 in descending order
        while (iterator1.hasNext()) {
            int i = iterator1.next();
            StdOut.println(i);
        }

        StdOut.println("");
        // Should print numbers 1-10 in ascending order
        while (!deque.isEmpty()) {
            StdOut.println(deque.removeLast());
            StdOut.println("(" + deque.size() + " items in queue)");
        }

        StdOut.println("");
        // Should print true
        StdOut.println(deque.isEmpty());

        StdOut.println("");

        for (int i = 1; i <= 10; i++) {
            deque.addLast(i);
            StdOut.println("(" + deque.size() + " items in queue)");
        }
        StdOut.println("");

        Iterator<Integer> iterator2 = deque.iterator();
        // Should print numbers 1-10 in ascending order
        while (iterator2.hasNext()) {
            int i = iterator2.next();
            StdOut.println(i);

        }
        StdOut.println("");

        // Should print numbers 1-10 in ascending order
        while (!deque.isEmpty()) {
            StdOut.println(deque.removeFirst());
            StdOut.println("(" + deque.size() + " items in queue)");
        }
    }

}
