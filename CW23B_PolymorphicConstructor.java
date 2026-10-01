class Base {
    private int qty = 10, prc = 5, tot;

    void calc() {
        tot = qty * prc;
        System.out.println("Total price in Base class = " + tot);
    }

    Base() {
        // -> qty and prc are initialized to 10 and 5 respectively here
        System.out.println("Inside Base class constructor");
        calc(); // 0 since Derived.calc() is called and Derived object creation is started yet + all IPV are 0 by default
        // You can't call Base.calc() from here in any way. Can make it private -> won't be overridden.
    }
}

class Derived extends Base {
    int p = 5, q = 6, t;
    
    Derived() {
        // PTR: super() is called implicitly here, so Base class constructor is executed first
        // Once done, p and q gets value and constructor execution resume
        System.out.println("Inside Derived class constructor");
        calc(); // 30 since Derived.calc() is called
    }

    void calc() {
        t = q * p;
        System.out.println("Total price in Derived class = " + t);
    }
}

class CW23B_PolymorphicConstructor {
    public static void main(String args[]) {
        Derived d = new Derived();
    }
}

/**
 * Output:
 * -------
 * Inside Base class constructor
 * Total price in Derived class = 0
 * Inside Derived class constructor
 * Total price in Derived class = 30
 */