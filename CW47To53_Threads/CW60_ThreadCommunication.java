class SharedResource {
	int data;
	boolean hasData = false;
	public synchronized void produce(int value) {
		// no if block, no return; Use while loop to recheck condition after being notified.
		while(hasData) {
			try {
				// gfg says that a notify() can be added here as well so as to wake up waiting consumer (if any).
				wait(); // no more production until data is consumed
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		data = value;
		hasData = true;
		System.out.println(Thread.currentThread().getName() + ": Produced " + data);
		notify(); // notify the waiting consumer (waiting on this SharedResource object monitor)
		// This thread would be consumer only as no other producer would be waiting since synchronized is used by one producer alone.
	}
	public synchronized int consume() {
		// no if block, no return; Use while loop to recheck condition after being notified.
		while(!hasData) {
			try {
				wait(); // no more consumption until data is populated
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		hasData = false;
		System.out.println(Thread.currentThread().getName() + ": Consumed " + data);
		notify(); // notify the waiting producer (waiting on this SharedResource object monitor)
		return data;
	}
}
public class CW60_ThreadCommunication {
	
	public static void main(String[] args) {
		// notifyAll is generally used rather than notify
		// this program would work fine with notifyAll as well.
		SharedResource sr = new SharedResource();
		Runnable producerTask = new Runnable() {
			@Override
			public void run() {
				for(int i=1; i<=5; i++){
					sr.produce(i);
				}
			}
		};
		Runnable consumerTask = new Runnable() {
			@Override
			public void run() {
				for(int i=1; i<=5; i++){
					sr.consume();
				}
			}
		};
		Thread producer = new Thread(producerTask, "Producer");
		Thread consumer = new Thread(consumerTask, "Consumer");

		producer.start();
		consumer.start();

		// For multiple producer and consumer, use a queue in SharedResource.
		// If in this example, I have 2 producers and 1 consumer, then "data" gets overriden and also, a producer goes into infinite wait state waiting for some magical consumer to consume. Here, consumer runs 5 times but producers would tentatively run 10 times.

		// PTR: put wait() in while loop to recheck condition after being notified. Putting in if block is wrong as after being notified, thread continues from wait() and assumes condition is met.

		// Good read on producer-consumer - https://www.baeldung.com/java-producer-consumer-problem
		// The program differs by multiple producers/consumers, use of queue in shared resource and 2 Objects for locking producer and consumer separately (which I think is unnecessary) and a lot of functions for concurrency control.

		// Java provides a BlockingQueue interface that is thread-safe. In other words, multiple threads can add and remove messages from this queue without any concurrency issues.
		// Its put() method blocks the calling thread if the queue is full. Similarly, if the queue is empty, its take() method blocks the calling thread.
	}
}

/**
 * Output:
 * -------
 * Producer: Produced 1
 * Consumer: Consumed 1
 * Producer: Produced 2
 * Consumer: Consumed 2
 * Producer: Produced 3
 * Consumer: Consumed 3
 * Producer: Produced 4
 * Consumer: Consumed 4
 * Producer: Produced 5
 * Consumer: Consumed 5
 * 
 * [Done] exited with code=0 in 2.323 seconds i.e. program terminates normally.
 */