import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CW61_ExecutorsFramework {

	public static long factorial(int n) {
		long result = 1;
		for (int i = 2; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	public static void manualThreadManagement() {
		long startTime = System.currentTimeMillis();
		List<Thread> threads = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			// since i is not effectively final in lambda expression, let's create one.
			int effectivelyFinalI = i;
			Thread t = new Thread(() -> {
				long result = factorial(effectivelyFinalI);
				System.out.println(Thread.currentThread().getName() + " - (" + effectivelyFinalI + ")! = " + result);
			}, ("Fact-Thread-" + i)); // here i can be used since it's outside lambda
			threads.add(t);
			t.start();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		// Since threads need to be joined before calculating time taken by program, we need to store threads.
		System.out.println("Time taken to calcualte factorials using manual thread management: "
				+ (System.currentTimeMillis() - startTime) + " ms");

		// We're not reusing threads here - Executor framework will take care of that for us.
	}

	public static void main(String[] args) {
		// there are mainly 3 interfaces in Executors Framework
		// 1. Executor - has execute(Runnable r) method to submit tasks for asynchronous execution.
		// 2. ExecutorService - extends Executor, adds lifecycle management methods like
		// shutdown(), submit(Callable<T> c) etc.
		// 3. ScheduledExecutorService - extends ExecutorService, adds scheduling
		// capabilities with methods like schedule(Runnable r, long delay, TimeUnit unit) etc.

		// Say we want to compute factorial from 1 to 10. We can effectively do this by spinning up 10 threads.
		// In this case we'll have to manage threads ourselves
		manualThreadManagement();

		System.out.println("-----------------------------------");

		// Executor framework takes away the thread management part from us, and let concentrate on business logic.
		// It even reuses threads.

		long startTime = System.currentTimeMillis();
		
		// use ExecutorService and not Executor (check reason below)
		ExecutorService executor = Executors.newFixedThreadPool(3); 
		
		for (int i = 1; i <= 10; i++) {
			// since i is not effectively final in lambda expression, let's create one.
			int effectivelyFinalI = i;
			executor.submit(() -> {
				long result = factorial(effectivelyFinalI);
				System.out.println(Thread.currentThread().getName() + " - (" + effectivelyFinalI + ")! = " + result);
			});
		}
		// Executor needs to be shutdown else JVM will not exit since threads in pool are non-daemon threads.
		// Once shutdown, no new tasks can be submitted, else RejectedExecutionException is thrown.
		// It waits for running tasks to finish before terminating threads in pool.
		executor.shutdown();
		// shutdownNow() will immediately shutdown threads, and nothing will get printed.

		// Main doesn't wait for executor to shutdown, so to execute below sout("Time taken to..) after all above threads, 
		// we need to await & terminate after a period of time.
		try {
			executor.awaitTermination(100, TimeUnit.SECONDS);
			// waits till 100 sec from the point of shutdown request before moving to the sout statement below.
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		System.out.println("Time taken to calcualte factorials using executors framework: "
				+ (System.currentTimeMillis() - startTime) + " ms");

		// For unlimited wait of executorService to get finished, do this -
		// Theory - executor.awaitTermination() return true if executor terminated else false
		/**
		 * while(!executor.awaitTermination(10, TimeUnit.MILLISECONDS)) {
		 * System.out.println("Waiting...");
		 * }
		 */

		// Executor interface is a functional interface containing void execute (Runnable command).
		// Since it lacks shutdown, awaitTermination() and other methods, it's of no use.
		// You can't even shutdown and have to do it manually.
		// ExecutorService contains Future<?> submit() & provides a lot functions
		// whereas Executor doesn't, and has void execute().
		// PTR: execute() != submit()

		// qq: What is Future?
		// ans: Check offical documentation on this by going into definition (ctrl + click)
		// def: A {@code Future} represents the result of an asynchronous computation.
		//
		// Methods are provided to check if the computation is complete, to wait for its
		// completion, and to retrieve the result of the computation. The result can
		// only be retrieved using method {@code get} when the computation has
		// completed, blocking if necessary until it is ready. Cancellation is performed
		// by the {@code cancel} method. Additional methods are provided to determine if
		// the task completed normally or was cancelled. Once a computation has
		// completed, the computation cannot be cancelled.
	}
}

/**
 * Output:
 * -------
 * Fact-Thread-3 - (3)! = 6
 * Fact-Thread-5 - (5)! = 120
 * Fact-Thread-8 - (8)! = 40320
 * Fact-Thread-2 - (2)! = 2
 * Fact-Thread-4 - (4)! = 24
 * Fact-Thread-7 - (7)! = 5040
 * Fact-Thread-10 - (10)! = 3628800
 * Fact-Thread-9 - (9)! = 362880
 * Fact-Thread-6 - (6)! = 720
 * Fact-Thread-1 - (1)! = 1
 * Time taken to calcualte factorials using manual thread management: 35 ms
 * -----------------------------------
 * pool-1-thread-2 - (2)! = 2
 * pool-1-thread-3 - (3)! = 6
 * pool-1-thread-1 - (1)! = 1
 * pool-1-thread-3 - (4)! = 24
 * pool-1-thread-2 - (6)! = 720
 * pool-1-thread-2 - (8)! = 40320
 * pool-1-thread-1 - (5)! = 120
 * pool-1-thread-2 - (9)! = 362880
 * pool-1-thread-3 - (7)! = 5040
 * pool-1-thread-1 - (10)! = 3628800
 * Time taken to calcualte factorials using executors framework: 21 ms
 */