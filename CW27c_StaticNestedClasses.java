// https://stackoverflow.com/questions/44036955/java-example-of-a-static-nested-class-accessing-an-instance-variable-through-an
class OC {
    int a = 1;
    static int b = 2;
    private int c = 3;

    static class SNC {
        int i = 0;

        void access() {

            // System.out.println(a);//Error = Cannot make a static reference to the non-static field
            System.out.println(b);
            // System.out.println(c);
        }
        void indirectAccess(OC obj) {

            System.out.println(obj.a);
            System.out.println(obj.c);
        }
    }
}
// driver class = class containing main()

class DriverClass {
    public static void main(String args[]) {

        OC obj = new OC();
        OC.SNC objSNC = new OC.SNC();
        objSNC.access();
        objSNC.indirectAccess(obj);
    }
}