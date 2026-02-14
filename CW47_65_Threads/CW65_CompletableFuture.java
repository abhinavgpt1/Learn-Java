import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CW65_CompletableFuture {
    public static void main(String[] args) {
        // Introduced in java 8 do to asynchronous programming.
        // Asynchronous programming allows tasks to execute independently of one another, enabling concurrent execution and improved performance.

        // Non-blocking execution = async execution. Line by line = synchronous execution.
        // Multithreading is one way to achieve async programming, but it can be complex and error-prone.

        // CompletableFuture is a powerful class in Java that represents a future result of an asynchronous computation.
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ok";
        });
        // Supplier in cf1 is a daemon thread. So to get the result, use get()
        try {
            System.out.println("cf1 result: " + cf1.get());
            // cf.getNow("default value"); // getNow() returns the result if available, otherwise returns the default value provided as an argument. PTR: throws no exception since no waiting happens, and the default value is returned immediately if the result is not ready.
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Main after cf1");

        System.out.println("------------------");

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ok";
        }).thenApply(x->x+x); // .get() can be used here as well. This was things won't be async since you're waiting for the result immediately.
        
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ok";
        }).thenApply(result -> result.toUpperCase());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(cf2, cf3); // Returns a new CompletableFuture that is completed when all of the given CompletableFutures complete. PTR: it doesn't return the results of the individual futures, just a future that completes when all are done.
        try {
            allOf.get(); // Wait for all futures to complete
            // allOf.join(); // Wait for all futures to complete without throwing checked exceptions
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // To get the results of individual futures, you can use cf2.get() and cf3.get() after allOf.get() to ensure they have completed.
        try {
            System.out.println("cf2 result: " + cf2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("cf3 result: " + cf3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("-------------------");

        CompletableFuture<String> cf4 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ok";
        }).orTimeout(1, TimeUnit.SECONDS).exceptionally(e -> "Timeout occurred"); // e = Throwable

        try {
            System.out.println("cf4 result: " + cf4.get()); // printed "Timeout occurred"
            // timeout exception comes after 1 sec. To handle this either catch TimeoutException or handle in cf4 definition.
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // PTR: CompletableFuture runs on daemon threads by default since it uses ForkJoinPool.commonPool() which uses daemon threads. 
        // We can control the thread type by providing a custom exector service. CF task in itself doesn't imply daemon or non-daemon thread, it's the executor that determines that. If you want to use non-daemon threads, you can create a custom ExecutorService with non-daemon threads and pass it to the CompletableFuture methods.

        System.out.println("--------------------");

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // now the threads from above executor service will be used for the CompletableFuture tasks. The value needs to retrieved using get(), but the main thread will wait for cf5 to end this time since it's a non-daemon thread.
        CompletableFuture<String> cf5 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ok";
        }, executorService).thenApply(x -> x + x + x)
          .thenApply(x -> x.toUpperCase())
          .orTimeout(2, TimeUnit.SECONDS)
          .exceptionally(e -> "Timeout occurred");
        cf5.join();
        System.out.println(cf5.getNow("def value not printing since join() called already"));
        System.out.println("Main msg at last");
        
        executorService.shutdown(); // IMP else program needs to manually shutdown.
    }
}

/**
 * Output:
 * -------
 * cf1 result: ok
 * Main after cf1
 * ------------------
 * cf2 result: okok
 * cf3 result: OK
 * -------------------
 * cf4 result: Timeout occurred
 * --------------------
 * OKOKOK
 * Main msg at last
 */