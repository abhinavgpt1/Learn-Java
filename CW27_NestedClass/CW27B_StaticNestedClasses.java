class OC {
    int a = 1;
    static int b = 2;
    private int c = 3;

    static class SNC {
        int i = 0;

        void access() {
            // System.out.println(a); // error: cannot make a static reference to the non-static field
            System.out.println("b: " + b);
            // System.out.println(c); // error: cannot make a static reference to the non-static field
        }

        // Recall: https://stackoverflow.com/questions/44036955/java-example-of-a-static-nested-class-accessing-an-instance-variable-through-an
        void indirectAccess(OC obj) {
            System.out.println("a: " + obj.a);
            System.out.println("c: " + obj.c);
        }
    }
}

class CW27B_StaticNestedClasses {
    public static void main(String args[]) {
        // FYI, Driver class = class containing main()
        OC obj = new OC();
        OC.SNC objSNC = new OC.SNC();
        objSNC.access();
        objSNC.indirectAccess(obj);
    }
}

/**
 * Output:
 * -------
 * b: 2
 * a: 1
 * c: 3
 */