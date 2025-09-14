// The life of a daemon thread depends on the mercy of user threads,
// meaning that when all user threads finish their execution, the JVM automatically terminates the daemon thread.
// To put it simply, daemon threads serve user threads by handling background tasks and have no role other than supporting the main execution.
// Examples of daemon threads in Java include garbage collection (gc) and finalizer.
// ref: https://www.geeksforgeeks.org/java/daemon-thread-java/
// ref: https://www.youtube.com/watch?v=4aYvLz4E1Ts
public class CW48_DaemonThread {
    public static void main(String[] args) {
        Thread daemon = new Thread(()->{
            while(true){
                System.out.println("hello world");
            }
        });
        daemon.setDaemon(true);
        daemon.start(); //might print some statements after "Main done" since JVM needs to check if all user threads are done before closing the program.
        System.out.println("Main done");

        // say there was user thread (t1) at line 9 (i.e. after daemon.start()), then JVM would wait for main and t1 to complete before terminating the program.
    }    
}
/**
 * Output1:
 * --------
 * Main done
 * 
 * Output2:
 * --------
 * Main done
 * hello world
 * hello world
 * hello world
 * hello world
 * hello world
 * hello world
 * hello world
 * hello world
 */