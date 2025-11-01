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
=> multithreading is not necessary for multitasking.

PTR: multithreading is possible within a single process, while multitasking involves multiple processes.

PTR: parallel running of processes/programs:multitasking :: efficient program running using threads:multithreading
eg. multitasking (managed by OS)
|->process 1 = ms word ---multithreading---> t1=user input, t2=spell check
|->process 2 = chrome ---multithreading---> t1=pade rendering, t2=data loading BTS

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
run method is implemented, and Thread class is instantiated with your class as an argument.
start() is called to begin execution.
Thread.currentThread() returns the currently executing thread object. 
Thread.currentThread().getName() returns the name of the currently executing thread. eg. main, Thread-0, etc.

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