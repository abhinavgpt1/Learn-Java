interface A {
    int x = 10;
    void fun1();

    interface B {
        int y = 20;
        void fun2();
    }

    class C {
        int z = 30;

        void fun3() {
            System.out.println("z: " + z);
        }
    }
}

public class CW27C_NestedInterface {
    public static void main(String[] args) {
        A objA = new A() {
            public void fun1() {
                System.out.println("x: " + x);
            }
        };
        System.out.println("A.x: " + A.x);
        System.out.print("A's object: objA.fun1(): ");
        objA.fun1();
        System.out.println();

        A.B objB = new A.B() {
            public void fun2() {
                System.out.println("A.B's object: obj.y: " + y); // Note: y is accessible directly here.
            }
        };
        System.out.println("A.B.y: " + A.B.y);
        System.out.print("A.B's object: objB.fun2(): ");
        objB.fun2();
        System.out.println();

        A.C objC = new A.C();
        System.out.println("A.C's object: objC.z: " + objC.z);
        System.out.print("A.C's object: objC.fun3(): ");
        objC.fun3();
    }
}

/**
 * Output:
 * -------
 * A.x: 10
 * A's object: objA.fun1(): x: 10
 * 
 * A.B.y: 20
 * A.B's object: objB.fun2(): A.B's object: obj.y: 20
 * 
 * A.C's object: objC.z: 30
 * A.C's object: objC.fun3(): z: 30
 */