import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CW62_Future_Callable {
	
	public static void main(String[] args) {
		// get() in Future waits for the task to be completed, and retrieves the result.
		// isDone() returns true if task is completed. Completion may be due to normal
		// termination, and exception, or cancellation -- in all of these cases, this
		// method will return true. 
		
		// PTR: isDone doesn't wait for thread to get executed. get() does that.

		ExecutorService executor = Executors.newSingleThreadExecutor(); // single thread in thread pool
		Future<?> future = executor.submit(() -> System.out.println("hello from runnable function"));
		if(future.isDone()) { // might not execute, but will definitely do after get()
			System.out.println("Future sout doesn't works in isDone() before executing get()");
		}

		try {
			System.out.println("returned value from Future<?>: " + future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		if(future.isDone()) {
			System.out.println("Future sout works in isDone() after executing get()");
		}		
		executor.shutdown(); // this statement is IMP. otherwise close the program manually


		// Runnable vs Callable
		// ---------------------
		// Runnable:
		// - Implements void run(), cannot return a value and cannot declare checked exceptions.
		// - Use when you don't need a result (fire-and-forget). Submitting a Runnable returns a Future<?> whose get() returns null.
		// Callable<V>:
		// - Implements V call() which can return a result and declare checked exceptions.
		// - Use when you need a computed result; submit() returns Future<V> and Future.get() returns the value (or throws ExecutionException).
		// Quick rules:
		// - If you need a return value or checked exceptions -> use Callable.
		// - If you only need to run code and don't need a result -> use Runnable.
		// - Runnable - public abstract void run();
		// - Callable - V call() throws Exception;
		
		// PTR:
		// executor.submit(() -> sout("hello")); // uses Runnable internally since return type of sout is void
		// executor.submit(() -> "hello"); // uses Callable internally
		
		// Code for callable:
		System.out.println("------------------------");
		// executor.submit(() -> "Hello Runnable"); // throws RejectedExecutionException since it was shutdown before

		ExecutorService executor2 = Executors.newSingleThreadExecutor();
		// Runnable runnable = () -> "Hello Callable"; // error: Void methods can't return value
		Callable<String> callable = () -> "Hello Callable";
		Future<String> future2 = executor2.submit(callable);
		try {
			System.out.println("Future result from callable:" + future2.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		if (future2.isDone()) {
			System.out.println("Future isDone after get()");
		}
		executor2.shutdown(); // IMP. don't forget

		// Code for submit(Runnable, T result) - returns result upon successful completion != V call()
		System.out.println("---------------------------");
		ExecutorService executor3 = Executors.newSingleThreadExecutor();
		Future<String> future3 = executor3.submit(() -> System.out.println("Hello from runnable with T result"), "SuccessMsg");
		try {
			System.out.println("Future result from runnable upon success/completion: " + future3.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		if (future3.isDone()) {
			System.out.println("Future isDone after get()");
		}
		executor3.shutdown();

		// Note: shutdown() - Initiates an orderly shutdown in which previously submitted
		// tasks are executed, but no new tasks will be accepted. 
		// Invocation has no additional effect if already shut down. 
		// This method does not wait / block main thread for submitted tasks to complete execution. 	<- IMP
		// Recall: awaitTermination after shutdown() in CW61
		
		// Extra functions:
		// isShutdown() - shutdown() doesn't instantly stop the threads - it's a signal that threads should start completing itself.
		System.out.println("isShutdown() on executor3 after shutdown(): " + executor3.isShutdown()); // can be false too
		
		// isTerminated() - Returns {@code true} if all tasks have completed following shut down.
     	// Note that {@code isTerminated} is never {@code true} unless either {@code shutdown} or {@code shutdownNow} was called first.
		System.out.println("isTerminated() on executor3 after shutdown(): " + executor3.isTerminated()); // can be false too

		// Waited get - throws TimeoutException if task is not completed within specified time limit. Otherwise, it behaves like normal get() and returns result.
		ExecutorService executor4 = Executors.newSingleThreadExecutor();
		Future<?> future4 = executor4.submit(() -> {
			System.out.println("Waiting for 2sec in executor4");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Hello from executor4");
		});
		try {
			future4.get(1, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			System.out.println("Exception: Future4 waited for 1 sec, but task is not completed, so TimeoutException is thrown");
		}
		executor4.shutdown();
		try {
			while(!executor4.awaitTermination(200, TimeUnit.MILLISECONDS)) {
				System.out.println("Waiting for executor4 to terminate...");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("-----------------------------");

		// invokeAll()
		// -----------
		// Executes the given tasks, returning a list of Futures holding their status and results when all complete. 
		// It is a blocking method that waits for all tasks to complete. 
		// The Future objects returned by this method will have their isDone() method return true when the task is completed.
		// Note that a completed task could have terminated either normally or by throwing an exception. 
		Callable<Integer> task1 = () -> {
			System.out.println("Executing task1");
			Thread.sleep(1000);
			return 1;
		};
		Callable<Integer> task2 = () -> {
			System.out.println("Executing task2");
			Thread.sleep(1000);
			return 2;
		};
		Callable<Integer> task3 = () -> {
			System.out.println("Executing task3");
			Thread.sleep(1000);
			return 3;
		};
		ExecutorService executor5 = Executors.newFixedThreadPool(2);
		List<Future<Integer>> futures = null;
		try {
			futures = executor5.invokeAll(List.of(task1, task2, task3), 1, TimeUnit.SECONDS); // blocks main thread, and waits for 1sec for all to finish.
			// For the ones which are not completed within the time limit will be cancelled, their Future.isDone() will return false, and Future.get() will throw a CancellationException.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(Future<Integer> f : futures) {
			try {
				System.out.println("Result from future: " + f.get());
			} catch (ExecutionException | InterruptedException | CancellationException e) {
				System.out.println("Exception: " + e.getClass());
			}
		}
		
		executor5.shutdown();
		System.out.println("Message from main after invokeAll()");
		System.out.println("------------------------------");

		// invokeAny()
		// -----------
		ExecutorService executor6 = Executors.newFixedThreadPool(3);
		Integer i = null;
		try {
			i = executor6.invokeAny(List.of(task1, task2, task3)); // blocks main; all tasks will be executed, but it will return the RESULT of the first completed task. The other tasks will be cancelled.
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("Result from invokeAny: " + i);
		executor6.shutdown();
		System.out.println("------------------------------");

		// PTR: future.isDone() is a non-blocking call. So, executing it before future.get() may result in false. Since get() is a blocking call, it will wait for the task to complete, and after that future.isDone() will return true.

		// cancel()
		// --------
		ExecutorService executor7 = Executors.newSingleThreadExecutor();
		Future<Integer> future7 = executor7.submit(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("Executor7 interrupted while sleeping");
				Thread.currentThread().interrupt();
			}
			System.out.println("Is executor7 thread interrupted? " + Thread.currentThread().isInterrupted());
			return 42;
		});
		// Let's assure the above task is in running state, coz there are high chances that thread won't be running immediately when future7.cancel(true) is getting executed.
		try {Thread.sleep(1000);} catch (InterruptedException e) {}

		// toggle between below two cancel() to see different behavior
		// future7.cancel(true); // true implies that interrupt the thread be it in running state or not. In this case, isCancelled = isDone = true.
		future7.cancel(false); // false implies that don't interrupt the thread if it's already running. In this case, isCancelled = isDone = true but the task will still run in the background and print 42 after 2 seconds.
		System.out.println("Future7 isCancelled: " + future7.isCancelled()); // true
		System.out.println("Future7 isDone: " + future7.isDone()); // true // isDone() returns true if task is completed, which includes normal completion, exception, or cancellation.
		executor7.shutdown();
	}
}

/**
 * Output:
 * -------
 * hello from runnable function
 * returned value from Future<?>: null
 * Future sout works in isDone() after executing get()
 * ------------------------
 * Future result from callable:Hello Callable
 * Future isDone after get()
 * ---------------------------
 * Hello from runnable with T result
 * Future result from runnable upon success/completion: SuccessMsg
 * Future isDone after get()
 * isShutdown() on executor3 after shutdown(): true
 * isTerminated() on executor3 after shutdown(): true
 * Waiting for 2sec in executor4
 * Exception: Future4 waited for 1 sec, but task is not completed, so TimeoutException is thrown
 * Waiting for executor4 to terminate...
 * Waiting for executor4 to terminate...
 * Waiting for executor4 to terminate...
 * Waiting for executor4 to terminate...
 * Hello from executor4
 * -----------------------------
 * Executing task1
 * Executing task2
 * Exception: class java.util.concurrent.CancellationException
 * Exception: class java.util.concurrent.CancellationException
 * Exception: class java.util.concurrent.CancellationException
 * Message from main after invokeAll()
 * ------------------------------
 * Executing task1
 * Executing task2
 * Executing task3
 * Result from invokeAny: 2
 * ------------------------------
 * Future7 isCancelled: true
 * Future7 isDone: true
 * Is executor7 thread interrupted? false
 * 
 */