Rulebook to learn threads
-------------------------
Refer video: https://youtu.be/4aYvLz4E1Ts?si=9WckTrYvYxVZRU8- (Or https://www.youtube.com/watch?v=4aYvLz4E1Ts&t=953s)
Corresponding notes: https://engineeringdigest.medium.com/multithreading-in-java-39f34724bbf6

CPU is brain of computer; inputs instructions.
What's so special about CPU? - core. Core is the processing unit within CPU. Modern CPUs can have multiple cores to process multiple tasks simultaneously.
4 cores means 4 processing units, each capable of executing tasks independently. One is running web browser, another is running a game, another music, and so on. Program is a set of instructions. eg. On a high level, ms word.

Process is an instance of a program in execution. When a program runs, the OS creates a process for it. Each process has its own memory space and resources.
PTR: when we open a program (eg. MS word), is becomes a process in the OS.
qq: Resources? => A process requires both hardware and software resources to run, including the CPU, memory, and I/O devices, as well as operating systems, libraries, sockets, ports and application software (program code).

A thread is the (smallest) unit of execution WITHIN a process. A process can have multiple threads, each capable of executing independently.
Threads share the same memory space and resources of the process, which allows for efficient communication and data sharing between them.
eg. each tab in chrome runs as a separate thread.

Multitasking allows OS to run multiple procesess simultaneously. On a single-core CPU, multitasking is achieved through time slicing, where the CPU rapidly switches between processes to give the illusion of simultaneous execution.
On a multi-core CPU, true parallelism can be achieved, allowing multiple processes to run simultaneously on different cores.
OS scheduler balances the load across cores, ensuring efficient and responsive system performance.

Def: Multithreading is the ability to execute multiple threads within a process concurrently.
eg. web browser can use multithreading by rendering page, running js, managing inputs, etc.
Multithreading enhances efficiency by breaking down individual tasks into smaller sub-tasks which can be executed concurrently, making better use of CPU capabilities.
=> multithreading is not necessary for multitasking, but vice-versa is true.

PTR: multithreading is possible within a single process, while multitasking involves multiple processes.

PTR: parallel running of processes/programs:multitasking :: efficient program running using threads:multithreading
eg. multitasking (managed by OS)
|->process 1 = ms word ---multithreading---> t1=user input, t2=spell check
|->process 2 = chrome ---multithreading---> t1=page rendering, t2=data loading BTS

Def: time slicing divides CPU time into small intervals called time slices or time quanta. Time slice in itself is a small unit of computation time.
OS allocates these time slices to different processes or threads, ensuring that each gets a fair share of CPU time.

Def: context switching is the process of saving the state of a currently running thread and loading/restoring the state of another thread.
When a process or thread's time slice expires, the OS performs a context switch to allow another thread to run.

JVM and OS handle multithreading. In multicore env., JVM can distribute threads across multiple cores for true parallel execution.
When a Java program is executed, one thread begins running immediately, which is the main thread. This thread is responsible for executing the main method of the program.

----------------- basic concepts done, check codes now -----------------

Basic program using Thread:
---------------------------
let your class extend Thread class.
run method is overriden, and start() is called.

Basic program using Runnable:
-----------------------------
let your class implement Runnable interface.
run method is implemented, and Thread object is instantiated with your class's object as an argument.
start() is called to begin execution.
Thread.currentThread() returns the currently executing thread object. 
Thread.currentThread().getName() returns the name of the currently executing thread. eg. main, Thread-0, etc.
PTR: getName() function is available in Thread class and not in Runnable. So, use Thread.currentThread() in Runnable's context.

Check Thread lifecycle theory and program from video: 
State enum: NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
PTR: there is no Running state. Runnable means running or ready to run.
t1.getState() returns State enum.
t1.join() inside main() will pause the main thread and the next line of code in main() will be executed only after t1 completes its execution.
-> Check CW47_ThreadLifecycle.java

qq - Why runnable?
ans - Multiple inheritance is not possible in java. So in case a class is extending some other class, and we want to create thread, then we can't extend Thread. 
Multiple inheritance is applicable for interfaces, hence, implement Runnable. This was the motive of creating Runnable.

