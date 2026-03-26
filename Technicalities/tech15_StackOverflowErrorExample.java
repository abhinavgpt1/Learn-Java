class A {
	B objB = new B();

	void setB(B objB) {
		this.objB = objB;
	}
}

class B {
	A objA = new A();

	void setA(A objA) {
		this.objA = objA;
	}
}

public class tech15_StackOverflowErrorExample {
	public static void main(String[] args) {
		// B d = new B(); // Exception in thread "main" java.lang.StackOverflowError
	}
}