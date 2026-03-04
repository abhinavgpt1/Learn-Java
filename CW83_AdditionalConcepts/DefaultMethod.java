/* Part 1/2 - Default Methods conflict in  multiple inheritance (interface) */
interface PaymentA {
    default void process() {
        System.out.println("PaymentA default process()");
    }
}

interface PaymentB {
    default void process() {
        System.out.println("PaymentB default process()");
    }
}

// If you remove this override, compilation fails because process() is ambiguous.
class OnlinePayment implements PaymentA, PaymentB {
    @Override
    public void process() {
        System.out.println("Resolving conflict in OnlinePayment:");
        PaymentA.super.process();
        PaymentB.super.process();
        System.out.println("Custom combined behavior in OnlinePayment");
    }
}

// Bonus point impl. 
/* Part 2/2 - Class method wins over interface default method */
class BaseProcessor {
    public void process() {
        System.out.println("BaseProcessor class method process()");
    }
}

class HybridProcessor extends BaseProcessor implements PaymentA {
    // No override needed: BaseProcessor.process() is preferred.
}

public class DefaultMethod {
    public static void main(String[] args) {
        // Default methods - The default keyword in function definition allows you to add method implementations directly in interfaces without breaking existing implementations. This was a game-changer introduced in Java 8.
        /* 
        * Why default methods were introduced:
        * - Backward compatibility: Add new methods to existing interfaces without breaking implementations
        * - Multiple inheritance of behavior (ofc not with same method name): Interfaces can now provide default behavior
        * - Evolution of APIs: Libraries like Collections framework could add new methods
        */
        
        // qq - What if a class implements multiple interfaces that have the same default method?
        // ans - The class must override this method. Inside this override, you can choose to call the default method from either interface 
        // using InterfaceName.super.methodName() syntax.
        OnlinePayment onlinePayment = new OnlinePayment();
        onlinePayment.process();
        
        // Bonus: Additionally, if a class inherits a method from a superclass and also
        // has a default method with the same signature from an interface, the class
        // method takes precedence over the default method.
        System.out.println("------------------------------------------");
        HybridProcessor hybridProcessor = new HybridProcessor();
        hybridProcessor.process(); // Calls BaseProcessor's process() method, not PaymentA's default process() method due to class method precedence and NOT method overriding.
        // PTR: It would be method overriding if HybridProcessor had its own process() method overriding BaseProcessor's method.
    }
}

/**
 * Output:
 * -------
 * Resolving conflict in OnlinePayment:
 * PaymentA default process()
 * PaymentB default process()
 * Custom combined behavior in OnlinePayment
 * ------------------------------------------
 * BaseProcessor class method process()
 */