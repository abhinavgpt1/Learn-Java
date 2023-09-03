//ref: https://www.geeksforgeeks.org/local-inner-class-java/
class OC {
    int oc_x = 10;
    static int oc_y = 10;
    static int num_invocations;

    void method1() {
        int method1_x = 10;
        class LIC {
            LIC() {
                System.out.println("Inside constructor in method1");
                oc_x = 11;
                oc_y = 11;
                num_invocations++;
            }
            void access() {
                // method1_x = 90; //error: local variable defined (method1_x) must be final or effectively final
                System.out.println("oc_x: " + oc_x + ", oc_y: " + oc_y + ", method1_x: " + method1_x);
            }
        }
        // no invocation to access()
    }

    void method2() {
        class LIC {
            LIC() {
                System.out.println("Inside constructor in method2");
                oc_x = 12;
                oc_y = 12;
                num_invocations++;
            }
            void access() {
                System.out.println("oc_x: " + oc_x + ", oc_y: " + oc_y);
            }
        }
        LIC lic = new LIC();
        lic.access();
        lic = new LIC();
        lic.access();
    }
    
    static void method3() {
        class LIC {
            LIC() {
                System.out.println("Inside constructor in method3");
                // oc_x = 13; // error: static reference to non-static field
                // oc_y = 13; // error: static reference to non-static field
                num_invocations++;
            }

            void access() {
                // System.out.println("oc_x: " + oc_x); // error: static reference to non-static field
                System.out.println("oc_x: " + oc_y);
            }
        }
        // no invocation to access()
    }

    void printWithDrawline() {
        System.out.println("oc_x: " + oc_x + ", oc_y: " + oc_y + ", num_invocations: " + num_invocations);
        System.out.println("---------------------------------------");
    }
}

public class tech7_LocalInnerClass {
    public static void main(String[] args) {
        OC oc = new OC();
        System.out.print("Before: ");
        oc.printWithDrawline();        
        
        System.out.print("At method 1 call, nothing changes: ");
        oc.method1();
        oc.printWithDrawline();        
        
        System.out.println("At method 2 call, changes: ");
        oc.method2();
        oc.printWithDrawline();
        
        System.out.print("At method 3 call, nothing changes: ");
        OC.method3();
        oc.printWithDrawline();        
    }

    /**
     * Classes Formed:
     *  -> OC.class
     *  -> OC$1LIC.class
     *  -> OC$2LIC.class
     *  -> OC$3LIC.class
     *  -> tech7_LocalInnerClass.class
     * Output:
     * =======
     * Before: oc_x: 10, oc_y: 10, num_invocations: 0
     * -------------------
     * At method 1 call, nothing changes: oc_x: 10, oc_y: 10, num_invocations: 0
     * -------------------
     * At method 2 call, changes:
     * Inside constructor in method2
     * oc_x: 12, oc_y: 12
     * Inside constructor in method2
     * oc_x: 12, oc_y: 12
     * oc_x: 12, oc_y: 12, num_invocations: 2
     * -------------------
     * At method 3 call, nothing changes: oc_x: 12, oc_y: 12, num_invocations: 2
     * -------------------
     */
}