import java.util.Stack;

public class CW70_Stack {
   public static void main(String[] args) {
       Stack<String> stack = new Stack<>();
       stack.push("apple");
       stack.push("banana");
       stack.push("cherry");

       System.out.println("Position of 'banana' element from the top of stack: " + stack.search("banana")); // 2 (1-based index)
       System.out.println("Position of 'apple' element from the top of stack: " + stack.search("apple")); // 2 (1-based index)
       System.out.println("Top element: " + stack.peek());
       System.out.println("Stack size: " + stack.size());

       // Pop elements from the stack
       while (!stack.isEmpty()) {
           System.out.println("Popped element: " + stack.pop());
       }

    // Reference: https://www.geeksforgeeks.org/java/stack-class-in-java/
    // It is recommended to use the Deque interface instead of the Stack class.
    // Stack is synchronized (thread-safe), but Deque is not, making Deque more efficient for single-threaded applications.
    // Explanation:
    // - The Java Stack class is thread-safe (synchronized), so multiple threads can use it safely, but this adds overhead of locking/unlocking on every operation - push, pop, peek, etc.
    // - Deque (such as ArrayDeque) is not synchronized, so it is faster for single-threaded use.
    // - For modern Java code, Deque is recommended over Stack for better performance and flexibility, unless you specifically need thread safety.
    //
    // Stack is a legacy class, and Deque provides more flexibility and better performance.
    // Deque can use streams to convert to a list while keeping LIFO order.
    // Example:
    //   stack.stream().collect(Collectors.toList()); // LIFO is not maintained, even though Stack's principle is LIFO.
    //   deque.stream().collect(Collectors.toList()); // LIFO is maintained.
   }
}