Thread constructor:
-------------------
* Thread() - creates a new thread with default name (Thread-0, Thread-1, etc.).
* Thread(String name) - creates a new thread with the specified name.
* Thread(Runnable target) - creates a new thread with the specified Runnable target.
* Thread(Runnable target, String name) - creates a new thread with the specified Runnable target and name.
* Thread(ThreadGroup group, Runnable target, String name) - creates a new thread in the specified thread group with the specified Runnable target and name.
...

Thread methods:
----------------
* start() - starts the thread and calls the run() method.
* run() - contains the code to be executed by the thread. Do not execute this directtly, else threads won't be threads but some normal method call.
* sleep(milliseconds) - pauses the thread for a specified time.
* join() - waits for the thread to finish execution before proceeding.
* setPriority(int priority) - sets the priority of the thread (1-10, where 1 is lowest and 10 is highest). Default is 5.
    * priority is a hint to the scheduler, not a guarantee. The OS decides how to allocate CPU time based on priority and other factors.
    * single core would give better results. Multicore may execute threads in parallel.
* getName()
* setName(String name) - sets the name of the thread.
* getState()
* interrupt() - interrupts the thread, causing it to set Interrupted Flag to true. This can be called anytime.
    * the effect of calling interrupt() differs depending on the thread's state:
        * If the thread is in a blocking state (e.g., sleep(), wait(), join(), or I/O operations that can throw InterruptedException), calling interrupt() will cause the thread to exit that blocking state by throwing an InterruptedException. This allows the thread to handle the interruption and potentially terminate or change its behavior.
        * If the thread is not in a blocking state, calling interrupt() will simply set the thread's internal "interrupted status" flag to true. The thread will continue its normal execution. It is then up to the thread's code to periodically check this flag using Thread.currentThread().isInterrupted() and respond to the interruption accordingly (e.g., by stopping its work or cleaning up resources).
* yield() - hints to the scheduler that the current thread is willing to yield/give away its current use of the CPU. It allows other threads to run, but does not guarantee that they will.

Def: user thread - User-level threads are threads that are managed entirely by the user-level thread library, without any direct intervention from the operating system's kernel.
* the kernel is unaware of user-level threads and treats the process as single-threaded.
* JVM waits for user threads to finish before exiting the program.
Def: daemon thread - Daemon threads are background threads which JVM doesn't wait for to finish before exiting the program. eg. garbage collector.
-> Check CW48_DaemonThread.java
-> Check CW49_Thread.java
-> Check CW50_Runnable.java
-> Check CW51_Synchronization_LockInstance.java
-> Check CW52_Synchronization_LockClass.java
-> Check CW53_Synchronization.java

Synchronization can be achieved using locks. Locks can be of 2 types - Intrinsic and Explicit
* Intrinsic locks are built-in locks provided by Java. Every object in Java has an intrinsic lock associated with it. When a thread enters a synchronized method or block, it acquires the intrinsic lock of the object.
* Explicit locks are provided by the java.util.concurrent.locks package. They offer more flexibility (we can when to lock/unlock) and features compared to intrinsic locks, such as tryLock() and lockInterruptibly().
-> Check CW54_ExplicitLocks.java

Demerits of using synchronized keyword - threads in line (t2,t3,...) have wait till the acquirer (t1) finishes executing the function (and might have to wait indefinitely). Hence other threads should be given a chance after some time. Here comes the need of explicit locks. 
So, indefinite blocking, no interruptibility, no timeout and no read/write locking.

Remember these functions on Lock class:
* tryLock()
* tryLock(time, unit)
* unlock()
* lockInterruptibly()
* lock()
-> Check CW55_ReentrantLock.java

Fairness of locks - by default, locks are unfair, meaning threads can acquire locks in random order.
To make locks fair, use ReentrantLock(true) constructor. This prevents thread starvation.
-> Check CW56_FairnessOfLocks.java

