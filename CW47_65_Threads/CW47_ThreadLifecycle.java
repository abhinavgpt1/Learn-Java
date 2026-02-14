class SampleThread extends Thread {
    @Override
    public void run() {
        System.out.println("World"); // RUNNABLE (running at present)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) { 
            // you can't have "public void run() throws InterruptedException" since parent run() doesn't throw any checked exceptions (Exceptions 101).
            // Therefore, handle exception in try-catch.
            e.printStackTrace();
        }
    }
}

// States of a thread: NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
public class CW47_ThreadLifecycle {
    public static void main(String[] args) throws InterruptedException {
        SampleThread t1 = new SampleThread();
        System.out.println(t1.getName() + ":" + t1.getState()); // NEW
        t1.start(); // this gets started as soon as JVM gets time which means it might not run immediately
        System.out.println(t1.getName() + ":" + t1.getState()); // RUNNABLE
        System.out.println(t1.getName() + ":" + t1.getState()); // TIMED_WAITING - if it sees thread stuck, otherwise copy this statement 10 times, you'll see.
        t1.join(); // main thread will wait for t1 to finish execution before proceeding
        System.out.println(t1.getName() + ":" + t1.getState()); // TERMINATED
    }
}

/**
 * Output (after copying line 22 multiple times):
 * ----------------------------------------------
 * Thread-0:NEW
 * Thread-0:RUNNABLE
 * World
 * Thread-0:RUNNABLE
 * Thread-0:RUNNABLE
 * Thread-0:RUNNABLE
 * Thread-0:RUNNABLE
 * Thread-0:RUNNABLE
 * Thread-0:TIMED_WAITING
 * Thread-0:TIMED_WAITING
 * Thread-0:TIMED_WAITING
 * Thread-0:TERMINATED
 */