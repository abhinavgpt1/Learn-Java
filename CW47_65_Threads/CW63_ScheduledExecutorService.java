import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CW63_ScheduledExecutorService {

	public static void main(String[] args) {
		// Use ScheduledExecutorService when you want to schedule a task to run after a delay, or to execute periodically.
		System.out.println(Instant.now() + " starting the program");
		ScheduledExecutorService schedule1 = Executors.newScheduledThreadPool(1);
		// schedule() has 2 options - Runnable or Callable, and delay with TimeUnit
		schedule1.schedule(
				() -> System.out.println(Instant.now() + " Task1 msg after 2 sec delay from start"),
				2,
				TimeUnit.SECONDS);
		schedule1.shutdown();
		// PTR: schedule() queues immediately, so schedule1 will wait for it finish/shutdown.

		System.out.println(Instant.now() + " message from main");

		ScheduledExecutorService schedule2 = Executors.newScheduledThreadPool(1);
		// scheduleAtFixedRate() - runs the task at a fixed rate, with a specified initial delay and period between executions.
		// PTR: individual task may take more time than periodici intervals, scheduler will queue next tasks after fixed time.
		schedule2.scheduleAtFixedRate(
				() -> System.out.println(Instant.now() + " Fixed rate says hello starting after 1 sec init delay running at fixed rate of 3 sec"),
				1,
				3,
				TimeUnit.SECONDS);
		// schedule2.shutdown(); 
		// PTR: since there's a init delay, the task might now even queue, and the task can get shutdown before execution. Since it runs every 3sec, it'll be running indefinitely if not shutdown. Hence, a proper handling is required.

		// Sol: schedule shutdown manually
		schedule2.schedule(() -> {
			System.out.println(Instant.now() + " shutting down schedule2");
			schedule2.shutdown();
		}, 9, TimeUnit.SECONDS); 
		// 9 sec delay allows 3 executions (+1, +4, +7 sec w.r.t schedule shutdown)
		// 10 sec delay allows 4 executions (+1, +4, +7, +10 sec w.r.t schedule shutdown)

		// scheduleWithFixedDelay() - runs the task with a fixed delay between the end of one execution and the start of the next, with a specified initial delay.
		// PTR: scheduler will run task with a fixed delay once the previous task finishes. No overlapping of tasks unlike scheduleAtFixedRate().
		 ScheduledExecutorService schedule3 = Executors.newScheduledThreadPool(1);
		 schedule3.scheduleWithFixedDelay(
				() -> System.out.println(Instant.now() + " Delay thread says hello starting after 1 sec init delay running with fixed delay of 3 sec"),
				1,
				3,
				TimeUnit.SECONDS);
		 schedule3.schedule(() -> {
			 System.out.println(Instant.now() + " shutting down schedule3");
			 schedule3.shutdown();
		 }, 9, TimeUnit.SECONDS); 
		 // 9 sec delay allows 3 executions (+1, +4, +7 sec w.r.t schedule shutdown)

		 // PTR: schedule(), scheduleAtFixedRate(), and scheduleWithFixedDelay() all return a ScheduledFuture, which can be used to cancel the task or check its status. 
		 // However, for periodic tasks, the ScheduledFuture will not complete until the task is cancelled or the executor is shut down.
	}
}

/**
 * Output:
 * -------
 * 2026-02-14T14:22:38.577175500Z starting the program
 * 2026-02-14T14:22:38.626044200Z message from main
 * 2026-02-14T14:22:39.629773400Z Fixed rate says hello starting after 1 sec init delay running at fixed rate of 3 sec
 * 2026-02-14T14:22:39.635749800Z Delay thread says hello starting after 1 sec init delay running with fixed delay of 3 sec
 * 2026-02-14T14:22:40.627504400Z Task1 msg after 2 sec delay from start
 * 2026-02-14T14:22:42.630174500Z Fixed rate says hello starting after 1 sec init delay running at fixed rate of 3 sec
 * 2026-02-14T14:22:42.637029300Z Delay thread says hello starting after 1 sec init delay running with fixed delay of 3 sec
 * 2026-02-14T14:22:45.628679800Z Fixed rate says hello starting after 1 sec init delay running at fixed rate of 3 sec
 * 2026-02-14T14:22:45.638646700Z Delay thread says hello starting after 1 sec init delay running with fixed delay of 3 sec
 * 2026-02-14T14:22:47.634764300Z shutting down schedule2
 * 2026-02-14T14:22:47.638610200Z shutting down schedule3
 */