Merits of using explicit locks over synchronized keyword:
---------------------------------------------------------
1. Fairness - explicit locks can be made fair, preventing thread starvation.
2. Timeout - tryLock() allows threads to attempt to acquire a lock without blocking indefinitely.
3. Interruptibility - lockInterruptibly() allows threads to be interrupted while waiting for a lock.
4. Read/Write Locking - explicit locks can be implemented as read/write locks, allowing for more granular control over access to shared resources.
    - a normal lock allows only one thread to access a resource at a time, regardless of whether the access is for reading or writing.
    - a read/write lock allows multiple threads to read a resource simultaneously (shared access), but only one thread can write to the resource at a time (exclusive access). This improves concurrency and performance in scenarios where reads are more frequent than writes. Resources are unlocked in way better manner than when a single lock or synchronized keyword is used.
-> Check CW57_ReadWriteLocks.java

Deadlock - a situation where two or more threads are blocked forever, each waiting for the other to release a resource.
-> Check CW58_Deadlock.java

Deadlock occurs when following 4 conditions hold simultaneously:
1. Mutual Exclusion: At least one resource must be held in a non-sharable mode. Only one thread can use the resource at any given time.
2. Hold and Wait: A thread holding at least one resource is waiting to acquire additional resources held by other threads.
3. No Preemption: Resources cannot be forcibly removed from threads holding them. A resource can only be released voluntarily by the thread holding it.
4. Circular Wait: A set of threads are waiting for each other in a circular chain. Each thread is waiting for a resource held by the next thread in the chain.

Dining philosophers problem is a classic deadlock example 
- https://www.baeldung.com/java-dining-philoshophers

Livelock is a situation where processes are not blocked (like in deadlock) but they continuously change their state in response to each other, without making any real progress. In short, processes keep moving but never reach completion.

Starvation is the problem that occurs when high priority processes keep executing and low priority processes get blocked for indefinite time.

Checkout methods to prevent deadlock & livelock, Dining philosophers problem, stavation:
-> Check CW59_DeadlockResolution.java

Thread Communication
----------------------
Inter-thread communication allows synchronized threads to communicate with each other. This is achieved through methods from Object class:
PTR: These methods reside in Object class and not Thread class.

1. wait():
   - Causes current thread to wait until another thread invokes notify() or notifyAll()
   - MUST be called from within synchronized context
   - Releases the lock on the object while waiting
   - Common usage: producer waits when buffer is full, consumer waits when buffer is empty

2. notify():
   - Wakes up a single waiting thread
   - If multiple threads are waiting, choice is arbitrary
   - MUST be called from synchronized context
   - Lock is not released immediately - only after synchronized block completes

3. notifyAll():
   - Wakes up all waiting threads
   - Threads compete for the lock
   - More commonly used than notify() to avoid thread starvation
   - MUST be called from synchronized context

Key Points:
- All three methods must be called from within synchronized block/method
- These methods are instance methods of Object class
- IllegalMonitorStateException if called without synchronization
- wait() throws InterruptedException

Example Pattern:
```java
synchronized void produce() {
    while (isBufferFull()) {
        wait();  // wait if buffer is full
    }
    // produce item
    notifyAll();  // notify consumers which are waiting on the Object monitor in this synchronized method <- IMP
}

synchronized void consume() {
    while (isBufferEmpty()) {
        wait();  // wait if buffer is empty
    }
    // consume item
    notifyAll();  // notify producers which are waiting on the Object monitor in this synchronized method <- IMP
}
```

Best Practices:
1. Always use wait() in a while loop (not if)
2. Prefer notifyAll() over notify()
3. Keep synchronized blocks as short as possible
4. Handle InterruptedException appropriately
5. Avoid calling these methods on constant objects like String literals because those objects are shared globally by the JVM, and other code may also synchronize on the same literal. 

Producer-Consumer problem is a classic example of inter-thread communication using wait() and notify()/notifyAll().
-> Check CW60_ThreadCommunication.java

Def: Functional interface 
- An interface with only one abstract member function is called a functional interface, or a Single Abstract Method (SAM) interface. 
- The functional interface can have several non-abstract member functions but only one abstract member function

Lambda expression 
- is an Anonymous function
- returns a Functional Interface.
- Any variable used in lambda expression should be final or effectively final
    - effectively final means that the variable is not explicitly declared as final, but its value is never changed after it is initialized. In other words, an effectively final variable behaves like a final variable because it is assigned a value only once and does not get modified thereafter.

Runnable is a functional interface, therefore threads can be created using lambda function as ```Thread t1 = new Thread(() -> sout("hello"))```

