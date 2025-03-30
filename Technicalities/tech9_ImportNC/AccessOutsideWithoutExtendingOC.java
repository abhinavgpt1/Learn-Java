package Technicalities.tech9_ImportNC;

import Technicalities.tech9_ImportNC.pack.PublicOC;

public class AccessOutsideWithoutExtendingOC {
    public static void main(String[] args) {
        PublicOC publicOC = new PublicOC();
        PublicOC.PublicIC publicIC = publicOC.new PublicIC();
        System.out.println(publicIC.pubx);
        // System.out.println(publicIC.protx); //not visible. similarly for private and pla
        // System.out.println(publicIC.plax);
        // System.out.println(publicIC.privx);
        PublicOC.PublicSNC publicSNC = new PublicOC.PublicSNC();
        System.out.println(publicSNC.pubx);
        // System.out.println(publicSNC.protx); //not visible. similarly for private and pla
        // System.out.println(publicSNC.plax);
        // System.out.println(publicSNC.privx);

        // PublicOC.ProtectedIC protectedIC = publicOC.new ProtectedIC(); //not visible. similarly for private and pla
        // PublicOC.ProtectedSNC protectedSNC = new PublicOC.ProtectedSNC();

        //all classes form during compilation. Some are not visible, but that's separate concern
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
     *  -> AccessOutsideWithoutExportingOC.class
     *
     * Executing Command
     * -----------------
     * javac .\Technicalities\tech9_ImportNC\AccessOutsideWithoutExtendingOC.java
     * java Technicalities.tech9_ImportNC.AccessOutsideWithoutExtendingOC
     * 
     * Output
     * ------
     * 10
     * 10
     * 
     */
}
