class Pen {
	public synchronized void writeWithPenAndPaper(Paper p){ // locks Pen object
		System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and trying to acquire paper: " + p);
		p.finishWriting(); // locks Paper object
	}
	public synchronized void finishWriting(){
		System.out.println(Thread.currentThread().getName() + " has finished writing on " + this);
	}
}

class Paper {
	public synchronized void writeWithPaperAndPen(Pen p) {
		System.out.println(Thread.currentThread().getName() + " is using paper " + this + " and trying to acquire pen: " + p);
		p.finishWriting();
	}
	public synchronized void finishWriting() {
		System.out.println(Thread.currentThread().getName() + " has finished writing on " + this);
	}
}

public class CW59_DeadlockResolution {
	
	public static void main(String[] args) {
		Pen pen = new Pen();
		Paper paper = new Paper();
		Runnable task1 = new Runnable() {
			@Override
			public void run() {
				synchronized(paper) { // IMP: ensuring pen has acquired paper before running/locking itself.
					pen.writeWithPenAndPaper(paper);
				}
			}
		};
		Runnable task2 = new Runnable() {
			@Override
			public void run() {
				// synchronized(pen) { // It's correct to write this, but Lock Ordering strategy says that locks should be acquired in same order to avoid any cyclic dependency in lock acquisition i.e. (here) paper then pen. Hence we should comment this synchronized block.
					paper.writeWithPaperAndPen(pen);
				// }
			}
		};
		Thread t1 = new Thread(task1, "Pen-person");
		Thread t2 = new Thread(task2, "Paper-person");
		t1.start();
		t2.start();

		// There exist multiple strategies to prevent deadlock, such as:
		// 1. Lock Ordering: Always acquire locks in a predefined order.	<- used in this example
			// (GFG definition) We have to always assign a numeric value to each lock and before acquiring the lock with a higher numeric value we have to acquire the locks with a lower numeric value.
		// 2. Lock Timeout: Use tryLock() with a timeout to avoid waiting indefinitely.
		// 3. (stackoverflow) use interruptible locks
		
		// Ways to prevent it:
		// 1. Avoid Nested Locks: This is the main reason for deadlock. Mainly happens when we give locks to multiple threads. Avoid giving lock to multiple threads if we already have given to one.
			// synchronized inside synchronized
		// 2. Avoid Unnecessary Locks: We should have lock only those members who are required. Having a lock on unnecessarily can lead to deadlock.
		// 3. Using thread join: Deadlock condition appears when one thread is waiting for the other to finish. If this condition occurs we can use Thread.join(timeunit) the with the maximum time you think the execution will take.
			// using time limit in join like t2_method { t1.join(1000); } can prevent deadlock since t2 will wait for t1 only for 1 second and then continue its execution.
		// 4. Deadlock Detection: Periodically check for deadlocks and recover.
			// https://www.geeksforgeeks.org/java/deadlock-in-java-multithreading/#:~:text=Detectecting%20Deadlocks
		// 5. Don't execute foreign code while holding a lock.

		/**
		 * Solution to Dining Philosophers Problem:
		 * Context: Each philosopher needs two forks to eat. If each philosopher picks up the left fork first and then the right fork, a deadlock can occur if all philosophers pick up their left forks simultaneously.
		 * Resource: https://www.baeldung.com/java-dining-philoshophers
		 * 
		 * The only thing causing deadlock here is the circular wait.
		 * This problem has a very simple solution to break this and make sure that circular wait doesn't occur - since all philosophers pick left fork first and right next, let the last philosopher do the opposite, and that's it!
		 * Otherwise a Lock Ordering strategy can be used here as well.
		 * tryLock(timeunit) can cause Livelock condition here, so a retry mechanism with some random delay can be used to avoid livelock. 
		 */			

		// Extra: Deadlock vs Livelock - https://www.baeldung.com/java-deadlock-livelock
		// Def: Livelock is a situation where two or more threads are actively responding to each other but are unable to make progress because they keep changing their state in response to the other threads.
		// Def: Starvation is the problem that occurs when high priority processes keep executing and low priority processes get blocked for indefinite time

		// Starvation vs Livelock - https://www.geeksforgeeks.org/operating-systems/deadlock-starvation-and-livelock/
	}
}

/**
 * Output:
 * -------
 * Pen-person is using pen Pen@50aa20fd and trying to acquire paper: Paper@5e6dc498
 * Pen-person has finished writing on Paper@5e6dc498
 * Paper-person is using paper Paper@5e6dc498 and trying to acquire pen: Pen@50aa20fd
 * Paper-person has finished writing on Pen@50aa20fd
 */

/**
 * Here are the key techniques to prevent livelock:
 * -----------------------------------------------
 * Random Backoff:
 * 	Instead of threads immediately retrying after a failed attempt, add random delays
 * 	This breaks the synchronous behavior that causes livelock
 * 
 * Priority-based Resolution:
 * 	Assign different priorities to threads
 * 	When conflict occurs, the thread with lower priority backs off
 * 
 * Resource Preemption:
 * 	Allow resources to be forcibly taken after a certain number of failed attempts
 * 	This breaks the continuous "polite" yielding pattern
 * 
 * State Monitoring:
 * 	Keep track of how many times threads have yielded
 * 	If the count exceeds a threshold, change the behavior
 * 
 * Timeout Mechanism:
 * 	Set a maximum time for resource acquisition attempts
 * 	If timeout occurs, take alternative action
 * 
 * The key principle in preventing livelock is to break the symmetry of the threads' behavior. 
 * While deadlock happens when threads are stuck waiting, livelock happens when threads are actively trying to resolve a conflict but doing so in a way that maintains the conflict.
 * By introducing randomness, priorities, or timeouts, you can break this cycle.
 * 
 * Remember: Livelock is often caused by overly cautious deadlock prevention mechanisms, so the solution is to find the right balance between cooperation and assertiveness in resource acquisition.
 */