import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
	private double balance = 100;
	private final Lock lock = new ReentrantLock();

	public void withdraw(double amount) {
		// lock.tryLock() - checks if lock is available, if yes, acquires the lock and returns true, else returns false immediately.
			// no need for try catch as it checks for lock availability and doesn't wait
		// lock.tryLock(time, unit) - tries to acquire the lock within the given time, if successful returns true, else returns false after time elapses.
			// needs try catch since it waits for the lock to be available and meanwhile thread can be interrupted
		
		// IMP Sonar suggestion: logging error when thread interrupts isn't sufficient. This way you're losing important information about the interruption.
		// Handling this exception means either re-throw it or manually re-interrupt the thread by calling Thread.currentThread().interrupt().
		// Simply logging it counts as ignoring it.
		// Potential impact:
		// It risks delaying thread shutdown + loses the info that thread was interrupted.
		// Hence, it's better to re-interrupt the thread after catching InterruptedException & restore the state so that higher-level interrupt handlers can deal with it appropriately.

		try { // to handle any interruption since thread is waiting
			if (lock.tryLock(5000, TimeUnit.MILLISECONDS)) { // waits till 5sec to acquire lock. This way t2 doesn't have to wait indefinitely on t1.
				try { // IMP: encapsulate critical section task with try-catch-finally so as to release lock
					if (balance >= amount) {
						System.out.println(Thread.currentThread().getName() + ": Acquired lock, proceeding with withdrawal");
						Thread.sleep(3000); // simulate time taken to process withdrawal
						balance -= amount;
						System.out.println(Thread.currentThread().getName() + ": Withdrawn " + amount + ", New balance: " + balance);
					} else {
						System.out.println(Thread.currentThread().getName() + ": insufficient balance. Withdrawal amount: " + amount + ", Current balance: " + balance);
					}
				} catch (Exception ex) {
					// any kinda exception that occurs in withdrawl process
					ex.printStackTrace();

					Thread.currentThread().interrupt(); // restore interrupt status
				} finally {
					// finally is used for releasing resources.
					lock.unlock(); // IMPORTANT !!
				}
			} else {
				System.out.println(Thread.currentThread().getName() + ": Could not acquire lock, try again later");
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();

			Thread.currentThread().interrupt(); // restore interrupt status
		}

		// By restoring the interrupt status, we can perform cleanups like
		if (Thread.currentThread().isInterrupted()) {
			// perform necessary cleanup actions here
		}

		/**
		 * Extra methods for lock class:
		 * -----------------------------
 		 * lock.lockInterruptibly() - lockInterruptibly() may block if the the lock is already held by another thread and will wait until the lock is aquired. This is the same as with regular lock(). But if another thread interrupts the waiting thread lockInterruptibly() will throw InterruptedException.
		 * 	src - https://stackoverflow.com/questions/17811544/actual-use-of-lockinterruptibly-for-a-reentrantlock
 		 * lock.lock() - acquires the lock, waits indefinitely if not available. It is similar to synchronized keyword.
		 */
	}

	public double getBalance() {
		return balance;
	}
}

public class CW54_ExplicitLocks {
	public static void main(String[] args) {
		BankAccount account = new BankAccount(); // shared resource
		Runnable task = new Runnable() {
			@Override
			public void run() {
				account.withdraw(50);
			}
		};
		Thread t1 = new Thread(task, "Thread-1");
		Thread t2 = new Thread(task, "Thread-2");
		t1.start(); // acquires lock at T0, withdraws for 3sec, releases lock at T3
		t2.start(); // waits for 5sec max. at T0, acquires lock at T3, withdraws for 3sec, releases lock at T6
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": Final balance: " + account.getBalance());
	}
}

/**
 * Output:
 * -------
 * Thread-1: Acquired lock, proceeding with withdrawal
 * Thread-1: Withdrawn 50.0, New balance: 50.0
 * Thread-2: Acquired lock, proceeding with withdrawal
 * Thread-2: Withdrawn 50.0, New balance: 0.0
 * main: Final balance: 0.0
 */