import java.util.ArrayDeque;

public class CW74_ArrayDeque {
    public static void main(String[] args) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(2);
        dq.addFirst(1);
        dq.addLast(3);
        System.out.println("ArrayDeque: " + dq);
        System.out.println("getFirst (throws exception if q empty):" + dq.getFirst());
        System.out.println("getLast (throws exception if q empty):" + dq.getLast());
        System.out.println("peekFirst:" + dq.peekFirst());
        System.out.println("peekLast:" + dq.peekLast());

        // add <-> offer
        // addFirst <-> offerFirst
        // addLast <-> offerLast
        
        // remove <-> poll
        // removeFirst <-> pollFirst
        // removeLast <-> pollLast
        // PTR: dq.pop(); == dq.removeFirst();
        // PTR: dq.push(); inserts at front not at last like standard queue
        // => ArrayDeque push/pop operates at Front.
        
        System.out.println("Queue size:" + dq.size());
        dq.clear();
        System.out.println("Is queue empty after clear():" + dq.isEmpty());
        System.out.println("----------");
        dq.push(1);
        dq.push(2);
        dq.push(3);
        System.out.println("Pushing 1,2,3 in new ArrayDeque. push(x) = addFirst(x) => DON'T USE IT: " + dq);
        
        // element <-> peek
        // getFirst <-> peekFirst
        // getLast <-> peekLast

        // BIG PTR: For a queue like behavior use functions add, remove, peek. Don't use push/pop.
        System.out.println("---ALWAYS USE add, remove, peek for a queue like behavior---");

        // ArrayDeque != LinkedList
        // LinkedList is a doubly linked list implementation of the List interface.
        // ArrayDeque is a resizable array implementation of the Deque interface.

        /*
         * Summary: How ArrayDeque Works Internally
         * ----------------------------------------
         *
         * - ArrayDeque is a resizable, array-based implementation of the Deque interface.
         * - Internally, it uses a plain Object[] array called 'elements' to store contents.
         * - The array acts as a circular buffer, managed by two indices:
         *     • head: points to the first element (front of the deque)
         *     • tail: points to the next available slot at the end (back of the deque)
         *
         * Adding elements:
         *   • addFirst: inserts at head (decrements head, wraps if needed)
         *   • addLast:  inserts at tail (places at tail, then increments tail, wraps if needed)
         *
         * Removing elements:
         *   • pollFirst: removes from head (nulls slot, increments head)
         *   • pollLast:  removes from tail (decrements tail, nulls slot)
         *
         * - The array always has at least one empty slot to distinguish full vs empty.
         * - If full, the array is resized (grown) and elements are copied over in order.
         * - Elements are logically contiguous but may wrap around the array's end.
         * - Null elements are not allowed.
         * - Most operations (add, remove, peek) are O(1) amortized time.
         *
         * In summary: ArrayDeque uses a circular array to efficiently manage both ends, resizing as needed, and tracks the logical start/end with head and tail indices.
         */
    }
}

/**
 * Output:
 * -------
 * ArrayDeque: [1, 2, 3]
 * getFirst (throws exception if q empty):1
 * getLast (throws exception if q empty):3
 * peekFirst:1
 * peekLast:3
 * Queue size:3
 * Is queue empty after clear():true
 * ----------
 * Pushing 1,2,3 in new ArrayDeque. push(x) = addFirst(x) => DON'T USE IT: [3, 2, 1]
 * ---ALWAYS USE add, remove, peek for a queue like behavior---
 */