// ref: https://www.baeldung.com/java-synchronized
// ref: https://www.youtube.com/watch?v=4aYvLz4E1Ts
class Counter {
    private int count = 0;
    public synchronized void increment(){ //synchronized method: adding synchronized will let only 1 thread access this function 
        count++;
    }
    public void increment2(){
        // ...some task is happening
        synchronized (this) { //synchronized block: if you don't want to make a synchronized method 
            // here this refers to Counter object => at a time only one thread can use this object.
            // If there was another Counter obj like Counter counter2 = new Counter(); then lock on counter1 won't affect counter2
            count++;
        }
    }
    public int getCount(){ 
        return count;
    }
}
public class CW51_Synchronization_LockInstance {
    /**
     * The entire theory on synchronization you need is as follows:
     * -------------------------------------------------------------
     * Def: Synchronization: is the mechanism to control the access of multiple threads to SHARED RESOURCES (CRITICAL SECTION), to prevent data inconsistency and ensure THREAD SAFETY in a multi-threaded environment.
     * 
     * Def: Thread safety: means when multiple threads access the same object or piece of code at the same time, the program still behaves correctly, without data corruption or unexpected results.
     * 
     * We can use the synchronized keyword on different levels:
     *  Instance methods
     *  Static methods
     *  Code blocks
     * 
     * When we use a synchronized block, Java internally uses a monitor, also known as a monitor lock or intrinsic lock, to provide synchronization. 
     * These monitors are bound to an object; therefore, all synchronized blocks of the same object can have only one thread executing them at the same time.
     * 
     * A lock can be on an instance of a class or on the class itself.
     * Lock on instance comes in picture for synchronized instance methods and synchronized blocks synchronized on "this" / instance. eg. synchronized(this) {}
     * Lock on class comes in picture for synchronized static methods and synchronized blocks synchronized on the class object. eg. synchronized(ClassName.class) {}
     * 
     * More on synchronized block:
     * ---------------------------
     * synchronized(X.class) {}
     *  Locks on the class object (the metadata object for class X).
     *  There is only one class object per JVM for each class.
     *  If you use synchronized(X.class), all threads across all instances of X must wait for the lock on the class object.
     *  When to use: This is useful for static methods or when you want to synchronize access to resources shared by "all instances".
     * 
     * synchronized(this) {}
     *  Locks on the current "instance" of class (this).
     *  Each instance of a class has its own lock.
     *  Only one thread per "instance" can enter a synchronized(this) block at a time.
     *  IMP: Other threads can enter the block for other instances simultaneously.
     *  When to use: Use this when you want to protect instance-specific data.
     * 
     * Extra:
     * ------
     * Process synchronization != thread synchronization
     *
     * Mutual exclusion is the principle that only one process can access a shared resource at any given time to prevent conflicts, such as a race condition. 
     * It is commonly implemented using a mutex (mutual exclusion lock), which a thread must acquire before entering a critical section of code and release when it leaves. 
     * This ensures that a thread operating on a shared resource temporarily excludes all other threads from accessing it until it is finished.
     * The term was first coined by Dijkstra.
     */
    public static void main(String[] args) {
        Counter counter = new Counter(); // shared resource
        Runnable runnable = new Runnable() {
            Counter c = counter;

            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++)
                    c.increment();
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        // Thread t1 = new Thread(new Runnable() {
        //     Counter c = counter;
        //     @Override
        //     public void run() {
        //         for (int i = 0; i < 1000000; i++)
        //             c.increment();
        //     }
        // });
        // Thread t2 = new Thread(new Runnable() {
        //     Counter c = counter;
        //     @Override
        //     public void run() {
        //         for (int i = 0; i < 1000000; i++)
        //             c.increment();
        //     }
        // });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        // without synchronized
        // ---------------------
        // this statement is run by main(), hence it's important to join() t1,t2
        System.out.println(counter.getCount()); // 1258159 

        // qq - does passing same runnable instance to multiple threads make a difference?
        // ans - passing same runnable is absolutely fine. Remember to use synchronization since the common runnable defintion contains shared data/fields. - https://stackoverflow.com/questions/9849109/passing-single-runnable-object-to-multiple-thread-constructors
        // qq - why ans != 2e6? 
        // ans - if count=x and t1 makes it x+1, then t2 might still see it as x since updation by t1 wasn't completed. t2 updates x to x+1, hence the work done by t1 is lost.

        // with synchronized method/block
        // ------------------------------
        System.out.println(counter.getCount()); // 2000000

        // Def: Critical section: Code where shared resources are accessed and modified is called critical section.
        // Def: Race condition is a situation that may occur in critical section where two or more processes try to access the same resource at the same time without proper coordination leading to improper results.
        //      This “race” can lead to incorrect results or unpredictable behavior because the order of execution is not controlled.

        // Def: Mutual exclusion = mutex: A mutex is like a lock that ensures only one process can access a resource at a time. 
        //      If a process holds a lock, others must wait their turn preventing race conditions.
        // 
        // Mutex != Semaphone
        // -> Mutex and semaphore are both synchronization primitives used in concurrent programming to manage access to shared resources and prevent race conditions.
        // -> Semaphore: It is a signaling mechanism that controls access to a resource by multiple threads through a non-negative integer counter, allowing a specified number of threads to access the resource concurrently.
    }
}

/**
 * Extra: Mutex and Semaphore in java
 * Good read: https://www.geeksforgeeks.org/operating-systems/mutex-vs-semaphore/
 * Mutex
 * -----
 * In Java, there is no built-in class explicitly named Mutex. The functionality of a mutex can be achieved using two main constructs:
 *  synchronized keyword: This language-level construct provides mutual exclusion for methods or code blocks.
 *      Usage:  The JVM automatically handles the acquisition and release of the lock, even if an exception occurs. 
 *              It is simple and straightforward but lacks advanced features.
 *  ReentrantLock class: This class from the java.util.concurrent.locks package offers a more flexible and robust locking mechanism.
 *      ReentrantLock offers more advanced features than the synchronized keyword.
 *      Features:
 *      Timed lock acquisition: tryLock(long timeout, TimeUnit unit)
 *      Interruptible lock acquisition: lockInterruptibly()
 *      Fairness policy: Can be set to grant access to the longest-waiting thread 
 * 
 * Semaphore in Java
 * -----------------
 * Java provides the java.util.concurrent.Semaphore class, which manages a counter of permits. 
 * Core concept: A semaphore controls access to resources by maintaining a count of available "permits". 
 * Threads request a permit to access the resource and release it when they are finished.
 * Types of Semaphores:
 *  Counting Semaphore: Initialized with a count greater than 1, it manages a pool of multiple resources.
 *  Binary Semaphore: Initialized with a single permit, it can be used to provide mutual exclusion, similar to a mutex. However, a binary semaphore does not track ownership, so a different thread can release the permit.
 * Syntax:
 * -------
 * import java.util.concurrent.Semaphore;
 * 
 * public class SemaphoreExample {
 *     // Allow up to 3 threads to access the resource at a time
 *     private final Semaphore semaphore = new Semaphore(3);
 * 
 *     public void useResource() throws InterruptedException {
 *         semaphore.acquire(); // Acquire a permit
 *         try {
 *             // Critical section (limited to 3 threads)
 *         } finally {
 *             semaphore.release(); // Release the permit
 *         }
 *     }
 * }
 */