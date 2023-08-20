class OC {
    int a = 1;
    static int b = 2;
    private int c = 3;

    class IC {
        int i = 0;

        void access() {
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
        }
    }
}
// driver class = class containing main()

class DriverClass {
    public static void main(String args[]) {
        OC objOC = new OC();
        OC.IC objIC = objOC.new IC();
        objIC.access();
        // objOC.i = 90; //not possible even if IC class and i are public
    }
}