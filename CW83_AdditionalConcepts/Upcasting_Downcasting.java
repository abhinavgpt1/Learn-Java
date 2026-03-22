public class Upcasting_Downcasting {
    public static void main(String[] args) {
        /* Upcasting: Assigning a derived class object to a base class reference */
        // Base b = new Derived();

        /* Downcasting: Assigning a base class object to a derived class reference */
        // Derived d = (Derived) b;

        // Note: Downcasting is explicitly done, whereas Upcasting is implicit (or can be done explicitly eg. Base a1 = (Base) new Derived(); Base a2 = (Base) b;)

        // Why Object Slicing doesn't occur in Java
        // ----------------------------------------
        // - In languages with value semantics for objects (like C++), "object slicing"
        // refers to the loss of information when an object of a derived class is
        // assigned to an instance of a base class.
        // - The base class object has a fixed,
        // smaller memory footprint, so the extra fields and methods specific to the
        // derived class are "sliced" away.
        // In Java:
        // - Reference Semantics: When you assign a derived class object to a base class
        // variable (an upcast), you are simply copying the reference to the original,
        // complete derived class object. The actual object in memory remains a full
        // instance of the derived class.
        // - Polymorphism: Because the object's original, actual type is PRESERVED at
        // runtime, all methods (which are virtual by default in Java) will use the
        // derived class's implementation (if overridden), not the base class's.

        class Base {
            int foo;

            void display() {
                System.out.println("I am Base, foo: " + foo);
            }
        }

        class Derived extends Base {
            int bar;

            void display() {
                System.out.println("I am Derived, foo: " + foo + ", bar: " + bar);
            }
        }

        Derived b = new Derived();
        b.foo = 1;
        b.bar = 2;

        Base a = b; // Upcasting: 'a' now holds a reference to the 'b' object

        a.display(); // Calls Derived's display() method due to polymorphism
        // System.out.println(a.bar); // Compilation error: 'bar' is not defined in Base class
    }
}

/**
 * Output:
 * -------
 * I am Derived, foo: 1, bar: 2
 */