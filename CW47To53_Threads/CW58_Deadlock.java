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

public class CW58_Deadlock {
	
	public static void main(String[] args) {
		Pen pen = new Pen();
		Paper paper = new Paper();
		Runnable task1 = new Runnable() {
			@Override
			public void run() {
				pen.writeWithPenAndPaper(paper);
			}
		};
		Runnable task2 = new Runnable() {
			@Override
			public void run() {
				paper.writeWithPaperAndPen(pen);
			}
		};
		Thread t1 = new Thread(task1, "Pen-person");
		Thread t2 = new Thread(task2, "Paper-person");
		t1.start();
		t2.start();

		// without deadlock prevention, output is:
		// Pen-person is using pen Pen@50aa20fd and trying to acquire paper: Paper@3cb46987
		// Paper-person is using paper Paper@3cb46987 and trying to acquire pen: Pen@50aa20fd

		// the program runs indefinitely as was stopped manually.
	}
}