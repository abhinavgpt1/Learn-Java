class OC {
    int oc_x = 10;
    static int oc_static_y = 10;
    static int oc_static_counter;

    void func() {
        int method1_x = 10;
        class LIC {
            LIC() {
                oc_x = 11;
                oc_static_y = 11;
                oc_static_counter++;
                System.out.println("Inside LIC constructor in func(), oc_static_counter: " + oc_static_counter);
            }

            void access() {
                System.out.println("Inside LIC access() in func()");
                // method1_x = 90; // error: local variable defined (method1_x) must be final or effectively final
                System.out.println("\toc_x: " + oc_x + ", oc_static_y: " + oc_static_y + ", method1_x: " + method1_x + ", oc_static_counter: " + oc_static_counter);
            }
        }
        LIC lic = new LIC();
        lic.access();
        lic = new LIC();
        lic.access();
    }

    static void staticFunc() {
        class LIC {
            LIC() {
                // oc_x = 12; // error: cannot make a static reference to the non-static field
                oc_static_y = 12;
                oc_static_counter++;
                System.out.println("Inside LIC constructor in staticFunc(), oc_static_counter: " + oc_static_counter);
            }

            void access() {
                System.out.println("Inside LIC access() in staticFunc()");
                System.out.println("\toc_static_y: " + oc_static_y + ", oc_static_counter: " + oc_static_counter);
            }
        }
        LIC lic = new LIC();
        lic.access();
        lic = new LIC();
        lic.access();
    }
}

public class CW27G_LocalInnerClass {
    public static void main(String[] args) {
        // ref: https://www.geeksforgeeks.org/local-inner-class-java/
        OC oc = new OC();
        System.out.println("Initially OC properties: " + "oc_x: " + oc.oc_x + ", oc_static_y: " + OC.oc_static_y + ", oc_static_counter: " + OC.oc_static_counter);

        System.out.println();
        
        oc.func();

        System.out.println();
        
        OC.staticFunc();
    }
}

/**
 * Classes formed:
 * ---------------
 * -> OC.class
 * -> OC$1LIC.class
 * -> OC$2LIC.class
 * -> CW27G_LocalInnerClass.class
 * 
 * Output:
 * -------
 * Initially OC properties: oc_x: 10, oc_static_y: 10, oc_static_counter: 0
 * 
 * Inside LIC constructor in func(), oc_static_counter: 1
 * Inside LIC access() in func()
 * 	oc_x: 11, oc_static_y: 11, method1_x: 10, oc_static_counter: 1
 * Inside LIC constructor in func(), oc_static_counter: 2
 * Inside LIC access() in func()
 * 	oc_x: 11, oc_static_y: 11, method1_x: 10, oc_static_counter: 2
 * 
 * Inside LIC constructor in staticFunc(), oc_static_counter: 3
 * Inside LIC access() in staticFunc()
 * 	oc_static_y: 12, oc_static_counter: 3
 * Inside LIC constructor in staticFunc(), oc_static_counter: 4
 * Inside LIC access() in staticFunc()
 * 	oc_static_y: 12, oc_static_counter: 4
 */