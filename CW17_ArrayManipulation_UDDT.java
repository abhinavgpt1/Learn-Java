class Employee {
	int BS, DA, GS;

	void setBS(int b) {
		BS = b;
		DA = BS * 50 / 100;
		GS = BS + DA;
	}

	void show() {
		System.out.println(BS + " " + DA + " " + GS);
	}
};

class CW17_ArrayManipulation_UDDT {
	public static void main(String[] ary) {
		System.out.println(ary.length); // 0
		int i;
		Employee[] E = new Employee[3];
		for (i = 0; i < E.length; i++) {
			E[i] = new Employee();
			E[i].setBS(10000 + i * 5000);
		}
		showAll(E);
	}

	static void showAll(Employee[] ary) {
		for (int i = 0; i < ary.length; i++) {
			ary[i].show();
		}
	}
}

/**
 * Output:
 * 0
 * 10000 5000 15000
 * 15000 7500 22500
 * 20000 10000 30000
 */