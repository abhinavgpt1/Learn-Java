class STUDENT {
	int per, tot;

	STUDENT(int per, int t) {
		this.per = per;
		tot = t;
	}
};

class CW14_equalsMethod {
	public static void main(String[] args) {
		STUDENT amn = new STUDENT(10, 20);
		STUDENT rmn = new STUDENT(10, 20);

		System.out.println(amn == rmn); // false because they are different objects in memory
		System.out.println(amn.equals(rmn)); // false because the default equals method in Object class checks for reference equality, not content equality
		String S1 = new String("BCE");
		String S2 = new String("BCE");
		System.out.println(S1 == S2); // false because they are different objects in memory
		System.out.println(S1.equals(S2)); // true because the equals method in String class is overridden to check for content equality

		// Rule: If you want to compare the content of two objects, you should override
		// the equals() method in your class. The default implementation of equals in the
		// Object class checks for reference equality.
	}
}
/**
 * Output:
 * false
 * false
 * false
 * true
 */