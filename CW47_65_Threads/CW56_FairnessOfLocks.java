import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CW56_FairnessOfLocks {
	private final Lock lock = new ReentrantLock(true);

	public void accessResource() {
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + ": acquired lock");
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt(); // restore interrupt status
		} finally {
			System.out.println(Thread.currentThread().getName() + ": releasing lock"); 
			// PTR: this sout statement should be written before unlock(), otherwise the order of sout here and in try block would be random.
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		// using a lock like - Lock lock = new ReentrantLock(); would run threads in random order
		// using a lock like - Lock lock = new ReentrantLock(true); would ensure threads get lock in the order they requested it (FIFO) - fair lock

		// PTR: Order is maintained by the scheduler which executes the threads. It's not like t1.start() will always execute before t2.start().
		// PTR: Fairness in locks resolves the problem of starvation i.e. a thread waiting indefinitely to acquire a lock because other threads are continuously acquiring it before it.

		CW56_FairnessOfLocks fairnessLock = new CW56_FairnessOfLocks();
		Runnable task = new Runnable() {
			@Override
			public void run() {
				fairnessLock.accessResource();
			}
		};

		Thread t1 = new Thread(task, "Thread-1");
		Thread t2 = new Thread(task, "Thread-2");
		Thread t3 = new Thread(task, "Thread-3");

		t1.start();
		t2.start();
		t3.start();

		// PTR (again): It's not necessary that scheduler will always execute t1 first. 
		// Whichever comes first to acquire lock will get it first. Yes, an order will be maintained i.e. no starvation.

		// In order to execute in order of t1, t2, t3 - put a thread.sleep(50) between their start() calls.
	}
}

/**
 * Output:
 * -------
 * Thread-1: acquired lock
 * Thread-1: releasing lock
 * Thread-3: acquired lock
 * Thread-3: releasing lock
 * Thread-2: acquired lock
 * Thread-2: releasing lock
 */