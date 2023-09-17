class Base {
    private int qty = 10, prc = 5, tot;

    void calc() {
        tot = qty * prc;
        System.out.println("Total price in Base class = " + tot);
    }

    Base() {
        System.out.println("Before Base class calc");
        calc();
        System.out.println("After Base class calc");
    }
}

class Derived extends Base {
    int p = 5, q = 6, t;

    Derived() {
        calc();
    }

    void calc() {
        t = q * p;
        System.out.println("Total price in Derived class = " + t);
    }
}

class PolymorphicConstructor {

    public static void main(String args[]) {
        Derived d = new Derived();
    }
}