class CLA {
	public static void main(final Integer[] ref) {
		System.out.println(ref.length);
		int i, sum = 0;
		for (i = 0; i < ref.length; i++) {
			System.out.println(ref[i]);
			// sum=sum+Integer.parseInt(ref[i]);
		}
		System.out.println("sum=" + sum + "in integer");
		// ref=new String[10];
	}

	public static void main(final String[] ref) {
		System.out.println(ref.length);
		int i, sum = 0;
		for (i = 0; i < ref.length; i++) {
			System.out.println(ref[i]);
			// sum=sum+Integer.parseInt(ref[i]);
		}
		System.out.println("sum=" + sum);
		// ref=new String[10];
		Integer ary[] = new Integer[3];
		ary[0] = new Integer(5);
		ary[1] = new Integer(10);
		ary[2] = new Integer(20);
		main(ary);
	}

}

class CLA2 {
	public static void main(final String[] ref) {
		System.out.println(ref.length);
		int i, sum = 0;
		for (i = 0; i < ref.length; i++) {
			System.out.println(ref[i]);
			sum = sum + Integer.parseInt(ref[i]);
		}
		System.out.println("sum=" + sum);
		// ref=new String[0];
	}
}
