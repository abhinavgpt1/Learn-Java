class OC {
    int a = 1;
    static int b = 2;
    private int c = 3;

    class IC {
        int i = 0;

        void access() {
            System.out.println("a: " + a);
            System.out.println("b: " + b);
            System.out.println("c: " + c);
        }
    }
}
// driver class = class containing main()

class CW27A_InnerNestedClasses {
    public static void main(String args[]) {
        OC objOC = new OC();
        OC.IC objIC = objOC.new IC();
        objIC.access();
        // objOC.i = 90; //not possible even if IC class and i are public
    }
}

/**
 * Output:
 * -------
 * a: 1
 * b: 2
 * c: 3
 */