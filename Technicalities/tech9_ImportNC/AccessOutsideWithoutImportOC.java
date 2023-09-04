package Technicalities.tech9_ImportNC;

import static Technicalities.tech9_ImportNC.pack.PublicOC.PublicSNC;

// Test: if IC and SNC can be accessed without importing OC
public class AccessOutsideWithoutImportOC {
    public static void main(String[] args) {
        // IC cannot be instantiated as it is strongly connected to OC
        // PublicOC.PublicIC publicIC = new PublicOC().new PublicIC(); // error: PublicOC cannot be accessed without import
            // this will work if both classes are in default package - refer tech0_packageAndMemberAccessCases > noPkg scenario
        // PublicOC.PublicSNC publicSNC = new PublicOC.PublicSNC();
            // this will also not work as PublicOC is not imported
        PublicSNC publicSNC = new PublicSNC(); // this works as only static member is imported instead of whole class
        System.out.println(publicSNC.pubx);

        //similarly if we import IC as "import tech9_ImportNC.pack.PublicOC.PublicIC;" then too it won't work without importing+initializing object of OC
        // PublicOC.PublicIC publicIC = publicOC.new PublicIC(); // error: needs enclosing instance

        // using fully qualified name will still require object intialization of OC
        // tech9_ImportNC.pack.PublicOC.PublicIC publicIC = new PublicIC(); // error: needs enclosing instance

        // RECALL: why PublicOC classes come into existence even when there is no import
        // it was an observation that class compiles when
            // class explictly imported or used (during On-demand declaration)
            // static member(s) imported
    }
    /**
     * Classed Formed:
     *  -> PublicOC.class
     *  -> PublicOC$PlaIC.class
     *  -> PublicOC$PlaSNC.class
     *  -> PublicOC$PrivateIC.class
     *  -> PublicOC$PrivateSNC.class
     *  -> PublicOC$ProtectedIC.class
     *  -> PublicOC$ProtectedSNC.class
     *  -> PublicOC$PublicIC.class
     *  -> PublicOC$PublicSNC.class
     *  -> AccessOutsideWithoutImportOC.class
     *
     * Executing Command
     * -----------------
     * javac .\Technicalities\tech9_ImportNC\AccessOutsideWithoutImportOC.java
     * java Technicalities.tech9_ImportNC.AccessOutsideWithoutImportOC
     * 
     * Output
     * ------
     * 10
     * 
     */
}
