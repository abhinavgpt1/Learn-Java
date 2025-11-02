class A extends Thread {
    @Override
    public void run() {
        for(int i=1; i<=5; i++){
            System.out.println(getName() + ":" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class B extends Thread {
    B(String name) {
        super(name);
    }
    // commented purposefully so as to indicate that you need this for creation of Threads with no name as "new B()".
    // B() {
    //     super(); // no need, happens by default
    // }
    @Override
    public void run() {
        for(int i=6; i<=10; i++){
            System.out.println(getName() + ":" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class CW49_Thread {
    public static void main(String[] args) {
        A t1 = new A();
        B t2 = new B("Thread-B");
        t1.setName("Thread-A");
        // t2.setName("Thread-B"); // no need since we have a parameterized constructor which calls Thread(String name).
        // Tip: you can't create new B() since you don't have a default constructor.
        
        // setDaemon()
        // -----------
        // t1.setDaemon(true); // setting this may not output full output of thread t1
        // t2.setDaemon(true); // setting this may not output full output of thread t2
        
        // qq - should setDaemon be called before thread starts?
        // ans - Yes, the setDaemon() method must be called before the start() method for a thread. 
        // If setDaemon() is called after a thread has been started, an IllegalThreadStateException will be thrown. This applies whether you are attempting to change a non-daemon thread to a daemon thread, or vice versa.
        
        t1.start(); // don't execute run() directly.
        t2.start();
        System.out.println(Thread.currentThread().getName() + " - Message from main thread");

        // you may use join() while t1 and t2 are daemon, but avoid it in general otherwise it defeats the purpose of daemon threads.
        try {
            t1.join();
            t2.join(); // waits indefinitely for t2 to finish
            // t2.join(10000); // waits for max 10 seconds for t2 to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("t1 state after join(): " + t1.getState()); // TERMINATED
        System.out.println("t2 state after join(): " + t2.getState()); // TERMINATED
        System.out.println("Main thread finished after waiting for t1 and t2 to be finished");

        // Tip: Thread can be initialized by 1. Thread() (or some class which extends Threads), 2. Thread(Runnable r).
    }
}

/**
 * FAQ: Calling a thread's run() method directly, instead of using the start() method, defeats the purpose of multithreading.
 * Here's why:
 * No New Thread Creation:
 *  When run() is called directly, it executes as a regular method call within the current thread of execution (e.g., the main thread). No new thread is created by the operating system or the Java Virtual Machine (JVM).
 * Synchronous Execution:
 *  The code within the run() method will execute synchronously, meaning it will block the current thread until its completion. This eliminates the concurrency that is the primary benefit of using threads.
 * Loss of Multithreading Benefits:
 *  The goal of multithreading is to allow multiple tasks to run concurrently, improving responsiveness and potentially performance. Calling run() directly prevents this parallelism, as the tasks will still execute sequentially within a single thread.
 * 
 * In contrast, the start() method:
 * Spawns a New Thread:
 *  The start() method is responsible for creating a new thread of execution in the operating system.
 * Schedules for Execution:
 *  It then schedules this new thread with the JVM, which will eventually call the run() method within the context of this newly created thread when resources and CPU time are available.
 * Enables Concurrency:
 *  This allows the run() method's code to execute concurrently with the original thread, achieving true multithreading.
 */

 /**
  * Output:
  * main - Message from main thread
  * Thread-A:1
  * Thread-B:6
  * Thread-A:2
  * Thread-B:7
  * Thread-B:8
  * Thread-A:3
  * Thread-B:9
  * Thread-A:4
  * Thread-B:10
  * Thread-A:5
  * t1 state after join(): TERMINATED
  * t2 state after join(): TERMINATED
  * Main thread finished after waiting for t1 and t2 to be finished
  */

/**
 * Output (t1 and t2 set as daemon threads, and no join()):
 * --------------------------------------------------------
 * main - Message from main thread
 * t1 state <<ignore>after join()>: RUNNABLE
 * t2 state <<ignore>after join()>: RUNNABLE
 * Main thread finished after waiting for t1 and t2 to be finished
 */