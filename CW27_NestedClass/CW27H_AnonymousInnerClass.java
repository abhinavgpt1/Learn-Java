abstract class Base {
    int base_x = 10;

    abstract void baseAbstractMethod();

    void baseConcreteMethod() {
        System.out.println("Inside Base concrete method");
    }

    void baseHelperMethodToCallAICNoRefOutsideMethod() {
        System.out.println("Inside Base baseHelperMethodToCallAICNoRefOutsideMethod method");
    }
}

public class CW27H_AnonymousInnerClass {
    public static void main(String[] args) {
        // ref: https://www.geeksforgeeks.org/anonymous-inner-class-java/

        // AIC extends/implements atmost one class/interface. Here, it's Base. No inheritance allowed.
        Base bref = new Base() {
            int aic_x_noRefOutside = 10; // Note: due to upcasting (NOT object slicing, check CW83_AdditionalConcepts) this won't be accessible by bref
            static int aic_static_x_noRefOutside = 10; // warning: due to object slicing this won't be referred outside class

            void baseAbstractMethod() {
                System.out.println("baseAbstractMethod defintion in AIC");
            }

            @Override // optional, but good to have
            void baseConcreteMethod() {
                System.out.println("baseConcreteMethod overrided in AIC");
            }

            // no restriction on static initializer/block or interface declaration, said otherwise by docs
            interface AICInterface_NoRefOutside {
            }

            class AIC_IC implements AICInterface_NoRefOutside {
            }

            static {
                aic_static_x_noRefOutside = 90;
                System.out.println("Modified aic_static_x_noRefOutside in static block: " + aic_static_x_noRefOutside);
            }

            void aicFunc_NoRefOutside() {
                System.out.println("aic_x_noRefOutside: " + aic_x_noRefOutside);
                System.out.println("aic_static_x_noRefOutside: " + aic_static_x_noRefOutside);
            }

            // overriding and using it to access AIC upcasted members
            void baseHelperMethodToCallAICNoRefOutsideMethod() {
                System.out.println("------------- inside baseHelperMethodToCallAICNoRefOutsideMethod -------------");
                aicFunc_NoRefOutside();
            }
        };
        
        System.out.println();
        
        // System.out.println(bref.aic_x_noRefOutside); // error: cannot find symbol
        // System.out.println(bref.aic_static_x_noRefOutside); // error: cannot find symbol
        bref.baseAbstractMethod();
        
        System.out.println();
        
        bref.baseConcreteMethod();
        
        System.out.println();
        
        // bref.aicFunc_NoRefOutside(); // error: cannot find symbol
        bref.baseHelperMethodToCallAICNoRefOutsideMethod();

        // Notice:
        // Name of class formed in LIC and AIC differs as:
        //  * <OuterClass>$1<LIC name> -> appending LIC name
        //  * <OuterClass>$1           -> nothing after $1 for AIC since its anonymous
        // Inner class/interface forms for AIC, but we can't use them outside.
    }
}

/**
 * Classes formed:
 * ---------------
 * -> Base.class
 * -> CW27H_AnonymousInnerClass.class
 * -> CW27H_AnonymousInnerClass$1.class
 * -> CW27H_AnonymousInnerClass$1$AIC_IC.class
 * -> CW27H_AnonymousInnerClass$1$AICInterface_NoRefOutside.class
 * 
 * Output:
 * -------
 * Modified aic_static_x_noRefOutside in static block: 90
 * 
 * baseAbstractMethod defintion in AIC
 * 
 * baseConcreteMethod overrided in AIC
 * 
 * ------------- inside baseHelperMethodToCallAICNoRefOutsideMethod -------------
 * aic_x_noRefOutside: 10
 * aic_static_x_noRefOutside: 90
 */