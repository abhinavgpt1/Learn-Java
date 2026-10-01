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
         * Definition:
         * Runtime polymorphism (also called dynamic polymorphism or late binding) is a
         * process where the exact method to execute is determined while the program is
         * running rather than at compile time.
         * - Runtime polymorphism = Overriding + Upcasting
         * 
		 * Upcasting: Converting a subclass reference to a superclass reference thus treating
		 * the object as an instance of the superclass; it is implicit and generally
         * safe.
         * 
         * Method Overriding: Happens when a subclass provides its own implementation of
         * an inherited method with the same signature and a same or covariant return type,
         * enabling runtime polymorphism through dynamic method dispatch.
         * - This allows the subclass to provide its own behavior for the method while
         * still maintaining the same interface as the superclass.
         * 
         * Method Signature: The combination of method name and parameter list. It
         * doesn't include the return type or access modifiers. eg. for method "public
         * void func(int a, String b)", the signature is "func(int, String)".
         * 
         * Dynamic Method Dispatch: The mechanism by which Java determines at runtime
         * which overridden method implementation to execute based on actual object type, not the reference type.
         * 
         * Object Slicing (in C++): A situation in object-oriented programming where a subclass
         * object is assigned to a superclass reference, causing the loss of
         * subclass-specific attributes and methods, leading to potential limitations in
         * accessing the full functionality of the subclass. Java equivalent is upcasting.
         */
    }
}
