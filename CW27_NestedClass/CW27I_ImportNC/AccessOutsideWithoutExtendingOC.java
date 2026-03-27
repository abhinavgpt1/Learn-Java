package CW27I_ImportNC.CW27I_ImportNC;

import CW27I_ImportNC.CW27I_ImportNC.pack.PublicOC;

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

        // all classes form during compilation. Some are not visible, but that's separate concern
    }
}

/**
 * Executing Command
 * -----------------
 * javac .\CW27I_ImportNC\CW27I_ImportNC\AccessOutsideWithoutExtendingOC.java
 * java CW27I_ImportNC.CW27I_ImportNC.AccessOutsideWithoutExtendingOC
 * 
 * Output
 * ------
 * 10
 * 10
 */
