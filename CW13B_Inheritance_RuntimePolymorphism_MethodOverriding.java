class RECT {
    void area() {
        System.out.println("Le Rect");
    }
};

class Circle extends RECT {
    void area() {
        System.out.println("Le Circle");
    }

    void Rarea() {
        area();
        super.area();
    }
};

class CW13B_Inheritance_RuntimePolymorphism_MethodOverriding {
    public static void main(String[] args) {
        Circle obj = new Circle();
        obj.area(); // Le Circle // no runtime polymorphism because compiler see Circle ref at compile-time and at runtime.
        obj.Rarea(); // Le Circle
                     // Le Rect

        RECT rectRef = new Circle(); // upcasting
        rectRef.area(); // runtime polymorphism happens because compiler see RECT ref at compile-time
                        // and at runtime the actual object is Circle, so Java invokes Circle.area() =>
                        // dynamic method dispatch
        // rectRef.Rarea(); // compile-time error because RECT class doesn't have
        // Rarea() method due to Object slicing / Upcasting.

        /**
         * Definitions:
         * Runtime Polymorphism: A form of polymorphism where the method to be invoked
         * is determined at runtime rather than at compile time. It is also known as
         * dynamic polymorphism or late binding.
         * - Runtime polymorphism = Overriding + Upcasting
         * 
         * Upcasting: Converting a subclass reference to a superclass reference,
         * allowing the subclass object to be treated as an instance of its superclass.
         * It is implicit and generally safe.
         * 
         * Method Overriding: A runtime polymorphism mechanism where a subclass
         * redefines an inherited method with the same signature and a same or covariant
         * return type, allowing the JVM to determine the method implementation at
         * runtime through dynamic method dispatch.
         * - This allows the subclass to provide its own behavior for the method while
         * still maintaining the same interface as the superclass.
         * 
         * Method Signature: The combination of method name and parameter list. It
         * doesn't include the return type or access modifiers. eg. for method "public
         * void func(int a, String b)", the signature is "func(int, String)".
         * 
         * Dynamic Method Dispatch: A runtime mechanism in which the JVM selects the
         * overridden method implementation based on the actual object type, rather than
         * the reference type.
         * 
         * Object Slicing (in C++): A situation in object-oriented programming where a
         * subclass object is assigned to a superclass reference, causing the loss of
         * subclass-specific attributes and methods, leading to potential limitations in
         * accessing the full functionality of the subclass. Java equivalent is
         * upcasting.
         * 
         * Object: An instance of a class that has its own state (fields/attributes)
         * and behavior (methods/properties) and occupies memory at runtime.
         */
    }
}
