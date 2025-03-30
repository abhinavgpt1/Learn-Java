package Technicalities.tech9_ImportNC;

import Technicalities.tech9_ImportNC.pack.PublicOC;

public class AccessOutsideProtectedICByExtending extends PublicOC {
    void accessProtectIC(){
        class MyClass extends PublicOC.ProtectedIC {
        }
        
        MyClass myClass = new MyClass();
        System.out.println("MyClass pubx:" + myClass.pubx); // 50
        // System.out.println("MyClass protx:" + myClass.protx); // !!not visible dnw!! - protected member of PublicOC.ProtectedIC
    }
    public static void main(String[] args) {
        // Task: comment the public constructor of PublicOC.ProtectedIC and run the code
        // using LIC to access ProtectedIC (having protected constructor (by default))
        new AccessOutsideProtectedICByExtending().accessProtectIC();
    }
    /**
     *
     * Executing Command
     * -----------------
     * javac .\Technicalities\tech9_ImportNC\AccessOutsideProtectedICByExtending.java
     * java Technicalities.tech9_ImportNC.AccessOutsideProtectedICByExtending
     * 
     * Output
     * ------
     * MyClass pubx:50
     * error: protx has protected access in PublicOC.ProtectedIC
     */
}
