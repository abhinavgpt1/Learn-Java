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
        // error: not visible because of following reasons
        // 1. visible to only subclass which is AccessOutside (by definition, protected members can be accessed outside only after class extension)
        // 2. constructor of ProtectedIC is not made public 
        //  - this will stop AccessOutside to create object of ProtectedIC
        //  - Downside is that PublicOC and AccessOutside both now can create object of ProtectedIC
        
        // OC can now instantiate Protected IC, SNC
        PublicOC.ProtectedIC pubProtectedIC = publicOC.new ProtectedIC();
        System.out.println("protectedIC pubx:" + pubProtectedIC.pubx);
        // System.out.println(pubProtectedIC.protx); //error: not visible. similarly for private and pla
        PublicOC.ProtectedSNC protectedSNC = new PublicOC.ProtectedSNC();
        System.out.println("protectedSNC pubx:" + protectedSNC.pubx);
        // System.out.println(protectedSNC.protx); //error: not visible. similarly for private and pla

        // Subclass can (and should) instantiate Protected IC, SNC
        AccessOutside.ProtectedIC protectedIC2 = new AccessOutside().new ProtectedIC();
        System.out.println("protectedIC pubx from Driver:" + protectedIC2.pubx);
        // System.out.println("protectedIC pubx from Driver:" + protectedIC2.protx); //not visible since, ProtectedIC is not a subclass of AccessOutside
        AccessOutside.ProtectedSNC protectedSNC2 = new AccessOutside.ProtectedSNC();
        System.out.println("protectedSNC pubx from Driver:" + protectedSNC2.pubx);
        // System.out.println(protectedSNC2.protx); //error: not visible. similarly for private and pla
        System.out.println(protectedIC2 instanceof PublicOC.ProtectedIC); // true
        System.out.println(protectedIC2 instanceof AccessOutside.ProtectedIC); // true
        System.out.println(protectedSNC2 instanceof PublicOC.ProtectedSNC); // true
        System.out.println(protectedSNC2 instanceof AccessOutside.ProtectedSNC); // true
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
