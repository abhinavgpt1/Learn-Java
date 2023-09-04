package Technicalities.tech9_ImportNC;

// import Technicalities.tech9_ImportNC.pack.PlaOC; // error: not visible -> all cases in it cancelled
import Technicalities.tech9_ImportNC.pack.PublicOC;

public class AccessOutside extends PublicOC {
    public static void main(String[] args) {
        PublicOC publicOC = new PublicOC();
        // Only PublicIC and PublicSNC can be accessed without extending PublicOC
        // Inside these classes, only public members are accessible
        PublicOC.PublicIC publicIC = publicOC.new PublicIC();
        System.out.println("publicIC pubx:" + publicIC.pubx);
        // System.out.println(publicIC.protx); //error: not visible. similarly for private and pla
        PublicOC.PublicSNC publicSNC = new PublicOC.PublicSNC();
        System.out.println("publicSNC pubx:" + publicSNC.pubx);
        // System.out.println(publicSNC.protx); //error: not visible. similarly for private and pla

        // Accessing protected NC by extending PublicOC
        // -------------------------------------------------
        // PublicOC.ProtectedIC ppic = publicOC.new ProtectedIC(); 
        // error: not visible 
            // unless PublicOC is not extended (by definition, protected members can be accessed outside only after class extension)
            // +
            // constructor of ProtectedIC is not made public
        PublicOC.ProtectedIC pubProtectedIC = publicOC.new ProtectedIC();
        System.out.println("protectedIC pubx:" + pubProtectedIC.pubx);
        // System.out.println(pubProtectedIC.protx); //error: not visible. similarly for private and pla
        PublicOC.ProtectedSNC protectedSNC = new PublicOC.ProtectedSNC();
        System.out.println("protectedSNC pubx:" + protectedSNC.pubx);
        // System.out.println(protectedSNC.protx); //error: not visible. similarly for private and pla

        // Base class can also instantiate as NC is a member of enclosing class
        PublicOC.ProtectedIC protectedIC2 = new AccessOutside().new ProtectedIC();
        System.out.println("protectedIC pubx from Driver:" + protectedIC2.pubx);
        // System.out.println(protectedIC2.protx); //error: not visible. similarly for private and pla
        PublicOC.ProtectedSNC protectedSNC2 = new AccessOutside.ProtectedSNC();
        System.out.println("protectedSNC pubx from Driver:" + protectedSNC2.pubx);
        // System.out.println(protectedSNC2.protx); //error: not visible. similarly for private and pla

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
     *  -> AccessOutside.class
     *
     * Executing Command
     * -----------------
     * javac .\Technicalities\tech9_ImportNC\AccessOutside.java
     * java Technicalities.tech9_ImportNC.AccessOutside
     * 
     * Output
     * ------
     * publicIC pubx:10
     * publicSNC pubx:10
     * protectedIC pubx:50
     * protectedSNC pubx:50
     * protectedIC pubx from Driver:50
     * protectedSNC pubx from Driver:50
     * 
     */
}
