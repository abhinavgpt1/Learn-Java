class IterateFromStartToEnd implements Runnable {
    private int start, end;

    IterateFromStartToEnd(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class CW50_Runnable {
    public static void main(String[] args) {
        IterateFromStartToEnd runnableT1 = new IterateFromStartToEnd(1, 5);
        IterateFromStartToEnd runnableT2 = new IterateFromStartToEnd(6, 10);
        Thread t1 = new Thread(runnableT1);
        t1.setName("T1");
        Thread t2 = new Thread(runnableT2, "T2");
        // t2.setName("T2");// no need

        t1.start();
        t2.start();

        // not adding join here, but this doesn't mean JVM will exit before t1 and t2 finish.
        // PTR: JVM will run main thread alongside t1 and t2 before exiting. Join is used when you want main thread to wait for t1 and t2 to finish before proceeding.
        // This is a fundamental distinction between user threads (also known as non-daemon threads) and daemon threads in Java:
        // User Threads: These are considered critical to the application's functionality. The JVM will not exit as long as any user thread is still running. It will wait for each user thread to finish its tasks naturally.
        // Daemon Threads: These are background threads that provide services to user threads (e.g., garbage collector). The JVM does not wait for daemon threads to complete.

        // Another way to create thread from runnable is to use lambda expression, thanks to runnable being @FunctionalInterface
        Thread lamdbThread = new Thread(() -> System.out.println("Hello from lambda thread"));
        lamdbThread.start();

        System.out.println(Thread.currentThread().getName() + " - Main finished");

        // qq - can I start() t1/t2/lamdbaThread again?
        // ans - Calling start() on the same Thread object twice in Java will result in an IllegalThreadStateException.
        // A thread in Java is designed to be started only once. Once start() is called, the thread begins its execution, and its run() method is invoked. 
        // After the run() method completes, the thread is considered to have finished its execution and cannot be restarted.
        // 
        // If start() is called a second time on the same Thread object, even if the thread has already completed its execution, 
        // the JVM will detect this attempt to restart a thread and throw the IllegalThreadStateException at runtime.
        // To execute a task multiple times or concurrently, new Thread instances should be created for each execution. Each new Thread instance can then be started independently.

        // uncomment following code to see the exception
        // PTR: the other threads don't terminate.
        // System.out.println("lamdbThread state presently:" + lamdbThread.getState());
        // lamdbThread.start();

        // Output:
        // lamdbThread state presently:TERMINATED
        // Exception in thread "main" java.lang.IllegalThreadStateException
        // 	at java.base/java.lang.Thread.start(Thread.java:1389)
        // 	at CW50_Runnable.main(CW50_Runnable.java:49)
    }
}

/**
 * Output:
 * -------
 * Hello from lambda thread
 * T1 - 1
 * T2 - 6
 * main - Main finished
 * T2 - 7
 * T1 - 2
 * T2 - 8
 * T1 - 3
 * T2 - 9
 * T1 - 4
 * T2 - 10
 * T1 - 5
 */