import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CW55_ReentrantLock {
	private final Lock lock = new ReentrantLock();

	public void outerMethod() {
		try {
			lock.lock(); // just like synchronized method/block - waits till lock is available 
						 // i.e. count of locks acquired = 0

			// use lock.lockInterruptibly() here instead of lock.lock() if you want to
			// interrupt the thread while waiting for the lock - add a catch for InterruptedException in this case

			System.out.println("Inside outerMethod");
			innerMethod(); // no deadlock occurs as same thread can acquire lock multiple times, hence re-entrant
		} finally {
			lock.unlock();
		}
	}

	public void innerMethod() {
		try {
			lock.lock();
			System.out.println("Inside innerMethod");
		} finally {
			lock.unlock(); // this DOESN'T unlock the lock acquired in outerMethod. Reentrant lock keeps a count of locks acquired by same thread.
		}

		// It's obvious that you can't put unlock() twice here and remove it from outerMethod - the current code would work, but what if there are statements after innerMethod() call in outerMethod() that also need synchronization?

		// PTR: unlock() operation when count = 0 will throw IllegalMonitorStateException
	}

	public static void main(String[] args) {
		CW55_ReentrantLock reentrantLock = new CW55_ReentrantLock();
		reentrantLock.outerMethod();
	}
}

/**
 * Output:
 * -------
 * Inside outerMethod
 * Inside innerMethod
 */