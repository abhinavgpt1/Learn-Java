class Base {
    int base_x = 5;
    static int base_static_x = 7;
}

class OC extends Base {
    int oc_x = 2;
    static int oc_y = 3;

    void func() {
        System.out.println("func inside OC");
    }

    private void privateFunc() {
        System.out.println("privateFunc inside OC");
    }

    private static void privateStaticFunc() {
        System.out.println("privateStaticFunc inside OC");
    }

    static final void drawline() {
        System.out.println("-------------------");
    }

    class IC {
        OC ref = OC.this; // this can get us all properties of OC's object from main/outside

        void testOCFieldsAccess() {
            System.out.println("------------------- Testing IC access of OC fields -------------------");
            System.out.println("Currently oc_x: " + oc_x);
            System.out.println("Currently oc_y: " + OC.oc_y);
            System.out.println("Currently base_x: " + base_x);
            System.out.println("Currently base_static_x from OC: " + OC.base_static_x);
            System.out.println("Currently base_static_x: " + Base.base_static_x);
            func();
            privateFunc();
        }

        void alterOCFields() {
            oc_x = 200;
            oc_y = 300;
            base_x = 500;
            base_static_x = 700;
        }
    }

    static class SNC {
        // OC ref = OC.this; // error: no OC object is associated with SNC
        // CW27D_AccessOC_Inside_IC_SNC_Main.java:50: error: non-static variable this cannot be referenced from a static context
        void testOCFieldsAccess() {
            System.out.println("------------------- Testing SNC access of OC fields -------------------");
            // oc_x; func(); privateFunc(); base_x; // error: cannot access non-static members
            System.out.println("Currently oc_y: " + OC.oc_y);
            System.out.println("Currently base_static_x: " + OC.base_static_x);
            privateStaticFunc();
        }

        void alterOCFields() {
            // oc_x = 400; // error: cannot access non-static members
            oc_y = 600;
            // base_x = 1000; // error: cannot access non-static members
            base_static_x = 1400;
        }
    }
}

class CW27D_AccessOC_Inside_IC_SNC_Main {
    public static void main(String args[]) {
        OC oc = new OC();
        OC.IC ic = oc.new IC();
        ic.testOCFieldsAccess();
        OC.drawline();
        // System.out.println(ic.oc_x); // CW27D_AccessOC_Inside_IC_SNC_Main.java:74: error: cannot find symbol
        // System.out.println(ic.oc_y); // CW27D_AccessOC_Inside_IC_SNC_Main.java:75: error: cannot find symbol
        // System.out.println(ic.base_x); // CW27D_AccessOC_Inside_IC_SNC_Main.java:76: error: cannot find symbol
        ic.alterOCFields();
        System.out.println("OC fields after ic's alter, oc_x: " + oc.oc_x);
        System.out.println("OC fields after ic's alter, oc_y: " + OC.oc_y);
        System.out.println("OC fields after ic's alter, base_x: " + oc.base_x);
        System.out.println("OC fields after ic's alter, base_static_x: " + OC.base_static_x);

        ic.ref.oc_x = -200;
        ic.ref.oc_y = -300;
        ic.ref.base_x = -500;
        ic.ref.base_static_x = -700;

        System.out.println("OC fields after ic.ref's alter, oc_x: " + oc.oc_x);
        System.out.println("OC fields after ic.ref's alter, oc_y: " + OC.oc_y);
        System.out.println("OC fields after ic.ref's alter, base_x: " + oc.base_x);
        System.out.println("OC fields after ic.ref's alter, base_static_x: " + OC.base_static_x);

        System.out.println();

        OC.SNC snc = new OC.SNC();
        snc.testOCFieldsAccess();
        // System.out.println(snc.oc_x); // CW27D_AccessOC_Inside_IC_SNC_Main.java:97: error: cannot find symbol
        // System.out.println(snc.oc_y); // CW27D_AccessOC_Inside_IC_SNC_Main.java:98: error: cannot find symbol
        // System.out.println(snc.base_static_x); // CW27D_AccessOC_Inside_IC_SNC_Main.java:99: error: cannot find symbol
        // snc.ref; // error: snc can't have ref of OC as snc isn't instance specific

        OC.drawline();
        snc.alterOCFields();
        System.out.println("After snc alteration, oc_y: " + OC.oc_y);
        System.out.println("After snc alteration, base_static_x: " + OC.base_static_x);
    }
}

/**
 * Output:
 * -------
 * ------------------- Testing IC access of OC fields -------------------
 * Currently oc_x: 2
 * Currently oc_y: 3
 * Currently base_x: 5
 * Currently base_static_x from OC: 7
 * Currently base_static_x: 7
 * func inside OC
 * privateFunc inside OC
 * -------------------
 * OC fields after ic's alter, oc_x: 200
 * OC fields after ic's alter, oc_y: 300
 * OC fields after ic's alter, base_x: 500
 * OC fields after ic's alter, base_static_x: 700
 * OC fields after ic.ref's alter, oc_x: -200
 * OC fields after ic.ref's alter, oc_y: -300
 * OC fields after ic.ref's alter, base_x: -500
 * OC fields after ic.ref's alter, base_static_x: -700
 * 
 * ------------------- Testing SNC access of OC fields -------------------
 * Currently oc_y: -300
 * Currently base_static_x: -700
 * privateStaticFunc inside OC
 * -------------------
 * After snc alteration, oc_y: 600
 * After snc alteration, base_static_x: 1400
 */