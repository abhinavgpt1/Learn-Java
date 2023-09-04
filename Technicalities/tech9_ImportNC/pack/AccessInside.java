package Technicalities.tech9_ImportNC.pack;

public class AccessInside extends PublicOC {
    public static void main(String[] args) {
        // need not to import PLA/public classes within package
        
        PublicOC publicOC = new PublicOC();
        // All except private is accessible
        PublicOC.PublicIC publicIC = publicOC.new PublicIC();
        PublicOC.ProtectedIC protectedIC = publicOC.new ProtectedIC();
        PublicOC.PlaIC plaIC = publicOC.new PlaIC();
        PublicOC.PublicSNC publicSNC = new PublicOC.PublicSNC();
        PublicOC.ProtectedSNC protectedSNC = new PublicOC.ProtectedSNC();
        PublicOC.PlaSNC plaSNC = new PublicOC.PlaSNC();
        // PublicOC.PrivateIC privateIC = publicOC.new PrivateIC(); // error: not visible
        // PublicOC.PrivateSNC privateSNC = new PublicOC.PrivateSNC(); // error: not visible

        System.out.println("publicIC pubx:" + publicIC.pubx + ", protx:" + publicIC.protx + ", plax:" + publicIC.plax);
        System.out.println("protectedIC pubx:" + protectedIC.pubx + ", protx:" + protectedIC.protx + ", plax:" + protectedIC.plax);
        System.out.println("plaIC pubx:" + plaIC.pubx + ", protx:" + plaIC.protx + ", plax:" + plaIC.plax);
        // System.out.println(publicIC.privx + " " + protectedIC.privx + " " + plaIC.privx); // error: not either of them is visible
        
        System.out.println("publicSNC pubx:" + publicSNC.pubx + ", protx:" + publicSNC.protx + ", plax:" + publicSNC.plax);
        System.out.println("protectedSNC pubx:" + protectedSNC.pubx + ", protx:" + protectedSNC.protx + ", plax:" + protectedSNC.plax);
        System.out.println("plaSNC pubx:" + plaSNC.pubx + ", protx:" + plaSNC.protx + ", plax:" + plaSNC.plax);
        // System.out.println(publicSNC.privx + " " + protectedSNC.privx + " " + plaSNC.privx); // error: not either of them is visible
        

        
        PlaOC plaOC = new PlaOC();
        // All except private is accessible
        PlaOC.PublicIC plapublicIC = plaOC.new PublicIC();
        PlaOC.ProtectedIC plaprotectedIC = plaOC.new ProtectedIC();
        PlaOC.PlaIC plaplaIC = plaOC.new PlaIC();
        PlaOC.PublicSNC plapublicSNC = new PlaOC.PublicSNC();
        PlaOC.ProtectedSNC plaprotectedSNC = new PlaOC.ProtectedSNC();
        PlaOC.PlaSNC plaplaSNC = new PlaOC.PlaSNC();
        // PlaOC.PrivateIC privateIC = plaOC.new PrivateIC(); // error: not visible
        // PlaOC.PrivateSNC privateSNC = new PlaOC.PrivateSNC(); // error: not visible

        System.out.println("plapublicIC pubx:" + plapublicIC.pubx + ", protx:" + plapublicIC.protx + ", plax:" + plapublicIC.plax);
        System.out.println("plaprotectedIC pubx:" + plaprotectedIC.pubx + ", protx:" + plaprotectedIC.protx + ", plax:" + plaprotectedIC.plax);
        System.out.println("plaplaIC pubx:" + plaplaIC.pubx + ", protx:" + plaplaIC.protx + ", plax:" + plaplaIC.plax);
        // System.out.println(plapublicIC.privx + " " + plaprotectedIC.privx + " " + plaplaIC.privx); // error: not either of them is visible
        
        System.out.println("plapublicSNC pubx:" + plapublicSNC.pubx + ", protx:" + plapublicSNC.protx + ", plax:" + plapublicSNC.plax);
        System.out.println("plaprotectedSNC pubx:" + plaprotectedSNC.pubx + ", protx:" + plaprotectedSNC.protx + ", plax:" + plaprotectedSNC.plax);
        System.out.println("plaplaSNC pubx:" + plaplaSNC.pubx + ", protx:" + plaplaSNC.protx + ", plax:" + plaplaSNC.plax);
        // System.out.println(plapublicSNC.privx + " " + plaprotectedSNC.privx + " " + plaplaSNC.privx); // error: not either of them is visible        
    }
    /**
     * Classed Formed:
     *  -> AccessInside.class
     *  -> PlaOC.class
     *  -> PlaOC$PlaIC.class
     *  -> PlaOC$PlaSNC.class
     *  -> PlaOC$PrivateIC.class
     *  -> PlaOC$PrivateSNC.class
     *  -> PlaOC$ProtectedIC.class
     *  -> PlaOC$ProtectedSNC.class
     *  -> PlaOC$PublicIC.class
     *  -> PlaOC$PublicSNC.class
     *  -> PublicOC.class
     *  -> PublicOC$PlaIC.class
     *  -> PublicOC$PlaSNC.class
     *  -> PublicOC$PrivateIC.class
     *  -> PublicOC$PrivateSNC.class
     *  -> PublicOC$ProtectedIC.class
     *  -> PublicOC$ProtectedSNC.class
     *  -> PublicOC$PublicIC.class
     *  -> PublicOC$PublicSNC.class
     *
     *
     * Executing Command
     * -----------------
     * java Technicalities.tech9_ImportNC.pack.AccessInside
     * java Technicalities.tech9_ImportNC.pack.AccessInside
     * 
     * Output
     * ------
     * publicIC pubx:10, protx:20, plax:30
     * protectedIC pubx:50, protx:60, plax:70    
     * plaIC pubx:90, protx:100, plax:110        
     * publicSNC pubx:10, protx:20, plax:30      
     * protectedSNC pubx:50, protx:60, plax:70   
     * plaSNC pubx:90, protx:100, plax:110       
     * plapublicIC pubx:10, protx:10, plax:10    
     * plaprotectedIC pubx:10, protx:10, plax:10 
     * plaplaIC pubx:10, protx:10, plax:10       
     * plapublicSNC pubx:10, protx:10, plax:10   
     * plaprotectedSNC pubx:10, protx:10, plax:10
     * plaplaSNC pubx:10, protx:10, plax:10
     * 
     */
}
