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
        obj.area();
        obj.Rarea();

        /**
         * Definition:
         * Method Overriding: Happens when a subclass provides its own implementation of an
         * inherited method with the same signature and a same or covariant return type,
         * enabling runtime polymorphism through dynamic method dispatch.
         * 
         * - Add-on:- The method in the subclass must have the same name, return type
         * (or covariant return type), and parameters as the method in the superclass. 
         * This allows the subclass to provide its own behavior for the method while 
         * still maintaining the same interface as the superclass.
         * 
         * Method Signature: The combination of method name and parameter list. It
         * doesn't include the return type or access modifiers. eg. for method "public
         * void func(int a, String b)", the signature is "func(int, String)".
         * 
         * Dynamic Method Dispatch: The mechanism by which Java determines at runtime
         * which overridden method implementation to execute based on the actual object
         * type, not the reference type.
         */
    }
}
