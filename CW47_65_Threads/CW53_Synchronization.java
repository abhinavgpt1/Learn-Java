class Printer {
	synchronized void printTable(int n) {
		for (int i = 1; i <= 5; i++) {
			System.out.println(n + "*" + i + "=" + n * i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException exp) {
				exp.printStackTrace();
			}
		}
	}
}

class User extends Thread {
	Printer printer;
	int n;
	User(Printer printer, int n) {
		this.printer = printer;
		this.n = n;
	}

	@Override
	public void run() {
		printer.printTable(n);
	}
}

public class CW53_Synchronization {
	public static void main(String[] args) {
		Printer tablePrinter = new Printer(); // shared resource
		User th1 = new User(tablePrinter, 5);
		User th2 = new User(tablePrinter, 10);
		th1.start();
		th2.start();
	}
}

/**
 * Output:
 * -------
 * 5*1=5
 * 5*2=10
 * 5*3=15
 * 5*4=20
 * 5*5=25
 * 10*1=10
 * 10*2=20
 * 10*3=30
 * 10*4=40
 * 10*5=50
 *
 * Output (without synchronized keyword):
 * --------------------------------------
 * 10*1=10
 * 5*1=5
 * 5*2=10
 * 10*2=20
 * 5*3=15
 * 10*3=30
 * 5*4=20
 * 10*4=40
 * 5*5=25
 * 10*5=50
 */