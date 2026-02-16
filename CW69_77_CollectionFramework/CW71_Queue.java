import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class CW71_Queue {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        // Following functions throw exception if the operation fails
        queue.add(null); // throws except if insertion fails i.e. if the queue is full.
        // queue.remove(); // throws exception if queue is empty.
        // queue.element(); // throws exception if queue is empty.

        // non-exception throwing functions
        queue.offer(2); // calls add() internally, returns true if successful, false if queue is full.
        // queue.poll(); // (Beware) returns null if queue is empty
        // queue.peek(); // (Beware) returns null if queue is empty
        System.out.println("Queue with elements null and 2: " + queue);
        System.out.println("Does the queue contain 1? " + queue.contains(1)); // false
        System.out.println("Is the queue empty? " + queue.isEmpty()); // false
        System.out.println("Queue size: " + queue.size()); // 2

        Integer[] arr = queue.toArray(new Integer[0]);
        System.out.println("Queue as array: " + Arrays.toString(arr)); // [null, 2]
        queue.clear();
        System.out.println("Cleared queue: " + queue);
        System.out.println("--------------");

        // PTR: add-remove-element vs offer-poll-peek

        // ref: https://www.geeksforgeeks.org/java/queue-interface-java/

        // Proof that add() throws exception:
        giveErrorOnAdd(); // add() generally gives error if ArrayBlockingQueue is used
        
        // PTR: The Queues available in java.util package are unbounded queues.
        // PTR: The Queues available in java.util.concurrent package are bounded queues generally.
    }
    public static void giveErrorOnAdd() {
        // ArrayBlockingQueue with capacity of 2 - size fixed
        Queue<Integer> q = new ArrayBlockingQueue<>(2);
        q.add(1);
        q.add(2);
        System.out.println("ArrayBlockingQueue i.e. bounded/fixed size queue: " + q); // Output: [1, 2]
        System.out.println("Trying adding element beyond capacity...");
        try {
            q.add(3); // This will throw IllegalStateException
            System.out.println(q);
        } catch (IllegalStateException e) {
            System.out.println("IllegalStateException caught: " + e.getMessage());
        }
        
        System.out.println("Final queue state: " + q);
    }
}

/**
 * Output:
 * -------
 * Queue with elements null and 2: [null, 2]
 * Does the queue contain 1? false
 * Is the queue empty? false
 * Queue size: 2
 * Queue as array: [null, 2]
 * Cleared queue: []
 * --------------
 * ArrayBlockingQueue i.e. bounded/fixed size queue: [1, 2]
 * Trying adding element beyond capacity...
 * IllegalStateException caught: Queue full
 * Final queue state: [1, 2]
 */