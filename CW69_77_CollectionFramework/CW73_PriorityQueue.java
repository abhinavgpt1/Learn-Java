import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class CW73_PriorityQueue {
    public static void main(String[] args) {
        // ref: https://www.geeksforgeeks.org/java/priority-queue-in-java/
        minHeap();
        System.out.println("------------------");

        maxHeap();
        System.out.println("------------------");
        
        customHeapForPOJO();

        // PTR: PriorityQueue & ArrayDeque do not allow null elements. 
        // Only a Queue implemented using LinkedList allows null.

        // PTR: Queue<String> q1 = new PriorityQueue<>() vs Queue<String> q2 = new LinkedList<>();
        // Both are implementations of the Queue interface, but they have different underlying data structures and behaviors.
        // q1 is a priority queue underneath.
    }

    private static void minHeap() {
        System.out.println("Min Heap Example:");
        PriorityQueue<Integer> q = new PriorityQueue<>();
        // q.add(null); // can't add null in PriorityQueue
        q.add(3);
        q.add(1);
        q.add(5);
        q.add(4);
        q.add(2);
        System.out.println("Nothing comes sorted on sout(q):" + q); // Output: [1, 2, 5, 4, 3]
        Integer[] arr = q.toArray(new Integer[0]);
        System.out.println("Nothing comes sorted on Arrays.toString(q.toArray(new Integer[0])):" + Arrays.toString(arr));
        // It's because peek is a min element. And on removing the peek element, heapifyDown happens.
        // The array may not look sorted now, but removing all elements surely produces sorted order.
        
        System.out.println("> Removing elements iteratively ensure sorted order...");

        while (!q.isEmpty()) {
            System.out.println("before removing " + q.peek() + ": " + q);
            System.out.println("Removed: " + q.remove());
        }
    }

    private static void maxHeap() {
        System.out.println("Max Heap Example:");
        Queue<Integer> q = new PriorityQueue<>((a, b) -> b - a); // or else pass Collections.reverseOrder() as comparator
        q.add(3);
        q.add(1);
        q.add(5);
        q.add(2);
        q.add(4);
        System.out.println("> Removing elements iteratively ensure sorted order...");
        while (!q.isEmpty()) {
            System.out.println("before removing " + q.peek() + ": " + q);
            System.out.println("Removed: " + q.remove());
        }
    }

    private static void customHeapForPOJO() {
        class Student {
            String name;
            int height;

            Student(String name, int height) {
                this.name = name;
                this.height = height;
            }

            @Override
            public String toString() {
                return "Student {name='" + name + "', height=" + height + '}';
            }

            String getName() {
                return name;
            }

            int getHeight() {
                return height;
            }
        }
        // queue students based on name (lower first) and then height (higher first)
        // IMP PTR: you don't have to worry about comparator playing role in vector vs
        // priority queue in c++ where a < b in sorting means a comes before b. Whereas
        // in priority queue, a < b means swap such that b comes before a. In java, you
        // just have to write the comparator as if you are sorting and it will work for
        // both sorting and priority queue. Demonstration at the end:
        Queue<Student> q = new PriorityQueue<>((s1, s2) -> {
            if (s1.name.compareTo(s2.name) != 0) {
                return s1.name.compareTo(s2.name);
            } else {
                return s2.height - s1.height;
            }
        });
        q.add(new Student("christi", 154));
        q.add(new Student("andy", 170));
        q.add(new Student("bella", 160));
        q.add(new Student("andy", 180));
        q.add(new Student("christi", 180));
        while(!q.isEmpty()) {
            System.out.println(q.remove());
        }

        // Multiple ways to write above comparator:
        // 1. Comparator<Student> nameLessHeightBig = Comparator.comparing((Student s) -> s.name).thenComparing(Comparator.comparing((Student s) -> s.height).reversed());
        // 2. Comparator<Student> nameLessHeightBig = Comparator.comparing(Student::getName).thenComparing(Comparator.comparingInt(Student::getHeight).reversed());
        // 3. Create a new class that implements Comparator<Student> and override public int compare method

        // Note: Sometimes, java struggles ot infer the type when chaining comparators with reversed(). Hence, we need to use (Student s) instead of just s.
        // Note: :: operator, introduced in Java 8, is called method reference.

        System.out.println("--------Demonstrating that same comparator works for sorting as well unlike CPP--------");
        // demonstration that same comparator works & results same for sorting as well
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("christi", 154));
        list.add(new Student("andy", 170));
        list.add(new Student("bella", 160));
        list.add(new Student("andy", 180));
        list.add(new Student("christi", 180));
        list.sort((s1, s2) -> {
            if (s1.name.compareTo(s2.name) != 0) {
                return s1.name.compareTo(s2.name);
            } else {
                return s2.height - s1.height;
            }
        });
        System.out.println(list);
    }
}
/**
 * Output:
 * -------
 * Min Heap Example:
 * Nothing comes sorted on sout(q):[1, 2, 5, 4, 3]
 * Nothing comes sorted on Arrays.toString(q.toArray(new Integer[0])):[1, 2, 5, 4, 3]
 * > Removing elements iteratively ensure sorted order...
 * before removing 1: [1, 2, 5, 4, 3]
 * Removed: 1
 * before removing 2: [2, 3, 5, 4]
 * Removed: 2
 * before removing 3: [3, 4, 5]
 * Removed: 3
 * before removing 4: [4, 5]
 * Removed: 4
 * before removing 5: [5]
 * Removed: 5
 * ------------------
 * Max Heap Example:
 * > Removing elements iteratively ensure sorted order...
 * before removing 5: [5, 4, 3, 1, 2]
 * Removed: 5
 * before removing 4: [4, 2, 3, 1]
 * Removed: 4
 * before removing 3: [3, 2, 1]
 * Removed: 3
 * before removing 2: [2, 1]
 * Removed: 2
 * before removing 1: [1]
 * Removed: 1
 * ------------------
 * Student {name='andy', height=180}
 * Student {name='andy', height=170}
 * Student {name='bella', height=160}
 * Student {name='christi', height=180}
 * Student {name='christi', height=154}
 * --------Demonstrating that same comparator works for sorting as well unlike CPP--------
 * [Student {name='andy', height=180}, Student {name='andy', height=170}, Student {name='bella', height=160}, Student {name='christi', height=180}, Student {name='christi', height=154}]
 */