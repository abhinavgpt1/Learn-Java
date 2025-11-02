// Fyi, find theory, resources and FAQs in CW51_Synchronization_LockInstance.java
class Counter {
    static int count = 0;

    static void increment() {
        synchronized (Counter.class) { // without this, ans != 6000 (99% times)
            count++;
        }
    }

    static int get() { // read-only operation, hence no synchronization needed
        return count;
    }
}

public class CW52_Synchronization_LockClass {
    public static void main(String[] args) {

        Counter counter1 = new Counter();
        Counter counter2 = new Counter();
        Runnable taskA = () -> {
            for (int i = 1; i <= 1000; i++) {
                // -------Assume some task of counter1 object is happening here-------
                // After which we want to do some class specific work.
                Counter.increment();
            }
        };
        Thread classAThread1 = new Thread(taskA);
        Thread classAThread2 = new Thread(taskA);

        Runnable taskB = () -> {
            for (int i = 1; i <= 2000; i++) {
                // -------Assume some task of counter2 object is happening here-------
                // After which we want to do some class specific work.
                Counter.increment();
            }
        };
        Thread classBThread1 = new Thread(taskB);
        Thread classBThread2 = new Thread(taskB);

        // Goal: Since Counter has class property "count" accessible by all threads, we want synchronized behavior for all 4 threads. The lock is no more per instance, but per class.
        classAThread1.start();
        classAThread2.start();
        classBThread1.start();
        classBThread2.start();

        try {
            classAThread1.join();
            classAThread2.join();
            classBThread1.join();
            classBThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + Counter.get()); // expect 6000 -> getting 5962 if not synchronized
    }
}
