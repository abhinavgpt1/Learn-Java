public class CW29 {
	public static void main(String[] args) {
		int a[] = { 4, 8, 10 };
		int d = 2;
		System.out.println("Start");
		try {
			int val = a[d];
			System.out.println("Array value=" + val);
			int dv = val / d;
			System.out.println("div=" + dv);
			CW29 ref = null;
			ref.toString();

		} catch (ArithmeticException exp) {
			System.out.println(exp.getMessage());
		} catch (ArrayIndexOutOfBoundsException exp) {
			System.out.println("Invalid Index");
		} finally {
			System.out.println("Finally executed:urgent");
		}
		System.out.println("End");
	}
}