qq: but why does lambda expression return functional interface?
ans - because functional interface has only one abstract method, so the lambda expression thus written, provides the implementation for that single method.

Thread Pool
-----------
Def: Thread pool is a collection of pre-initialized, reusable threads that are ready to perform tasks. Instead of creating new threads for each task, tasks are submitted to the pool where waiting threads pick them up.

Benefits of Thread Pools:
1. Better Resource Management
   - Reduces overhead of thread creation/destruction
   - Controls number of threads in application
   - Prevents resource exhaustion

2. Improved Performance
   - Faster response time (no thread creation overhead)
   - Better resource utilization
   - Prevents thread-related memory leaks

3. Control over Thread count
   - Allows setting maximum and minimum number of threads in the pool

4. Task Management
   - Queue tasks when all threads are busy
   - Control task execution order
   - Handle task completion and failures

Executors Framework
-------------------
Def: The Executors Framework is a high-level API in Java that simplifies the management of thread pools and task execution. It provides factory methods to create different types of thread pools and manages the lifecycle of threads.

Problems prior to Executors Framework:
1. Manual Thread Management - Manual creation/destruction of threads, complex lifecycle management, and lack of standardized thread handling.
2. Resource Inefficiency - High overhead from thread creation/destruction, memory wastage, and excessive context switching.
3. Scalability Issues - No task queuing, poor load handling, and performance degradation with too many threads.
4. Thread Reuse Problems - No thread recycling mechanism, requiring new thread creation for each task.
5. Error Handling Challenges - Poor exception propagation and lack of standardized error recovery mechanisms.

Types of Thread Pools (via Executors):
1. Fixed Thread Pool
   ```java
   ExecutorService fixed = Executors.newFixedThreadPool(5);
   // Creates pool with 5 threads that stay constant
   ```

2. Cached Thread Pool
   ```java
   ExecutorService cached = Executors.newCachedThreadPool();
   // Creates pool that adds threads as needed and removes idle ones. Use it when there's variable load, but make sure tasks are short-lived, otherwise whole CPU time will be consumed by this and system might crash due to resource exhaustion.
   ```

3. Single Thread Executor
   ```java
   ExecutorService single = Executors.newSingleThreadExecutor();
   // One thread executing tasks sequentially
   ```

4. Scheduled Thread Pool
   ```java
   ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(3);
   // For tasks that need to run periodically or with delay
   ```

Key Methods:
1. submit() vs execute():
   - submit(): returns Future<?> for tracking task completion
   - execute(): void return type, fire-and-forget

2. shutdown() vs shutdownNow():
   - shutdown(): stops accepting new tasks, completes queued ones
   - shutdownNow(): stops new tasks, interrupts running ones

Best Practices:
1. Choose appropriate pool size:
   - CPU-intensive tasks: number of CPU cores
   - I/O-intensive tasks: can be much larger

2. Handle task rejection:
   ```java
   ThreadPoolExecutor executor = new ThreadPoolExecutor(
       5, 10, 60, TimeUnit.SECONDS,
       new ArrayBlockingQueue<>(100),
       new ThreadPoolExecutor.CallerRunsPolicy()
   );
   ```

3. Always shutdown pools properly:
   ```java
   try {
       executor.shutdown();
       if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
           executor.shutdownNow();
       }
   } catch (InterruptedException e) {
       executor.shutdownNow();
   }
   ```

4. Use try-with-resources when possible:
   ```java
   try (ExecutorService executor = Executors.newFixedThreadPool(5)) {
       // pool usage
   } // auto shutdown
   ```

Industry Practice:
- Avoid manual thread creation, use Executor Framework
- Use appropriate thread pool type for the use case
- Implement proper shutdown and error handling
- Monitor pool performance and adjust as needed

INTERVIEW QQ - Runnable vs Callable. 
ans 
- Callable returns template @ V call() vs Runnable doesn't @ void run()
- one uses call() and other uses run()
- call() declares throwing of Exception using throws, where run() doesn't eg. Thread.sleep() in run() can't 

AtomicInteger is thread safe. So, you don't need locks or synchronized to share variables among multiple threads.
https://www.youtube.com/watch?v=WDn_Bax0UFo