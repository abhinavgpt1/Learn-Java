class time {
	int hr, min;

	time(int h, int m) {
		hr = h;
		min = m;
	}

	time diff(time t) {
		time d = new time(0, 0);
		int x = Math.abs((hr * 60 + min) - (t.hr * 60 + t.min));

		d.hr = x / 60;
		d.min = x % 60;
		return d;
	}

	void show() {
		System.out.println(hr + " " + min);
	}
};

public class hw3 {
	public static void main(String[] args) {
		time t1 = new time(1, 2);
		time t2 = new time(3, 4);

		time d = t1.diff(t2);
		d.show();

	}
}
