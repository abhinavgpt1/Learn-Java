class Base{
    int bx = 5;
    static int bsx = 7;
}
class OC extends Base {
    int oc_x = 2;
    static int oc_y = 3;
    void fun(){
        System.out.println("fun inside OC");
    }
    private void privFun(){
        System.out.println("privFun inside OC");
    }
    private static void privStaticFun(){
        System.out.println("privStaticFun inside OC");
    }
    static final void drawline(){
        System.out.println("-------------------");
    }
    class IC {
        OC ref = OC.this; // this can get us all properties of OC's object from main/outside
        void testOCFieldsAccess() {
            System.out.println("Currently oc_x: " + oc_x);
            System.out.println("Currently oc_y: " + OC.oc_y);
            System.out.println("Currently bx: " + bx);
            System.out.println("Currently bsx from OC: " + OC.bsx);
            System.out.println("Currently bsx: " + Base.bsx);
            fun();
            privFun();
        }
        void alterOCFields(){
            oc_x = 200;
            oc_y = 300;
            bx = 500;
            bsx = 700;
        }
    }
    
    static class SNC {
        // OC ref = OC.this; // error: no OC object is associated with SNC
        // tech4_accessOCFromIC.java:37: error: non-static variable this cannot be referenced from a static context
        void testOCFieldsAccess() {
            // oc_x; fun(); privFun(); bx; // cannot access non-static members
            System.out.println("Currently oc_y: " + OC.oc_y);
            System.out.println("Currently bsx: " + OC.bsx);
            privStaticFun();
        }
        void alterOCFields(){
            // oc_x = 400; // cannot access non-static members
            oc_y = 600;
            // bx = 1000; // cannot access non-static members
            bsx = 1400;
        }
    }
}
class DriverClass {
    public static void main(String args[]) {
        OC oc = new OC();
        OC.IC ic = oc.new IC();
        ic.testOCFieldsAccess();
        OC.drawline();
        // System.out.println(ic.oc_x); // tech4_accessOCFromIC.java:25: error: cannot find symbol
        // System.out.println(ic.oc_y); // tech4_accessOCFromIC.java:26: error: cannot find symbol
        // System.out.println(ic.bx); // tech4_accessOCFromIC.java:26: error: cannot find symbol
        ic.alterOCFields();
        System.out.println("After ic alteration, oc_x: " + oc.oc_x);
        System.out.println("After ic alteration, oc_y: " + OC.oc_y);
        System.out.println("After ic alteration, bx: " + oc.bx);
        System.out.println("After ic alteration, bsx: " + OC.bsx);
        
        ic.ref.oc_x = -200;
        ic.ref.oc_y = -300;
        ic.ref.bx = -500;
        ic.ref.bsx = -700;
        
        System.out.println("After ic's ref alteration, oc_x: " + oc.oc_x);
        System.out.println("After ic's ref alteration, oc_y: " + OC.oc_y);
        System.out.println("After ic's ref alteration, bx: " + oc.bx);
        System.out.println("After ic's ref alteration, bsx: " + OC.bsx);
        
        OC.drawline();
        OC.SNC snc = new OC.SNC();
        snc.testOCFieldsAccess();
        // System.out.println(snc.oc_x); // tech4_accessOCFromIC.java:42: error: cannot find symbol
        // System.out.println(snc.oc_y); // tech4_accessOCFromIC.java:43: error: cannot find symbol
        // System.out.println(snc.bsx); // tech4_accessOCFromIC.java:43: error: cannot find symbol
        
        // snc.ref; //Error: snc can't have ref of OC as snc isn't instance specific
        OC.drawline();
        snc.alterOCFields();
        System.out.println("After snc alteration, oc_y: " + OC.oc_y);
        System.out.println("After snc alteration, bsx: " + OC.bsx);
    }
    /**
     * Executing Command:
     * -----------------
     * CW core java> javac .\Technicalities\tech4_accessOCFromIC.java
     * CW core java\Technicalities> java DriverClass
     *
     * Error in "...\CW core java> java Technicalities.DriverClass" as there is no package involved
     *
     * Output
     * ------
     * Currently oc_x: 2
     * Currently oc_y: 3
     * Currently bx: 5
     * Currently bsx from OC: 7
     * Currently bsx: 7
     * fun inside OC
     * privFun inside OC
     * -------------------
     * After ic alteration, oc_x: 200
     * After ic alteration, oc_y: 300
     * After ic alteration, bx: 500
     * After ic alteration, bsx: 700
     * After ic's ref alteration, oc_x: -200
     * After ic's ref alteration, oc_y: -300
     * After ic's ref alteration, bx: -500
     * After ic's ref alteration, bsx: -700
     * -------------------
     * Currently oc_y: -300
     * Currently bsx: -700
     * privStaticFun inside OC
     * -------------------
     * After snc alteration, oc_y: 600
     * After snc alteration, bsx: 1400
     */
}