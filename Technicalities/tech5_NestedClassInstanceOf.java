class OC {
    class IC {
    }

    static class SNC {
    }
}

public class tech5_NestedClassInstanceOf {
    public static void main(String[] args) {
        OC obj = new OC();
        OC.IC icObj = obj.new IC();
        OC.SNC sncObj = new OC.SNC();

        System.out.println(obj instanceof OC);
        // System.out.println(obj instanceof OC.IC); // error: incompatible types: OC
        // cannot be converted to OC.IC
        // System.out.println(obj instanceof OC.SNC); // error: incompatible types: OC
        // cannot be converted to SNC

        System.out.println(icObj instanceof OC.IC);
        // System.out.println(icObj instanceof IC); // IC type cannot be resolved
        // System.out.println(icObj instanceof OC); // error: incompatible conditional operand types OC.IC and OC
        // cannot be converted to OC

        System.out.println(sncObj instanceof OC.SNC);
        // System.out.println(sncObj instanceof OC); // error: incompatible conditional operand types OC.SNC and OC
        // cannot be converted to OC
    }
    /**
     * Classes formed:
     * -> OC.class
     * -> OC$IC.class
     * -> OC$SNC.class
     * -> tech5_NestedClassInstanceOf.class
     */
}
