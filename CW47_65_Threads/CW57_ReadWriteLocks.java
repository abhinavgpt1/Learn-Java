import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CW57_ReadWriteLocks {
	private int count = 0; // shared resource

	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();

	private int getCount() {
		readLock.lock(); // multiple threads can enter and acquire this lock (iff no thread holds write lock, else they wait here indefinitely)
		try {
			return count;
		} finally {
			readLock.unlock();
		}
	}

	private void increment() {
		writeLock.lock();
		try {
			count++;
			Thread.sleep(10); // reader threads would be able to read only after write lock is released. Till then they will be blocked at line 13.
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			ex.printStackTrace();
		} finally {
			writeLock.unlock();
		}
	}

	public static void main(String[] args) {
		// synchronized doesn't differentiate between read and write operations. It locks the entire resource for both reading and writing.
		// ReadWriteLock allows multiple threads to read a resource simultaneously (shared lock) but only one thread to write (exclusive lock).

		// we need to keep 2 locks - one for read and one for write
		// multiple threads can hold the read lock if no thread holds the write lock. 
		// But if a thread holds the write lock, no other thread can hold either read or write lock until the write lock is released.

		CW57_ReadWriteLocks counter = new CW57_ReadWriteLocks();
		Runnable readTask = new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<5; i++){
					System.out.println(Thread.currentThread().getName() + ": " + counter.getCount());
				}
			}
		};
		Runnable writeTask = new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<5; i++){
					counter.increment();
					System.out.println(Thread.currentThread().getName() + ": incremented count");
				}
			}
		};

		Thread writerThread = new Thread(writeTask, "Writer-Thread");
		Thread readerThread1 = new Thread(readTask, "Reader-Thread-1");
		Thread readerThread2 = new Thread(readTask, "Reader-Thread-2");

		writerThread.start();
		readerThread1.start();
		readerThread2.start();

		try {
			writerThread.join();
			readerThread1.join();
			readerThread2.join();
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt(); // restore interrupt status
			ex.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + ": Final Count: " + counter.getCount());

		// qq: how does readLock know when not to get acquired?
		// ans: internally ReadWriteLock maintains a count of readers and writers. If a writer is active, readers are blocked until the writer releases the lock.
		// in short the parent object ReadWriteLock maintains the state of both locks.

		// PTR: which thread to pick depends on cpu scheduler. So order of output may vary. 
		// (IMP) Understand that sout statements are not part of critical section, so their order may slightly vary.

		/**
		 * Here's how it works:
		 * --------------------
		 * Separate Locks for Reading and Writing: A ReadWriteLock internally manages two distinct locks:
		 * 	Read Lock: This lock is acquired by threads that only intend to read the shared resource. Multiple threads can hold the read lock concurrently, as reading does not modify the data and therefore does not introduce race conditions among readers.
		 * 	Write Lock: This lock is acquired by threads that intend to modify (write to) the shared resource. The write lock is exclusive, meaning only one thread can hold the write lock at a time, and no other threads (neither readers nor writers) can hold any lock while a writer is active.
		 * Concurrency Rules:
		 * 	Multiple Readers: If no writer holds the write lock, any number of reader threads can acquire the read lock and access the shared resource concurrently.
		 * 	Exclusive Writer: If a writer holds the write lock, no other thread (reader or writer) can acquire any lock until the writer releases the write lock.
		 * 	Writer Blocks Readers: If a writer is waiting to acquire the write lock, new readers may be blocked from acquiring the read lock to prevent starvation of the writer. The specific fairness policy can vary between implementations.
   (IMP) * 	Reader Blocks Writer: If one or more readers hold the read lock, a writer attempting to acquire the write lock will be blocked until all active readers release their read locks.
		*/
	}
}

/**
 * Output:
 * -------
 * Writer-Thread: incremented count
 * Reader-Thread-2: 1
 * Reader-Thread-1: 1
 * Reader-Thread-2: 2
 * Reader-Thread-2: 2
 * Reader-Thread-1: 2
 * Writer-Thread: incremented count
 * Reader-Thread-1: 2
 * Reader-Thread-2: 2
 * Writer-Thread: incremented count
 * Reader-Thread-2: 3
 * Reader-Thread-1: 3
 * Writer-Thread: incremented count
 * Reader-Thread-1: 4
 * Writer-Thread: incremented count
 * main: Final Count: 5
 * 
 * Output 2
 * ---------
 * Writer-Thread: incremented count
 * Writer-Thread: incremented count		<- as soon as 1st write unlocked, both reader threads read the value, but before they could sout, writer got chance to write again + sout (ig cpu scheduler had to do with this). Hence, the output below is 1 and not 2. Thing to note is that no thread was interrupted. Instead writer waits for reader threads to release the lock as per Concurrency Rules above. Understand this - sout are not part of critical section, so order of sout may vary between threads.
 * Reader-Thread-1: 1
 * Reader-Thread-2: 1
 * Writer-Thread: incremented count
 * Reader-Thread-1: 3
 * Reader-Thread-2: 3
 * Writer-Thread: incremented count
 * Reader-Thread-2: 4
 * Reader-Thread-1: 4
 * Writer-Thread: incremented count
 * Reader-Thread-1: 5
 * Reader-Thread-1: 5
 * Reader-Thread-2: 5
 * Reader-Thread-2: 5
 * main: Final Count: 5
 */